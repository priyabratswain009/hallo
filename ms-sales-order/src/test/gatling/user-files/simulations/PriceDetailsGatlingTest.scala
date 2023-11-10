import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PriceDetails entity.
 */
class PriceDetailsGatlingTest extends Simulation {

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

    val scn = scenario("Test the PriceDetails entity")
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
            exec(http("Get all priceDetails")
            .get("/services/salesorder/api/price-details")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new priceDetails")
            .post("/services/salesorder/api/price-details")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "priceTableId":"0"
                , "itemId":"0"
                , "hcpcs":"SAMPLE_TEXT"
                , "billingCodeWhenSecondary":"SAMPLE_TEXT"
                , "priceType":"SAMPLE_TEXT"
                , "effectiveStartDate":"2020-01-01T00:00:00.000Z"
                , "effectiveEndDate":"2020-01-01T00:00:00.000Z"
                , "cmnReqdToBillStatus":"SAMPLE_TEXT"
                , "cmnFormName":"SAMPLE_TEXT"
                , "priorAuthReqStatus":"SAMPLE_TEXT"
                , "functionalAbilityReqStatus":"SAMPLE_TEXT"
                , "optionNumber":"SAMPLE_TEXT"
                , "optionName":"SAMPLE_TEXT"
                , "defaultOptionStatus":"SAMPLE_TEXT"
                , "billingCyclePeriod":"SAMPLE_TEXT"
                , "billingCycleInterval":"SAMPLE_TEXT"
                , "billingInArrearsStatus":"SAMPLE_TEXT"
                , "proRateBillingStatus":"SAMPLE_TEXT"
                , "dailyBillingInvoiceFreq":"SAMPLE_TEXT"
                , "dailyBillingInvoiceInterval":"SAMPLE_TEXT"
                , "chargeAmt":"0"
                , "allowedAmt":"0"
                , "allowedModifier1":"SAMPLE_TEXT"
                , "allowedModifier2":"SAMPLE_TEXT"
                , "allowedModifier3":"SAMPLE_TEXT"
                , "allowedModifier4":"SAMPLE_TEXT"
                , "acceptAssignmentStatus":"SAMPLE_TEXT"
                , "taxableStatus":"SAMPLE_TEXT"
                , "nontaxTypeName":"SAMPLE_TEXT"
                , "convertToPurchaseLastStatus":"SAMPLE_TEXT"
                , "convertToPurchaseChargeAmt":"0"
                , "convertToPurchaseAllowAmt":"0"
                , "convertToPurchaseModifier1":"SAMPLE_TEXT"
                , "convertToPurchaseModifier2":"SAMPLE_TEXT"
                , "convertToPurchaseModifier3":"SAMPLE_TEXT"
                , "convertToPurchaseModifier4":"SAMPLE_TEXT"
                , "billingMultiplierUnit":"0"
                , "status":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedByName":"SAMPLE_TEXT"
                , "updatedById":"SAMPLE_TEXT"
                , "priceDetailsUuid":null
                , "priceTableName":"SAMPLE_TEXT"
                , "itemNo":"SAMPLE_TEXT"
                , "itemName":"SAMPLE_TEXT"
                , "itemUom":"SAMPLE_TEXT"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "priceOptionBillingPeriodStart":"0"
                , "priceOptionBillingPeriodEnd":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_priceDetails_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created priceDetails")
                .get("/services/salesorder${new_priceDetails_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created priceDetails")
            .delete("/services/salesorder${new_priceDetails_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
