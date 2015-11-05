name := """fabricator-web"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val commonSettings = Seq(

    scalaVersion := "2.11.4",

    crossScalaVersions := Seq("2.10.4", "2.11.4"),

    scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

)

ScoverageSbtPlugin.ScoverageKeys.coverageExcludedPackages := "<empty>;controllers\\..*Reverse.*"

ScoverageSbtPlugin.ScoverageKeys.coverageExcludedFiles := ".*((R|r)everse|routing|template).*;.*Application.*"

herokuAppName in Compile := "fabricator-web"

resolvers += "Fabricator" at "http://dl.bintray.com/biercoff/Fabricator"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies += "com.github.azakordonets" % "fabricator_2.10" % "2.0.1"

libraryDependencies += "org.webjars.bower" % "bootstrap" % "3.3.4"

libraryDependencies += "com.typesafe.play" %% "anorm" % "2.4.0"

libraryDependencies += specs2 % Test


libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
