name := """fabricator-web"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "Fabricator" at "http://dl.bintray.com/biercoff/Fabricator"

libraryDependencies ++= Seq(
    jdbc,
    anorm,
    cache,
    ws,
    libraryDependencies += "com.github.azakordonets" % "fabricator_2.10" % "1.0.1"
)
