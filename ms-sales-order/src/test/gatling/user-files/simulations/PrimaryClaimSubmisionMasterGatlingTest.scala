import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PrimaryClaimSubmisionMaster entity.
 */
class PrimaryClaimSubmisionMasterGatlingTest extends Simulation {

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

    val scn = scenario("Test the PrimaryClaimSubmisionMaster entity")
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
            exec(http("Get all primaryClaimSubmisionMasters")
            .get("/services/salesorder/api/primary-claim-submision-masters")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new primaryClaimSubmisionMaster")
            .post("/services/salesorder/api/primary-claim-submision-masters")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "salesOrderId":"0"
                , "claimControlNo":"SAMPLE_TEXT"
                , "tradingPartnerServiceId":"SAMPLE_TEXT"
                , "tradingPartnerName":"SAMPLE_TEXT"
                , "submitterOrganizationName":"SAMPLE_TEXT"
                , "submitterContactPersonName":"SAMPLE_TEXT"
                , "submitterContactNo":"SAMPLE_TEXT"
                , "receiverOrganizationName":"SAMPLE_TEXT"
                , "subscriberMemberIdNo":"SAMPLE_TEXT"
                , "subscriberPaymentResponsibilityLevelCode":"SAMPLE_TEXT"
                , "subscriberFirstName":"SAMPLE_TEXT"
                , "subscriberLastName":"SAMPLE_TEXT"
                , "subscriberGender":"SAMPLE_TEXT"
                , "subscriberDob":"2020-01-01T00:00:00.000Z"
                , "primaryInsurerPolicyNo":"SAMPLE_TEXT"
                , "subscriberAddressLine1":"SAMPLE_TEXT"
                , "subscriberCity":"SAMPLE_TEXT"
                , "subscriberState":"SAMPLE_TEXT"
                , "subscriberZipCode":"SAMPLE_TEXT"
                , "billingProviderNpi":"SAMPLE_TEXT"
                , "billingProviderEin":"SAMPLE_TEXT"
                , "billingProviderOrganizationName":"SAMPLE_TEXT"
                , "billingProviderAddressLine1":"SAMPLE_TEXT"
                , "billingProviderCity":"SAMPLE_TEXT"
                , "billingProviderState":"SAMPLE_TEXT"
                , "billingProviderZipCode":"SAMPLE_TEXT"
                , "billingProviderContactPersonName":"SAMPLE_TEXT"
                , "billingProviderContactNo":"SAMPLE_TEXT"
                , "claimFilingCode":"SAMPLE_TEXT"
                , "claimChargeAmount":"0"
                , "posCode":"SAMPLE_TEXT"
                , "claimFrequencyCode":"SAMPLE_TEXT"
                , "signatureIndicator":"SAMPLE_TEXT"
                , "planParticipationCode":"SAMPLE_TEXT"
                , "benefitsAssignmentCertificationIndicator":"SAMPLE_TEXT"
                , "releaseInformationCode":"SAMPLE_TEXT"
                , "primaryDiagnosis":"SAMPLE_TEXT"
                , "icd10DiagnosisCode1":"SAMPLE_TEXT"
                , "icd10DiagnosisCode2":"SAMPLE_TEXT"
                , "icd10DiagnosisCode3":"SAMPLE_TEXT"
                , "icd10DiagnosisCode4":"SAMPLE_TEXT"
                , "icd10DiagnosisCode5":"SAMPLE_TEXT"
                , "icd10DiagnosisCode6":"SAMPLE_TEXT"
                , "icd10DiagnosisCode7":"SAMPLE_TEXT"
                , "icd10DiagnosisCode8":"SAMPLE_TEXT"
                , "icd10DiagnosisCode9":"SAMPLE_TEXT"
                , "icd10DiagnosisCode10":"SAMPLE_TEXT"
                , "icd10DiagnosisCode11":"SAMPLE_TEXT"
                , "icd10DiagnosisCode12":"SAMPLE_TEXT"
                , "insertedById":"0"
                , "insertedDate":"2020-01-01T00:00:00.000Z"
                , "ipdatedById":"0"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "billingProviderType":"SAMPLE_TEXT"
                , "insertedByName":"SAMPLE_TEXT"
                , "updatedByName":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "billingProviderAddressLine2":"SAMPLE_TEXT"
                , "insuredFirstName":"SAMPLE_TEXT"
                , "insuredLastName":"SAMPLE_TEXT"
                , "insuredAddressLine1":"SAMPLE_TEXT"
                , "insuredAddressLine2":"SAMPLE_TEXT"
                , "insuredCity":"SAMPLE_TEXT"
                , "insuredState":"SAMPLE_TEXT"
                , "insuredZip":"SAMPLE_TEXT"
                , "insuredContactNo":"SAMPLE_TEXT"
                , "insuredDob":"2020-01-01T00:00:00.000Z"
                , "insuredGender":"SAMPLE_TEXT"
                , "orderingProviderFirstName":"SAMPLE_TEXT"
                , "orderingProviderLastName":"SAMPLE_TEXT"
                , "orderingProviderNpi":"SAMPLE_TEXT"
                , "patientRelationshipInsured":"SAMPLE_TEXT"
                , "patientConditionEmployment":"SAMPLE_TEXT"
                , "patientConditionAutoAccident":"SAMPLE_TEXT"
                , "patientConditionOtherAccident":"SAMPLE_TEXT"
                , "isNextLevelInsurerPresentStatus":"SAMPLE_TEXT"
                , "originalDos":"2020-01-01T00:00:00.000Z"
                , "parNo":"SAMPLE_TEXT"
                , "billingProviderTaxonomy":"SAMPLE_TEXT"
                , "serviceProviderNpi":"SAMPLE_TEXT"
                , "serviceProviderOrganisationName":"SAMPLE_TEXT"
                , "serviceProviderAddressLine1":"SAMPLE_TEXT"
                , "serviceProviderAddressLine2":"SAMPLE_TEXT"
                , "serviceProviderCity":"SAMPLE_TEXT"
                , "serviceProviderState":"SAMPLE_TEXT"
                , "serviceProviderCountry":"SAMPLE_TEXT"
                , "serviceProviderZipCode":"SAMPLE_TEXT"
                , "serviceProviderTaxonomy":"SAMPLE_TEXT"
                , "cms1500FormName":"SAMPLE_TEXT"
                , "tradingPartnerAddressLine1":"SAMPLE_TEXT"
                , "tradingPartnerAddressLine2":"SAMPLE_TEXT"
                , "tradingPatnerCity":"SAMPLE_TEXT"
                , "tradingPartnerState":"SAMPLE_TEXT"
                , "tradingPartnerZip":"SAMPLE_TEXT"
                , "diagnosisCodeType":"SAMPLE_TEXT"
                , "primaryClaimSubmisionMasterUuid":null
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_primaryClaimSubmisionMaster_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created primaryClaimSubmisionMaster")
                .get("/services/salesorder${new_primaryClaimSubmisionMaster_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created primaryClaimSubmisionMaster")
            .delete("/services/salesorder${new_primaryClaimSubmisionMaster_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
