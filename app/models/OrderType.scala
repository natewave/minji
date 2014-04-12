package models

trait OrderType {
  def price: PriceLevel

  def isFulfillable(price: Double): Boolean

  def remaining(qty: Double): Order
}

object OrderType {

  def all(): PartialFunction[Order, OrderType] = {

    case self@LimitOrder(side, _, limit) => new OrderType {
      def price: PriceLevel = LimitPrice(limit)

      def isFulfillable(price: Double): Boolean = side match {
        case Buy => price <= limit
        case Sell => price >= limit
      }

      override def remaining(qty: Double): LimitOrder = self.copy(qty = self.qty - qty)
    }

    case self@MarketOrder( _, _) => new OrderType {
      def price: PriceLevel = MarketPrice

      def isFulfillable(price: Double): Boolean = true

      def remaining(qty: Double): MarketOrder = self.copy(qty = self.qty - qty)
    }
  }
}
