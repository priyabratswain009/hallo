import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the ClaimsCOB835Details entity.
 */
class ClaimsCOB835DetailsGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8086"""

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

    val scn = scenario("Test the ClaimsCOB835Details entity")
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
            exec(http("Get all claimsCOB835Details")
            .get("/services/claims/api/claims-cob-835-details")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new claimsCOB835Details")
            .post("/services/claims/api/claims-cob-835-details")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "serviceDate":"2020-01-01T00:00:00.000Z"
                , "adjudicatedProcedureCode":"SAMPLE_TEXT"
                , "adjudicatedProcedureModifierCodes":"SAMPLE_TEXT"
                , "chargeAmount":"0"
                , "allowedAmount":"0"
                , "adjustmentPrCode1":"SAMPLE_TEXT"
                , "adjustmentPrCode1Amount":"0"
                , "adjustmentPrCode2":"SAMPLE_TEXT"
                , "adjustmentPrCode2Amount":"0"
                , "adjustmentPrCode3":"SAMPLE_TEXT"
                , "adjustmentPrCode3Amount":"0"
                , "adjustmentPrCode4":"SAMPLE_TEXT"
                , "adjustmentPrCode4Amount":"0"
                , "adjustmentCoCode1":"SAMPLE_TEXT"
                , "adjustmentCoCode1Amount":"0"
                , "adjustmentCoCode2":"SAMPLE_TEXT"
                , "adjustmentCoCode2Amount":"0"
                , "adjustmentCoCode3":"SAMPLE_TEXT"
                , "adjustmentCoCode3Amount":"0"
                , "adjustmentCoCode4":"SAMPLE_TEXT"
                , "adjustmentCoCode4Amount":"0"
                , "adjustmentCrCode1":"SAMPLE_TEXT"
                , "adjustmentCrCode1Amount":"0"
                , "adjustmentCrCode2":"SAMPLE_TEXT"
                , "adjustmentCrCode2Amount":"0"
                , "adjustmentCrCode3":"SAMPLE_TEXT"
                , "adjustmentCrCode3Amount":"0"
                , "adjustmentCrCode4":"SAMPLE_TEXT"
                , "adjustmentCrCode4Amount":"0"
                , "adjustmentOaCode1":"SAMPLE_TEXT"
                , "adjustmentOaCode1Amount":"0"
                , "adjustmentOaCode2":"SAMPLE_TEXT"
                , "adjustmentOaCode2Amount":"0"
                , "adjustmentOaCode3":"SAMPLE_TEXT"
                , "adjustmentOaCode3Amount":"0"
                , "adjustmentOaCode4":"SAMPLE_TEXT"
                , "adjustmentOaCode4Amount":"0"
                , "adjustmentPiCode1":"SAMPLE_TEXT"
                , "adjustmentPiCode1Amount":"0"
                , "adjustmentPiCode2":"SAMPLE_TEXT"
                , "adjustmentPiCode2Amount":"0"
                , "adjustmentPiCode3":"SAMPLE_TEXT"
                , "adjustmentPiCode3Amount":"0"
                , "adjustmentPiCode4":"SAMPLE_TEXT"
                , "adjustmentPiCode4Amount":"0"
                , "providerPaymentAmount":"0"
                , "claimCob835MasterId":"0"
                , "serviceDateTo":"2020-01-01T00:00:00.000Z"
                , "unitCount":"0"
                , "claimsCob835DetailsUuid":null
                , "status":"SAMPLE_TEXT"
                , "postStatus":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_claimsCOB835Details_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created claimsCOB835Details")
                .get("/services/claims${new_claimsCOB835Details_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created claimsCOB835Details")
            .delete("/services/claims${new_claimsCOB835Details_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
