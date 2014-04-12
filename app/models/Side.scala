package models

sealed trait Side {
    val coin: Coin
}

case class Buy(coin: Coin) extends Side

case class Sell(coin: Coin) extends Side