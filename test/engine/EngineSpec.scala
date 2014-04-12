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
      1 shouldEqual 1


      // BTC/USD

      // limit buy 1btc at 100$
      LimitOrder(Buy, 1, 100)


      // limit sell 1btc at 150$
      LimitOrder(Sell, 1, 150)


      // LTC/USD


    }
  }
}
