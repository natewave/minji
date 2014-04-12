package models

sealed trait OrderBookEvent

case class Trade(side: Side, price: Double, qty: Double) extends OrderBookEvent

case class PlacedOrder(order: Order) extends OrderBookEvent

case class RejectedOrder(order: Order) extends OrderBookEvent

case class CancelledOrder(order: Order) extends OrderBookEvent
