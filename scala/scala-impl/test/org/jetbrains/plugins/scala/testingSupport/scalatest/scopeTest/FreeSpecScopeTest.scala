package org.jetbrains.plugins.scala.testingSupport.scalatest.scopeTest

import org.jetbrains.plugins.scala.testingSupport.scalatest.generators.FreeSpecGenerator

trait FreeSpecScopeTest extends FreeSpecGenerator {

  def testFreeSpecEmptyScope(): Unit = {
    assertConfigAndSettings(createTestFromLocation(31, 7, complexFreeSpecFileName), complexFreeSpecClassName)
  }

  def testFreeSpecScope(): Unit = {
    val testNames = Seq(
      "A ComplexFreeSpec Outer scope 2 Inner scope 2 Another innermost scope",
      "A ComplexFreeSpec Outer scope 2 Inner test"
    )
    val path1 = List("[root]", complexFreeSpecClassName, "A ComplexFreeSpec", "Outer scope 2", "Inner scope 2", "Another innermost scope")
    val path2 = List("[root]", complexFreeSpecClassName, "A ComplexFreeSpec", "Outer scope 2", "Inner test")
    runTestByLocation2(
      10, 10, complexFreeSpecFileName,
      assertConfigAndSettings(_, complexFreeSpecClassName, testNames:_*),
      root => {
        assertResultTreeHasExactNamedPaths(root)(Seq(
          path1,
          path2
        ))
        assertResultTreeDoesNotHaveNodes(root,
          "Innermost scope",
          "Outer scope 3",
          "Not nested scope"
        )
      })
  }
}
