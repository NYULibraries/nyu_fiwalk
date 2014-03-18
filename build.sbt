import AssemblyKeys._

assemblySettings

jarName in assembly := "nyudgi.jar"

name := "NYU Fiwalk"

version := "0.1"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "commons-logging" % "commons-logging" % "1.1.3",
  "org.apache.tika" % "tika-core" % "1.5"
)
            