package scala.meta.converter

import scala.meta.TreeConverterTestBaseWithLibrary
import scala.meta._
import scala.{Seq => _}

class TreeConverterMatchTest extends TreeConverterTestBaseWithLibrary {

  def testMatchRef(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |val a = 42
//        |//start
//        |a match { case foo => }
//      """.stripMargin,
//      Term.Match(Term.Name("a"), List(Case(Pat.Var.Term(Term.Name("foo")), None, Term.Block(Nil))))
//    )
  }

  def testMatchExtractorSimple(): Unit = {
    // TODO 2.13
//    doTest(
//      """def f = {
//        |case class Foo(a: Any, b: Any)
//        |val a = Foo(1,2)
//        |//start
//        |a match { case Foo(bar, baz) => }}
//      """.stripMargin,
//      Term.Match(Term.Name("a"), List(Case(Pat.Extract(Term.Name("Foo"), Nil,
//        List(Pat.Var.Term(Term.Name("bar")), Pat.Var.Term(Term.Name("baz")))), None, Term.Block(Nil))))
//    )
  }

  def testMatchExtractorTypedArgs(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |val a = 42
//        |//start
//        |a match { case Some(bar: Int) => }
//      """.stripMargin,
//      Term.Match(Term.Name("a"), List(Case(Pat.Extract(Term.Name("Some"), Nil,
//        List(Pat.Typed(Pat.Var.Term(Term.Name("bar")), Type.Name("Int")))), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchBinding(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |val a = 42
//        |//start
//        |a match { case b @ Some() => }
//      """.stripMargin,
//      Term.Match(Term.Name("a"), List(Case(Pat.Bind(Pat.Var.Term(Term.Name("b")), Pat.Extract(Term.Name("Some"), Nil, Nil)), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchTyped(): Unit = {
    // TODO 2.13
//    doTest(
//      "42 match { case b: Int => }",
//      Term.Match(Lit.Int(42), List(Case(Pat.Typed(Pat.Var.Term(Term.Name("b")), Type.Name("Int")), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchTuple(): Unit = {
    // TODO 2.13
//    doTest(
//      "(42, 24) match { case (b, c) => }",
//      Term.Match(Term.Tuple(List(Lit.Int(42), Lit.Int(24))) , List(Case(Pat.Tuple(List(Pat.Var.Term(Term.Name("b")), Pat.Var.Term(Term.Name("c")))), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchWildCard(): Unit = {
    doTest(
      "42 match { case _ => }",
      Term.Match(Lit.Int(42), List(Case(Pat.Wildcard(), None, Term.Block(Nil))))
    )
  }
  
  def testMatchLiteral(): Unit = {
    doTest(
      "42 match { case 42 => }",
      Term.Match(Lit.Int(42), List(Case(Lit.Int(42), None, Term.Block(Nil))))
    )
  }
  
  def testMatchComposite(): Unit = {
    doTest(
      "42 match { case 1 | 2 | 3 => }",
      Term.Match(Lit.Int(42), List(Case(Pat.Alternative(Lit.Int(1), Pat.Alternative(Lit.Int(2), Lit.Int(3))), None, Term.Block(Nil))))
    )
  }

  def testMatchTypedWildCard(): Unit = {
    doTest(
      "42 match { case _: Int => }",
      Term.Match(Lit.Int(42), List(Case(Pat.Typed(Pat.Wildcard(), Type.Name("Int")), None, Term.Block(Nil))))
    )
  }

  // TODO: type variables
  // FIXME: disabled until semantics engine is implemented
  def testMatchWildCardTypeVar(): Unit = {
    return
//    doTest(
//      "42 match { case _: Any[t] => }",
//      Term.Match(Lit.Int(42), List(Case(Pat.Typed(Pat.Wildcard(), Pat.Type.Apply(Type.Name("Any"), List(Pat.Var.Type(Type.Name("t"))))), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchWildCardTypeApplyWildCard(): Unit = {
    // TODO 2.13
//    doTest(
//      "42 match { case _: Any[_] => }",
//      Term.Match(Lit.Int(42), List(Case(Pat.Typed(Pat.Wildcard(), Pat.Type.Apply(Type.Name("Any"), List(Pat.Type.Wildcard()))), None, Term.Block(Nil))))
//    )
  }

  def testMatchTypeApplyInfix(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |def f[T,U] = {
//        |//start
//        |42 match { case _: (T Map U) => }}
//      """.stripMargin,
//      Term.Match(Lit.Int(42), List(Case(Pat.Typed(Pat.Wildcard(), Pat.Type.ApplyInfix(Type.Name("T"), Type.Name("Map"), Type.Name("U"))), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchExtractorWildCardSeq(): Unit = {
    // TODO 2.13
//    doTest(
//      "42 match { case Some(_*) => }",
//      Term.Match(Lit.Int(42), List(Case(Pat.Extract(Term.Name("Some"), Nil, List(Pat.Arg.SeqWildcard())), None, Term.Block(Nil))))
//    )
  }

  def testMatchExtractorBoundWildCardSeq(): Unit = {
    // TODO 2.13
//    doTest(
//      "42 match { case Some(x @ _*) => }",
//      Term.Match(Lit.Int(42), List(Case(Pat.Extract(Term.Name("Some"), Nil, List(Pat.Bind(Pat.Var.Term(Term.Name("x")), Pat.Arg.SeqWildcard()))), None, Term.Block(Nil))))
//    )
  }
  
  def testMatchInfix(): Unit = {
    // TODO 2.13
//    doTest(
//      "42 match { case a :: b => }",
//      Term.Match(Lit.Int(42), List(Case(Pat.ExtractInfix(Pat.Var.Term(Term.Name("a")), Term.Name("::"), List(Pat.Var.Term(Term.Name("b")))), None, Term.Block(Nil))))
//    )
  }
}
