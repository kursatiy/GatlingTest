package basic
import Requests._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.json4s.jackson.Json
import io.gatling.core.session._
import io.gatling.core.Predef._
import io.gatling.core.session._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.parsing.json._

class MyFirstGatlingTest extends Simulation {

  val httpProtocol = http
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")


  val loadAllMethods = scenario("LoadAllAppMethods")

    .exec(generateToken)

    .exec(validateToken)

    .exec(getAllProducts)
      //.exec( session => { //to check response
      //println( "Some Restful Service:" )
      //println( session( "RESPONSE_DATA_ALL_PRODUCTS" ).as[String] )
      //session
      //})
    .exec(getTenthProducts)

    .exec(getTwelvethProducts)

    .exec(addTenthItem)

    .exec(addTwelvethItem)

    .exec(getItemsFromCart)

    .exec(checkout)

  setUp(
    loadAllMethods.inject//(atOnceUsers(10),
    // rampUsers(10) during (5 seconds),
     // constantUsersPerSec(20) during (15 seconds),
    // constantUsersPerSec(20) during (15 seconds) randomized,
      (rampUsersPerSec(1) to 20 during (2 minutes),
    // rampUsersPerSec(10) to 20 during (10 minutes) randomized

  ).protocols(httpProtocol))
}