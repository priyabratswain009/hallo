import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PickupExchange entity.
 */
class PickupExchangeGatlingTest extends Simulation {

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

    val scn = scenario("Test the PickupExchange entity")
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
            exec(http("Get all pickupExchanges")
            .get("/services/salesorder/api/pickup-exchanges")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new pickupExchange")
            .post("/services/salesorder/api/pickup-exchanges")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "pickupExchangeNo":"SAMPLE_TEXT"
                , "pickupExchangeType":"SAMPLE_TEXT"
                , "soId":"0"
                , "soNo":"SAMPLE_TEXT"
                , "branchId":"0"
                , "branchName":"SAMPLE_TEXT"
                , "inventoryLocationId":"0"
                , "inventoryLocationName":"SAMPLE_TEXT"
                , "patientId":"0"
                , "patientIdNo":"SAMPLE_TEXT"
                , "patientFirstName":"SAMPLE_TEXT"
                , "patientMiddleName":"SAMPLE_TEXT"
                , "patientLastName":"SAMPLE_TEXT"
                , "patientContact1":"SAMPLE_TEXT"
                , "patientContact2":"SAMPLE_TEXT"
                , "patientBillingAddressLine1":"SAMPLE_TEXT"
                , "patientBillingAddressLine2":"SAMPLE_TEXT"
                , "patientBillingAddressState":"SAMPLE_TEXT"
                , "patientBillingAddressCity":"SAMPLE_TEXT"
                , "patientBillingAddressZip":"SAMPLE_TEXT"
                , "patientDeliveyAddressLine1":"SAMPLE_TEXT"
                , "patientDeliveyAddressLine2":"SAMPLE_TEXT"
                , "patientDeliveyAddressState":"SAMPLE_TEXT"
                , "patientDeliveyAddressCity":"SAMPLE_TEXT"
                , "patientDeliveyAddressZip":"SAMPLE_TEXT"
                , "pickupExchangeScheduleDateTime":"2020-01-01T00:00:00.000Z"
                , "pickupExchangeActualDateTime":"2020-01-01T00:00:00.000Z"
                , "pickupExchangeReason":"SAMPLE_TEXT"
                , "pickupExchangeRequest":"SAMPLE_TEXT"
                , "pickupExchangeNote":"SAMPLE_TEXT"
                , "pickupExchangeAgentIdNo":"SAMPLE_TEXT"
                , "pickupExchangeAgentName":"SAMPLE_TEXT"
                , "pickupExchangeDocumentId":"SAMPLE_TEXT"
                , "pickupExchangeDocumentNo":"SAMPLE_TEXT"
                , "pickupExchangeDocumentName":"SAMPLE_TEXT"
                , "pickupExchangeStatus":"SAMPLE_TEXT"
                , "pickupExchangeComments":"SAMPLE_TEXT"
                , "isPatientSigned":"SAMPLE_TEXT"
                , "relationshipWithPatient":"SAMPLE_TEXT"
                , "patientSignedDateTime":"2020-01-01T00:00:00.000Z"
                , "isAgentSigned":"SAMPLE_TEXT"
                , "lastBillingDate":"2020-01-01T00:00:00.000Z"
                , "dateOfDeath":"2020-01-01T00:00:00.000Z"
                , "pickupExchangeSupportingDocument1":"SAMPLE_TEXT"
                , "pickupExchangeSupportingDocument2":"SAMPLE_TEXT"
                , "patientNotsignedReason":"SAMPLE_TEXT"
                , "pickupExchangeJsonData":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdByName":"SAMPLE_TEXT"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "updatedById":"0"
                , "updatedByName":"SAMPLE_TEXT"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "pickupExchangeUuid":null
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_pickupExchange_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created pickupExchange")
                .get("/services/salesorder${new_pickupExchange_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created pickupExchange")
            .delete("/services/salesorder${new_pickupExchange_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
