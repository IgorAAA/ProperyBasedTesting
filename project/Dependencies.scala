import sbt._

object Dependencies {
  object ScalaCheck {
    val version                    = "1.14.3"
    val scalacheckShapelessVersion = "1.2.5"

    val core      = "org.scalacheck"    %% "scalacheck"               % version       % Test
    val scalaTest = "org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2" % Test

    val scalaCheckShapeless =
      "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % scalacheckShapelessVersion

    val all = Seq(core, scalaTest, scalaCheckShapeless)
  }

  object ScalaTest {
    val scalaTest = "org.scalatest" %% "scalatest" % "3.2.0" % Test
  }

}
