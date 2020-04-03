package gatling.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
class Mysimulation extends Simulation {

  //  val rampUpTimeSecs = 5
  //  val testTimeSecs = 100
  //  val noOfUsers = 1
  //  val minWaitMs = 1000 milliseconds
  //  val maxWaitMs = 3000 milliseconds
  //
  //  val baseURL = "http://localhost:8080"
  //  val baseName = "greeting"
  //  val requestName = baseName + "-request"
  //  val scenarioName = baseName + "-scenario"
  //  val URI = "/greeting"
  //
  //  val httpConf = http//.proxy(Proxy("localhost", 8080))
  //    .baseUrl(baseURL)
  //    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
  ////    .doNotTrackHeader("1")
  //    .acceptLanguageHeader("en-US,en;q=0.5")
  //    .acceptEncodingHeader("gzip, deflate")
  ////    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
  //
  //  val scn = scenario(scenarioName)
  //    .during(testTimeSecs) {
  //      exec(
  //        http(requestName)
  //          .get(URI)
  //          .check(status.is(200))
  //      )//.pause(minWaitMs, maxWaitMs)
  //    }
  //
  //  setUp(
  //    scn.inject(rampUsers(noOfUsers) over (rampUpTimeSecs))
  //  ).protocols(httpConf)

  val rampUpTimeSecs = 5
  val testTimeSecs = 100
  val noOfUsers = 1
  val minWaitMs = 1000 milliseconds
  val maxWaitMs = 3000 milliseconds

  val baseUrl = "http://localhost:8080";

  val httpConf = http.baseUrl(baseUrl)
  //val httpConf = http.proxy("http://localhost:8080")

  val headers = Map("Accept" -> """application/json""")
  val requestBody = ElFileBody("request.json")

  val csvFeeder = csv("addresses.csv").circular

  val greetingPage = {
    exec(http("Say the greeting..")
      .get("/greeting"))
      .pause(2, 2)
  }

  val scn = scenario("Greeting scenario")
    .during(testTimeSecs) {
      exec(repeat(5) {
        greetingPage
      })

    }

  val postHeaders = Map("content-type" -> "application/json")

  val postStudent = {
    feed(csvFeeder).
    exec(http("Posting the student")
      .post("/student")
      .headers(postHeaders)
      .body(requestBody)
    ).pause(2, 2)

  }

  val postScenario = scenario("Student post scenari0").during((testTimeSecs)) {
    exec(repeat(5) {
      postStudent
    })
  }


  //  setUp(scn.inject(atOnceUsers(5)).protocols(httpConf)).assertions(
  //    global.successfulRequests.percent.gte(100))

  setUp(
    postScenario.inject(rampUsers(noOfUsers).during(rampUpTimeSecs))
  ).protocols(httpConf)

}

