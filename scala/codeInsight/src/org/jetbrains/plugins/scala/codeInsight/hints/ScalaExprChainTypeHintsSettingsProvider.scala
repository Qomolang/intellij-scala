package org.jetbrains.plugins.scala.codeInsight.hints

import java.{util => ju}

import com.intellij.codeInsight.hints.settings.{InlayProviderSettingsModel, InlaySettingsProvider}
import com.intellij.lang.Language
import com.intellij.openapi.project.Project
import org.jetbrains.plugins.scala.ScalaLanguage

class ScalaExprChainTypeHintsSettingsProvider extends InlaySettingsProvider {
  override def createModels(project: Project, language: Language): ju.List[InlayProviderSettingsModel] =
    if (language == ScalaLanguage.INSTANCE) ju.Collections.singletonList(new ScalaExprChainTypeHintSettingsModel)
    else ju.Collections.emptyList()

  override def getSupportedLanguages(project: Project): ju.Collection[Language] =
    ju.Collections.singletonList(ScalaLanguage.INSTANCE)
}