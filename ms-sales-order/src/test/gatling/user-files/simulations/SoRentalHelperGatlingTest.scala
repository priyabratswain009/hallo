import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the SoRentalHelper entity.
 */
class SoRentalHelperGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8081"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results
        .disableFollowRedirect // We must follow redirects manually to get the xsrf token from the keycloak redirect
        .disableAutoReferer

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "X-XSRF-TOKEN" -> "${xsrf_token}"
    )

    val keycloakHeaders = Map(
        "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Upgrade-Insecure-Requests" -> "1"
    )

    val scn = scenario("Test the SoRentalHelper entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(302))
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .get("/oauth2/authorization/oidc")
        .check(status.is(302))
        .check(header("Location").saveAs("loginUrl"))).exitHereIfFailed
        .pause(2)
        .exec(http("Login Redirect")
        .get("${loginUrl}")
        .silent
        .headers(keycloakHeaders)
        .check(css("#kc-form-login", "action").saveAs("kc-form-login"))).exitHereIfFailed
        .pause(10)
        .exec(http("Authenticate")
        .post("${kc-form-login}")
        .silent
        .headers(keycloakHeaders)
        .formParam("username", "admin")
        .formParam("password", "admin")
        .formParam("submit", "Login")
        .check(status.is(302))
        .check(header("Location").saveAs("afterLoginUrl"))).exitHereIfFailed
        .pause(2)
        .exec(http("After Login Redirect")
        .get("${afterLoginUrl}")
        .silent
        .check(status.is(302))
        .check(header("Location").saveAs("finalRedirectUrl"))
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token")))
        .exec(http("Final Redirect")
        .get("${finalRedirectUrl}")
        .silent
        .check(status.is(200))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all soRentalHelpers")
            .get("/services/salesorder/api/so-rental-helpers")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new soRentalHelper")
            .post("/services/salesorder/api/so-rental-helpers")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "soId":"0"
                , "primaryInsurerId":"0"
                , "primaryInsurerName":"SAMPLE_TEXT"
                , "itemId":"0"
                , "itemNo":"SAMPLE_TEXT"
                , "itemName":"SAMPLE_TEXT"
                , "chargedAmount":"0"
                , "allowedAmount":"0"
                , "sou":"SAMPLE_TEXT"
                , "qty":"0"
                , "dosStart":"2020-01-01T00:00:00.000Z"
                , "dosEnd":"2020-01-01T00:00:00.000Z"
                , "periodNo":"SAMPLE_TEXT"
                , "rentalPeriod":"SAMPLE_TEXT"
                , "nextDos":"2020-01-01T00:00:00.000Z"
                , "status":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedById":"0"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "updatedByName":"SAMPLE_TEXT"
                , "soRentalHelperUuid":null
                , "patientId":"0"
                , "saleType":"SAMPLE_TEXT"
                , "primaryInsurancePriceTableId":"0"
                , "primaryInsurancePriceTableName":"SAMPLE_TEXT"
                , "modifier1":"SAMPLE_TEXT"
                , "modifier2":"SAMPLE_TEXT"
                , "modifier3":"SAMPLE_TEXT"
                , "modifier4":"SAMPLE_TEXT"
                , "icdPointer":"SAMPLE_TEXT"
                , "procedureIdentifier":"SAMPLE_TEXT"
                , "orderingProviderFirstName":"SAMPLE_TEXT"
                , "orderingProviderLastName":"SAMPLE_TEXT"
                , "orderingProviderNpi":"SAMPLE_TEXT"
                , "reference":"SAMPLE_TEXT"
                , "procCode":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_soRentalHelper_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created soRentalHelper")
                .get("/services/salesorder${new_soRentalHelper_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created soRentalHelper")
            .delete("/services/salesorder${new_soRentalHelper_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}