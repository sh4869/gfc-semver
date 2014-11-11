name := "gfc-semver"

organization := "com.gilt"

scalaVersion := "2.11.1"

crossScalaVersions := Seq("2.11.1", "2.10.4")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses := Seq("MIT" -> url("https://github.com/gilt/gfc-semver/blob/master/LICENSE"))

homepage := Some(url("https://github.com/gilt/gfc-semver"))

pomExtra := <scm>
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

