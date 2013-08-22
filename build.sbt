//import com.typesafe.startscript.StartScriptPlugin

//seq(StartScriptPlugin.startScriptForClassesSettings: _*)

scalaVersion := "2.10.0"

mainClass in Compile := Some("JettyLauncher")

net.virtualvoid.sbt.graph.Plugin.graphSettings

libraryDependencies += "org.scalatest" %% "scalatest_2" % "2.0.M5b" % "test"

libraryDependencies += "org.scalanlp" %% "breeze-process" % "0.3"

libraryDependencies += "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.4"

libraryDependencies += "org.mongodb" %% "casbah" % "2.6.2"
