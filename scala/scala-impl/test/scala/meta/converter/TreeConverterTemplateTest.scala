package scala.meta.converter

import scala.meta.TreeConverterTestBaseNoLibrary
import scala.meta._

class TreeConverterTemplateTest extends TreeConverterTestBaseNoLibrary {

  def testTraitEmpty(): Unit = {
    // TODO 2.13
//    doTest(
//      "trait A",
//      Defn.Trait(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testTraitEmptyNone(): Unit = {
    // TODO 2.13
//    doTest(
//      "trait T {}",
//      Defn.Trait(Nil, Type.Name("T"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), Some(Nil)))
//    )
  }

  def testTraitTyped(): Unit = {
    // TODO 2.13
//    doTest(
//      "trait T[A]",
//      Defn.Trait(Nil, Type.Name("T"), List(Type.Param(Nil, Type.Name("A"), Nil, Type.Bounds(None, None), Nil, Nil)),
//        Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil), Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testTraitExtends(): Unit = {
    // TODO 2.13
//    doTest(
//    """
//      |trait B
//      |//start
//      |trait A extends B
//    """.stripMargin,
//      Defn.Trait(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, List(Term.Apply(Ctor.Ref.Name("B"), Nil)), Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testTraitExtendsEarly(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |trait B
//        |//start
//        |trait A extends { val x: Int = 42 } with B
//      """.stripMargin,
//       Defn.Trait(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//         Template(List(Defn.Val(Nil, List(Pat.Var.Term(Term.Name("x"))), Some(Type.Name("Int")), Lit.Int(42))),
//           List(Term.Apply(Ctor.Ref.Name("B"), Nil)), Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testTraitSelf(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |trait B
//        |//start
//        |trait A { self: B => }
//      """.stripMargin,
//      Defn.Trait(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Term.Name("self"), Some(Type.Name("B")), None), Some(Nil)))
//    )
  }

  def testTraitTemplateBody(): Unit = {
    // TODO 2.13
//    doTest(
//      "trait T { def x: Int }",
//      Defn.Trait(Nil, Type.Name("T"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None),
//          Some(List(Decl.Def(Nil, Term.Name("x"), Nil, Nil, Type.Name("Int"))))))
//    )
  }

  def testTraitTemplateExprs(): Unit = {
    // TODO 2.13
//    doTest(
//      "trait T { def f = 1 ; f()}",
//      Defn.Trait(Nil, Type.Name("T"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None),
//          Some(List(Defn.Def(Nil, Term.Name("f"), Nil, Nil, None, Lit.Int(1)), Term.Apply(Term.Name("f"), Nil)))))
//    )
  }

  def testClassEmpty(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C",
//      Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassParametrized(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C[T]",
//      Defn.Class(Nil, Type.Name("C"), List(Type.Param(Nil, Type.Name("T"), Nil, Type.Bounds(None, None), Nil, Nil)),
//        Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil), Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassExtends(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |class B
//        |//start
//        |class A extends B
//      """.stripMargin,
//      Defn.Class(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, List(Term.Apply(Ctor.Ref.Name("B"), Nil)), Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testNestedClass(): Unit = {
    // TODO 2.13
//    doTest(
//      "class A { class B { class C }}}",
//      Defn.Class(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None),
//          Some(List(Defn.Class(Nil, Type.Name("B"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//          Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None),
//            Some(List(Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//            Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))))))))))
//    )
  }

  def testClassMixedChildren(): Unit = {
    // TODO 2.13
//    doTest(
//       FIXME: member/expr order preser vation not working for now, exprs will always go after members
//      "class A {val a = 42; class B; def f = 42; type T; trait Foo; 42; f(a)}",
//      Defn.Class(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None),
//          Some(List(Defn.Val(Nil, List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42)),
//            Defn.Class(Nil, Type.Name("B"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//              Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None)),
//            Defn.Def(Nil, Term.Name("f"), Nil, Nil, None, Lit.Int(42)),
//            Decl.Type(Nil, Type.Name("T"), Nil, Type.Bounds(None, None)),
//            Defn.Trait(Nil, Type.Name("Foo"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//              Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None)), Lit.Int(42),
//            Term.Apply(Term.Name("f"), List(Term.Name("a")))))))
//    )
  }


  def testClassEarlyDefn(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |class B
//        |//start
//        |class A extends { val x: Int = 42 } with B
//      """.stripMargin,
//      Defn.Class(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(List(Defn.Val(Nil, List(Pat.Var.Term(Term.Name("x"))),
//          Some(Type.Name("Int")), Lit.Int(42))), List(Term.Apply(Ctor.Ref.Name("B"), Nil)), Term.Param(Nil, Name.Anonymous(), None, None), None))
//
//    )
  }

  def testClassSelf(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |class B
//        |//start
//        |class A { self: B => }
//      """.stripMargin,
//       Defn.Class(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//         Template(Nil, Nil, Term.Param(Nil, Term.Name("self"), Some(Type.Name("B")), None), Some(Nil)))
//    )
  }

//  def testClassThisSelf() {
//    doTest(
//      "class A { this => }",
//      Defn.Class(Nil, Type.Name("A"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), Some(Nil)))
//    )
//  }

  def testClassTemplate(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C { def x: Int }",
//       Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//         Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None),
//           Some(List(Decl.Def(Nil, Term.Name("x"), Nil, Nil, Type.Name("Int"))))))
//    )
  }

  def testClassCtor(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C(x: Int)",
//       Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"),
//         List(List(Term.Param(Nil, Term.Name("x"), Some(Type.Name("Int")), None)))),
//         Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassCtorPrivate(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C private(x: Int)",
//      Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(List(Mod.Private(Name.Anonymous())),
//        Ctor.Ref.Name("this"), List(List(Term.Param(Nil, Term.Name("x"), Some(Type.Name("Int")), None)))),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassValParam(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C(val x: Int)",
//      Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"),
//        List(List(Term.Param(List(Mod.ValParam()), Term.Name("x"), Some(Type.Name("Int")), None)))),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassVarParam(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C(var x: Int)",
//      Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"),
//        List(List(Term.Param(List(Mod.VarParam()), Term.Name("x"), Some(Type.Name("Int")), None)))),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassImplicitParam(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C(implicit x: Int)",
//      Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"),
//        List(List(Term.Param(List(Mod.Implicit()), Term.Name("x"), Some(Type.Name("Int")), None)))),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testClassParamOverride(): Unit = {
    // TODO 2.13
//    doTest(
//      "class C(override val x: Int)",
//      Defn.Class(Nil, Type.Name("C"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"),
//        List(List(Term.Param(List(Mod.Override(), Mod.ValParam()), Term.Name("x"), Some(Type.Name("Int")), None)))),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testObjectSimple(): Unit = {
    // TODO 2.13
//    doTest(
//      "object A",
//      Defn.Object(Nil, Term.Name("A"),
//        Template(Nil, Nil, Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testObjectExtends(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |trait B
//        |//start
//        |object A extends B
//      """.stripMargin,
//      Defn.Object(Nil, Term.Name("A"),
//        Template(Nil, List(Term.Apply(Ctor.Ref.Name("B"), Nil)), Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testObjectEarlyDefns(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |trait B
//        |//start
//        |object A extends { val x: Int = 2 } with B
//      """.stripMargin,
//       Defn.Object(Nil, Term.Name("A"),
//         Template(List(Defn.Val(Nil, List(Pat.Var.Term(Term.Name("x"))), Some(Type.Name("Int")), Lit.Int(2))),
//           List(Term.Apply(Ctor.Ref.Name("B"), Nil)), Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

  def testObjectSelfType(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |trait B
//        |//start
//        |object A { self: B => }
//      """.stripMargin,
//      Defn.Object(Nil, Term.Name("A"),
//        Template(Nil, Nil, Term.Param(Nil, Term.Name("self"), Some(Type.Name("B")), None), Some(Nil)))
//    )
  }

  def testTraitExtendsTparams(): Unit = {
    // TODO 2.13
//    doTest(
//      """
//        |trait A[T]
//        |trait B[U]
//        |trait C
//        |//start
//        |class Foo extends A[Foo] with B[A[Foo] with C] with C
//      """.stripMargin,
//      Defn.Class(Nil, Type.Name("Foo"), Nil, Ctor.Primary(Nil, Ctor.Ref.Name("this"), Nil),
//        Template(Nil, List(Term.Apply(Term.ApplyType(Ctor.Ref.Name("A"), List(Type.Name("Foo"))), Nil),
//          Term.Apply(Term.ApplyType(Ctor.Ref.Name("B"), List(Type.With(Type.Apply(Type.Name("A"),
//            List(Type.Name("Foo"))), Type.Name("C")))), Nil), Term.Apply(Ctor.Ref.Name("C"), Nil)),
//          Term.Param(Nil, Name.Anonymous(), None, None), None))
//    )
  }

}
