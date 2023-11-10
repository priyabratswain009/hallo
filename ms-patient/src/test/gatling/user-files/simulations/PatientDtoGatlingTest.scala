import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PatientDto entity.
 */
class PatientDtoGatlingTest extends Simulation {

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

    val scn = scenario("Test the PatientDto entity")
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
            exec(http("Get all patientDtos")
            .get("/services/patient/api/patient-dtos")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new patientDto")
            .post("/services/patient/api/patient-dtos")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "patientId":"0"
                , "patientFirstName":"SAMPLE_TEXT"
                , "patientMiddleName":"SAMPLE_TEXT"
                , "patientLastName":"SAMPLE_TEXT"
                , "dob":"2020-01-01T00:00:00.000Z"
                , "gender":"SAMPLE_TEXT"
                , "ssn":"SAMPLE_TEXT"
                , "taxZoneId":"0"
                , "taxZoneName":"SAMPLE_TEXT"
                , "taxRate":"0"
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
                , "branchName":"SAMPLE_TEXT"
                , "billingAddressLine1":"SAMPLE_TEXT"
                , "billingAddressLine2":"SAMPLE_TEXT"
                , "billingAddressCity":"SAMPLE_TEXT"
                , "billingAddressState":"SAMPLE_TEXT"
                , "billingAddressZip":"SAMPLE_TEXT"
                , "caregiverName":"SAMPLE_TEXT"
                , "caregiverContact":"SAMPLE_TEXT"
                , "caregiverRelatinshipPatient":"SAMPLE_TEXT"
                , "primaryInsuranceId":"0"
                , "primaryInsuranceName":"SAMPLE_TEXT"
                , "primaryInsurancePayerIdNo":"SAMPLE_TEXT"
                , "primaryInsurancePayerContactNo":"SAMPLE_TEXT"
                , "primaryInsurancePolicyNum":"SAMPLE_TEXT"
                , "primaryInsurancePolicyGroupNum":"SAMPLE_TEXT"
                , "primaryInsurancePolicyGroupId":"0"
                , "primaryInsurancePolicyStartDate":"2020-01-01T00:00:00.000Z"
                , "primaryInsurancePolicyEndDate":"2020-01-01T00:00:00.000Z"
                , "primaryInsurancePayPercentage":"0"
                , "primaryInsuranceDeductableAmt":"0"
                , "secondaryInsuranceId":"0"
                , "secondaryInsuranceName":"SAMPLE_TEXT"
                , "secondaryInsurancePayerIdNo":"SAMPLE_TEXT"
                , "secondaryInsurancePayerContactNo":"SAMPLE_TEXT"
                , "secondaryInsurancePolicyNum":"SAMPLE_TEXT"
                , "secondaryInsurancePolicyGroupNum":"SAMPLE_TEXT"
                , "secondaryInsurancePolicyGroupId":"0"
                , "secondaryInsurancePolicyStartDate":"2020-01-01T00:00:00.000Z"
                , "secondaryInsurancePolicyEndDate":"2020-01-01T00:00:00.000Z"
                , "secondaryInsurancePayPercentage":"0"
                , "secondaryInsuranceDeductableAmt":"0"
                , "tertiaryInsuranceId":"0"
                , "tertiaryInsuranceName":"SAMPLE_TEXT"
                , "tertiaryInsurancePayerIdNo":"SAMPLE_TEXT"
                , "tertiaryInsurancePayerContactNo":"SAMPLE_TEXT"
                , "tertiaryInsurancePolicyNum":"SAMPLE_TEXT"
                , "tertiaryInsurancePolicyGroupNum":"SAMPLE_TEXT"
                , "tertiaryInsurancePolicyGroupId":"0"
                , "tertiaryInsurancePolicyStartDate":"2020-01-01T00:00:00.000Z"
                , "tertiaryInsurancePolicyEndDate":"2020-01-01T00:00:00.000Z"
                , "tertiaryInsurancePayPercentage":"0"
                , "tertiaryInsuranceDeductableAmt":"0"
                , "relationship":"SAMPLE_TEXT"
                , "insuredFirstName":"SAMPLE_TEXT"
                , "insuredMiddleName":"SAMPLE_TEXT"
                , "insuredLastName":"SAMPLE_TEXT"
                , "insuredSuffix":"SAMPLE_TEXT"
                , "insuredDob":"2020-01-01T00:00:00.000Z"
                , "insuredSsn":"SAMPLE_TEXT"
                , "insuredGender":"SAMPLE_TEXT"
                , "primaryInsurerAddressLine1":"SAMPLE_TEXT"
                , "primaryInsurerAddressLine2":"SAMPLE_TEXT"
                , "primaryInsurerCity":"SAMPLE_TEXT"
                , "primaryInsurerState":"SAMPLE_TEXT"
                , "primaryInsurerZip":"SAMPLE_TEXT"
                , "primaryInsurerContact1":"SAMPLE_TEXT"
                , "primaryInsurerFax":"SAMPLE_TEXT"
                , "alwaysCrossoverStatus":"SAMPLE_TEXT"
                , "primaryInsuranceMemberId":"SAMPLE_TEXT"
                , "secondaryInsuranceMemberId":"SAMPLE_TEXT"
                , "tertiaryInsuranceMemberId":"SAMPLE_TEXT"
                , "patientRelationshipInsured":"SAMPLE_TEXT"
                , "patientConditionEmployment":"SAMPLE_TEXT"
                , "patientConditionAutoAccident":"SAMPLE_TEXT"
                , "patientConditionOtherAccident":"SAMPLE_TEXT"
                , "insuredEmployer":"SAMPLE_TEXT"
                , "workersCompensationPayerIdNumber":"SAMPLE_TEXT"
                , "workersCompensationPlanName":"SAMPLE_TEXT"
                , "workersCompensationAdditionalDtls":"SAMPLE_TEXT"
                , "workersCompensationClaimFillingCode":"SAMPLE_TEXT"
                , "workersCompensationTplCode":"SAMPLE_TEXT"
                , "workersCompensationTplName":"SAMPLE_TEXT"
                , "wcPropertyCasualtyAgencyClaimNo":"SAMPLE_TEXT"
                , "wcCarrierId":"SAMPLE_TEXT"
                , "employerAddressLine1":"SAMPLE_TEXT"
                , "employerAddressLine2":"SAMPLE_TEXT"
                , "employerCity":"SAMPLE_TEXT"
                , "employerState":"SAMPLE_TEXT"
                , "employerCountry":"SAMPLE_TEXT"
                , "employerZip":"SAMPLE_TEXT"
                , "employerContactNo1":"SAMPLE_TEXT"
                , "employerContactNo2":"SAMPLE_TEXT"
                , "employerFax":"SAMPLE_TEXT"
                , "employerEfax":"SAMPLE_TEXT"
                , "employerEmail":"SAMPLE_TEXT"
                , "employeeRelationship":"SAMPLE_TEXT"
                , "height":"0"
                , "weight":"0"
                , "functionalAbilities":"SAMPLE_TEXT"
                , "infectionConditionStatus":"SAMPLE_TEXT"
                , "diabetesStatus":"SAMPLE_TEXT"
                , "diagnosisCodeType":"SAMPLE_TEXT"
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
                , "doctorFirstName":"SAMPLE_TEXT"
                , "doctorMiddleName":"SAMPLE_TEXT"
                , "doctorLastName":"SAMPLE_TEXT"
                , "doctorNameSuffix":"SAMPLE_TEXT"
                , "doctorAddressLine1":"SAMPLE_TEXT"
                , "doctorAddressLine2":"SAMPLE_TEXT"
                , "doctorAddressCity":"SAMPLE_TEXT"
                , "doctorAddressState":"SAMPLE_TEXT"
                , "doctorAddressZip":"SAMPLE_TEXT"
                , "doctorContact1":"SAMPLE_TEXT"
                , "doctorContact2":"SAMPLE_TEXT"
                , "doctorFax":"SAMPLE_TEXT"
                , "doctorNpiNumber":"SAMPLE_TEXT"
                , "doctorGender":"SAMPLE_TEXT"
                , "doctorTaxonomyCode":"SAMPLE_TEXT"
                , "doctorTaxonomyDescription":"SAMPLE_TEXT"
                , "doctorPracticeState":"SAMPLE_TEXT"
                , "doctorLicenseNumber":"SAMPLE_TEXT"
                , "status":"SAMPLE_TEXT"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "createdById":"0"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "updatedById":"0"
                , "updatedByName":"SAMPLE_TEXT"
                , "patientDtoUuid":null
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
                , "patientDod":"2020-01-01T00:00:00.000Z"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_patientDto_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created patientDto")
                .get("/services/patient${new_patientDto_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created patientDto")
            .delete("/services/patient${new_patientDto_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
