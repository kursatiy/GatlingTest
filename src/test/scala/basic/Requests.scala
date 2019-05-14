package basic
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.json4s.jackson.Json
import io.gatling.core.session._
import io.gatling.core.Predef._
import io.gatling.core.session._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.parsing.json._

object Requests {
  val generateToken = http("generateToken")
    .get("http://localhost:7778/api/auth/generate_token")
    .check(status.is(200),//check status, can be removed
      jsonPath("$.token").saveAs("tokenId"))

  val validateToken = http("validateToken")
    .get("http://localhost:7778/api/auth/validate_token/"+"${tokenId}")

  val getAllProducts = http("getAllProducts")
    .get("http://localhost:7777/api/products/get_all")
    .header("Cookie", "token="+"${tokenId}")
    .check(status.is(200), jsonPath( "$" ).saveAs( "RESPONSE_DATA_ALL_PRODUCTS" ) )


  val getTenthProducts = http("getTenthProducts")
    .get("http://localhost:7777/api/products/get_product/5c76cf4769d192e23d7207d7")
    .header("Cookie", "token="+"${tokenId}")
    .check( status.is(200) )

  val getTwelvethProducts = http("getTwelvethProducts")
    .get("http://localhost:7777/api/products/get_product/5c76cf5b92442f7e4730e319")
    .header("Cookie", "token="+"${tokenId}")
    .check( status.is(200))

  val addTenthItem = http("addTenthItem")
    .get("http://localhost:7779/api/cart/add_item/5c76cf4769d192e23d7207d7")
    .header("Cookie", "token="+"${tokenId}")
    .check( status.is(200))

  val addTwelvethItem = http("addTwelvethItem")
    .get("http://localhost:7779/api/cart/add_item/5c76cf5b92442f7e4730e319")
    .header("Cookie", "token="+"${tokenId}")
    .check( status.is(200))

  val getItemsFromCart = http("getItemsFromCart")
    .get("http://localhost:7779/api/cart/get_items")
    .header("Cookie", "token="+"${tokenId}")
    .check( status.is(200))

  val checkout = http("checkout")
    .get("http://localhost:7779/api/cart/checkout")
    .header("Cookie", "token="+"${tokenId}")
    .check( status.is(200))

}
