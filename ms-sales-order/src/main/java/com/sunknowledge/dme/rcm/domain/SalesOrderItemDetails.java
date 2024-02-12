package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderItemDetails.
 */
@Table("t_sales_order_item_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderItemDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("sales_order_item_details_id")
    private Long salesOrderItemDetailsId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("patient_id")
    private Long patientId;

    @Column("item_location_id")
    private Long itemLocationId;

    @Column("sales_order_details_item_id")
    private Long salesOrderDetailsItemId;

    @Column("sales_order_details_item_name")
    private String salesOrderDetailsItemName;

    @Column("sales_order_details_stocking_uom")
    private String salesOrderDetailsStockingUom;

    @Column("item_asset_no")
    private String itemAssetNo;

    @Column("sales_order_details_item_description")
    private String salesOrderDetailsItemDescription;

    @Column("sales_order_details_default_vendor")
    private String salesOrderDetailsDefaultVendor;

    @Column("sales_order_details_original_dos")
    private LocalDate salesOrderDetailsOriginalDos;

    @Column("sales_order_details_previous_billing_date")
    private LocalDate salesOrderDetailsPreviousBillingDate;

    @Column("sales_order_details_next_billing_date")
    private LocalDate salesOrderDetailsNextBillingDate;

    @Column("sales_order_details_dos_to")
    private LocalDate salesOrderDetailsDosTo;

    @Column("sales_order_details_next_period")
    private String salesOrderDetailsNextPeriod;

    @Column("sales_order_details_special_pricing")
    private String salesOrderDetailsSpecialPricing;

    @Column("sales_order_details_price_override")
    private String salesOrderDetailsPriceOverride;

    @Column("sales_order_details_special_tax_rate")
    private Long salesOrderDetailsSpecialTaxRate;

    @Column("sales_order_details_qty")
    private Long salesOrderDetailsQty;

    @Column("sales_order_details_bqty")
    private Long salesOrderDetailsBqty;

    @Column("sales_order_details_line_qty")
    private Long salesOrderDetailsLineQty;

    @Column("sales_order_details_proc_code")
    private String salesOrderDetailsProcCode;

    @Column("sales_order_details_price_option")
    private String salesOrderDetailsPriceOption;

    @Column("sales_order_details_modifier_1")
    private String salesOrderDetailsModifier1;

    @Column("sales_order_details_modifier_2")
    private String salesOrderDetailsModifier2;

    @Column("sales_order_details_modifier_3")
    private String salesOrderDetailsModifier3;

    @Column("sales_order_details_modifier_4")
    private String salesOrderDetailsModifier4;

    @Column("sales_order_details_charge_amt")
    private Double salesOrderDetailsChargeAmt;

    @Column("sales_order_details_allowed_amt")
    private Double salesOrderDetailsAllowedAmt;

    @Column("sales_order_details_taxable")
    private String salesOrderDetailsTaxable;

    @Column("sales_order_details_abn")
    private String salesOrderDetailsAbn;

    @Column("sales_order_details_abn_upgrade")
    private String salesOrderDetailsAbnUpgrade;

    @Column("sales_order_details_abn_print_date")
    private LocalDate salesOrderDetailsAbnPrintDate;

    @Column("sales_order_details_abn_item")
    private String salesOrderDetailsAbnItem;

    @Column("sales_order_details_abn_proc_code")
    private String salesOrderDetailsAbnProcCode;

    @Column("sales_order_details_abn_allow")
    private String salesOrderDetailsAbnAllow;

    @Column("sales_order_details_abn_charge")
    private Double salesOrderDetailsAbnCharge;

    @Column("sales_order_details_abn_modifier_1")
    private String salesOrderDetailsAbnModifier1;

    @Column("sales_order_details_abn_modifier_2")
    private String salesOrderDetailsAbnModifier2;

    @Column("sales_order_details_tax_rate")
    private Long salesOrderDetailsTaxRate;

    @Column("sales_order_details_tax_zone")
    private String salesOrderDetailsTaxZone;

    @Column("sales_order_details_non_tax_reason")
    private String salesOrderDetailsNonTaxReason;

    @Column("sales_order_details_note")
    private String salesOrderDetailsNote;

    @Column("sales_order_details_sale_type")
    private String salesOrderDetailsSaleType;

    @Column("sales_order_details_item_group")
    private String salesOrderDetailsItemGroup;

    @Column("sales_order_details_item_user_1")
    private String salesOrderDetailsItemUser1;

    @Column("sales_order_details_item_user_2")
    private String salesOrderDetailsItemUser2;

    @Column("sales_order_details_item_user_3")
    private String salesOrderDetailsItemUser3;

    @Column("sales_order_details_item_user_4")
    private String salesOrderDetailsItemUser4;

    @Column("sales_order_details_converted_to_purchase")
    private String salesOrderDetailsConvertedToPurchase;

    @Column("sales_order_details_manual_convert_to_purchase_mctp")
    private String salesOrderDetailsManualConvertToPurchaseMctp;

    @Column("sales_order_details_mctp_charge_amt")
    private Double salesOrderDetailsMctpChargeAmt;

    @Column("sales_order_details_mctp_allowed_amt")
    private Double salesOrderDetailsMctpAllowedAmt;

    @Column("sales_order_details_mctp_modifier_1")
    private String salesOrderDetailsMctpModifier1;

    @Column("sales_order_details_mctp_modifier_2")
    private String salesOrderDetailsMctpModifier2;

    @Column("sales_order_details_mctp_modifier_3")
    private String salesOrderDetailsMctpModifier3;

    @Column("sales_order_details_mctp_modifier_4")
    private String salesOrderDetailsMctpModifier4;

    @Column("sales_order_details_mctp_period")
    private Long salesOrderDetailsMctpPeriod;

    @Column("sales_order_details_addtl_modifier_1")
    private String salesOrderDetailsAddtlModifier1;

    @Column("sales_order_details_addtl_modifier_2")
    private String salesOrderDetailsAddtlModifier2;

    @Column("sales_order_details_addtl_modifier_3")
    private String salesOrderDetailsAddtlModifier3;

    @Column("sales_order_details_addtl_modifier_4")
    private String salesOrderDetailsAddtlModifier4;

    @Column("sales_order_details_next_date_of_service")
    private LocalDate salesOrderDetailsNextDateOfService;

    @Column("sales_order_details_price_table")
    private String salesOrderDetailsPriceTable;

    @Column("sales_order_details_price_option_name")
    private String salesOrderDetailsPriceOptionName;

    @Column("sales_order_details_extended_charge_amount")
    private Double salesOrderDetailsExtendedChargeAmount;

    @Column("sales_order_details_extended_allowance_amount")
    private Double salesOrderDetailsExtendedAllowanceAmount;

    @Column("sales_order_details_item_ndc_code")
    private String salesOrderDetailsItemNdcCode;

    @Column("sales_order_details_manufacturer")
    private String salesOrderDetailsManufacturer;

    @Column("sales_order_details_cb_pricing")
    private String salesOrderDetailsCbPricing;

    @Column("sales_order_details_cb_price_table_override")
    private String salesOrderDetailsCbPriceTableOverride;

    @Column("sales_order_details_cb_override")
    private String salesOrderDetailsCbOverride;

    @Column("sales_order_details_messages")
    private String salesOrderDetailsMessages;

    @Column("sales_order_details_location")
    private Long salesOrderDetailsLocation;

    @Column("sales_order_details_calories_per_day")
    private Long salesOrderDetailsCaloriesPerDay;

    @Column("sales_order_details_secondary_billing_procudure_code")
    private String salesOrderDetailsSecondaryBillingProcudureCode;

    @Column("sales_order_details_secondary_billing_price_option")
    private String salesOrderDetailsSecondaryBillingPriceOption;

    @Column("sales_order_details_secondary_billing_price_option_name")
    private String salesOrderDetailsSecondaryBillingPriceOptionName;

    @Column("sales_order_details_secondary_billing_modifier_1")
    private String salesOrderDetailsSecondaryBillingModifier1;

    @Column("sales_order_details_secondary_billing_modifier_2")
    private String salesOrderDetailsSecondaryBillingModifier2;

    @Column("sales_order_details_secondary_billing_modifier_3")
    private String salesOrderDetailsSecondaryBillingModifier3;

    @Column("sales_order_details_secondary_billing_modifier_4")
    private String salesOrderDetailsSecondaryBillingModifier4;

    @Column("sales_order_details_secondary_billing_additional_modifier_1")
    private String salesOrderDetailsSecondaryBillingAdditionalModifier1;

    @Column("sales_order_details_secondary_billing_additional_modifier_2")
    private String salesOrderDetailsSecondaryBillingAdditionalModifier2;

    @Column("sales_order_details_secondary_billing_additional_modifier_3")
    private String salesOrderDetailsSecondaryBillingAdditionalModifier3;

    @Column("sales_order_details_secondary_billing_additional_modifier_4")
    private String salesOrderDetailsSecondaryBillingAdditionalModifier4;

    @Column("sales_order_details_secondary_billing_ignore")
    private String salesOrderDetailsSecondaryBillingIgnore;

    @Column("sales_order_details_secondary_special_billing")
    private String salesOrderDetailsSecondarySpecialBilling;

    @Column("sales_order_details_span_date_split_billing")
    private String salesOrderDetailsSpanDateSplitBilling;

    @Column("sales_order_details_cmnpar_cmn_form_id")
    private Long salesOrderDetailsCmnparCmnFormId;

    @Column("sales_order_details_cmnpar_cmn_key")
    private String salesOrderDetailsCmnparCmnKey;

    @Column("sales_order_details_cmnpar_cmn_create_date")
    private LocalDate salesOrderDetailsCmnparCmnCreateDate;

    @Column("sales_order_details_cmnpar_cmn_expiration_date")
    private LocalDate salesOrderDetailsCmnparCmnExpirationDate;

    @Column("sales_order_details_cmnpar_cmn_initial_date")
    private LocalDate salesOrderDetailsCmnparCmnInitialDate;

    @Column("sales_order_details_cmnpar_cmn_renewal_date")
    private LocalDate salesOrderDetailsCmnparCmnRenewalDate;

    @Column("sales_order_details_cmnpar_cmn_recertification_date")
    private LocalDate salesOrderDetailsCmnparCmnRecertificationDate;

    @Column("sales_order_details_cmnpar_cmn_physician_date")
    private LocalDate salesOrderDetailsCmnparCmnPhysicianDate;

    @Column("sales_order_details_cmnpar_cmn_status")
    private String salesOrderDetailsCmnparCmnStatus;

    @Column("sales_order_details_cmnpar_par_id")
    private String salesOrderDetailsCmnparParId;

    @Column("sales_order_details_cmnpar_par_descr")
    private String salesOrderDetailsCmnparParDescr;

    @Column("sales_order_details_cmnpar_par_initial_date")
    private LocalDate salesOrderDetailsCmnparParInitialDate;

    @Column("sales_order_details_cmnpar_par_expiration_date")
    private LocalDate salesOrderDetailsCmnparParExpirationDate;

    @Column("sales_order_details_cmnpar_cmn_log_date")
    private LocalDate salesOrderDetailsCmnparCmnLogDate;

    @Column("sales_order_details_cmnpar_cmn_length_of_need")
    private Long salesOrderDetailsCmnparCmnLengthOfNeed;

    @Column("sales_order_details_cmnpar_cmn_printed_date")
    private LocalDate salesOrderDetailsCmnparCmnPrintedDate;

    @Column("sales_order_details_cmnpar_cmn_printed_by")
    private String salesOrderDetailsCmnparCmnPrintedBy;

    @Column("sales_order_details_cmnpar_faxed_date")
    private LocalDate salesOrderDetailsCmnparFaxedDate;

    @Column("sales_order_details_cmnpar_cmn_placeholder")
    private String salesOrderDetailsCmnparCmnPlaceholder;

    @Column("sales_order_details_cmnpar_cmn_faxed_by")
    private String salesOrderDetailsCmnparCmnFaxedBy;

    @Column("sales_order_details_cmnpar_cmn_logged_by")
    private String salesOrderDetailsCmnparCmnLoggedBy;

    @Column("sales_order_details_cmnpar_number_of_refills")
    private Long salesOrderDetailsCmnparNumberOfRefills;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("status")
    private String status;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("sales_order_details_manufacturer_item_id_number")
    private String salesOrderDetailsManufacturerItemIdNumber;

    @Column("cmn_id")
    private Long cmnId;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("sales_order_item_details_uuid")
    private UUID salesOrderItemDetailsUuid;

    @Column("sales_order_item_number")
    private String salesOrderItemNumber;

    @Column("is_asset_tagged")
    private String isAssetTagged;

    @Column("item_serial_no")
    private Long itemSerialNo;

    @Column("sales_order_details_icd_pointer")
    private String salesOrderDetailsIcdPointer;

    @Column("procedure_identifier")
    private String procedureIdentifier;

    @Column("par_no")
    private String parNo;

    @Column("whether_serialised")
    private String whetherSerialised;

    @Column("pickup_exchange_no")
    private String pickupExchangeNo;

    @Column("sales_order_abn_user_response")
    private String salesOrderAbnUserResponse;

    @Column("is_dropship_allowed")
    private String isDropshipAllowed;

    @Column("po_number")
    private String poNumber;

    @Column("purchase_order_uuid")
    private UUID purchaseOrderUuid;

    @Column("is_resupply_type")
    private String isResupplyType;

    @Column("frequency_count")
    private Long frequencyCount;

    @Column("frequency_interval")
    private Long frequencyInterval;

    @Column("item_group_id")
    private Long itemGroupId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSalesOrderItemDetailsId() {
        return this.salesOrderItemDetailsId;
    }

    public SalesOrderItemDetails salesOrderItemDetailsId(Long salesOrderItemDetailsId) {
        this.setSalesOrderItemDetailsId(salesOrderItemDetailsId);
        return this;
    }

    public void setSalesOrderItemDetailsId(Long salesOrderItemDetailsId) {
        this.salesOrderItemDetailsId = salesOrderItemDetailsId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SalesOrderItemDetails salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public SalesOrderItemDetails patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getItemLocationId() {
        return this.itemLocationId;
    }

    public SalesOrderItemDetails itemLocationId(Long itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public Long getSalesOrderDetailsItemId() {
        return this.salesOrderDetailsItemId;
    }

    public SalesOrderItemDetails salesOrderDetailsItemId(Long salesOrderDetailsItemId) {
        this.setSalesOrderDetailsItemId(salesOrderDetailsItemId);
        return this;
    }

    public void setSalesOrderDetailsItemId(Long salesOrderDetailsItemId) {
        this.salesOrderDetailsItemId = salesOrderDetailsItemId;
    }

    public String getSalesOrderDetailsItemName() {
        return this.salesOrderDetailsItemName;
    }

    public SalesOrderItemDetails salesOrderDetailsItemName(String salesOrderDetailsItemName) {
        this.setSalesOrderDetailsItemName(salesOrderDetailsItemName);
        return this;
    }

    public void setSalesOrderDetailsItemName(String salesOrderDetailsItemName) {
        this.salesOrderDetailsItemName = salesOrderDetailsItemName;
    }

    public String getSalesOrderDetailsStockingUom() {
        return this.salesOrderDetailsStockingUom;
    }

    public SalesOrderItemDetails salesOrderDetailsStockingUom(String salesOrderDetailsStockingUom) {
        this.setSalesOrderDetailsStockingUom(salesOrderDetailsStockingUom);
        return this;
    }

    public void setSalesOrderDetailsStockingUom(String salesOrderDetailsStockingUom) {
        this.salesOrderDetailsStockingUom = salesOrderDetailsStockingUom;
    }

    public String getItemAssetNo() {
        return this.itemAssetNo;
    }

    public SalesOrderItemDetails itemAssetNo(String itemAssetNo) {
        this.setItemAssetNo(itemAssetNo);
        return this;
    }

    public void setItemAssetNo(String itemAssetNo) {
        this.itemAssetNo = itemAssetNo;
    }

    public String getSalesOrderDetailsItemDescription() {
        return this.salesOrderDetailsItemDescription;
    }

    public SalesOrderItemDetails salesOrderDetailsItemDescription(String salesOrderDetailsItemDescription) {
        this.setSalesOrderDetailsItemDescription(salesOrderDetailsItemDescription);
        return this;
    }

    public void setSalesOrderDetailsItemDescription(String salesOrderDetailsItemDescription) {
        this.salesOrderDetailsItemDescription = salesOrderDetailsItemDescription;
    }

    public String getSalesOrderDetailsDefaultVendor() {
        return this.salesOrderDetailsDefaultVendor;
    }

    public SalesOrderItemDetails salesOrderDetailsDefaultVendor(String salesOrderDetailsDefaultVendor) {
        this.setSalesOrderDetailsDefaultVendor(salesOrderDetailsDefaultVendor);
        return this;
    }

    public void setSalesOrderDetailsDefaultVendor(String salesOrderDetailsDefaultVendor) {
        this.salesOrderDetailsDefaultVendor = salesOrderDetailsDefaultVendor;
    }

    public LocalDate getSalesOrderDetailsOriginalDos() {
        return this.salesOrderDetailsOriginalDos;
    }

    public SalesOrderItemDetails salesOrderDetailsOriginalDos(LocalDate salesOrderDetailsOriginalDos) {
        this.setSalesOrderDetailsOriginalDos(salesOrderDetailsOriginalDos);
        return this;
    }

    public void setSalesOrderDetailsOriginalDos(LocalDate salesOrderDetailsOriginalDos) {
        this.salesOrderDetailsOriginalDos = salesOrderDetailsOriginalDos;
    }

    public LocalDate getSalesOrderDetailsPreviousBillingDate() {
        return this.salesOrderDetailsPreviousBillingDate;
    }

    public SalesOrderItemDetails salesOrderDetailsPreviousBillingDate(LocalDate salesOrderDetailsPreviousBillingDate) {
        this.setSalesOrderDetailsPreviousBillingDate(salesOrderDetailsPreviousBillingDate);
        return this;
    }

    public void setSalesOrderDetailsPreviousBillingDate(LocalDate salesOrderDetailsPreviousBillingDate) {
        this.salesOrderDetailsPreviousBillingDate = salesOrderDetailsPreviousBillingDate;
    }

    public LocalDate getSalesOrderDetailsNextBillingDate() {
        return this.salesOrderDetailsNextBillingDate;
    }

    public SalesOrderItemDetails salesOrderDetailsNextBillingDate(LocalDate salesOrderDetailsNextBillingDate) {
        this.setSalesOrderDetailsNextBillingDate(salesOrderDetailsNextBillingDate);
        return this;
    }

    public void setSalesOrderDetailsNextBillingDate(LocalDate salesOrderDetailsNextBillingDate) {
        this.salesOrderDetailsNextBillingDate = salesOrderDetailsNextBillingDate;
    }

    public LocalDate getSalesOrderDetailsDosTo() {
        return this.salesOrderDetailsDosTo;
    }

    public SalesOrderItemDetails salesOrderDetailsDosTo(LocalDate salesOrderDetailsDosTo) {
        this.setSalesOrderDetailsDosTo(salesOrderDetailsDosTo);
        return this;
    }

    public void setSalesOrderDetailsDosTo(LocalDate salesOrderDetailsDosTo) {
        this.salesOrderDetailsDosTo = salesOrderDetailsDosTo;
    }

    public String getSalesOrderDetailsNextPeriod() {
        return this.salesOrderDetailsNextPeriod;
    }

    public SalesOrderItemDetails salesOrderDetailsNextPeriod(String salesOrderDetailsNextPeriod) {
        this.setSalesOrderDetailsNextPeriod(salesOrderDetailsNextPeriod);
        return this;
    }

    public void setSalesOrderDetailsNextPeriod(String salesOrderDetailsNextPeriod) {
        this.salesOrderDetailsNextPeriod = salesOrderDetailsNextPeriod;
    }

    public String getSalesOrderDetailsSpecialPricing() {
        return this.salesOrderDetailsSpecialPricing;
    }

    public SalesOrderItemDetails salesOrderDetailsSpecialPricing(String salesOrderDetailsSpecialPricing) {
        this.setSalesOrderDetailsSpecialPricing(salesOrderDetailsSpecialPricing);
        return this;
    }

    public void setSalesOrderDetailsSpecialPricing(String salesOrderDetailsSpecialPricing) {
        this.salesOrderDetailsSpecialPricing = salesOrderDetailsSpecialPricing;
    }

    public String getSalesOrderDetailsPriceOverride() {
        return this.salesOrderDetailsPriceOverride;
    }

    public SalesOrderItemDetails salesOrderDetailsPriceOverride(String salesOrderDetailsPriceOverride) {
        this.setSalesOrderDetailsPriceOverride(salesOrderDetailsPriceOverride);
        return this;
    }

    public void setSalesOrderDetailsPriceOverride(String salesOrderDetailsPriceOverride) {
        this.salesOrderDetailsPriceOverride = salesOrderDetailsPriceOverride;
    }

    public Long getSalesOrderDetailsSpecialTaxRate() {
        return this.salesOrderDetailsSpecialTaxRate;
    }

    public SalesOrderItemDetails salesOrderDetailsSpecialTaxRate(Long salesOrderDetailsSpecialTaxRate) {
        this.setSalesOrderDetailsSpecialTaxRate(salesOrderDetailsSpecialTaxRate);
        return this;
    }

    public void setSalesOrderDetailsSpecialTaxRate(Long salesOrderDetailsSpecialTaxRate) {
        this.salesOrderDetailsSpecialTaxRate = salesOrderDetailsSpecialTaxRate;
    }

    public Long getSalesOrderDetailsQty() {
        return this.salesOrderDetailsQty;
    }

    public SalesOrderItemDetails salesOrderDetailsQty(Long salesOrderDetailsQty) {
        this.setSalesOrderDetailsQty(salesOrderDetailsQty);
        return this;
    }

    public void setSalesOrderDetailsQty(Long salesOrderDetailsQty) {
        this.salesOrderDetailsQty = salesOrderDetailsQty;
    }

    public Long getSalesOrderDetailsBqty() {
        return this.salesOrderDetailsBqty;
    }

    public SalesOrderItemDetails salesOrderDetailsBqty(Long salesOrderDetailsBqty) {
        this.setSalesOrderDetailsBqty(salesOrderDetailsBqty);
        return this;
    }

    public void setSalesOrderDetailsBqty(Long salesOrderDetailsBqty) {
        this.salesOrderDetailsBqty = salesOrderDetailsBqty;
    }

    public Long getSalesOrderDetailsLineQty() {
        return this.salesOrderDetailsLineQty;
    }

    public SalesOrderItemDetails salesOrderDetailsLineQty(Long salesOrderDetailsLineQty) {
        this.setSalesOrderDetailsLineQty(salesOrderDetailsLineQty);
        return this;
    }

    public void setSalesOrderDetailsLineQty(Long salesOrderDetailsLineQty) {
        this.salesOrderDetailsLineQty = salesOrderDetailsLineQty;
    }

    public String getSalesOrderDetailsProcCode() {
        return this.salesOrderDetailsProcCode;
    }

    public SalesOrderItemDetails salesOrderDetailsProcCode(String salesOrderDetailsProcCode) {
        this.setSalesOrderDetailsProcCode(salesOrderDetailsProcCode);
        return this;
    }

    public void setSalesOrderDetailsProcCode(String salesOrderDetailsProcCode) {
        this.salesOrderDetailsProcCode = salesOrderDetailsProcCode;
    }

    public String getSalesOrderDetailsPriceOption() {
        return this.salesOrderDetailsPriceOption;
    }

    public SalesOrderItemDetails salesOrderDetailsPriceOption(String salesOrderDetailsPriceOption) {
        this.setSalesOrderDetailsPriceOption(salesOrderDetailsPriceOption);
        return this;
    }

    public void setSalesOrderDetailsPriceOption(String salesOrderDetailsPriceOption) {
        this.salesOrderDetailsPriceOption = salesOrderDetailsPriceOption;
    }

    public String getSalesOrderDetailsModifier1() {
        return this.salesOrderDetailsModifier1;
    }

    public SalesOrderItemDetails salesOrderDetailsModifier1(String salesOrderDetailsModifier1) {
        this.setSalesOrderDetailsModifier1(salesOrderDetailsModifier1);
        return this;
    }

    public void setSalesOrderDetailsModifier1(String salesOrderDetailsModifier1) {
        this.salesOrderDetailsModifier1 = salesOrderDetailsModifier1;
    }

    public String getSalesOrderDetailsModifier2() {
        return this.salesOrderDetailsModifier2;
    }

    public SalesOrderItemDetails salesOrderDetailsModifier2(String salesOrderDetailsModifier2) {
        this.setSalesOrderDetailsModifier2(salesOrderDetailsModifier2);
        return this;
    }

    public void setSalesOrderDetailsModifier2(String salesOrderDetailsModifier2) {
        this.salesOrderDetailsModifier2 = salesOrderDetailsModifier2;
    }

    public String getSalesOrderDetailsModifier3() {
        return this.salesOrderDetailsModifier3;
    }

    public SalesOrderItemDetails salesOrderDetailsModifier3(String salesOrderDetailsModifier3) {
        this.setSalesOrderDetailsModifier3(salesOrderDetailsModifier3);
        return this;
    }

    public void setSalesOrderDetailsModifier3(String salesOrderDetailsModifier3) {
        this.salesOrderDetailsModifier3 = salesOrderDetailsModifier3;
    }

    public String getSalesOrderDetailsModifier4() {
        return this.salesOrderDetailsModifier4;
    }

    public SalesOrderItemDetails salesOrderDetailsModifier4(String salesOrderDetailsModifier4) {
        this.setSalesOrderDetailsModifier4(salesOrderDetailsModifier4);
        return this;
    }

    public void setSalesOrderDetailsModifier4(String salesOrderDetailsModifier4) {
        this.salesOrderDetailsModifier4 = salesOrderDetailsModifier4;
    }

    public Double getSalesOrderDetailsChargeAmt() {
        return this.salesOrderDetailsChargeAmt;
    }

    public SalesOrderItemDetails salesOrderDetailsChargeAmt(Double salesOrderDetailsChargeAmt) {
        this.setSalesOrderDetailsChargeAmt(salesOrderDetailsChargeAmt);
        return this;
    }

    public void setSalesOrderDetailsChargeAmt(Double salesOrderDetailsChargeAmt) {
        this.salesOrderDetailsChargeAmt = salesOrderDetailsChargeAmt;
    }

    public Double getSalesOrderDetailsAllowedAmt() {
        return this.salesOrderDetailsAllowedAmt;
    }

    public SalesOrderItemDetails salesOrderDetailsAllowedAmt(Double salesOrderDetailsAllowedAmt) {
        this.setSalesOrderDetailsAllowedAmt(salesOrderDetailsAllowedAmt);
        return this;
    }

    public void setSalesOrderDetailsAllowedAmt(Double salesOrderDetailsAllowedAmt) {
        this.salesOrderDetailsAllowedAmt = salesOrderDetailsAllowedAmt;
    }

    public String getSalesOrderDetailsTaxable() {
        return this.salesOrderDetailsTaxable;
    }

    public SalesOrderItemDetails salesOrderDetailsTaxable(String salesOrderDetailsTaxable) {
        this.setSalesOrderDetailsTaxable(salesOrderDetailsTaxable);
        return this;
    }

    public void setSalesOrderDetailsTaxable(String salesOrderDetailsTaxable) {
        this.salesOrderDetailsTaxable = salesOrderDetailsTaxable;
    }

    public String getSalesOrderDetailsAbn() {
        return this.salesOrderDetailsAbn;
    }

    public SalesOrderItemDetails salesOrderDetailsAbn(String salesOrderDetailsAbn) {
        this.setSalesOrderDetailsAbn(salesOrderDetailsAbn);
        return this;
    }

    public void setSalesOrderDetailsAbn(String salesOrderDetailsAbn) {
        this.salesOrderDetailsAbn = salesOrderDetailsAbn;
    }

    public String getSalesOrderDetailsAbnUpgrade() {
        return this.salesOrderDetailsAbnUpgrade;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnUpgrade(String salesOrderDetailsAbnUpgrade) {
        this.setSalesOrderDetailsAbnUpgrade(salesOrderDetailsAbnUpgrade);
        return this;
    }

    public void setSalesOrderDetailsAbnUpgrade(String salesOrderDetailsAbnUpgrade) {
        this.salesOrderDetailsAbnUpgrade = salesOrderDetailsAbnUpgrade;
    }

    public LocalDate getSalesOrderDetailsAbnPrintDate() {
        return this.salesOrderDetailsAbnPrintDate;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
        this.setSalesOrderDetailsAbnPrintDate(salesOrderDetailsAbnPrintDate);
        return this;
    }

    public void setSalesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
        this.salesOrderDetailsAbnPrintDate = salesOrderDetailsAbnPrintDate;
    }

    public String getSalesOrderDetailsAbnItem() {
        return this.salesOrderDetailsAbnItem;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnItem(String salesOrderDetailsAbnItem) {
        this.setSalesOrderDetailsAbnItem(salesOrderDetailsAbnItem);
        return this;
    }

    public void setSalesOrderDetailsAbnItem(String salesOrderDetailsAbnItem) {
        this.salesOrderDetailsAbnItem = salesOrderDetailsAbnItem;
    }

    public String getSalesOrderDetailsAbnProcCode() {
        return this.salesOrderDetailsAbnProcCode;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnProcCode(String salesOrderDetailsAbnProcCode) {
        this.setSalesOrderDetailsAbnProcCode(salesOrderDetailsAbnProcCode);
        return this;
    }

    public void setSalesOrderDetailsAbnProcCode(String salesOrderDetailsAbnProcCode) {
        this.salesOrderDetailsAbnProcCode = salesOrderDetailsAbnProcCode;
    }

    public String getSalesOrderDetailsAbnAllow() {
        return this.salesOrderDetailsAbnAllow;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnAllow(String salesOrderDetailsAbnAllow) {
        this.setSalesOrderDetailsAbnAllow(salesOrderDetailsAbnAllow);
        return this;
    }

    public void setSalesOrderDetailsAbnAllow(String salesOrderDetailsAbnAllow) {
        this.salesOrderDetailsAbnAllow = salesOrderDetailsAbnAllow;
    }

    public Double getSalesOrderDetailsAbnCharge() {
        return this.salesOrderDetailsAbnCharge;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnCharge(Double salesOrderDetailsAbnCharge) {
        this.setSalesOrderDetailsAbnCharge(salesOrderDetailsAbnCharge);
        return this;
    }

    public void setSalesOrderDetailsAbnCharge(Double salesOrderDetailsAbnCharge) {
        this.salesOrderDetailsAbnCharge = salesOrderDetailsAbnCharge;
    }

    public String getSalesOrderDetailsAbnModifier1() {
        return this.salesOrderDetailsAbnModifier1;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
        this.setSalesOrderDetailsAbnModifier1(salesOrderDetailsAbnModifier1);
        return this;
    }

    public void setSalesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
        this.salesOrderDetailsAbnModifier1 = salesOrderDetailsAbnModifier1;
    }

    public String getSalesOrderDetailsAbnModifier2() {
        return this.salesOrderDetailsAbnModifier2;
    }

    public SalesOrderItemDetails salesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
        this.setSalesOrderDetailsAbnModifier2(salesOrderDetailsAbnModifier2);
        return this;
    }

    public void setSalesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
        this.salesOrderDetailsAbnModifier2 = salesOrderDetailsAbnModifier2;
    }

    public Long getSalesOrderDetailsTaxRate() {
        return this.salesOrderDetailsTaxRate;
    }

    public SalesOrderItemDetails salesOrderDetailsTaxRate(Long salesOrderDetailsTaxRate) {
        this.setSalesOrderDetailsTaxRate(salesOrderDetailsTaxRate);
        return this;
    }

    public void setSalesOrderDetailsTaxRate(Long salesOrderDetailsTaxRate) {
        this.salesOrderDetailsTaxRate = salesOrderDetailsTaxRate;
    }

    public String getSalesOrderDetailsTaxZone() {
        return this.salesOrderDetailsTaxZone;
    }

    public SalesOrderItemDetails salesOrderDetailsTaxZone(String salesOrderDetailsTaxZone) {
        this.setSalesOrderDetailsTaxZone(salesOrderDetailsTaxZone);
        return this;
    }

    public void setSalesOrderDetailsTaxZone(String salesOrderDetailsTaxZone) {
        this.salesOrderDetailsTaxZone = salesOrderDetailsTaxZone;
    }

    public String getSalesOrderDetailsNonTaxReason() {
        return this.salesOrderDetailsNonTaxReason;
    }

    public SalesOrderItemDetails salesOrderDetailsNonTaxReason(String salesOrderDetailsNonTaxReason) {
        this.setSalesOrderDetailsNonTaxReason(salesOrderDetailsNonTaxReason);
        return this;
    }

    public void setSalesOrderDetailsNonTaxReason(String salesOrderDetailsNonTaxReason) {
        this.salesOrderDetailsNonTaxReason = salesOrderDetailsNonTaxReason;
    }

    public String getSalesOrderDetailsNote() {
        return this.salesOrderDetailsNote;
    }

    public SalesOrderItemDetails salesOrderDetailsNote(String salesOrderDetailsNote) {
        this.setSalesOrderDetailsNote(salesOrderDetailsNote);
        return this;
    }

    public void setSalesOrderDetailsNote(String salesOrderDetailsNote) {
        this.salesOrderDetailsNote = salesOrderDetailsNote;
    }

    public String getSalesOrderDetailsSaleType() {
        return this.salesOrderDetailsSaleType;
    }

    public SalesOrderItemDetails salesOrderDetailsSaleType(String salesOrderDetailsSaleType) {
        this.setSalesOrderDetailsSaleType(salesOrderDetailsSaleType);
        return this;
    }

    public void setSalesOrderDetailsSaleType(String salesOrderDetailsSaleType) {
        this.salesOrderDetailsSaleType = salesOrderDetailsSaleType;
    }

    public String getSalesOrderDetailsItemGroup() {
        return this.salesOrderDetailsItemGroup;
    }

    public SalesOrderItemDetails salesOrderDetailsItemGroup(String salesOrderDetailsItemGroup) {
        this.setSalesOrderDetailsItemGroup(salesOrderDetailsItemGroup);
        return this;
    }

    public void setSalesOrderDetailsItemGroup(String salesOrderDetailsItemGroup) {
        this.salesOrderDetailsItemGroup = salesOrderDetailsItemGroup;
    }

    public String getSalesOrderDetailsItemUser1() {
        return this.salesOrderDetailsItemUser1;
    }

    public SalesOrderItemDetails salesOrderDetailsItemUser1(String salesOrderDetailsItemUser1) {
        this.setSalesOrderDetailsItemUser1(salesOrderDetailsItemUser1);
        return this;
    }

    public void setSalesOrderDetailsItemUser1(String salesOrderDetailsItemUser1) {
        this.salesOrderDetailsItemUser1 = salesOrderDetailsItemUser1;
    }

    public String getSalesOrderDetailsItemUser2() {
        return this.salesOrderDetailsItemUser2;
    }

    public SalesOrderItemDetails salesOrderDetailsItemUser2(String salesOrderDetailsItemUser2) {
        this.setSalesOrderDetailsItemUser2(salesOrderDetailsItemUser2);
        return this;
    }

    public void setSalesOrderDetailsItemUser2(String salesOrderDetailsItemUser2) {
        this.salesOrderDetailsItemUser2 = salesOrderDetailsItemUser2;
    }

    public String getSalesOrderDetailsItemUser3() {
        return this.salesOrderDetailsItemUser3;
    }

    public SalesOrderItemDetails salesOrderDetailsItemUser3(String salesOrderDetailsItemUser3) {
        this.setSalesOrderDetailsItemUser3(salesOrderDetailsItemUser3);
        return this;
    }

    public void setSalesOrderDetailsItemUser3(String salesOrderDetailsItemUser3) {
        this.salesOrderDetailsItemUser3 = salesOrderDetailsItemUser3;
    }

    public String getSalesOrderDetailsItemUser4() {
        return this.salesOrderDetailsItemUser4;
    }

    public SalesOrderItemDetails salesOrderDetailsItemUser4(String salesOrderDetailsItemUser4) {
        this.setSalesOrderDetailsItemUser4(salesOrderDetailsItemUser4);
        return this;
    }

    public void setSalesOrderDetailsItemUser4(String salesOrderDetailsItemUser4) {
        this.salesOrderDetailsItemUser4 = salesOrderDetailsItemUser4;
    }

    public String getSalesOrderDetailsConvertedToPurchase() {
        return this.salesOrderDetailsConvertedToPurchase;
    }

    public SalesOrderItemDetails salesOrderDetailsConvertedToPurchase(String salesOrderDetailsConvertedToPurchase) {
        this.setSalesOrderDetailsConvertedToPurchase(salesOrderDetailsConvertedToPurchase);
        return this;
    }

    public void setSalesOrderDetailsConvertedToPurchase(String salesOrderDetailsConvertedToPurchase) {
        this.salesOrderDetailsConvertedToPurchase = salesOrderDetailsConvertedToPurchase;
    }

    public String getSalesOrderDetailsManualConvertToPurchaseMctp() {
        return this.salesOrderDetailsManualConvertToPurchaseMctp;
    }

    public SalesOrderItemDetails salesOrderDetailsManualConvertToPurchaseMctp(String salesOrderDetailsManualConvertToPurchaseMctp) {
        this.setSalesOrderDetailsManualConvertToPurchaseMctp(salesOrderDetailsManualConvertToPurchaseMctp);
        return this;
    }

    public void setSalesOrderDetailsManualConvertToPurchaseMctp(String salesOrderDetailsManualConvertToPurchaseMctp) {
        this.salesOrderDetailsManualConvertToPurchaseMctp = salesOrderDetailsManualConvertToPurchaseMctp;
    }

    public Double getSalesOrderDetailsMctpChargeAmt() {
        return this.salesOrderDetailsMctpChargeAmt;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpChargeAmt(Double salesOrderDetailsMctpChargeAmt) {
        this.setSalesOrderDetailsMctpChargeAmt(salesOrderDetailsMctpChargeAmt);
        return this;
    }

    public void setSalesOrderDetailsMctpChargeAmt(Double salesOrderDetailsMctpChargeAmt) {
        this.salesOrderDetailsMctpChargeAmt = salesOrderDetailsMctpChargeAmt;
    }

    public Double getSalesOrderDetailsMctpAllowedAmt() {
        return this.salesOrderDetailsMctpAllowedAmt;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpAllowedAmt(Double salesOrderDetailsMctpAllowedAmt) {
        this.setSalesOrderDetailsMctpAllowedAmt(salesOrderDetailsMctpAllowedAmt);
        return this;
    }

    public void setSalesOrderDetailsMctpAllowedAmt(Double salesOrderDetailsMctpAllowedAmt) {
        this.salesOrderDetailsMctpAllowedAmt = salesOrderDetailsMctpAllowedAmt;
    }

    public String getSalesOrderDetailsMctpModifier1() {
        return this.salesOrderDetailsMctpModifier1;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpModifier1(String salesOrderDetailsMctpModifier1) {
        this.setSalesOrderDetailsMctpModifier1(salesOrderDetailsMctpModifier1);
        return this;
    }

    public void setSalesOrderDetailsMctpModifier1(String salesOrderDetailsMctpModifier1) {
        this.salesOrderDetailsMctpModifier1 = salesOrderDetailsMctpModifier1;
    }

    public String getSalesOrderDetailsMctpModifier2() {
        return this.salesOrderDetailsMctpModifier2;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpModifier2(String salesOrderDetailsMctpModifier2) {
        this.setSalesOrderDetailsMctpModifier2(salesOrderDetailsMctpModifier2);
        return this;
    }

    public void setSalesOrderDetailsMctpModifier2(String salesOrderDetailsMctpModifier2) {
        this.salesOrderDetailsMctpModifier2 = salesOrderDetailsMctpModifier2;
    }

    public String getSalesOrderDetailsMctpModifier3() {
        return this.salesOrderDetailsMctpModifier3;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpModifier3(String salesOrderDetailsMctpModifier3) {
        this.setSalesOrderDetailsMctpModifier3(salesOrderDetailsMctpModifier3);
        return this;
    }

    public void setSalesOrderDetailsMctpModifier3(String salesOrderDetailsMctpModifier3) {
        this.salesOrderDetailsMctpModifier3 = salesOrderDetailsMctpModifier3;
    }

    public String getSalesOrderDetailsMctpModifier4() {
        return this.salesOrderDetailsMctpModifier4;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpModifier4(String salesOrderDetailsMctpModifier4) {
        this.setSalesOrderDetailsMctpModifier4(salesOrderDetailsMctpModifier4);
        return this;
    }

    public void setSalesOrderDetailsMctpModifier4(String salesOrderDetailsMctpModifier4) {
        this.salesOrderDetailsMctpModifier4 = salesOrderDetailsMctpModifier4;
    }

    public Long getSalesOrderDetailsMctpPeriod() {
        return this.salesOrderDetailsMctpPeriod;
    }

    public SalesOrderItemDetails salesOrderDetailsMctpPeriod(Long salesOrderDetailsMctpPeriod) {
        this.setSalesOrderDetailsMctpPeriod(salesOrderDetailsMctpPeriod);
        return this;
    }

    public void setSalesOrderDetailsMctpPeriod(Long salesOrderDetailsMctpPeriod) {
        this.salesOrderDetailsMctpPeriod = salesOrderDetailsMctpPeriod;
    }

    public String getSalesOrderDetailsAddtlModifier1() {
        return this.salesOrderDetailsAddtlModifier1;
    }

    public SalesOrderItemDetails salesOrderDetailsAddtlModifier1(String salesOrderDetailsAddtlModifier1) {
        this.setSalesOrderDetailsAddtlModifier1(salesOrderDetailsAddtlModifier1);
        return this;
    }

    public void setSalesOrderDetailsAddtlModifier1(String salesOrderDetailsAddtlModifier1) {
        this.salesOrderDetailsAddtlModifier1 = salesOrderDetailsAddtlModifier1;
    }

    public String getSalesOrderDetailsAddtlModifier2() {
        return this.salesOrderDetailsAddtlModifier2;
    }

    public SalesOrderItemDetails salesOrderDetailsAddtlModifier2(String salesOrderDetailsAddtlModifier2) {
        this.setSalesOrderDetailsAddtlModifier2(salesOrderDetailsAddtlModifier2);
        return this;
    }

    public void setSalesOrderDetailsAddtlModifier2(String salesOrderDetailsAddtlModifier2) {
        this.salesOrderDetailsAddtlModifier2 = salesOrderDetailsAddtlModifier2;
    }

    public String getSalesOrderDetailsAddtlModifier3() {
        return this.salesOrderDetailsAddtlModifier3;
    }

    public SalesOrderItemDetails salesOrderDetailsAddtlModifier3(String salesOrderDetailsAddtlModifier3) {
        this.setSalesOrderDetailsAddtlModifier3(salesOrderDetailsAddtlModifier3);
        return this;
    }

    public void setSalesOrderDetailsAddtlModifier3(String salesOrderDetailsAddtlModifier3) {
        this.salesOrderDetailsAddtlModifier3 = salesOrderDetailsAddtlModifier3;
    }

    public String getSalesOrderDetailsAddtlModifier4() {
        return this.salesOrderDetailsAddtlModifier4;
    }

    public SalesOrderItemDetails salesOrderDetailsAddtlModifier4(String salesOrderDetailsAddtlModifier4) {
        this.setSalesOrderDetailsAddtlModifier4(salesOrderDetailsAddtlModifier4);
        return this;
    }

    public void setSalesOrderDetailsAddtlModifier4(String salesOrderDetailsAddtlModifier4) {
        this.salesOrderDetailsAddtlModifier4 = salesOrderDetailsAddtlModifier4;
    }

    public LocalDate getSalesOrderDetailsNextDateOfService() {
        return this.salesOrderDetailsNextDateOfService;
    }

    public SalesOrderItemDetails salesOrderDetailsNextDateOfService(LocalDate salesOrderDetailsNextDateOfService) {
        this.setSalesOrderDetailsNextDateOfService(salesOrderDetailsNextDateOfService);
        return this;
    }

    public void setSalesOrderDetailsNextDateOfService(LocalDate salesOrderDetailsNextDateOfService) {
        this.salesOrderDetailsNextDateOfService = salesOrderDetailsNextDateOfService;
    }

    public String getSalesOrderDetailsPriceTable() {
        return this.salesOrderDetailsPriceTable;
    }

    public SalesOrderItemDetails salesOrderDetailsPriceTable(String salesOrderDetailsPriceTable) {
        this.setSalesOrderDetailsPriceTable(salesOrderDetailsPriceTable);
        return this;
    }

    public void setSalesOrderDetailsPriceTable(String salesOrderDetailsPriceTable) {
        this.salesOrderDetailsPriceTable = salesOrderDetailsPriceTable;
    }

    public String getSalesOrderDetailsPriceOptionName() {
        return this.salesOrderDetailsPriceOptionName;
    }

    public SalesOrderItemDetails salesOrderDetailsPriceOptionName(String salesOrderDetailsPriceOptionName) {
        this.setSalesOrderDetailsPriceOptionName(salesOrderDetailsPriceOptionName);
        return this;
    }

    public void setSalesOrderDetailsPriceOptionName(String salesOrderDetailsPriceOptionName) {
        this.salesOrderDetailsPriceOptionName = salesOrderDetailsPriceOptionName;
    }

    public Double getSalesOrderDetailsExtendedChargeAmount() {
        return this.salesOrderDetailsExtendedChargeAmount;
    }

    public SalesOrderItemDetails salesOrderDetailsExtendedChargeAmount(Double salesOrderDetailsExtendedChargeAmount) {
        this.setSalesOrderDetailsExtendedChargeAmount(salesOrderDetailsExtendedChargeAmount);
        return this;
    }

    public void setSalesOrderDetailsExtendedChargeAmount(Double salesOrderDetailsExtendedChargeAmount) {
        this.salesOrderDetailsExtendedChargeAmount = salesOrderDetailsExtendedChargeAmount;
    }

    public Double getSalesOrderDetailsExtendedAllowanceAmount() {
        return this.salesOrderDetailsExtendedAllowanceAmount;
    }

    public SalesOrderItemDetails salesOrderDetailsExtendedAllowanceAmount(Double salesOrderDetailsExtendedAllowanceAmount) {
        this.setSalesOrderDetailsExtendedAllowanceAmount(salesOrderDetailsExtendedAllowanceAmount);
        return this;
    }

    public void setSalesOrderDetailsExtendedAllowanceAmount(Double salesOrderDetailsExtendedAllowanceAmount) {
        this.salesOrderDetailsExtendedAllowanceAmount = salesOrderDetailsExtendedAllowanceAmount;
    }

    public String getSalesOrderDetailsItemNdcCode() {
        return this.salesOrderDetailsItemNdcCode;
    }

    public SalesOrderItemDetails salesOrderDetailsItemNdcCode(String salesOrderDetailsItemNdcCode) {
        this.setSalesOrderDetailsItemNdcCode(salesOrderDetailsItemNdcCode);
        return this;
    }

    public void setSalesOrderDetailsItemNdcCode(String salesOrderDetailsItemNdcCode) {
        this.salesOrderDetailsItemNdcCode = salesOrderDetailsItemNdcCode;
    }

    public String getSalesOrderDetailsManufacturer() {
        return this.salesOrderDetailsManufacturer;
    }

    public SalesOrderItemDetails salesOrderDetailsManufacturer(String salesOrderDetailsManufacturer) {
        this.setSalesOrderDetailsManufacturer(salesOrderDetailsManufacturer);
        return this;
    }

    public void setSalesOrderDetailsManufacturer(String salesOrderDetailsManufacturer) {
        this.salesOrderDetailsManufacturer = salesOrderDetailsManufacturer;
    }

    public String getSalesOrderDetailsCbPricing() {
        return this.salesOrderDetailsCbPricing;
    }

    public SalesOrderItemDetails salesOrderDetailsCbPricing(String salesOrderDetailsCbPricing) {
        this.setSalesOrderDetailsCbPricing(salesOrderDetailsCbPricing);
        return this;
    }

    public void setSalesOrderDetailsCbPricing(String salesOrderDetailsCbPricing) {
        this.salesOrderDetailsCbPricing = salesOrderDetailsCbPricing;
    }

    public String getSalesOrderDetailsCbPriceTableOverride() {
        return this.salesOrderDetailsCbPriceTableOverride;
    }

    public SalesOrderItemDetails salesOrderDetailsCbPriceTableOverride(String salesOrderDetailsCbPriceTableOverride) {
        this.setSalesOrderDetailsCbPriceTableOverride(salesOrderDetailsCbPriceTableOverride);
        return this;
    }

    public void setSalesOrderDetailsCbPriceTableOverride(String salesOrderDetailsCbPriceTableOverride) {
        this.salesOrderDetailsCbPriceTableOverride = salesOrderDetailsCbPriceTableOverride;
    }

    public String getSalesOrderDetailsCbOverride() {
        return this.salesOrderDetailsCbOverride;
    }

    public SalesOrderItemDetails salesOrderDetailsCbOverride(String salesOrderDetailsCbOverride) {
        this.setSalesOrderDetailsCbOverride(salesOrderDetailsCbOverride);
        return this;
    }

    public void setSalesOrderDetailsCbOverride(String salesOrderDetailsCbOverride) {
        this.salesOrderDetailsCbOverride = salesOrderDetailsCbOverride;
    }

    public String getSalesOrderDetailsMessages() {
        return this.salesOrderDetailsMessages;
    }

    public SalesOrderItemDetails salesOrderDetailsMessages(String salesOrderDetailsMessages) {
        this.setSalesOrderDetailsMessages(salesOrderDetailsMessages);
        return this;
    }

    public void setSalesOrderDetailsMessages(String salesOrderDetailsMessages) {
        this.salesOrderDetailsMessages = salesOrderDetailsMessages;
    }

    public Long getSalesOrderDetailsLocation() {
        return this.salesOrderDetailsLocation;
    }

    public SalesOrderItemDetails salesOrderDetailsLocation(Long salesOrderDetailsLocation) {
        this.setSalesOrderDetailsLocation(salesOrderDetailsLocation);
        return this;
    }

    public void setSalesOrderDetailsLocation(Long salesOrderDetailsLocation) {
        this.salesOrderDetailsLocation = salesOrderDetailsLocation;
    }

    public Long getSalesOrderDetailsCaloriesPerDay() {
        return this.salesOrderDetailsCaloriesPerDay;
    }

    public SalesOrderItemDetails salesOrderDetailsCaloriesPerDay(Long salesOrderDetailsCaloriesPerDay) {
        this.setSalesOrderDetailsCaloriesPerDay(salesOrderDetailsCaloriesPerDay);
        return this;
    }

    public void setSalesOrderDetailsCaloriesPerDay(Long salesOrderDetailsCaloriesPerDay) {
        this.salesOrderDetailsCaloriesPerDay = salesOrderDetailsCaloriesPerDay;
    }

    public String getSalesOrderDetailsSecondaryBillingProcudureCode() {
        return this.salesOrderDetailsSecondaryBillingProcudureCode;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingProcudureCode(String salesOrderDetailsSecondaryBillingProcudureCode) {
        this.setSalesOrderDetailsSecondaryBillingProcudureCode(salesOrderDetailsSecondaryBillingProcudureCode);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingProcudureCode(String salesOrderDetailsSecondaryBillingProcudureCode) {
        this.salesOrderDetailsSecondaryBillingProcudureCode = salesOrderDetailsSecondaryBillingProcudureCode;
    }

    public String getSalesOrderDetailsSecondaryBillingPriceOption() {
        return this.salesOrderDetailsSecondaryBillingPriceOption;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingPriceOption(String salesOrderDetailsSecondaryBillingPriceOption) {
        this.setSalesOrderDetailsSecondaryBillingPriceOption(salesOrderDetailsSecondaryBillingPriceOption);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingPriceOption(String salesOrderDetailsSecondaryBillingPriceOption) {
        this.salesOrderDetailsSecondaryBillingPriceOption = salesOrderDetailsSecondaryBillingPriceOption;
    }

    public String getSalesOrderDetailsSecondaryBillingPriceOptionName() {
        return this.salesOrderDetailsSecondaryBillingPriceOptionName;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingPriceOptionName(String salesOrderDetailsSecondaryBillingPriceOptionName) {
        this.setSalesOrderDetailsSecondaryBillingPriceOptionName(salesOrderDetailsSecondaryBillingPriceOptionName);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingPriceOptionName(String salesOrderDetailsSecondaryBillingPriceOptionName) {
        this.salesOrderDetailsSecondaryBillingPriceOptionName = salesOrderDetailsSecondaryBillingPriceOptionName;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier1() {
        return this.salesOrderDetailsSecondaryBillingModifier1;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingModifier1(String salesOrderDetailsSecondaryBillingModifier1) {
        this.setSalesOrderDetailsSecondaryBillingModifier1(salesOrderDetailsSecondaryBillingModifier1);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier1(String salesOrderDetailsSecondaryBillingModifier1) {
        this.salesOrderDetailsSecondaryBillingModifier1 = salesOrderDetailsSecondaryBillingModifier1;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier2() {
        return this.salesOrderDetailsSecondaryBillingModifier2;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingModifier2(String salesOrderDetailsSecondaryBillingModifier2) {
        this.setSalesOrderDetailsSecondaryBillingModifier2(salesOrderDetailsSecondaryBillingModifier2);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier2(String salesOrderDetailsSecondaryBillingModifier2) {
        this.salesOrderDetailsSecondaryBillingModifier2 = salesOrderDetailsSecondaryBillingModifier2;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier3() {
        return this.salesOrderDetailsSecondaryBillingModifier3;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingModifier3(String salesOrderDetailsSecondaryBillingModifier3) {
        this.setSalesOrderDetailsSecondaryBillingModifier3(salesOrderDetailsSecondaryBillingModifier3);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier3(String salesOrderDetailsSecondaryBillingModifier3) {
        this.salesOrderDetailsSecondaryBillingModifier3 = salesOrderDetailsSecondaryBillingModifier3;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier4() {
        return this.salesOrderDetailsSecondaryBillingModifier4;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingModifier4(String salesOrderDetailsSecondaryBillingModifier4) {
        this.setSalesOrderDetailsSecondaryBillingModifier4(salesOrderDetailsSecondaryBillingModifier4);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier4(String salesOrderDetailsSecondaryBillingModifier4) {
        this.salesOrderDetailsSecondaryBillingModifier4 = salesOrderDetailsSecondaryBillingModifier4;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier1() {
        return this.salesOrderDetailsSecondaryBillingAdditionalModifier1;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingAdditionalModifier1(
        String salesOrderDetailsSecondaryBillingAdditionalModifier1
    ) {
        this.setSalesOrderDetailsSecondaryBillingAdditionalModifier1(salesOrderDetailsSecondaryBillingAdditionalModifier1);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier1(String salesOrderDetailsSecondaryBillingAdditionalModifier1) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier1 = salesOrderDetailsSecondaryBillingAdditionalModifier1;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier2() {
        return this.salesOrderDetailsSecondaryBillingAdditionalModifier2;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingAdditionalModifier2(
        String salesOrderDetailsSecondaryBillingAdditionalModifier2
    ) {
        this.setSalesOrderDetailsSecondaryBillingAdditionalModifier2(salesOrderDetailsSecondaryBillingAdditionalModifier2);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier2(String salesOrderDetailsSecondaryBillingAdditionalModifier2) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier2 = salesOrderDetailsSecondaryBillingAdditionalModifier2;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier3() {
        return this.salesOrderDetailsSecondaryBillingAdditionalModifier3;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingAdditionalModifier3(
        String salesOrderDetailsSecondaryBillingAdditionalModifier3
    ) {
        this.setSalesOrderDetailsSecondaryBillingAdditionalModifier3(salesOrderDetailsSecondaryBillingAdditionalModifier3);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier3(String salesOrderDetailsSecondaryBillingAdditionalModifier3) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier3 = salesOrderDetailsSecondaryBillingAdditionalModifier3;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier4() {
        return this.salesOrderDetailsSecondaryBillingAdditionalModifier4;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingAdditionalModifier4(
        String salesOrderDetailsSecondaryBillingAdditionalModifier4
    ) {
        this.setSalesOrderDetailsSecondaryBillingAdditionalModifier4(salesOrderDetailsSecondaryBillingAdditionalModifier4);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier4(String salesOrderDetailsSecondaryBillingAdditionalModifier4) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier4 = salesOrderDetailsSecondaryBillingAdditionalModifier4;
    }

    public String getSalesOrderDetailsSecondaryBillingIgnore() {
        return this.salesOrderDetailsSecondaryBillingIgnore;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondaryBillingIgnore(String salesOrderDetailsSecondaryBillingIgnore) {
        this.setSalesOrderDetailsSecondaryBillingIgnore(salesOrderDetailsSecondaryBillingIgnore);
        return this;
    }

    public void setSalesOrderDetailsSecondaryBillingIgnore(String salesOrderDetailsSecondaryBillingIgnore) {
        this.salesOrderDetailsSecondaryBillingIgnore = salesOrderDetailsSecondaryBillingIgnore;
    }

    public String getSalesOrderDetailsSecondarySpecialBilling() {
        return this.salesOrderDetailsSecondarySpecialBilling;
    }

    public SalesOrderItemDetails salesOrderDetailsSecondarySpecialBilling(String salesOrderDetailsSecondarySpecialBilling) {
        this.setSalesOrderDetailsSecondarySpecialBilling(salesOrderDetailsSecondarySpecialBilling);
        return this;
    }

    public void setSalesOrderDetailsSecondarySpecialBilling(String salesOrderDetailsSecondarySpecialBilling) {
        this.salesOrderDetailsSecondarySpecialBilling = salesOrderDetailsSecondarySpecialBilling;
    }

    public String getSalesOrderDetailsSpanDateSplitBilling() {
        return this.salesOrderDetailsSpanDateSplitBilling;
    }

    public SalesOrderItemDetails salesOrderDetailsSpanDateSplitBilling(String salesOrderDetailsSpanDateSplitBilling) {
        this.setSalesOrderDetailsSpanDateSplitBilling(salesOrderDetailsSpanDateSplitBilling);
        return this;
    }

    public void setSalesOrderDetailsSpanDateSplitBilling(String salesOrderDetailsSpanDateSplitBilling) {
        this.salesOrderDetailsSpanDateSplitBilling = salesOrderDetailsSpanDateSplitBilling;
    }

    public Long getSalesOrderDetailsCmnparCmnFormId() {
        return this.salesOrderDetailsCmnparCmnFormId;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnFormId(Long salesOrderDetailsCmnparCmnFormId) {
        this.setSalesOrderDetailsCmnparCmnFormId(salesOrderDetailsCmnparCmnFormId);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnFormId(Long salesOrderDetailsCmnparCmnFormId) {
        this.salesOrderDetailsCmnparCmnFormId = salesOrderDetailsCmnparCmnFormId;
    }

    public String getSalesOrderDetailsCmnparCmnKey() {
        return this.salesOrderDetailsCmnparCmnKey;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnKey(String salesOrderDetailsCmnparCmnKey) {
        this.setSalesOrderDetailsCmnparCmnKey(salesOrderDetailsCmnparCmnKey);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnKey(String salesOrderDetailsCmnparCmnKey) {
        this.salesOrderDetailsCmnparCmnKey = salesOrderDetailsCmnparCmnKey;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnCreateDate() {
        return this.salesOrderDetailsCmnparCmnCreateDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnCreateDate(LocalDate salesOrderDetailsCmnparCmnCreateDate) {
        this.setSalesOrderDetailsCmnparCmnCreateDate(salesOrderDetailsCmnparCmnCreateDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnCreateDate(LocalDate salesOrderDetailsCmnparCmnCreateDate) {
        this.salesOrderDetailsCmnparCmnCreateDate = salesOrderDetailsCmnparCmnCreateDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnExpirationDate() {
        return this.salesOrderDetailsCmnparCmnExpirationDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnExpirationDate(LocalDate salesOrderDetailsCmnparCmnExpirationDate) {
        this.setSalesOrderDetailsCmnparCmnExpirationDate(salesOrderDetailsCmnparCmnExpirationDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnExpirationDate(LocalDate salesOrderDetailsCmnparCmnExpirationDate) {
        this.salesOrderDetailsCmnparCmnExpirationDate = salesOrderDetailsCmnparCmnExpirationDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnInitialDate() {
        return this.salesOrderDetailsCmnparCmnInitialDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnInitialDate(LocalDate salesOrderDetailsCmnparCmnInitialDate) {
        this.setSalesOrderDetailsCmnparCmnInitialDate(salesOrderDetailsCmnparCmnInitialDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnInitialDate(LocalDate salesOrderDetailsCmnparCmnInitialDate) {
        this.salesOrderDetailsCmnparCmnInitialDate = salesOrderDetailsCmnparCmnInitialDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnRenewalDate() {
        return this.salesOrderDetailsCmnparCmnRenewalDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnRenewalDate(LocalDate salesOrderDetailsCmnparCmnRenewalDate) {
        this.setSalesOrderDetailsCmnparCmnRenewalDate(salesOrderDetailsCmnparCmnRenewalDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnRenewalDate(LocalDate salesOrderDetailsCmnparCmnRenewalDate) {
        this.salesOrderDetailsCmnparCmnRenewalDate = salesOrderDetailsCmnparCmnRenewalDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnRecertificationDate() {
        return this.salesOrderDetailsCmnparCmnRecertificationDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnRecertificationDate(LocalDate salesOrderDetailsCmnparCmnRecertificationDate) {
        this.setSalesOrderDetailsCmnparCmnRecertificationDate(salesOrderDetailsCmnparCmnRecertificationDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnRecertificationDate(LocalDate salesOrderDetailsCmnparCmnRecertificationDate) {
        this.salesOrderDetailsCmnparCmnRecertificationDate = salesOrderDetailsCmnparCmnRecertificationDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnPhysicianDate() {
        return this.salesOrderDetailsCmnparCmnPhysicianDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnPhysicianDate(LocalDate salesOrderDetailsCmnparCmnPhysicianDate) {
        this.setSalesOrderDetailsCmnparCmnPhysicianDate(salesOrderDetailsCmnparCmnPhysicianDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnPhysicianDate(LocalDate salesOrderDetailsCmnparCmnPhysicianDate) {
        this.salesOrderDetailsCmnparCmnPhysicianDate = salesOrderDetailsCmnparCmnPhysicianDate;
    }

    public String getSalesOrderDetailsCmnparCmnStatus() {
        return this.salesOrderDetailsCmnparCmnStatus;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnStatus(String salesOrderDetailsCmnparCmnStatus) {
        this.setSalesOrderDetailsCmnparCmnStatus(salesOrderDetailsCmnparCmnStatus);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnStatus(String salesOrderDetailsCmnparCmnStatus) {
        this.salesOrderDetailsCmnparCmnStatus = salesOrderDetailsCmnparCmnStatus;
    }

    public String getSalesOrderDetailsCmnparParId() {
        return this.salesOrderDetailsCmnparParId;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparParId(String salesOrderDetailsCmnparParId) {
        this.setSalesOrderDetailsCmnparParId(salesOrderDetailsCmnparParId);
        return this;
    }

    public void setSalesOrderDetailsCmnparParId(String salesOrderDetailsCmnparParId) {
        this.salesOrderDetailsCmnparParId = salesOrderDetailsCmnparParId;
    }

    public String getSalesOrderDetailsCmnparParDescr() {
        return this.salesOrderDetailsCmnparParDescr;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparParDescr(String salesOrderDetailsCmnparParDescr) {
        this.setSalesOrderDetailsCmnparParDescr(salesOrderDetailsCmnparParDescr);
        return this;
    }

    public void setSalesOrderDetailsCmnparParDescr(String salesOrderDetailsCmnparParDescr) {
        this.salesOrderDetailsCmnparParDescr = salesOrderDetailsCmnparParDescr;
    }

    public LocalDate getSalesOrderDetailsCmnparParInitialDate() {
        return this.salesOrderDetailsCmnparParInitialDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparParInitialDate(LocalDate salesOrderDetailsCmnparParInitialDate) {
        this.setSalesOrderDetailsCmnparParInitialDate(salesOrderDetailsCmnparParInitialDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparParInitialDate(LocalDate salesOrderDetailsCmnparParInitialDate) {
        this.salesOrderDetailsCmnparParInitialDate = salesOrderDetailsCmnparParInitialDate;
    }

    public LocalDate getSalesOrderDetailsCmnparParExpirationDate() {
        return this.salesOrderDetailsCmnparParExpirationDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparParExpirationDate(LocalDate salesOrderDetailsCmnparParExpirationDate) {
        this.setSalesOrderDetailsCmnparParExpirationDate(salesOrderDetailsCmnparParExpirationDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparParExpirationDate(LocalDate salesOrderDetailsCmnparParExpirationDate) {
        this.salesOrderDetailsCmnparParExpirationDate = salesOrderDetailsCmnparParExpirationDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnLogDate() {
        return this.salesOrderDetailsCmnparCmnLogDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnLogDate(LocalDate salesOrderDetailsCmnparCmnLogDate) {
        this.setSalesOrderDetailsCmnparCmnLogDate(salesOrderDetailsCmnparCmnLogDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnLogDate(LocalDate salesOrderDetailsCmnparCmnLogDate) {
        this.salesOrderDetailsCmnparCmnLogDate = salesOrderDetailsCmnparCmnLogDate;
    }

    public Long getSalesOrderDetailsCmnparCmnLengthOfNeed() {
        return this.salesOrderDetailsCmnparCmnLengthOfNeed;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnLengthOfNeed(Long salesOrderDetailsCmnparCmnLengthOfNeed) {
        this.setSalesOrderDetailsCmnparCmnLengthOfNeed(salesOrderDetailsCmnparCmnLengthOfNeed);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnLengthOfNeed(Long salesOrderDetailsCmnparCmnLengthOfNeed) {
        this.salesOrderDetailsCmnparCmnLengthOfNeed = salesOrderDetailsCmnparCmnLengthOfNeed;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnPrintedDate() {
        return this.salesOrderDetailsCmnparCmnPrintedDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnPrintedDate(LocalDate salesOrderDetailsCmnparCmnPrintedDate) {
        this.setSalesOrderDetailsCmnparCmnPrintedDate(salesOrderDetailsCmnparCmnPrintedDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnPrintedDate(LocalDate salesOrderDetailsCmnparCmnPrintedDate) {
        this.salesOrderDetailsCmnparCmnPrintedDate = salesOrderDetailsCmnparCmnPrintedDate;
    }

    public String getSalesOrderDetailsCmnparCmnPrintedBy() {
        return this.salesOrderDetailsCmnparCmnPrintedBy;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnPrintedBy(String salesOrderDetailsCmnparCmnPrintedBy) {
        this.setSalesOrderDetailsCmnparCmnPrintedBy(salesOrderDetailsCmnparCmnPrintedBy);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnPrintedBy(String salesOrderDetailsCmnparCmnPrintedBy) {
        this.salesOrderDetailsCmnparCmnPrintedBy = salesOrderDetailsCmnparCmnPrintedBy;
    }

    public LocalDate getSalesOrderDetailsCmnparFaxedDate() {
        return this.salesOrderDetailsCmnparFaxedDate;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparFaxedDate(LocalDate salesOrderDetailsCmnparFaxedDate) {
        this.setSalesOrderDetailsCmnparFaxedDate(salesOrderDetailsCmnparFaxedDate);
        return this;
    }

    public void setSalesOrderDetailsCmnparFaxedDate(LocalDate salesOrderDetailsCmnparFaxedDate) {
        this.salesOrderDetailsCmnparFaxedDate = salesOrderDetailsCmnparFaxedDate;
    }

    public String getSalesOrderDetailsCmnparCmnPlaceholder() {
        return this.salesOrderDetailsCmnparCmnPlaceholder;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnPlaceholder(String salesOrderDetailsCmnparCmnPlaceholder) {
        this.setSalesOrderDetailsCmnparCmnPlaceholder(salesOrderDetailsCmnparCmnPlaceholder);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnPlaceholder(String salesOrderDetailsCmnparCmnPlaceholder) {
        this.salesOrderDetailsCmnparCmnPlaceholder = salesOrderDetailsCmnparCmnPlaceholder;
    }

    public String getSalesOrderDetailsCmnparCmnFaxedBy() {
        return this.salesOrderDetailsCmnparCmnFaxedBy;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnFaxedBy(String salesOrderDetailsCmnparCmnFaxedBy) {
        this.setSalesOrderDetailsCmnparCmnFaxedBy(salesOrderDetailsCmnparCmnFaxedBy);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnFaxedBy(String salesOrderDetailsCmnparCmnFaxedBy) {
        this.salesOrderDetailsCmnparCmnFaxedBy = salesOrderDetailsCmnparCmnFaxedBy;
    }

    public String getSalesOrderDetailsCmnparCmnLoggedBy() {
        return this.salesOrderDetailsCmnparCmnLoggedBy;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparCmnLoggedBy(String salesOrderDetailsCmnparCmnLoggedBy) {
        this.setSalesOrderDetailsCmnparCmnLoggedBy(salesOrderDetailsCmnparCmnLoggedBy);
        return this;
    }

    public void setSalesOrderDetailsCmnparCmnLoggedBy(String salesOrderDetailsCmnparCmnLoggedBy) {
        this.salesOrderDetailsCmnparCmnLoggedBy = salesOrderDetailsCmnparCmnLoggedBy;
    }

    public Long getSalesOrderDetailsCmnparNumberOfRefills() {
        return this.salesOrderDetailsCmnparNumberOfRefills;
    }

    public SalesOrderItemDetails salesOrderDetailsCmnparNumberOfRefills(Long salesOrderDetailsCmnparNumberOfRefills) {
        this.setSalesOrderDetailsCmnparNumberOfRefills(salesOrderDetailsCmnparNumberOfRefills);
        return this;
    }

    public void setSalesOrderDetailsCmnparNumberOfRefills(Long salesOrderDetailsCmnparNumberOfRefills) {
        this.salesOrderDetailsCmnparNumberOfRefills = salesOrderDetailsCmnparNumberOfRefills;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderItemDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderItemDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public SalesOrderItemDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderItemDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderItemDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSalesOrderDetailsManufacturerItemIdNumber() {
        return this.salesOrderDetailsManufacturerItemIdNumber;
    }

    public SalesOrderItemDetails salesOrderDetailsManufacturerItemIdNumber(String salesOrderDetailsManufacturerItemIdNumber) {
        this.setSalesOrderDetailsManufacturerItemIdNumber(salesOrderDetailsManufacturerItemIdNumber);
        return this;
    }

    public void setSalesOrderDetailsManufacturerItemIdNumber(String salesOrderDetailsManufacturerItemIdNumber) {
        this.salesOrderDetailsManufacturerItemIdNumber = salesOrderDetailsManufacturerItemIdNumber;
    }

    public Long getCmnId() {
        return this.cmnId;
    }

    public SalesOrderItemDetails cmnId(Long cmnId) {
        this.setCmnId(cmnId);
        return this;
    }

    public void setCmnId(Long cmnId) {
        this.cmnId = cmnId;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderItemDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderItemDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSalesOrderItemDetailsUuid() {
        return this.salesOrderItemDetailsUuid;
    }

    public SalesOrderItemDetails salesOrderItemDetailsUuid(UUID salesOrderItemDetailsUuid) {
        this.setSalesOrderItemDetailsUuid(salesOrderItemDetailsUuid);
        return this;
    }

    public void setSalesOrderItemDetailsUuid(UUID salesOrderItemDetailsUuid) {
        this.salesOrderItemDetailsUuid = salesOrderItemDetailsUuid;
    }

    public String getSalesOrderItemNumber() {
        return this.salesOrderItemNumber;
    }

    public SalesOrderItemDetails salesOrderItemNumber(String salesOrderItemNumber) {
        this.setSalesOrderItemNumber(salesOrderItemNumber);
        return this;
    }

    public void setSalesOrderItemNumber(String salesOrderItemNumber) {
        this.salesOrderItemNumber = salesOrderItemNumber;
    }

    public String getIsAssetTagged() {
        return this.isAssetTagged;
    }

    public SalesOrderItemDetails isAssetTagged(String isAssetTagged) {
        this.setIsAssetTagged(isAssetTagged);
        return this;
    }

    public void setIsAssetTagged(String isAssetTagged) {
        this.isAssetTagged = isAssetTagged;
    }

    public Long getItemSerialNo() {
        return this.itemSerialNo;
    }

    public SalesOrderItemDetails itemSerialNo(Long itemSerialNo) {
        this.setItemSerialNo(itemSerialNo);
        return this;
    }

    public void setItemSerialNo(Long itemSerialNo) {
        this.itemSerialNo = itemSerialNo;
    }

    public String getSalesOrderDetailsIcdPointer() {
        return this.salesOrderDetailsIcdPointer;
    }

    public SalesOrderItemDetails salesOrderDetailsIcdPointer(String salesOrderDetailsIcdPointer) {
        this.setSalesOrderDetailsIcdPointer(salesOrderDetailsIcdPointer);
        return this;
    }

    public void setSalesOrderDetailsIcdPointer(String salesOrderDetailsIcdPointer) {
        this.salesOrderDetailsIcdPointer = salesOrderDetailsIcdPointer;
    }

    public String getProcedureIdentifier() {
        return this.procedureIdentifier;
    }

    public SalesOrderItemDetails procedureIdentifier(String procedureIdentifier) {
        this.setProcedureIdentifier(procedureIdentifier);
        return this;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getParNo() {
        return this.parNo;
    }

    public SalesOrderItemDetails parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getWhetherSerialised() {
        return this.whetherSerialised;
    }

    public SalesOrderItemDetails whetherSerialised(String whetherSerialised) {
        this.setWhetherSerialised(whetherSerialised);
        return this;
    }

    public void setWhetherSerialised(String whetherSerialised) {
        this.whetherSerialised = whetherSerialised;
    }

    public String getPickupExchangeNo() {
        return this.pickupExchangeNo;
    }

    public SalesOrderItemDetails pickupExchangeNo(String pickupExchangeNo) {
        this.setPickupExchangeNo(pickupExchangeNo);
        return this;
    }

    public void setPickupExchangeNo(String pickupExchangeNo) {
        this.pickupExchangeNo = pickupExchangeNo;
    }

    public String getSalesOrderAbnUserResponse() {
        return this.salesOrderAbnUserResponse;
    }

    public SalesOrderItemDetails salesOrderAbnUserResponse(String salesOrderAbnUserResponse) {
        this.setSalesOrderAbnUserResponse(salesOrderAbnUserResponse);
        return this;
    }

    public void setSalesOrderAbnUserResponse(String salesOrderAbnUserResponse) {
        this.salesOrderAbnUserResponse = salesOrderAbnUserResponse;
    }

    public String getIsDropshipAllowed() {
        return this.isDropshipAllowed;
    }

    public SalesOrderItemDetails isDropshipAllowed(String isDropshipAllowed) {
        this.setIsDropshipAllowed(isDropshipAllowed);
        return this;
    }

    public void setIsDropshipAllowed(String isDropshipAllowed) {
        this.isDropshipAllowed = isDropshipAllowed;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public SalesOrderItemDetails poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public UUID getPurchaseOrderUuid() {
        return this.purchaseOrderUuid;
    }

    public SalesOrderItemDetails purchaseOrderUuid(UUID purchaseOrderUuid) {
        this.setPurchaseOrderUuid(purchaseOrderUuid);
        return this;
    }

    public void setPurchaseOrderUuid(UUID purchaseOrderUuid) {
        this.purchaseOrderUuid = purchaseOrderUuid;
    }

    public String getIsResupplyType() {
        return this.isResupplyType;
    }

    public SalesOrderItemDetails isResupplyType(String isResupplyType) {
        this.setIsResupplyType(isResupplyType);
        return this;
    }

    public void setIsResupplyType(String isResupplyType) {
        this.isResupplyType = isResupplyType;
    }

    public Long getFrequencyCount() {
        return this.frequencyCount;
    }

    public SalesOrderItemDetails frequencyCount(Long frequencyCount) {
        this.setFrequencyCount(frequencyCount);
        return this;
    }

    public void setFrequencyCount(Long frequencyCount) {
        this.frequencyCount = frequencyCount;
    }

    public Long getFrequencyInterval() {
        return this.frequencyInterval;
    }

    public SalesOrderItemDetails frequencyInterval(Long frequencyInterval) {
        this.setFrequencyInterval(frequencyInterval);
        return this;
    }

    public void setFrequencyInterval(Long frequencyInterval) {
        this.frequencyInterval = frequencyInterval;
    }

    public Long getItemGroupId() {
        return this.itemGroupId;
    }

    public SalesOrderItemDetails itemGroupId(Long itemGroupId) {
        this.setItemGroupId(itemGroupId);
        return this;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderItemDetails)) {
            return false;
        }
        return salesOrderItemDetailsId != null && salesOrderItemDetailsId.equals(((SalesOrderItemDetails) o).salesOrderItemDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderItemDetails{" +
            "salesOrderItemDetailsId=" + getSalesOrderItemDetailsId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", patientId=" + getPatientId() +
            ", itemLocationId=" + getItemLocationId() +
            ", salesOrderDetailsItemId=" + getSalesOrderDetailsItemId() +
            ", salesOrderDetailsItemName='" + getSalesOrderDetailsItemName() + "'" +
            ", salesOrderDetailsStockingUom='" + getSalesOrderDetailsStockingUom() + "'" +
            ", itemAssetNo='" + getItemAssetNo() + "'" +
            ", salesOrderDetailsItemDescription='" + getSalesOrderDetailsItemDescription() + "'" +
            ", salesOrderDetailsDefaultVendor='" + getSalesOrderDetailsDefaultVendor() + "'" +
            ", salesOrderDetailsOriginalDos='" + getSalesOrderDetailsOriginalDos() + "'" +
            ", salesOrderDetailsPreviousBillingDate='" + getSalesOrderDetailsPreviousBillingDate() + "'" +
            ", salesOrderDetailsNextBillingDate='" + getSalesOrderDetailsNextBillingDate() + "'" +
            ", salesOrderDetailsDosTo='" + getSalesOrderDetailsDosTo() + "'" +
            ", salesOrderDetailsNextPeriod='" + getSalesOrderDetailsNextPeriod() + "'" +
            ", salesOrderDetailsSpecialPricing='" + getSalesOrderDetailsSpecialPricing() + "'" +
            ", salesOrderDetailsPriceOverride='" + getSalesOrderDetailsPriceOverride() + "'" +
            ", salesOrderDetailsSpecialTaxRate=" + getSalesOrderDetailsSpecialTaxRate() +
            ", salesOrderDetailsQty=" + getSalesOrderDetailsQty() +
            ", salesOrderDetailsBqty=" + getSalesOrderDetailsBqty() +
            ", salesOrderDetailsLineQty=" + getSalesOrderDetailsLineQty() +
            ", salesOrderDetailsProcCode='" + getSalesOrderDetailsProcCode() + "'" +
            ", salesOrderDetailsPriceOption='" + getSalesOrderDetailsPriceOption() + "'" +
            ", salesOrderDetailsModifier1='" + getSalesOrderDetailsModifier1() + "'" +
            ", salesOrderDetailsModifier2='" + getSalesOrderDetailsModifier2() + "'" +
            ", salesOrderDetailsModifier3='" + getSalesOrderDetailsModifier3() + "'" +
            ", salesOrderDetailsModifier4='" + getSalesOrderDetailsModifier4() + "'" +
            ", salesOrderDetailsChargeAmt=" + getSalesOrderDetailsChargeAmt() +
            ", salesOrderDetailsAllowedAmt=" + getSalesOrderDetailsAllowedAmt() +
            ", salesOrderDetailsTaxable='" + getSalesOrderDetailsTaxable() + "'" +
            ", salesOrderDetailsAbn='" + getSalesOrderDetailsAbn() + "'" +
            ", salesOrderDetailsAbnUpgrade='" + getSalesOrderDetailsAbnUpgrade() + "'" +
            ", salesOrderDetailsAbnPrintDate='" + getSalesOrderDetailsAbnPrintDate() + "'" +
            ", salesOrderDetailsAbnItem='" + getSalesOrderDetailsAbnItem() + "'" +
            ", salesOrderDetailsAbnProcCode='" + getSalesOrderDetailsAbnProcCode() + "'" +
            ", salesOrderDetailsAbnAllow='" + getSalesOrderDetailsAbnAllow() + "'" +
            ", salesOrderDetailsAbnCharge=" + getSalesOrderDetailsAbnCharge() +
            ", salesOrderDetailsAbnModifier1='" + getSalesOrderDetailsAbnModifier1() + "'" +
            ", salesOrderDetailsAbnModifier2='" + getSalesOrderDetailsAbnModifier2() + "'" +
            ", salesOrderDetailsTaxRate=" + getSalesOrderDetailsTaxRate() +
            ", salesOrderDetailsTaxZone='" + getSalesOrderDetailsTaxZone() + "'" +
            ", salesOrderDetailsNonTaxReason='" + getSalesOrderDetailsNonTaxReason() + "'" +
            ", salesOrderDetailsNote='" + getSalesOrderDetailsNote() + "'" +
            ", salesOrderDetailsSaleType='" + getSalesOrderDetailsSaleType() + "'" +
            ", salesOrderDetailsItemGroup='" + getSalesOrderDetailsItemGroup() + "'" +
            ", salesOrderDetailsItemUser1='" + getSalesOrderDetailsItemUser1() + "'" +
            ", salesOrderDetailsItemUser2='" + getSalesOrderDetailsItemUser2() + "'" +
            ", salesOrderDetailsItemUser3='" + getSalesOrderDetailsItemUser3() + "'" +
            ", salesOrderDetailsItemUser4='" + getSalesOrderDetailsItemUser4() + "'" +
            ", salesOrderDetailsConvertedToPurchase='" + getSalesOrderDetailsConvertedToPurchase() + "'" +
            ", salesOrderDetailsManualConvertToPurchaseMctp='" + getSalesOrderDetailsManualConvertToPurchaseMctp() + "'" +
            ", salesOrderDetailsMctpChargeAmt=" + getSalesOrderDetailsMctpChargeAmt() +
            ", salesOrderDetailsMctpAllowedAmt=" + getSalesOrderDetailsMctpAllowedAmt() +
            ", salesOrderDetailsMctpModifier1='" + getSalesOrderDetailsMctpModifier1() + "'" +
            ", salesOrderDetailsMctpModifier2='" + getSalesOrderDetailsMctpModifier2() + "'" +
            ", salesOrderDetailsMctpModifier3='" + getSalesOrderDetailsMctpModifier3() + "'" +
            ", salesOrderDetailsMctpModifier4='" + getSalesOrderDetailsMctpModifier4() + "'" +
            ", salesOrderDetailsMctpPeriod=" + getSalesOrderDetailsMctpPeriod() +
            ", salesOrderDetailsAddtlModifier1='" + getSalesOrderDetailsAddtlModifier1() + "'" +
            ", salesOrderDetailsAddtlModifier2='" + getSalesOrderDetailsAddtlModifier2() + "'" +
            ", salesOrderDetailsAddtlModifier3='" + getSalesOrderDetailsAddtlModifier3() + "'" +
            ", salesOrderDetailsAddtlModifier4='" + getSalesOrderDetailsAddtlModifier4() + "'" +
            ", salesOrderDetailsNextDateOfService='" + getSalesOrderDetailsNextDateOfService() + "'" +
            ", salesOrderDetailsPriceTable='" + getSalesOrderDetailsPriceTable() + "'" +
            ", salesOrderDetailsPriceOptionName='" + getSalesOrderDetailsPriceOptionName() + "'" +
            ", salesOrderDetailsExtendedChargeAmount=" + getSalesOrderDetailsExtendedChargeAmount() +
            ", salesOrderDetailsExtendedAllowanceAmount=" + getSalesOrderDetailsExtendedAllowanceAmount() +
            ", salesOrderDetailsItemNdcCode='" + getSalesOrderDetailsItemNdcCode() + "'" +
            ", salesOrderDetailsManufacturer='" + getSalesOrderDetailsManufacturer() + "'" +
            ", salesOrderDetailsCbPricing='" + getSalesOrderDetailsCbPricing() + "'" +
            ", salesOrderDetailsCbPriceTableOverride='" + getSalesOrderDetailsCbPriceTableOverride() + "'" +
            ", salesOrderDetailsCbOverride='" + getSalesOrderDetailsCbOverride() + "'" +
            ", salesOrderDetailsMessages='" + getSalesOrderDetailsMessages() + "'" +
            ", salesOrderDetailsLocation=" + getSalesOrderDetailsLocation() +
            ", salesOrderDetailsCaloriesPerDay=" + getSalesOrderDetailsCaloriesPerDay() +
            ", salesOrderDetailsSecondaryBillingProcudureCode='" + getSalesOrderDetailsSecondaryBillingProcudureCode() + "'" +
            ", salesOrderDetailsSecondaryBillingPriceOption='" + getSalesOrderDetailsSecondaryBillingPriceOption() + "'" +
            ", salesOrderDetailsSecondaryBillingPriceOptionName='" + getSalesOrderDetailsSecondaryBillingPriceOptionName() + "'" +
            ", salesOrderDetailsSecondaryBillingModifier1='" + getSalesOrderDetailsSecondaryBillingModifier1() + "'" +
            ", salesOrderDetailsSecondaryBillingModifier2='" + getSalesOrderDetailsSecondaryBillingModifier2() + "'" +
            ", salesOrderDetailsSecondaryBillingModifier3='" + getSalesOrderDetailsSecondaryBillingModifier3() + "'" +
            ", salesOrderDetailsSecondaryBillingModifier4='" + getSalesOrderDetailsSecondaryBillingModifier4() + "'" +
            ", salesOrderDetailsSecondaryBillingAdditionalModifier1='" + getSalesOrderDetailsSecondaryBillingAdditionalModifier1() + "'" +
            ", salesOrderDetailsSecondaryBillingAdditionalModifier2='" + getSalesOrderDetailsSecondaryBillingAdditionalModifier2() + "'" +
            ", salesOrderDetailsSecondaryBillingAdditionalModifier3='" + getSalesOrderDetailsSecondaryBillingAdditionalModifier3() + "'" +
            ", salesOrderDetailsSecondaryBillingAdditionalModifier4='" + getSalesOrderDetailsSecondaryBillingAdditionalModifier4() + "'" +
            ", salesOrderDetailsSecondaryBillingIgnore='" + getSalesOrderDetailsSecondaryBillingIgnore() + "'" +
            ", salesOrderDetailsSecondarySpecialBilling='" + getSalesOrderDetailsSecondarySpecialBilling() + "'" +
            ", salesOrderDetailsSpanDateSplitBilling='" + getSalesOrderDetailsSpanDateSplitBilling() + "'" +
            ", salesOrderDetailsCmnparCmnFormId=" + getSalesOrderDetailsCmnparCmnFormId() +
            ", salesOrderDetailsCmnparCmnKey='" + getSalesOrderDetailsCmnparCmnKey() + "'" +
            ", salesOrderDetailsCmnparCmnCreateDate='" + getSalesOrderDetailsCmnparCmnCreateDate() + "'" +
            ", salesOrderDetailsCmnparCmnExpirationDate='" + getSalesOrderDetailsCmnparCmnExpirationDate() + "'" +
            ", salesOrderDetailsCmnparCmnInitialDate='" + getSalesOrderDetailsCmnparCmnInitialDate() + "'" +
            ", salesOrderDetailsCmnparCmnRenewalDate='" + getSalesOrderDetailsCmnparCmnRenewalDate() + "'" +
            ", salesOrderDetailsCmnparCmnRecertificationDate='" + getSalesOrderDetailsCmnparCmnRecertificationDate() + "'" +
            ", salesOrderDetailsCmnparCmnPhysicianDate='" + getSalesOrderDetailsCmnparCmnPhysicianDate() + "'" +
            ", salesOrderDetailsCmnparCmnStatus='" + getSalesOrderDetailsCmnparCmnStatus() + "'" +
            ", salesOrderDetailsCmnparParId='" + getSalesOrderDetailsCmnparParId() + "'" +
            ", salesOrderDetailsCmnparParDescr='" + getSalesOrderDetailsCmnparParDescr() + "'" +
            ", salesOrderDetailsCmnparParInitialDate='" + getSalesOrderDetailsCmnparParInitialDate() + "'" +
            ", salesOrderDetailsCmnparParExpirationDate='" + getSalesOrderDetailsCmnparParExpirationDate() + "'" +
            ", salesOrderDetailsCmnparCmnLogDate='" + getSalesOrderDetailsCmnparCmnLogDate() + "'" +
            ", salesOrderDetailsCmnparCmnLengthOfNeed=" + getSalesOrderDetailsCmnparCmnLengthOfNeed() +
            ", salesOrderDetailsCmnparCmnPrintedDate='" + getSalesOrderDetailsCmnparCmnPrintedDate() + "'" +
            ", salesOrderDetailsCmnparCmnPrintedBy='" + getSalesOrderDetailsCmnparCmnPrintedBy() + "'" +
            ", salesOrderDetailsCmnparFaxedDate='" + getSalesOrderDetailsCmnparFaxedDate() + "'" +
            ", salesOrderDetailsCmnparCmnPlaceholder='" + getSalesOrderDetailsCmnparCmnPlaceholder() + "'" +
            ", salesOrderDetailsCmnparCmnFaxedBy='" + getSalesOrderDetailsCmnparCmnFaxedBy() + "'" +
            ", salesOrderDetailsCmnparCmnLoggedBy='" + getSalesOrderDetailsCmnparCmnLoggedBy() + "'" +
            ", salesOrderDetailsCmnparNumberOfRefills=" + getSalesOrderDetailsCmnparNumberOfRefills() +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", salesOrderDetailsManufacturerItemIdNumber='" + getSalesOrderDetailsManufacturerItemIdNumber() + "'" +
            ", cmnId=" + getCmnId() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", salesOrderItemDetailsUuid='" + getSalesOrderItemDetailsUuid() + "'" +
            ", salesOrderItemNumber='" + getSalesOrderItemNumber() + "'" +
            ", isAssetTagged='" + getIsAssetTagged() + "'" +
            ", itemSerialNo=" + getItemSerialNo() +
            ", salesOrderDetailsIcdPointer='" + getSalesOrderDetailsIcdPointer() + "'" +
            ", procedureIdentifier='" + getProcedureIdentifier() + "'" +
            ", parNo='" + getParNo() + "'" +
            ", whetherSerialised='" + getWhetherSerialised() + "'" +
            ", pickupExchangeNo='" + getPickupExchangeNo() + "'" +
            ", salesOrderAbnUserResponse='" + getSalesOrderAbnUserResponse() + "'" +
            ", isDropshipAllowed='" + getIsDropshipAllowed() + "'" +
            ", poNumber='" + getPoNumber() + "'" +
            ", purchaseOrderUuid='" + getPurchaseOrderUuid() + "'" +
            ", isResupplyType='" + getIsResupplyType() + "'" +
            ", frequencyCount=" + getFrequencyCount() +
            ", frequencyInterval=" + getFrequencyInterval() +
            ", itemGroupId=" + getItemGroupId() +
            "}";
    }
}
