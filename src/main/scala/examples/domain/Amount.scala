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
