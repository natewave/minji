package models

trait OrderType {
  def bookDisplay: String

  def price: PriceLevel

  def crossesAt(price: Double): Boolean

  def decreasedBy(qty: Double): Order
}

object OrderType {

  def all(): PartialFunction[Order, OrderType] = {

    case self@LimitOrder(side, _, limit) => new OrderType {
      def bookDisplay: String = limit.toString

      def price: PriceLevel = LimitPrice(limit)

      def crossesAt(price: Double): Boolean = side match {
        case Buy => price <= limit
        case Sell => price >= limit
      }

      override def decreasedBy(qty: Double): LimitOrder = self.copy(qty = self.qty - qty)
    }

    case self@MarketOrder( _, _) => new OrderType {
      def bookDisplay: String = "MO"

      def price: PriceLevel = MarketPrice

      def crossesAt(price: Double): Boolean = true

      def decreasedBy(qty: Double): MarketOrder = self.copy(qty = self.qty - qty)
    }
  }
}
