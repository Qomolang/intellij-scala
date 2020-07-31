package org.jetbrains.plugins.scala.worksheet.integration

import com.intellij.compiler.CompilerMessageImpl
import com.intellij.openapi.compiler.CompilerMessageCategory
import com.intellij.openapi.editor.Editor
import org.jetbrains.plugins.scala.extensions.StringExt
import org.jetbrains.plugins.scala.worksheet.integration.WorksheetIntegrationBaseTest.{Folding, ViewerEditorData}
import org.junit.Assert.{assertEquals, assertNotNull, fail}

trait WorksheetItAssertions {
  self: WorksheetIntegrationBaseTest =>

  def assertViewerEditorText(editor: Editor, expectedText: String): Unit =
    assertViewerEditorText(
      editor,
      assertEquals(expectedText.withNormalizedSeparator, _)
    )

  def assertViewerEditorText(editor: Editor, textAssert: String => Unit): Unit = {
    val ViewerEditorData(_, actualText, _) = viewerEditorDataFromLeftEditor(editor)
    textAssert(actualText)
  }

  def assertViewerOutput(editor: Editor, expectedText: String, expectedFoldings: Seq[Folding]): Unit =
    assertViewerOutput(
      editor,
      assertEquals(expectedText.withNormalizedSeparator, _),
      expectedFoldings
    )

  def assertViewerOutput(editor: Editor, textAssert: String => Unit, expectedFoldings: Seq[Folding]): Unit = {
    val ViewerEditorData(_, actualText, actualFoldings) = viewerEditorDataFromLeftEditor(editor)
    try {
      textAssert(actualText)
    } catch {
      case ex: AssertionError =>
        printCollectedCompilerMessages(editor)
        throw ex
    }
    assertFoldings(expectedFoldings, actualFoldings)
  }

  private def printCollectedCompilerMessages(editor: Editor): Unit =
    collectedCompilerMessages(editor).foreach { message =>
      System.err.println(messageText(message))
    }

  private def messageText(message: CompilerMessageImpl): String =
    s"(${message.getLine}:${message.getColumn}) ${message.getCategory} ${message.toString}"

  def assertFoldings(expectedFoldings: Seq[Folding], actualFoldings: Seq[Folding]): Unit =
    expectedFoldings.zipAll(actualFoldings, null, null).toList.foreach { case (expected, actual) =>
      assertNotNull(
        s"""there are to few actual foldings:
           |expected : $expected
           |expected all : $expectedFoldings
           |actual all : $actualFoldings
           |""".stripMargin,
        actual
      )
      assertNotNull(
        s"""there are some unexpected foldings:
           |actual: $actual
           |expected all : $expectedFoldings
           |actual all : $actualFoldings
           |""".stripMargin,
        expected
      )
      assertEquals(expected, actual)
    }

  // This is not the most fine-grained testing but quite easy to produce new test data,
  // just copying from actual messages window (though should be checked manually first)
  protected def assertCompilerMessages(editor: Editor)(expectedAllMessagesText: String): Unit = {
    val messages = collectedCompilerMessages(editor).sortBy(m => (m.getLine, m.getColumn))
    val messagesRendered = messages.map { message: CompilerMessageImpl =>
      val level = message.getCategory.toString.toLowerCase.capitalize
      s"$level:(${message.getLine}, ${message.getColumn}) ${message.getMessage}"
    }
    val actualAllMessagesText = messagesRendered.mkString("\n")
    val expectedWithoutBlankLines = expectedAllMessagesText.withNormalizedSeparator.replaceAll("\n\n+", "\n").trim
    assertEquals(
      expectedWithoutBlankLines,
      actualAllMessagesText
    )
  }

  def assertNoErrorMessages(editor: Editor): Unit =
    assertNoMessages(editor, CompilerMessageCategory.ERROR)

  def assertNoWarningMessages(editor: Editor): Unit =
    assertNoMessages(editor, CompilerMessageCategory.WARNING)

  def assertNoMessages(editor: Editor, category: CompilerMessageCategory): Unit = {
    val messages = collectedCompilerMessages(editor).filter(_.getCategory == category)
    if (messages.nonEmpty) {
      val messagesRenders = messages.map { err =>
        s"${err.getCategory} (${err.getLine}, ${err.getColumn}) ${err.getMessage}}"
      }
      val typ = category match {
        case CompilerMessageCategory.ERROR       => "errors"
        case CompilerMessageCategory.WARNING     => "warnings"
        case CompilerMessageCategory.INFORMATION => "information messages"
        case CompilerMessageCategory.STATISTICS  => "???"
      }
      fail(s"Unexpected compilation $typ occurred during worksheet evaluation:\n${messagesRenders.mkString("\n")}")
    }
  }

  protected def collectedCompilerMessages(editor: Editor): Seq[CompilerMessageImpl] = {
    val collector = worksheetCache.getCompilerMessagesCollector(editor).orNull
    assertNotNull(collector)
    collector.collectedMessages.map(_.asInstanceOf[CompilerMessageImpl]).toSeq
  }
}
