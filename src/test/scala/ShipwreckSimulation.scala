import io.gatling.core.Predef._
import io.gatling.http.Predef._
class ShipwreckSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://shipwreck-qa.jqzi5wdvms.us-east-1.elasticbeanstalk.com")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:49.0) Gecko/20100101 Firefox/49.0")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_3 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

  val headers_4 = Map("Accept" -> "text/css,*/*;q=0.1")

  val headers_19 = Map("Accept" -> "text/html")

  val headers_22 = Map("Accept" -> "application/json, text/plain, */*")

  val headers_25 = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Content-Type" -> "application/json;charset=utf-8")



  val scn = scenario("RecordedSimulation")
    // shipwreck
    .exec(http("Open the application")
    .get("/index.html")
    .headers(headers_0)
    .resources(http("request_1")
      .get("/js/app.js"),
      http("request_2")
        .get("/lib/bootstrap/js/bootstrap.min.js"),
      http("request_3")
        .get("/favicon.ico")
        .headers(headers_3),
      http("request_4")
        .get("/lib/bootstrap/css/bootstrap-theme.min.css")
        .headers(headers_4),
      http("request_5")
        .get("/js/controllers.js"),
      http("request_6")
        .get("/css/style.css")
        .headers(headers_4),
      http("request_7")
        .get("/js/nav-controller.js"),
      http("request_8")
        .get("/lib/assets/js/ie10-viewport-bug-workaround.js"),
      http("request_9")
        .get("/lib/angular/css/angular-csp.css")
        .headers(headers_4),
      http("request_10")
        .get("/js/services.js"),
      http("request_11")
        .get("/lib/bootstrap/css/bootstrap.min.css")
        .headers(headers_4),
      http("request_12")
        .get("/lib/angular/js/angular-resource.js"),
      http("request_13")
        .get("/lib/require/require.js"),
      http("request_14")
        .get("/lib/angular/js/angular-ui-bootstrap-tpls.js"),
      http("request_15")
        .get("/lib/angular/js/angular-animate.js"),
      http("request_16")
        .get("/lib/jquery/jquery.min.js"),
      http("request_17")
        .get("/lib/angular/js/angular-ui-router.js"),
      http("request_18")
        .get("/lib/angular/js/angular.js"),
      http("request_19")
        .get("/views/home.html")
        .headers(headers_19),
      http("request_20")
        .get("/images/shipwreck.jpg")))
    .pause(5)
    .exec(http("request_21")
      .get("/views/shipwrecks.html")
      .headers(headers_19)
      .resources(http("Get the ship wrecks")
        .get("/api/v1/shipwrecks")
        .headers(headers_22)))
    .pause(6)
    .exec(http("request_23")
      .get("/views/shipwreck-add.html")
      .headers(headers_19)
      .resources(http("request_24")
        .get("/views/_form.html")
        .headers(headers_22)))
    .pause(51)
    .exec(http("Add the ship wreck item")
      .post("/api/v1/shipwrecks")
      .headers(headers_25)
      .body(RawFileBody("RecordedSimulation_0025_request.txt"))
      .resources(http("request_26")
        .get("/api/v1/shipwrecks")
        .headers(headers_22)))
    .pause(6)
    .exec(http("request_27")
      .get("/views/shipwreck-view.html")
      .headers(headers_19)
      .resources(http("request_28")
        .get("/api/v1/shipwrecks/103")
        .headers(headers_22)))
    .pause(2)
    .exec(http("request_30")
      .get("/views/shipwreck-edit.html")
      .headers(headers_19)
      .resources(http("request_30")
        .get("/api/v1/shipwrecks/103")
        .headers(headers_22)))
    .pause(4)
    .exec(http("Edit the shipwreck item")
      .put("/api/v1/shipwrecks/103")
      .headers(headers_25)
      .body(RawFileBody("RecordedSimulation_0031_request.txt"))
      .resources(http("request_32")
        .get("/api/v1/shipwrecks")
        .headers(headers_22)))
    .pause(5)
    .exec(http("Delete the ship wreck item")
      .delete("/api/v1/shipwrecks/103")
      .headers(headers_22)
      .resources(http("request_34")
        .get("/api/v1/shipwrecks")
        .headers(headers_22)))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol).assertions(global.failedRequests.count.is(0))
    .assertions(global.responseTime.max.between(500,7500))
    .assertions(global.responseTime.percentile1.lessThan(1000))
    .assertions(global.responseTime.percentile2.lessThan(2000))
    .assertions(global.responseTime.percentile3.lessThan(3000))
    .assertions(global.responseTime.percentile4.lessThan(4500))
}