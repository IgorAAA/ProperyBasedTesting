import Dependencies._

name := "Property Testing Example"

ThisBuild / scalaVersion := "2.13.3"

ThisBuild / libraryDependencies := ScalaCheck.all :+ ScalaTest.scalaTest
