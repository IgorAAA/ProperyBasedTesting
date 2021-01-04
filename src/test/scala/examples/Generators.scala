package examples

import examples.domain.{Account, AccountCat, Amount}
import examples.domain.Ids._
import org.scalacheck.{Arbitrary, Gen}
import Arbitrary.arbitrary
import org.scalatest.matchers.should._
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class Generators extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {
  import Generators._

  property("non-empty amount additions") {
    forAll { (amount: Amount, amount2: Amount) =>
      (amount + amount2) should be > Amount.zeroAmount
    }
  }

  property("non-empty amount substractions") {
    forAll { (amount: Amount, amount2: Amount) =>
      (amount - amount2) should be >= Amount.zeroAmount
    }
  }

  property("account endOfYear ops") {
    forAll { account: Account =>
      val res = account.endOfYearOp
      if (res.accountCat.value == 'C') {
        res.amount shouldBe Amount.zeroAmount
        res.userId.value shouldBe 0L
      }
      if (res.accountCat.value == 'B')
        res.amount shouldBe Amount.zeroAmount
    }
  }

}

object Generators {
  val amountGen: Gen[Amount] =
    Gen.choose(0.01d, 1000000d).map(value => Amount(BigDecimal(value)))

  implicit val amountArb: Arbitrary[Amount] = Arbitrary(amountGen)

  val catUniformDistrGen: Gen[AccountCat] = Gen.oneOf('A', 'B', 'C').map(AccountCat)

  val catGen: Gen[AccountCat] = Gen.frequency((1, 'A'), (2, 'B'), (1, 'C')).map(AccountCat)

  val accountGen: Gen[Account] =
    for {
      id     <- arbitrary[Long]
      userId <- arbitrary[Long]
      name   <- Gen.alphaNumStr
      amount <- amountGen
      cat    <- catGen
    } yield Account(AccountId(id), name, cat, amount, UserId(userId))

  implicit val accountArb: Arbitrary[Account] = Arbitrary(accountGen)

}
