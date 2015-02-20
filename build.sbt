name := """fabricator-web"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val commonSettings = Seq(

    scalaVersion := "2.10.4",

    crossScalaVersions := Seq("2.10.4", "2.11.4"),

    scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

)

ScoverageSbtPlugin.ScoverageKeys.coverageExcludedPackages := "<empty>;controllers\\..*Reverse.*"

ScoverageSbtPlugin.ScoverageKeys.coverageExcludedFiles := ".*((R|r)everse|routing|template).*;.*Application.*"

herokuAppName in Compile := "fabricator-web"

resolvers += "Fabricator" at "http://dl.bintray.com/biercoff/Fabricator"

libraryDependencies += "com.github.azakordonets" % "fabricator_2.10" % "1.0.3"

libraryDependencies ++= Seq(
    jdbc,
    anorm,
    cache,
    ws
)
