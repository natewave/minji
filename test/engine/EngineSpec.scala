package engine

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import models._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class EngineSpec extends Specification {

  "Application" should {

    "test" in {


      // BTC/USD

      // limit buy 1btc at 100$
      val limitBuy = LimitOrder(Buy(BTC), 1, 100)


      // limit sell 1btc at 150$
      val limitSell = LimitOrder(Sell(BTC), 1, 150)


      val buyOrderBook = new OrderBook(Buy(BTC), OrderType.all())
      val sellOrderBook = new OrderBook(Buy(BTC), OrderType.all())

      buyOrderBook.bestLimit should beNone
      buyOrderBook.orders() should beEmpty
      buyOrderBook.top should beNone

      buyOrderBook.add(limitBuy)

      buyOrderBook.bestLimit should beSome(limitBuy.limit)
      buyOrderBook.top should beSome(limitBuy)

      sellOrderBook.add(limitSell)



      1 shouldEqual 1
    }
  }
}
