package examples.domain

final case class Amount(value: BigDecimal) {
  def +(that: Amount): Amount = Amount(this.value + that.value)
  def -(that: Amount): Amount =
    if (value < that.value)
      Amount(0)
    else
      Amount(value - that.value)
}

object Amount {
  val zeroAmount: Amount = Amount(BigDecimal(0))

  implicit val ord: Ordering[Amount] = Ordering.by(_.value)
}

final case class AccountId(value: Long)

final case class UserId(value: Long)

final case class AccountCat(value: Char)

final case class Account(
  id: AccountId,
  name: String,
  accountCat: AccountCat,
  amount: Amount,
  userId: UserId
) {
  def endOfYearOp: Account = accountCat.value match {
    case 'A' => this
    case 'B' => this.copy(amount = Amount.zeroAmount)
    case 'C' => closeAccount
  }

  private def closeAccount: Account = this.copy(amount = Amount.zeroAmount, userId = UserId(0L))
}
