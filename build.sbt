name := "gfc-semver"

organization := "com.gilt"

scalaVersion := "2.11.6"

crossScalaVersions := Seq("2.11.6", "2.10.4")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

publishMavenStyle := true

bintraySettings

bintrayPublishSettings

bintray.Keys.bintrayOrganization in bintray.Keys.bintray := Some("giltgroupe")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/gilt/gfc-semver"))

version := "git describe --tags --always --dirty".!!.trim.replaceFirst("^v", "")
