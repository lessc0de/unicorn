name := "console"

enablePlugins(JavaAppPackaging)

maintainer := "Haifeng Li <Haifeng.Li@ADP.COM>"

packageName := "adp-unicorn-console"

packageSummary := "ADP Unicorn Console"

packageDescription := "ADP Unicorn Console"

executableScriptName := "unicorn"

bashScriptExtraDefines += """addJava "-Dscala.repl.autoruncode=${app_home}/init.scala""""

mainClass in Compile := Some("com.adp.unicorn.console.Console")

libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.7"

libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.2"

libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.2"
