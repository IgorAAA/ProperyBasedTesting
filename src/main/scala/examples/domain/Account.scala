package examples.domain

import examples.domain.Ids.{AccountId, UserId}

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
