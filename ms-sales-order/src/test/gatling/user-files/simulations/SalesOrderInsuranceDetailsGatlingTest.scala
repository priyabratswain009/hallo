import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the SalesOrderInsuranceDetails entity.
 */
class SalesOrderInsuranceDetailsGatlingTest extends Simulation {

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

    val scn = scenario("Test the SalesOrderInsuranceDetails entity")
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
            exec(http("Get all salesOrderInsuranceDetails")
            .get("/services/salesorder/api/sales-order-insurance-details")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new salesOrderInsuranceDetails")
            .post("/services/salesorder/api/sales-order-insurance-details")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "salesOrderId":"0"
                , "patientId":"0"
                , "primaryInsurerId":"0"
                , "primaryInsurerName":"SAMPLE_TEXT"
                , "primaryInsurerPolicyNo":"SAMPLE_TEXT"
                , "primaryInsurerPatientIdNumber":"SAMPLE_TEXT"
                , "primaryInsurerEffectiveDate":"2020-01-01T00:00:00.000Z"
                , "primaryInsurerVerificationStatus":"SAMPLE_TEXT"
                , "primaryInsurerVerificationDate":"2020-01-01T00:00:00.000Z"
                , "primaryInsurerPayPercentage":"0"
                , "primaryBox10D":"SAMPLE_TEXT"
                , "primaryBox19":"SAMPLE_TEXT"
                , "primaryBox24Ia":"SAMPLE_TEXT"
                , "primaryBox24Ja":"SAMPLE_TEXT"
                , "primaryBox24Jb":"SAMPLE_TEXT"
                , "primaryIncludeBox24Jbstatus":"SAMPLE_TEXT"
                , "primaryIncludePayerSalesOrderStatus":"SAMPLE_TEXT"
                , "primaryWaitForPreviousPayerBeforeBillingStatus":"SAMPLE_TEXT"
                , "primaryPayPercentageStatus":"SAMPLE_TEXT"
                , "secondaryInsurerId":"0"
                , "secondaryInsurerName":"SAMPLE_TEXT"
                , "secondaryInsurerPolicyNo":"SAMPLE_TEXT"
                , "secondaryInsurerPatientIdNumber":"SAMPLE_TEXT"
                , "secondaryInsurerEffectiveDate":"2020-01-01T00:00:00.000Z"
                , "secondaryInsurerVerificationStatus":"SAMPLE_TEXT"
                , "secondaryInsurerVerificationDate":"2020-01-01T00:00:00.000Z"
                , "secondaryInsurerPayPercentage":"0"
                , "secondaryBox10D":"SAMPLE_TEXT"
                , "secondaryBox19":"SAMPLE_TEXT"
                , "secondaryBox24Ia":"SAMPLE_TEXT"
                , "secondaryBox24Ja":"SAMPLE_TEXT"
                , "secondaryBox24Jb":"SAMPLE_TEXT"
                , "secondaryIncludeBox24JbStatus":"SAMPLE_TEXT"
                , "secondaryIncludePayerSalesOrderStatus":"SAMPLE_TEXT"
                , "secondaryWaitPreviousPayerBefrBillingStatus":"SAMPLE_TEXT"
                , "secondaryPayPercentageStatus":"SAMPLE_TEXT"
                , "tertiaryInsurerId":"0"
                , "tertiaryInsurerName":"SAMPLE_TEXT"
                , "tertiaryInsurerPolicyno":"SAMPLE_TEXT"
                , "tertiaryInsurerPatientIdNumber":"SAMPLE_TEXT"
                , "tertiaryInsurerEffectiveDate":"2020-01-01T00:00:00.000Z"
                , "tertiaryInsurerVerificationStatus":"SAMPLE_TEXT"
                , "tertiaryInsurerVerificationDate":"2020-01-01T00:00:00.000Z"
                , "tertiaryInsurerPayPercentage":"0"
                , "tertiaryBox10D":"SAMPLE_TEXT"
                , "tertiaryBox19":"SAMPLE_TEXT"
                , "tertiaryBox24Ia":"SAMPLE_TEXT"
                , "tertiaryBox24Ja":"SAMPLE_TEXT"
                , "tertiaryBox24Jb":"SAMPLE_TEXT"
                , "tertiaryIncludeBox24JbStatus":"SAMPLE_TEXT"
                , "tertiaryIncludePayerInSalesOrderStatus":"SAMPLE_TEXT"
                , "tertiaryWaitPreviousPayerBeforeBillingStatus":"SAMPLE_TEXT"
                , "tertiaryPayPercentageStatus":"SAMPLE_TEXT"
                , "insuranceVerificationStatus":"SAMPLE_TEXT"
                , "coverageVerificationStatus":"SAMPLE_TEXT"
                , "excludeFromEligibilityCheckStatus":"SAMPLE_TEXT"
                , "patientPayPercentage":"0"
                , "patientIncludeThisPayorInSalesOrderStatus":"SAMPLE_TEXT"
                , "patientWaitForPreviousPayerBeforeBillingStatus":"SAMPLE_TEXT"
                , "workersCompDateOfOnset":"2020-01-01T00:00:00.000Z"
                , "workersCompInjuryRelatedEmploymentStatus":"SAMPLE_TEXT"
                , "workersCompInjuryRelatedAutoAccidentStatus":"SAMPLE_TEXT"
                , "workersCompAutoAccidentStateCode":"SAMPLE_TEXT"
                , "workersCompInjuryRelatedToOtherAccidentStatus":"SAMPLE_TEXT"
                , "eclaimsAttachmentStatus":"SAMPLE_TEXT"
                , "attachmentNumber":"0"
                , "typeCode":"SAMPLE_TEXT"
                , "transactionCode":"SAMPLE_TEXT"
                , "claimsNoteType":"SAMPLE_TEXT"
                , "claimsNote":"SAMPLE_TEXT"
                , "createdById":"0"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "status":"SAMPLE_TEXT"
                , "updatedById":"0"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderNo":"SAMPLE_TEXT"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedByName":"SAMPLE_TEXT"
                , "primaryInsuranceGroupId":"0"
                , "primaryInsuranceGroupName":"SAMPLE_TEXT"
                , "secondaryInsuranceGroupId":"0"
                , "secondaryInsuranceGroupName":"SAMPLE_TEXT"
                , "tertiaryInsuranceGroupId":"0"
                , "tertiaryInsuranceGroupName":"SAMPLE_TEXT"
                , "primaryInsurancePlanId":"0"
                , "primaryInsurancePlanType":"SAMPLE_TEXT"
                , "secondaryInsurancePlanId":"0"
                , "secondaryInsurancePlanType":"SAMPLE_TEXT"
                , "tertiaryInsurancePlanId":"0"
                , "tertiaryInsurancePlanType":"SAMPLE_TEXT"
                , "salesOrderInsuranceDetailsUuid":null
                , "primaryInsurancePayerId":"SAMPLE_TEXT"
                , "secondaryInsurancePayerId":"SAMPLE_TEXT"
                , "tertiaryInsurancePayerId":"SAMPLE_TEXT"
                , "primaryInsuranceIndicatorCode":"SAMPLE_TEXT"
                , "secondaryInsuranceIndicatorCode":"SAMPLE_TEXT"
                , "tertiaryInsuranceIndicatorCode":"SAMPLE_TEXT"
                , "priceTableId":"0"
                , "priceTableName":"SAMPLE_TEXT"
                , "primaryInsurerAddressLine1":"SAMPLE_TEXT"
                , "primaryInsurerAddressLine2":"SAMPLE_TEXT"
                , "primaryInsurerCity":"SAMPLE_TEXT"
                , "primaryInsurerState":"SAMPLE_TEXT"
                , "primaryInsurerZip":"SAMPLE_TEXT"
                , "primaryInsurerContact1":"SAMPLE_TEXT"
                , "primaryInsurerFax":"SAMPLE_TEXT"
                , "secondaryInsurerAddressLine1":"SAMPLE_TEXT"
                , "secondaryInsurerAddressLine2":"SAMPLE_TEXT"
                , "secondaryInsurerCity":"SAMPLE_TEXT"
                , "secondaryInsurerState":"SAMPLE_TEXT"
                , "secondaryInsurerZip":"SAMPLE_TEXT"
                , "secondaryInsurerContact1":"SAMPLE_TEXT"
                , "secondaryInsurerFax":"SAMPLE_TEXT"
                , "tertiaryInsurerAddressLine1":"SAMPLE_TEXT"
                , "tertiaryInsurerAddressLine2":"SAMPLE_TEXT"
                , "tertiaryInsurerCity":"SAMPLE_TEXT"
                , "tertiaryInsurerState":"SAMPLE_TEXT"
                , "tertiaryInsurerZip":"SAMPLE_TEXT"
                , "tertiaryInsurerContact1":"SAMPLE_TEXT"
                , "tertiaryInsurerFax":"SAMPLE_TEXT"
                , "primaryInsurerPolicyEndDate":"2020-01-01T00:00:00.000Z"
                , "primaryInsurerContactName":"SAMPLE_TEXT"
                , "primaryClaimProgram":"SAMPLE_TEXT"
                , "secondaryClaimProgram":"SAMPLE_TEXT"
                , "tertiaryClaimProgram":"SAMPLE_TEXT"
                , "workersCompInsuredEmployer":"SAMPLE_TEXT"
                , "workersCompPayerIdNumber":"SAMPLE_TEXT"
                , "workersCompPlanName":"SAMPLE_TEXT"
                , "workersCompAdditionalDtls":"SAMPLE_TEXT"
                , "workersCompClaimFillingCode":"SAMPLE_TEXT"
                , "workersCompTplCode":"SAMPLE_TEXT"
                , "workersCompTplName":"SAMPLE_TEXT"
                , "workersCompPropertyCasualtyAgencyClaimNo":"SAMPLE_TEXT"
                , "workersCompCarrierId":"SAMPLE_TEXT"
                , "workersCompEmployerAddressLine1":"SAMPLE_TEXT"
                , "workersCompEmployerAddressLine2":"SAMPLE_TEXT"
                , "workersCompEmployerCity":"SAMPLE_TEXT"
                , "workersCompEmployerState":"SAMPLE_TEXT"
                , "workersCompEmployerCountry":"SAMPLE_TEXT"
                , "workersCompEmployerZip":"SAMPLE_TEXT"
                , "workersCompEmployerContactNo1":"SAMPLE_TEXT"
                , "workersCompEmployerContactNo2":"SAMPLE_TEXT"
                , "workersCompEmployerFax":"SAMPLE_TEXT"
                , "workersCompEmployerEfax":"SAMPLE_TEXT"
                , "workersCompEmployerEmail":"SAMPLE_TEXT"
                , "workersCompRelationship":"SAMPLE_TEXT"
                , "workersCompModeOfContact":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_salesOrderInsuranceDetails_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created salesOrderInsuranceDetails")
                .get("/services/salesorder${new_salesOrderInsuranceDetails_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created salesOrderInsuranceDetails")
            .delete("/services/salesorder${new_salesOrderInsuranceDetails_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
