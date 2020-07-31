package scala.meta.intellij

import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiManager
import org.jetbrains.plugins.scala.lang.psi.api.base.{ScInterpolated, ScInterpolatedStringLiteral, ScReference}
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScReferenceExpression
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunction
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory.createTypeFromText
import org.jetbrains.plugins.scala.lang.psi.impl.base.patterns.ScInterpolationPatternImpl
import org.jetbrains.plugins.scala.lang.psi.impl.expr.ScMethodCallImpl
import org.jetbrains.plugins.scala.lang.psi.types.nonvalue.Parameter
import org.jetbrains.plugins.scala.lang.psi.types.result._
import org.jetbrains.plugins.scala.lang.resolve.ScalaResolveResult
import org.jetbrains.plugins.scala.project.ProjectContext

import scala.meta._
import scala.meta.Dialect
import scala.meta.inputs.Input
import scala.meta.parsers.{ParseException, Parsed}
import scala.meta.trees.error.toDo213

/**
  * @author Mikhail Mutcianko
  * @since 11.09.16
  */
object QuasiquoteInferUtil {

  import scala.{meta => m}

  def isMetaQQ(ref: ScReference): Boolean = {
    ref.bind() match {
      case Some(ScalaResolveResult(fun: ScFunction, _)) if fun.name == "unapply" || fun.name == "apply" && isMetaQQ(fun) => true
      case _ => false
    }
  }

  def isMetaQQ(fun: ScFunction): Boolean = {
    val fqnO = Option(fun.containingClass).flatMap(x=>Option(x.qualifiedName))
    fqnO.exists(_.startsWith("scala.meta.quasiquotes.Api.XtensionQuasiquote"))
  }

  def getMetaQQExpectedTypes(stringContextApplicationRef: ScReferenceExpression): Seq[Parameter] = {
    // TODO 2.13
//    ProgressManager.checkCanceled()
//    val joined = stringContextApplicationRef.qualifier match {
//      case Some(mc: ScMethodCallImpl) => mc.argumentExpressions.zipWithIndex.foldLeft("") {
//        case (a, (expr, i)) if i > 0 => s"$a$$__meta$i${unquoteString(expr.getText)}"
//        case (_, (expr, i)) if i == 0 => unquoteString(expr.getText)
//      }
//      case _ => ""
//    }
//    val qqdialect = if (joined.contains("\n"))
//      scala.meta.dialects.QuasiquoteTerm(m.Dialect.standards("Scala211"), multiline = true)
//    else
//      scala.meta.dialects.QuasiquoteTerm(m.Dialect.standards("Scala211"), multiline = false)
//    val typeStrings = parseQQExpr(stringContextApplicationRef.refName, joined, qqdialect) match {
//      case Parsed.Success(qqparts) =>
//        val parts = collectQQParts(qqparts)
//        val classes = parts.map(_.pt)
//        classes.map(classToScTypeString)
//      case Parsed.Error(_, cause, exc) =>
//        Seq.empty
//    }
//    val types = typeStrings.map(createTypeFromText(_, stringContextApplicationRef, null))
//    val treeType = createTypeFromText("scala.meta.Tree", stringContextApplicationRef, null)
//    types.zipWithIndex.map {
//      case (maybeType, i) =>
//        val tp = maybeType.orElse(treeType).get
//        new Parameter(s"__meta$i", None, tp, tp, isDefault = false, isRepeated = false, isByName = false, index = i)
//    }
    toDo213
  }

  def getMetaQQExprType(pat: ScInterpolatedStringLiteral): TypeResult = {
    ProgressManager.checkCanceled()
    implicit val context: ProjectContext = pat.projectContext

    val patternText = escapeQQ(pat)
    val qqdialect = if (pat.isMultiLineString)
      scala.meta.dialects.QuasiquoteTerm(m.Dialect.standards("Scala211"), multiline = true)
    else
      scala.meta.dialects.QuasiquoteTerm(m.Dialect.standards("Scala211"), multiline = false)
    val prefix = pat.reference.fold(throw new ParseException(null, s"Failed to get QQ ref in ${pat.getText}"))(_.getText)
    try {
      val parsed = parseQQExpr(prefix, patternText, qqdialect)
      parsed match {
        case Parsed.Success(qq) =>
          ScalaPsiElementFactory
            .createTypeElementFromText(s"scala.meta.${qq.productPrefix}")(PsiManager.getInstance(pat.getProject))
            .`type`()
        case Parsed.Error(_, message, _) =>
          Failure(message)
      }
    } catch {
      case _: ArrayIndexOutOfBoundsException =>  // workaround for meta parser failure on malformed quasiquotes
        createTypeFromText("scala.meta.Tree", pat, null).asTypeResult
    }
  }

  def getMetaQQPatternTypes(pat: ScInterpolationPatternImpl): Seq[String] = {
    ProgressManager.checkCanceled()
    val prefix = pat.ref.refName
    val patternText = escapeQQ(pat)
    val qqDialect = if (pat.isMultiLineString)
      scala.meta.dialects.QuasiquotePat(m.Dialect.standards("Scala211"), multiline = true)
    else
      scala.meta.dialects.QuasiquotePat(m.Dialect.standards("Scala211"), multiline = false)
    parseQQExpr(prefix, patternText, qqDialect) match {
      case Parsed.Success(qqparts) =>
        // TODO 2.13
//        val parts = collectQQParts(qqparts)
//        val classes = parts.map(_.pt)
//        classes.map(classToScTypeString)
        toDo213
      case Parsed.Error(_, cause, exc) =>
        Seq.empty
    }
  }

  private def parseQQExpr(prefix: String, text: String, dialect: m.Dialect): Parsed[m.Tree] = {
    val p: (Dialect, Input) = dialect(text)
    prefix match {
      // FIXME: this seems wrong - reference q parser only parses Stat or Ctor, however this way many qqs couldn't be parsed
      case "q"          => p.parse[m.Stat].orElse(p.parse[m.Source])
      case "t"          => p.parse[m.Type]
      case "p"          => p.parse[m.Case].orElse(p.parse[m.Pat])
      case "pt"         => toDo213 /* p.parse[m.Pat.Type]*/ // TODO 2.13
      case "arg"        => toDo213 /*p.parse[m.Term.Arg]*/ // TODO 2.13
      case "mod"        => p.parse[m.Mod]
      case "targ"       => toDo213 /*p.parse[m.Type.Arg]*/ // TODO 2.13
      case "parg"       => toDo213 /*p.parse[m.Pat.Arg]*/ // TODO 2.13
      case "ctor"       => toDo213 /*p.parse[m.Ctor.Call]*/ // TODO 2.13
      case "param"      => p.parse[m.Term.Param]
      case "tparam"     => p.parse[m.Type.Param]
      case "source"     => p.parse[m.Source]
      case "template"   => p.parse[m.Template]
      case "importer"   => p.parse[m.Importer]
      case "importee"   => p.parse[m.Importee]
      case "enumerator" => p.parse[m.Enumerator]
      case _ => Parsed.Error(null, s"Unknown Quasiquote kind - $prefix", new MatchError(prefix))
    }
  }

  // max(rank) for filtering out nested quasi types(we only need top level parts for conformance checks)
  // TODO 2.13
//  private def collectQQParts(t: scala.meta.Tree, maxParentRank: Int = -1): Seq[m.internal.ast.Quasi] = {
//    import m.internal.ast.Quasi
//    t match {
//      case tt: Quasi if tt.rank > maxParentRank => Seq(tt) ++ tt.children.flatMap(c=>collectQQParts(c, tt.rank))
//      case _ => t.children.flatMap(c=>collectQQParts(c, maxParentRank))
//    }
//  }

  private def escapeQQ(pat: ScInterpolated): String = {
    if (pat.isMultiLineString) {
      pat.getText.replaceAll("^[a-z]+\"\"\"", "").replaceAll("\"\"\"$", "").trim
    } else {
      pat.getText.replaceAll("^[a-z]+\"", "").replaceAll("\"$", "").trim
    }
  }

  private def unquoteString(str: String): String = {
    if (str.startsWith("\"\"\""))
      str.replaceAll("^\"\"\"", "").replaceAll("\"\"\"$", "")
    else
      str.replaceAll("^\"", "").replaceAll("\"$", "")
  }

  private def classToScTypeString(c: Class[_]): String = {
    if (c.isArray) {
      s"scala.collection.immutable.Seq[${classToScTypeString(c.getComponentType)}]"
    } else {
      c.getTypeName.replaceAll("\\$", ".")
    }
  }
}
