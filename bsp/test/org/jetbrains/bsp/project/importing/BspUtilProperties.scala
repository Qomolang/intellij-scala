package org.jetbrains.bsp.project.importing

import ch.epfl.scala.bsp.testkit.gen.UtilGenerators._
import org.jetbrains.bsp.BspUtil._
import org.jetbrains.plugins.scala.SlowTests
import org.junit.experimental.categories.Category
import org.junit.{Ignore, Test}
import org.scalacheck.Prop.forAll
// TODO 2.13
//import org.scalatest.junit.AssertionsForJUnit
//import org.scalatest.prop.Checkers

@Category(Array(classOf[SlowTests]))
class BspUtilProperties /*extends AssertionsForJUnit with Checkers*/ {

  // TODO 2.13
//  @Test
//  def stringOpsToUri(): Unit = check(
//    forAll(genUri) { uri =>
//      uri.toURI.toString == uri
//    }
//  )

  // TODO 2.13
//  @Test @Ignore
//  def uriOpsToFile(): Unit = check(
//    forAll(genPath) { path =>
//      path.toUri.toFile == path.toFile
//    }
//  )
}
