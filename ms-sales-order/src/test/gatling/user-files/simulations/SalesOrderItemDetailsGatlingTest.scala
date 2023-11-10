import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the SalesOrderItemDetails entity.
 */
class SalesOrderItemDetailsGatlingTest extends Simulation {

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

    val scn = scenario("Test the SalesOrderItemDetails entity")
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
            exec(http("Get all salesOrderItemDetails")
            .get("/services/salesorder/api/sales-order-item-details")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new salesOrderItemDetails")
            .post("/services/salesorder/api/sales-order-item-details")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "salesOrderId":"0"
                , "patientId":"0"
                , "itemLocationId":"0"
                , "salesOrderDetailsItemId":"0"
                , "salesOrderDetailsItemName":"SAMPLE_TEXT"
                , "salesOrderDetailsStockingUom":"SAMPLE_TEXT"
                , "itemAssetNo":"SAMPLE_TEXT"
                , "salesOrderDetailsItemDescription":"SAMPLE_TEXT"
                , "salesOrderDetailsDefaultVendor":"SAMPLE_TEXT"
                , "salesOrderDetailsOriginalDos":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsPreviousBillingDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsNextBillingDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsDosTo":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsNextPeriod":"SAMPLE_TEXT"
                , "salesOrderDetailsSpecialPricing":"SAMPLE_TEXT"
                , "salesOrderDetailsPriceOverride":"SAMPLE_TEXT"
                , "salesOrderDetailsSpecialTaxRate":"0"
                , "salesOrderDetailsQty":"0"
                , "salesOrderDetailsBqty":"0"
                , "salesOrderDetailsLineQty":"0"
                , "salesOrderDetailsProcCode":"SAMPLE_TEXT"
                , "salesOrderDetailsPriceOption":"SAMPLE_TEXT"
                , "salesOrderDetailsModifier1":"SAMPLE_TEXT"
                , "salesOrderDetailsModifier2":"SAMPLE_TEXT"
                , "salesOrderDetailsModifier3":"SAMPLE_TEXT"
                , "salesOrderDetailsModifier4":"SAMPLE_TEXT"
                , "salesOrderDetailsChargeAmt":"0"
                , "salesOrderDetailsAllowedAmt":"0"
                , "salesOrderDetailsTaxable":"SAMPLE_TEXT"
                , "salesOrderDetailsAbn":"SAMPLE_TEXT"
                , "salesOrderDetailsAbnUpgrade":"SAMPLE_TEXT"
                , "salesOrderDetailsAbnPrintDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsAbnItem":"SAMPLE_TEXT"
                , "salesOrderDetailsAbnProcCode":"SAMPLE_TEXT"
                , "salesOrderDetailsAbnAllow":"SAMPLE_TEXT"
                , "salesOrderDetailsAbnCharge":"0"
                , "salesOrderDetailsAbnModifier1":"SAMPLE_TEXT"
                , "salesOrderDetailsAbnModifier2":"SAMPLE_TEXT"
                , "salesOrderDetailsTaxRate":"0"
                , "salesOrderDetailsTaxZone":"SAMPLE_TEXT"
                , "salesOrderDetailsNonTaxReason":"SAMPLE_TEXT"
                , "salesOrderDetailsNote":"SAMPLE_TEXT"
                , "salesOrderDetailsSaleType":"SAMPLE_TEXT"
                , "salesOrderDetailsItemGroup":"SAMPLE_TEXT"
                , "salesOrderDetailsItemUser1":"SAMPLE_TEXT"
                , "salesOrderDetailsItemUser2":"SAMPLE_TEXT"
                , "salesOrderDetailsItemUser3":"SAMPLE_TEXT"
                , "salesOrderDetailsItemUser4":"SAMPLE_TEXT"
                , "salesOrderDetailsConvertedToPurchase":"SAMPLE_TEXT"
                , "salesOrderDetailsManualConvertToPurchaseMctp":"SAMPLE_TEXT"
                , "salesOrderDetailsMctpChargeAmt":"0"
                , "salesOrderDetailsMctpAllowedAmt":"0"
                , "salesOrderDetailsMctpModifier1":"SAMPLE_TEXT"
                , "salesOrderDetailsMctpModifier2":"SAMPLE_TEXT"
                , "salesOrderDetailsMctpModifier3":"SAMPLE_TEXT"
                , "salesOrderDetailsMctpModifier4":"SAMPLE_TEXT"
                , "salesOrderDetailsMctpPeriod":"0"
                , "salesOrderDetailsAddtlModifier1":"SAMPLE_TEXT"
                , "salesOrderDetailsAddtlModifier2":"SAMPLE_TEXT"
                , "salesOrderDetailsAddtlModifier3":"SAMPLE_TEXT"
                , "salesOrderDetailsAddtlModifier4":"SAMPLE_TEXT"
                , "salesOrderDetailsNextDateOfService":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsPriceTable":"SAMPLE_TEXT"
                , "salesOrderDetailsPriceOptionName":"SAMPLE_TEXT"
                , "salesOrderDetailsExtendedChargeAmount":"0"
                , "salesOrderDetailsExtendedAllowanceAmount":"0"
                , "salesOrderDetailsItemNdcCode":"SAMPLE_TEXT"
                , "salesOrderDetailsManufacturer":"SAMPLE_TEXT"
                , "salesOrderDetailsCbPricing":"SAMPLE_TEXT"
                , "salesOrderDetailsCbPriceTableOverride":"SAMPLE_TEXT"
                , "salesOrderDetailsCbOverride":"SAMPLE_TEXT"
                , "salesOrderDetailsMessages":"SAMPLE_TEXT"
                , "salesOrderDetailsLocation":"0"
                , "salesOrderDetailsCaloriesPerDay":"0"
                , "salesOrderDetailsSecondaryBillingProcudureCode":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingPriceOption":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingPriceOptionName":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingModifier1":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingModifier2":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingModifier3":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingModifier4":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingAdditionalModifier1":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingAdditionalModifier2":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingAdditionalModifier3":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingAdditionalModifier4":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondaryBillingIgnore":"SAMPLE_TEXT"
                , "salesOrderDetailsSecondarySpecialBilling":"SAMPLE_TEXT"
                , "salesOrderDetailsSpanDateSplitBilling":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparCmnFormId":"0"
                , "salesOrderDetailsCmnparCmnKey":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparCmnCreateDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnExpirationDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnInitialDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnRenewalDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnRecertificationDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnPhysicianDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnStatus":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparParId":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparParDescr":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparParInitialDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparParExpirationDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnLogDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnLengthOfNeed":"0"
                , "salesOrderDetailsCmnparCmnPrintedDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnPrintedBy":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparFaxedDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsCmnparCmnPlaceholder":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparCmnFaxedBy":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparCmnLoggedBy":"SAMPLE_TEXT"
                , "salesOrderDetailsCmnparNumberOfRefills":"0"
                , "createdById":"0"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "status":"SAMPLE_TEXT"
                , "updatedById":"0"
                , "updatedDate":"2020-01-01T00:00:00.000Z"
                , "salesOrderDetailsManufacturerItemIdNumber":"SAMPLE_TEXT"
                , "cmnId":"0"
                , "createdByName":"SAMPLE_TEXT"
                , "updatedByName":"SAMPLE_TEXT"
                , "salesOrderItemDetailsUuid":null
                , "salesOrderItemNumber":"SAMPLE_TEXT"
                , "isAssetTagged":"SAMPLE_TEXT"
                , "itemSerialNo":"0"
                , "salesOrderDetailsIcdPointer":"SAMPLE_TEXT"
                , "procedureIdentifier":"SAMPLE_TEXT"
                , "parNo":"SAMPLE_TEXT"
                , "whetherSerialised":"SAMPLE_TEXT"
                , "pickupExchangeNo":"SAMPLE_TEXT"
                , "salesOrderAbnUserResponse":"SAMPLE_TEXT"
                , "isDropshipAllowed":"SAMPLE_TEXT"
                , "poNumber":"SAMPLE_TEXT"
                , "purchaseOrderUuid":null
                , "isResupplyType":"SAMPLE_TEXT"
                , "frequencyCount":"0"
                , "frequencyInterval":"0"
                , "itemGroupId":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_salesOrderItemDetails_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created salesOrderItemDetails")
                .get("/services/salesorder${new_salesOrderItemDetails_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created salesOrderItemDetails")
            .delete("/services/salesorder${new_salesOrderItemDetails_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
