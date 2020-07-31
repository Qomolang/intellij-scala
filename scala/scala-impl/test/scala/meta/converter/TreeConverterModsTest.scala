package scala.meta.converter

import scala.meta.TreeConverterTestBaseNoLibrary
import scala.meta._

class TreeConverterModsTest extends TreeConverterTestBaseNoLibrary {

def testPrivateUnqual(): Unit = {
  // TODO 2.13
//  doTest(
//    "private val a = 42",
//     Defn.Val(List(Mod.Private(Name.Anonymous())), List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42))
//  )
}
  
  def testPrivateThis(): Unit = {
    // TODO 2.13
//    doTest(
//      "private[this] val a = 42",
//      Defn.Val(List(Mod.Private(Term.This(Name.Anonymous()))), List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42))
//    )
  }
  
  def testPrivatePackage(): Unit = {
    // TODO 2.13
//    doTest(
//      "private[scala] val a = 42",
//      Defn.Val(List(Mod.Private(Name.Indeterminate("scala"))), List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42))
//    )
  }

  def testProtectedUnqual(): Unit = {
    // TODO 2.13
//    doTest(
//      "protected val a = 42",
//      Defn.Val(List(Mod.Protected(Name.Anonymous())), List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42))
//    )
  }

  def testProtectedThis(): Unit = {
    // TODO 2.13
//    doTest(
//      "protected[this] val a = 42",
//      Defn.Val(List(Mod.Protected(Term.This(Name.Anonymous()))), List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42))
//    )
  }

  def testProtectedPackage(): Unit = {
    // TODO 2.13
//    doTest(
//      "protected[scala] val a = 42",
//      Defn.Val(List(Mod.Protected(Name.Indeterminate("scala"))), List(Pat.Var.Term(Term.Name("a"))), None, Lit.Int(42))
//    )
  }
}
