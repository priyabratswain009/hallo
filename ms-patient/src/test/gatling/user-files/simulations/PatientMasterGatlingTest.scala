import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PatientMaster entity.
 */
class PatientMasterGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8091"""

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

    val scn = scenario("Test the PatientMaster entity")
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
            exec(http("Get all patientMasters")
            .get("/services/patient/api/patient-masters")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new patientMaster")
            .post("/services/patient/api/patient-masters")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "patientFirstName":"SAMPLE_TEXT"
                , "patientMiddleName":"SAMPLE_TEXT"
                , "patientLastName":"SAMPLE_TEXT"
                , "dob":"2020-01-01T00:00:00.000Z"
                , "gender":"SAMPLE_TEXT"
                , "ssn":"SAMPLE_TEXT"
                , "taxZoneId":"0"
                , "discountPercent":"0"
                , "posId":"0"
                , "priorSystemKey":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "branchId":"0"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedByName":"SAMPLE_TEXT"
                , "updatedById":"0"
                , "patientMasterUUID":null
                , "patientIdNumber":"SAMPLE_TEXT"
                , "addressLine1":"SAMPLE_TEXT"
                , "addressLine2":"SAMPLE_TEXT"
                , "city":"SAMPLE_TEXT"
                , "state":"SAMPLE_TEXT"
                , "country":"SAMPLE_TEXT"
                , "zip":"SAMPLE_TEXT"
                , "contactNo1":"SAMPLE_TEXT"
                , "contactNo2":"SAMPLE_TEXT"
                , "fax":"SAMPLE_TEXT"
                , "efax":"SAMPLE_TEXT"
                , "email":"SAMPLE_TEXT"
                , "modeOfContact":"SAMPLE_TEXT"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "branchName":"SAMPLE_TEXT"
                , "billingAddressLine1":"SAMPLE_TEXT"
                , "billingAddressLine2":"SAMPLE_TEXT"
                , "billingAddressCity":"SAMPLE_TEXT"
                , "billingAddressState":"SAMPLE_TEXT"
                , "billingAddressZip":"SAMPLE_TEXT"
                , "caregiverName":"SAMPLE_TEXT"
                , "caregiverContact":"SAMPLE_TEXT"
                , "caregiverRelatinshipPatient":"SAMPLE_TEXT"
                , "taxZoneName":"SAMPLE_TEXT"
                , "taxRate":"0"
                , "patientDod":"2020-01-01T00:00:00.000Z"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_patientMaster_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created patientMaster")
                .get("/services/patient${new_patientMaster_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created patientMaster")
            .delete("/services/patient${new_patientMaster_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
