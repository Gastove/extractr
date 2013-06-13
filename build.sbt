//import com.typesafe.startscript.StartScriptPlugin

//seq(StartScriptPlugin.startScriptForClassesSettings: _*)

scalaVersion := "2.10.2"

mainClass in Compile := Some("JettyLauncher")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"

libraryDependencies += "org.scalanlp" %% "breeze-process" % "0.3"

libraryDependencies += "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.4"
