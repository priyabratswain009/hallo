import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the DeliveryTicket entity.
 */
class DeliveryTicketGatlingTest extends Simulation {

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

    val scn = scenario("Test the DeliveryTicket entity")
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
            exec(http("Get all deliveryTickets")
            .get("/services/salesorder/api/delivery-tickets")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new deliveryTicket")
            .post("/services/salesorder/api/delivery-tickets")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "deliveryTicketNo":"SAMPLE_TEXT"
                , "soId":"0"
                , "soNo":"SAMPLE_TEXT"
                , "patientFirstName":"SAMPLE_TEXT"
                , "patientMiddleName":"SAMPLE_TEXT"
                , "patientLastName":"SAMPLE_TEXT"
                , "gender":"SAMPLE_TEXT"
                , "ageAsOnDate":"0"
                , "phone1":"SAMPLE_TEXT"
                , "phone2":"SAMPLE_TEXT"
                , "email":"SAMPLE_TEXT"
                , "deliveryAddress1":"SAMPLE_TEXT"
                , "deliveryAddress2":"SAMPLE_TEXT"
                , "deliveryCity":"SAMPLE_TEXT"
                , "deliveryState":"SAMPLE_TEXT"
                , "deliveryZip":"SAMPLE_TEXT"
                , "deliveryStatus":"SAMPLE_TEXT"
                , "deliveryDate":"2020-01-01T00:00:00.000Z"
                , "deliveryPriority":"SAMPLE_TEXT"
                , "deliveryNote":"SAMPLE_TEXT"
                , "deliveryComment":"SAMPLE_TEXT"
                , "deliveryAcceptedBy":"SAMPLE_TEXT"
                , "relationshipWithPatient":"SAMPLE_TEXT"
                , "deliveryAcceptedByContactNo":"SAMPLE_TEXT"
                , "primaryInsurerName":"SAMPLE_TEXT"
                , "primaryInsurerPolicyNo":"SAMPLE_TEXT"
                , "primaryInsurerPatientIdNumber":"SAMPLE_TEXT"
                , "patientIdNo":"SAMPLE_TEXT"
                , "branchAddressLine1":"SAMPLE_TEXT"
                , "branchAddressLine2":"SAMPLE_TEXT"
                , "branchCity":"SAMPLE_TEXT"
                , "branchState":"SAMPLE_TEXT"
                , "branchZipCode":"SAMPLE_TEXT"
                , "branchContactNo1":"SAMPLE_TEXT"
                , "branchContactNo2":"SAMPLE_TEXT"
                , "branchNpi":"SAMPLE_TEXT"
                , "branchEin":"SAMPLE_TEXT"
                , "branchFax":"SAMPLE_TEXT"
                , "orderingProviderFirstName":"SAMPLE_TEXT"
                , "orderingProviderMiddleName":"SAMPLE_TEXT"
                , "orderingProviderLastName":"SAMPLE_TEXT"
                , "orderingProviderNpi":"SAMPLE_TEXT"
                , "orderingProviderAddressLine1":"SAMPLE_TEXT"
                , "orderingProviderAddressLine2":"SAMPLE_TEXT"
                , "orderingProviderCity":"SAMPLE_TEXT"
                , "orderingProviderState":"SAMPLE_TEXT"
                , "orderingProviderZip":"SAMPLE_TEXT"
                , "orderingProviderPhone1":"SAMPLE_TEXT"
                , "orderingProviderPhone2":"SAMPLE_TEXT"
                , "orderingProviderFax":"SAMPLE_TEXT"
                , "orderingProviderEmail":"SAMPLE_TEXT"
                , "branchName":"SAMPLE_TEXT"
                , "patientBranchId":"0"
                , "status":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdByName":"SAMPLE_TEXT"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"0"
                , "updatedByName":"SAMPLE_TEXT"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "deliveryTicketUuid":null
                , "billingAddressLine1":"SAMPLE_TEXT"
                , "billingAddressLine2":"SAMPLE_TEXT"
                , "billingCity":"SAMPLE_TEXT"
                , "billingState":"SAMPLE_TEXT"
                , "billingZip":"SAMPLE_TEXT"
                , "inventoryLocationId":"0"
                , "inventoryLocationName":"SAMPLE_TEXT"
                , "deliveryTicketDocumentId":"0"
                , "deliveryTicketDocumentNo":"SAMPLE_TEXT"
                , "deliveryTicketDocumentName":"SAMPLE_TEXT"
                , "deliveryType":"SAMPLE_TEXT"
                , "carrierName":"SAMPLE_TEXT"
                , "shippingDate":"2020-01-01T00:00:00.000Z"
                , "trackingNo":"SAMPLE_TEXT"
                , "referenceNo":"SAMPLE_TEXT"
                , "packageWeight":"SAMPLE_TEXT"
                , "setupMethod":"SAMPLE_TEXT"
                , "setupTechnicianNo":"SAMPLE_TEXT"
                , "setupTechnicianContactNo":"SAMPLE_TEXT"
                , "setupTechnicianFirstName":"SAMPLE_TEXT"
                , "setupTechnicianMiddleName":"SAMPLE_TEXT"
                , "setupTechnicianLastName":"SAMPLE_TEXT"
                , "setupDateTime":"2020-01-01T00:00:00.000Z"
                , "scheduleSetupDateTime":"2020-01-01T00:00:00.000Z"
                , "setupComments":"SAMPLE_TEXT"
                , "setupStatus":"SAMPLE_TEXT"
                , "courierPackageAcceptedBy":"SAMPLE_TEXT"
                , "therapistFirstName":"SAMPLE_TEXT"
                , "therapistMiddleName":"SAMPLE_TEXT"
                , "therapistLastName":"SAMPLE_TEXT"
                , "therapistLicenseNo":"SAMPLE_TEXT"
                , "therapistNpi":"SAMPLE_TEXT"
                , "therapistTaxonomyCode":"SAMPLE_TEXT"
                , "scheduleTherapyDate":"2020-01-01T00:00:00.000Z"
                , "actualTherapyDate":"2020-01-01T00:00:00.000Z"
                , "therapyMode":"SAMPLE_TEXT"
                , "therapyStatus":"SAMPLE_TEXT"
                , "therapyNotes":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_deliveryTicket_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created deliveryTicket")
                .get("/services/salesorder${new_deliveryTicket_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created deliveryTicket")
            .delete("/services/salesorder${new_deliveryTicket_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
