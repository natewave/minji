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
      val limitBuy = LimitOrder(Buy, 1, 100)
      val limitBuy2 = LimitOrder(Buy, 0.5, 180)


      // limit sell 1btc at 150$
      val limitSell = LimitOrder(Sell, 1, 150)
      val limitSell2 = LimitOrder(Sell, 2, 100)

      val orderTypes = OrderType.all()

      val buyOrderBook = new OrderBook(Buy, orderTypes)
      val sellOrderBook = new OrderBook(Buy, orderTypes)

      buyOrderBook.bestLimit should beNone
      buyOrderBook.orders() should beEmpty
      buyOrderBook.top should beNone

      buyOrderBook.add(limitBuy)

      buyOrderBook.bestLimit should beSome(limitBuy.limit)
      buyOrderBook.top should beSome(limitBuy)

      sellOrderBook.add(limitSell)


      val matchingEngine = new MatchingEngine(buyOrderBook, sellOrderBook, orderTypes)

      matchingEngine.subscribe(new matchingEngine.Sub {
        def notify(pub: matchingEngine.Pub, event: OrderBookEvent) {
          println(event)
        }
      })

      matchingEngine.acceptOrder(limitBuy2)
      matchingEngine.acceptOrder(limitBuy2)
      matchingEngine.acceptOrder(limitBuy2)
      matchingEngine.acceptOrder(limitSell2)


      1 shouldEqual 1
    }
  }
}
