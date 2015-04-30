package com.gilt.gfc.semver

import org.scalatest.{FunSuite, Matchers}

class SemVerTest extends FunSuite with Matchers {

  test("Intrinsic equality") {
    assert(SemVer("1.2.3") === SemVer("1.2.3"))
    assert(SemVer("1.2.3.20130429085931") === SemVer("1.2.3.20130429085931"))
    assert(SemVer("1.2.3-SNAPSHOT") === SemVer("1.2.3-SNAPSHOT"))

  }

  test("Ambiguous equality") {
    assert(SemVer("1.2.3.20130429085931") === SemVer("1.2.3-SNAPSHOT"))
    assert(SemVer("1.2.3.20130429085931") === SemVer("1.2.3-RC4"))
    assert(SemVer("1.2.3-SNAPSHOT") === SemVer("1.2.3.20130429085931"))
    assert(SemVer("1.2.3-RC4") === SemVer("1.2.3.20130429085931"))
    assert(SemVer("1.2.3-SNAPSHOT") === SemVer("1.2.3-RC1"))
    assert(SemVer("0.7.3.2-gilt") === SemVer("0.7.3.2-gilt"))
    assert(SemVer("0.8.cross-gilt") === SemVer("0.8.cross-gilt"))
  }

  test("ordering") {
    SemVer("1.2.3") should be < SemVer("1.2.4")
    SemVer("1.2.4") should be > SemVer("1.2.3")

    SemVer("1.2.9") should be < SemVer("1.2.10")
    SemVer("1.2.10") should be > SemVer("1.2.9")

    SemVer("1.0.0") should be < SemVer("2.0.0")
    SemVer("2.0.0") should be > SemVer("1.0.0")

    SemVer("1.1.0") should be > SemVer("1.0.0")
    SemVer("1.0.0") should be < SemVer("1.1.0")

    SemVer("1.2.3") should be > SemVer("1.2.3.20130429085931")
    SemVer("1.2.3.20130429085931") should be < SemVer("1.2.3")

    SemVer("1.2.3") should be > SemVer("1.2.3-SNAPSHOT")
    SemVer("1.2.3-SNAPSHOT") should be < SemVer("1.2.3")

    SemVer("0.7.3.2-gilt") should be < SemVer("0.7.3.3-gilt")

    SemVer("6.0.0-RC4") should be < SemVer("6.0.0")
    SemVer("6.0.0") should be > SemVer("6.0.0-RC4")

    SemVer("6.0.0-RC5") should be > SemVer("6.0.0-RC4")
    SemVer("6.0.0-RC3") should be < SemVer("6.0.0-RC4")

    SemVer("1.2.4") should be < SemVer("6.0.0-RC4")
    SemVer("6.0.0-RC4") should be > SemVer("1.2.4")

    SemVer("1.2.4") should be < SemVer("1.3.0-RC4")
    SemVer("1.3.0-RC4") should be > SemVer("1.2.4")

    SemVer("1.2.4") should be < SemVer("1.2.5-RC4")
    SemVer("1.2.5-RC4") should be > SemVer("1.2.4")
  }

  test("Integration versions") {
    SemVer.isIntegrationVersion("1.2.3.20130429085931") should equal (true)
    SemVer.isIntegrationVersion("1.2.3") should equal (false)
    SemVer.isIntegrationVersion("1.2.3-SNAPSHOT") should equal (false)
  }

  test("Snapshop versions") {
    SemVer.isSnapshotVersion("1.2.3.20130429085931") should equal (false)
    SemVer.isSnapshotVersion("1.2.3") should equal (false)
    SemVer.isSnapshotVersion("1.2.3-SNAPSHOT") should equal (true)
  }

  test("Release versions") {
    SemVer.isReleaseVersion("1.2.3.20130429085931") should equal (false)
    SemVer.isReleaseVersion("1.2.3") should equal (true)
    SemVer.isReleaseVersion("1.2.3-SNAPSHOT") should equal (false)
  }

  test("Git tag version") {
    val dirtyGitTag = "0.7.5-8-g476747c-dirty"
    assert(SemVer(dirtyGitTag) === SemVer(0,7,5,Some("8-g476747c-dirty"),dirtyGitTag))
  }
}
