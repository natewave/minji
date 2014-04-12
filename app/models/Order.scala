package models

sealed trait Order {
  val qty: Double
  val side: Side
}

case class LimitOrder(side: Side, qty: Double, limit: Double) extends Order

case class MarketOrder(side: Side, qty: Double) extends Order