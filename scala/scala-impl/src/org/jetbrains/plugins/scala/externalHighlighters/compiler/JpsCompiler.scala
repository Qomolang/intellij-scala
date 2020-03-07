package org.jetbrains.plugins.scala.externalHighlighters.compiler

import java.io.File

import com.intellij.compiler.server.BuildManager
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.project.Project
import com.intellij.util.io.PathKt
import org.jetbrains.jps.incremental.Utils
import org.jetbrains.jps.incremental.scala.remote.{CommandIds, CompileServerCommand}
import org.jetbrains.plugins.scala.compiler.RemoteServerRunner

trait JpsCompiler {

  def compile(project: Project): Unit
}

class JpsCompilerImpl
  extends JpsCompiler {

  override def compile(project: Project): Unit = {
    val command = CommandIds.CompileJps
    val projectPath = project.getBasePath
    val globalOptionsPath = PathManager.getOptionsPath
    val dataStorageRootPath = Utils.getDataStorageRoot(
      new File(PathKt.getSystemIndependentPath(BuildManager.getInstance.getBuildSystemDirectory)),
      projectPath
    ).getCanonicalPath
    val args = CompileServerCommand.CompileJps(
      token = "",
      projectPath = projectPath,
      globalOptionsPath = globalOptionsPath,
      dataStorageRootPath = dataStorageRootPath
    ).asArgsWithoutToken
    val client = new CompilationClient(project)
    new RemoteServerRunner(project).buildProcess(command, args, client).runSync()
  }
}

