package org.jetbrains.plugins.scala.testingSupport.scalatest.singleTest

import org.jetbrains.plugins.scala.testingSupport.scalatest.generators.WordSpecGenerator

trait WordSpecSingleTestTest extends WordSpecGenerator {

  val wordSpecTestPath = List("[root]", wordSpecClassName, "WordSpecTest", "Run single test")
  val wordSpecTestTaggedPath = List("[root]", wordSpecClassName, "tagged", "be tagged")

  def testWordSpec(): Unit =
    runTestByLocation2(5, 10, wordSpecFileName,
      assertConfigAndSettings(_, wordSpecClassName, "WordSpecTest should Run single test"),
      root => {
        assertResultTreeHasExactNamedPath(root, wordSpecTestPath)
        assertResultTreeDoesNotHaveNodes(root, "ignore other tests")
      }
    )

  def testTaggedWordSpec(): Unit =
    runTestByLocation2(20, 6, wordSpecFileName,
      assertConfigAndSettings(_, wordSpecClassName, "tagged should be tagged"),
      root => {
        assertResultTreeHasExactNamedPath(root, wordSpecTestTaggedPath)
        assertResultTreeDoesNotHaveNodes(root, "ignore other tests")
      }
    )
}
