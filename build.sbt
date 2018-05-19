name := "gfc-semver"

organization := "com.gilt"

scalaVersion := "2.12.6"

crossScalaVersions := Seq(scalaVersion.value, "2.11.11", "2.10.7")

scalacOptions += "-target:jvm-1.7"

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

releaseCrossBuild := true

releasePublishArtifactsAction := PgpKeys.publishSigned.value

publishMavenStyle := true

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/gilt/gfc-semver"))

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>https://github.com/gilt/gfc-semver.git</url>
    <connection>scm:git:git@github.com:gilt/gfc-semver.git</connection>
  </scm>
  <developers>
    <developer>
      <id>stjohnb</id>
      <name>Brendan St John</name>
      <url>https://github.com/stjohnb</url>
    </developer>
  </developers>
)

