import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the SalesOrderMaster entity.
 */
class SalesOrderMasterGatlingTest extends Simulation {

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

    val scn = scenario("Test the SalesOrderMaster entity")
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
            exec(http("Get all salesOrderMasters")
            .get("/services/salesorder/api/sales-order-masters")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new salesOrderMaster")
            .post("/services/salesorder/api/sales-order-masters")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "salesOrderNo":"SAMPLE_TEXT"
                , "patientId":"0"
                , "patientFirstName":"SAMPLE_TEXT"
                , "patientMiddleName":"SAMPLE_TEXT"
                , "patientLastName":"SAMPLE_TEXT"
                , "patientAddressLine1":"SAMPLE_TEXT"
                , "patientAddressLine2":"SAMPLE_TEXT"
                , "patientContactNo1":"SAMPLE_TEXT"
                , "patientContactNo2":"SAMPLE_TEXT"
                , "patientDob":"2020-01-01T00:00:00.000Z"
                , "patientHeight":"0"
                , "patientWeight":"0"
                , "patientSsn":"SAMPLE_TEXT"
                , "patientGender":"SAMPLE_TEXT"
                , "hipaaOnFileStatus":"SAMPLE_TEXT"
                , "patientBranchId":"0"
                , "branchName":"SAMPLE_TEXT"
                , "deliveryScheduleDatetime":"2020-01-01T00:00:00.000Z"
                , "deliveryActualDatetime":"2020-01-01T00:00:00.000Z"
                , "deliveryAddressLine1":"SAMPLE_TEXT"
                , "deliveryAddressLine2":"SAMPLE_TEXT"
                , "deliveryPhoneNo1":"SAMPLE_TEXT"
                , "deliveryPhoneNo2":"SAMPLE_TEXT"
                , "facilityId":"0"
                , "facilityName":"SAMPLE_TEXT"
                , "facilityNpi":"SAMPLE_TEXT"
                , "taxZoneId":"0"
                , "taxRate":"0"
                , "salesOrderNote":"SAMPLE_TEXT"
                , "deliveryNote":"SAMPLE_TEXT"
                , "deliveryTechnician":"SAMPLE_TEXT"
                , "signatureRequiredStatus":"SAMPLE_TEXT"
                , "podStatus":"SAMPLE_TEXT"
                , "podStatusDatetime":"2020-01-01T00:00:00.000Z"
                , "podLastMessage":"SAMPLE_TEXT"
                , "podMessageDatetime":"2020-01-01T00:00:00.000Z"
                , "mutualHoldStatus":"SAMPLE_TEXT"
                , "holdStatus":"SAMPLE_TEXT"
                , "holdReasonDescription":"SAMPLE_TEXT"
                , "stopDate":"2020-01-01T00:00:00.000Z"
                , "stopReasonDescription":"SAMPLE_TEXT"
                , "branchId":"0"
                , "billingBranchName":"SAMPLE_TEXT"
                , "inventoryLocationId":"0"
                , "orderStatus":"SAMPLE_TEXT"
                , "orderClassificationId":"0"
                , "posId":"0"
                , "admissionDate":"2020-01-01T00:00:00.000Z"
                , "dischargeDate":"2020-01-01T00:00:00.000Z"
                , "discountPercentage":"0"
                , "poNumber":"SAMPLE_TEXT"
                , "userField1":"SAMPLE_TEXT"
                , "userField2":"SAMPLE_TEXT"
                , "userField3":"SAMPLE_TEXT"
                , "userField4":"SAMPLE_TEXT"
                , "reference":"SAMPLE_TEXT"
                , "wipStatus":"SAMPLE_TEXT"
                , "wipDaysInState":"0"
                , "wipAssignedToId":"0"
                , "wipDateNeeded":"2020-01-01T00:00:00.000Z"
                , "completedStatus":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "cityName":"SAMPLE_TEXT"
                , "stateName":"SAMPLE_TEXT"
                , "zipCode":"SAMPLE_TEXT"
                , "deliveryCityName":"SAMPLE_TEXT"
                , "deliveryStateName":"SAMPLE_TEXT"
                , "deliveryZipCode":"SAMPLE_TEXT"
                , "patientDod":"2020-01-01T00:00:00.000Z"
                , "posName":"SAMPLE_TEXT"
                , "updatedById":"0"
                , "confirmationById":"0"
                , "confirmationByName":"SAMPLE_TEXT"
                , "confirmationDate":"2020-01-01T00:00:00.000Z"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedByName":"SAMPLE_TEXT"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderMasterUuid":null
                , "branchContactPersonName":"SAMPLE_TEXT"
                , "branchNpi":"SAMPLE_TEXT"
                , "branchEin":"SAMPLE_TEXT"
                , "branchContactNo1":"SAMPLE_TEXT"
                , "branchContactNo2":"SAMPLE_TEXT"
                , "patientIdNo":"SAMPLE_TEXT"
                , "posCode":"SAMPLE_TEXT"
                , "eclaimCarrierName":"SAMPLE_TEXT"
                , "planParticipationCode":"SAMPLE_TEXT"
                , "patientMemberId":"SAMPLE_TEXT"
                , "contactPersonName":"SAMPLE_TEXT"
                , "providerType":"SAMPLE_TEXT"
                , "branchAddressLine1":"SAMPLE_TEXT"
                , "branchAddressLine2":"SAMPLE_TEXT"
                , "branchCity":"SAMPLE_TEXT"
                , "branchState":"SAMPLE_TEXT"
                , "branchZipCode":"SAMPLE_TEXT"
                , "patientDeliveryAddressLine1":"SAMPLE_TEXT"
                , "patientDeliveryAddressLine2":"SAMPLE_TEXT"
                , "patientDeliveryCity":"SAMPLE_TEXT"
                , "patientDeliveryState":"SAMPLE_TEXT"
                , "patientDeliveryCountry":"SAMPLE_TEXT"
                , "patientDeliveryZip":"SAMPLE_TEXT"
                , "patientBillingAddressLine1":"SAMPLE_TEXT"
                , "patientBillingAddressLine2":"SAMPLE_TEXT"
                , "patientBillingCity":"SAMPLE_TEXT"
                , "patientBillingState":"SAMPLE_TEXT"
                , "patientBillingCountry":"SAMPLE_TEXT"
                , "patientBillingZip":"SAMPLE_TEXT"
                , "patientFax":"SAMPLE_TEXT"
                , "branchFax":"SAMPLE_TEXT"
                , "patientEmail":"SAMPLE_TEXT"
                , "relationship":"SAMPLE_TEXT"
                , "modeOfContact":"SAMPLE_TEXT"
                , "insuredFirstName":"SAMPLE_TEXT"
                , "insuredLastName":"SAMPLE_TEXT"
                , "insuredAddressLine1":"SAMPLE_TEXT"
                , "insuredAddressLine2":"SAMPLE_TEXT"
                , "insuredCity":"SAMPLE_TEXT"
                , "insuredState":"SAMPLE_TEXT"
                , "insuredZip":"SAMPLE_TEXT"
                , "insuredContactNo":"SAMPLE_TEXT"
                , "insuredGender":"SAMPLE_TEXT"
                , "patientRelationshipInsured":"SAMPLE_TEXT"
                , "patientConditionEmployment":"SAMPLE_TEXT"
                , "patientConditionAutoAccident":"SAMPLE_TEXT"
                , "patientConditionOtherAccident":"SAMPLE_TEXT"
                , "billingProviderTaxonomy":"SAMPLE_TEXT"
                , "billingProviderNpi":"SAMPLE_TEXT"
                , "billingProviderOrganisationName":"SAMPLE_TEXT"
                , "billingProviderAddressLine1":"SAMPLE_TEXT"
                , "billingProviderAddressLine2":"SAMPLE_TEXT"
                , "billingProviderCity":"SAMPLE_TEXT"
                , "billingProviderState":"SAMPLE_TEXT"
                , "billingProviderCountry":"SAMPLE_TEXT"
                , "billingProviderZipCode":"SAMPLE_TEXT"
                , "insuredDob":"2020-01-01T00:00:00.000Z"
                , "branchCountry":"SAMPLE_TEXT"
                , "branchTaxonomy":"SAMPLE_TEXT"
                , "primaryInsurerPriceTableId":"0"
                , "primaryInsurerPriceTableName":"SAMPLE_TEXT"
                , "inventoryLocationName":"SAMPLE_TEXT"
                , "soControlNumber":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_salesOrderMaster_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created salesOrderMaster")
                .get("/services/salesorder${new_salesOrderMaster_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created salesOrderMaster")
            .delete("/services/salesorder${new_salesOrderMaster_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
