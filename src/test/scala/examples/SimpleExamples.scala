package examples

import org.scalatest.matchers.must.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class SimpleExamples extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {
  property("list double reverse") {
    forAll { ls: List[String] =>
      ls.reverse.reverse mustBe ls
    }
  }

  property("starts with") {
    forAll { (a: String, b: String) =>
      val res = a + b
      res.startsWith(a) mustBe true
    }
  }
}
