package models

sealed trait Side

case object Buy extends Side

case object Sell extends Side