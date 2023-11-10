package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails} entity.
 */
public class SalesOrderItemDetailsDTO implements Serializable {

    private Long salesOrderItemDetailsId;

    private Long salesOrderId;

    private Long patientId;

    private Long itemLocationId;

    private Long salesOrderDetailsItemId;

    private String salesOrderDetailsItemName;

    private String salesOrderDetailsStockingUom;

    private String itemAssetNo;

    private String salesOrderDetailsItemDescription;

    private String salesOrderDetailsDefaultVendor;

    private LocalDate salesOrderDetailsOriginalDos;

    private LocalDate salesOrderDetailsPreviousBillingDate;

    private LocalDate salesOrderDetailsNextBillingDate;

    private LocalDate salesOrderDetailsDosTo;

    private String salesOrderDetailsNextPeriod;

    private String salesOrderDetailsSpecialPricing;

    private String salesOrderDetailsPriceOverride;

    private Long salesOrderDetailsSpecialTaxRate;

    private Long salesOrderDetailsQty;

    private Long salesOrderDetailsBqty;

    private Long salesOrderDetailsLineQty;

    private String salesOrderDetailsProcCode;

    private String salesOrderDetailsPriceOption;

    private String salesOrderDetailsModifier1;

    private String salesOrderDetailsModifier2;

    private String salesOrderDetailsModifier3;

    private String salesOrderDetailsModifier4;

    private Double salesOrderDetailsChargeAmt;

    private Double salesOrderDetailsAllowedAmt;

    private String salesOrderDetailsTaxable;

    private String salesOrderDetailsAbn;

    private String salesOrderDetailsAbnUpgrade;

    private LocalDate salesOrderDetailsAbnPrintDate;

    private String salesOrderDetailsAbnItem;

    private String salesOrderDetailsAbnProcCode;

    private String salesOrderDetailsAbnAllow;

    private Double salesOrderDetailsAbnCharge;

    private String salesOrderDetailsAbnModifier1;

    private String salesOrderDetailsAbnModifier2;

    private Long salesOrderDetailsTaxRate;

    private String salesOrderDetailsTaxZone;

    private String salesOrderDetailsNonTaxReason;

    private String salesOrderDetailsNote;

    private String salesOrderDetailsSaleType;

    private String salesOrderDetailsItemGroup;

    private String salesOrderDetailsItemUser1;

    private String salesOrderDetailsItemUser2;

    private String salesOrderDetailsItemUser3;

    private String salesOrderDetailsItemUser4;

    private String salesOrderDetailsConvertedToPurchase;

    private String salesOrderDetailsManualConvertToPurchaseMctp;

    private Double salesOrderDetailsMctpChargeAmt;

    private Double salesOrderDetailsMctpAllowedAmt;

    private String salesOrderDetailsMctpModifier1;

    private String salesOrderDetailsMctpModifier2;

    private String salesOrderDetailsMctpModifier3;

    private String salesOrderDetailsMctpModifier4;

    private Long salesOrderDetailsMctpPeriod;

    private String salesOrderDetailsAddtlModifier1;

    private String salesOrderDetailsAddtlModifier2;

    private String salesOrderDetailsAddtlModifier3;

    private String salesOrderDetailsAddtlModifier4;

    private LocalDate salesOrderDetailsNextDateOfService;

    private String salesOrderDetailsPriceTable;

    private String salesOrderDetailsPriceOptionName;

    private Double salesOrderDetailsExtendedChargeAmount;

    private Double salesOrderDetailsExtendedAllowanceAmount;

    private String salesOrderDetailsItemNdcCode;

    private String salesOrderDetailsManufacturer;

    private String salesOrderDetailsCbPricing;

    private String salesOrderDetailsCbPriceTableOverride;

    private String salesOrderDetailsCbOverride;

    private String salesOrderDetailsMessages;

    private Long salesOrderDetailsLocation;

    private Long salesOrderDetailsCaloriesPerDay;

    private String salesOrderDetailsSecondaryBillingProcudureCode;

    private String salesOrderDetailsSecondaryBillingPriceOption;

    private String salesOrderDetailsSecondaryBillingPriceOptionName;

    private String salesOrderDetailsSecondaryBillingModifier1;

    private String salesOrderDetailsSecondaryBillingModifier2;

    private String salesOrderDetailsSecondaryBillingModifier3;

    private String salesOrderDetailsSecondaryBillingModifier4;

    private String salesOrderDetailsSecondaryBillingAdditionalModifier1;

    private String salesOrderDetailsSecondaryBillingAdditionalModifier2;

    private String salesOrderDetailsSecondaryBillingAdditionalModifier3;

    private String salesOrderDetailsSecondaryBillingAdditionalModifier4;

    private String salesOrderDetailsSecondaryBillingIgnore;

    private String salesOrderDetailsSecondarySpecialBilling;

    private String salesOrderDetailsSpanDateSplitBilling;

    private Long salesOrderDetailsCmnparCmnFormId;

    private String salesOrderDetailsCmnparCmnKey;

    private LocalDate salesOrderDetailsCmnparCmnCreateDate;

    private LocalDate salesOrderDetailsCmnparCmnExpirationDate;

    private LocalDate salesOrderDetailsCmnparCmnInitialDate;

    private LocalDate salesOrderDetailsCmnparCmnRenewalDate;

    private LocalDate salesOrderDetailsCmnparCmnRecertificationDate;

    private LocalDate salesOrderDetailsCmnparCmnPhysicianDate;

    private String salesOrderDetailsCmnparCmnStatus;

    private String salesOrderDetailsCmnparParId;

    private String salesOrderDetailsCmnparParDescr;

    private LocalDate salesOrderDetailsCmnparParInitialDate;

    private LocalDate salesOrderDetailsCmnparParExpirationDate;

    private LocalDate salesOrderDetailsCmnparCmnLogDate;

    private Long salesOrderDetailsCmnparCmnLengthOfNeed;

    private LocalDate salesOrderDetailsCmnparCmnPrintedDate;

    private String salesOrderDetailsCmnparCmnPrintedBy;

    private LocalDate salesOrderDetailsCmnparFaxedDate;

    private String salesOrderDetailsCmnparCmnPlaceholder;

    private String salesOrderDetailsCmnparCmnFaxedBy;

    private String salesOrderDetailsCmnparCmnLoggedBy;

    private Long salesOrderDetailsCmnparNumberOfRefills;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String salesOrderDetailsManufacturerItemIdNumber;

    private Long cmnId;

    private String createdByName;

    private String updatedByName;

    private UUID salesOrderItemDetailsUuid;

    private String salesOrderItemNumber;

    private String isAssetTagged;

    private Long itemSerialNo;

    private String salesOrderDetailsIcdPointer;

    private String procedureIdentifier;

    private String parNo;

    private String whetherSerialised;

    private String pickupExchangeNo;

    private String salesOrderAbnUserResponse;

    private String isDropshipAllowed;

    private String poNumber;

    private UUID purchaseOrderUuid;

    private String isResupplyType;

    private Long frequencyCount;

    private Long frequencyInterval;

    private Long itemGroupId;

    public Long getSalesOrderItemDetailsId() {
        return salesOrderItemDetailsId;
    }

    public void setSalesOrderItemDetailsId(Long salesOrderItemDetailsId) {
        this.salesOrderItemDetailsId = salesOrderItemDetailsId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public Long getSalesOrderDetailsItemId() {
        return salesOrderDetailsItemId;
    }

    public void setSalesOrderDetailsItemId(Long salesOrderDetailsItemId) {
        this.salesOrderDetailsItemId = salesOrderDetailsItemId;
    }

    public String getSalesOrderDetailsItemName() {
        return salesOrderDetailsItemName;
    }

    public void setSalesOrderDetailsItemName(String salesOrderDetailsItemName) {
        this.salesOrderDetailsItemName = salesOrderDetailsItemName;
    }

    public String getSalesOrderDetailsStockingUom() {
        return salesOrderDetailsStockingUom;
    }

    public void setSalesOrderDetailsStockingUom(String salesOrderDetailsStockingUom) {
        this.salesOrderDetailsStockingUom = salesOrderDetailsStockingUom;
    }

    public String getItemAssetNo() {
        return itemAssetNo;
    }

    public void setItemAssetNo(String itemAssetNo) {
        this.itemAssetNo = itemAssetNo;
    }

    public String getSalesOrderDetailsItemDescription() {
        return salesOrderDetailsItemDescription;
    }

    public void setSalesOrderDetailsItemDescription(String salesOrderDetailsItemDescription) {
        this.salesOrderDetailsItemDescription = salesOrderDetailsItemDescription;
    }

    public String getSalesOrderDetailsDefaultVendor() {
        return salesOrderDetailsDefaultVendor;
    }

    public void setSalesOrderDetailsDefaultVendor(String salesOrderDetailsDefaultVendor) {
        this.salesOrderDetailsDefaultVendor = salesOrderDetailsDefaultVendor;
    }

    public LocalDate getSalesOrderDetailsOriginalDos() {
        return salesOrderDetailsOriginalDos;
    }

    public void setSalesOrderDetailsOriginalDos(LocalDate salesOrderDetailsOriginalDos) {
        this.salesOrderDetailsOriginalDos = salesOrderDetailsOriginalDos;
    }

    public LocalDate getSalesOrderDetailsPreviousBillingDate() {
        return salesOrderDetailsPreviousBillingDate;
    }

    public void setSalesOrderDetailsPreviousBillingDate(LocalDate salesOrderDetailsPreviousBillingDate) {
        this.salesOrderDetailsPreviousBillingDate = salesOrderDetailsPreviousBillingDate;
    }

    public LocalDate getSalesOrderDetailsNextBillingDate() {
        return salesOrderDetailsNextBillingDate;
    }

    public void setSalesOrderDetailsNextBillingDate(LocalDate salesOrderDetailsNextBillingDate) {
        this.salesOrderDetailsNextBillingDate = salesOrderDetailsNextBillingDate;
    }

    public LocalDate getSalesOrderDetailsDosTo() {
        return salesOrderDetailsDosTo;
    }

    public void setSalesOrderDetailsDosTo(LocalDate salesOrderDetailsDosTo) {
        this.salesOrderDetailsDosTo = salesOrderDetailsDosTo;
    }

    public String getSalesOrderDetailsNextPeriod() {
        return salesOrderDetailsNextPeriod;
    }

    public void setSalesOrderDetailsNextPeriod(String salesOrderDetailsNextPeriod) {
        this.salesOrderDetailsNextPeriod = salesOrderDetailsNextPeriod;
    }

    public String getSalesOrderDetailsSpecialPricing() {
        return salesOrderDetailsSpecialPricing;
    }

    public void setSalesOrderDetailsSpecialPricing(String salesOrderDetailsSpecialPricing) {
        this.salesOrderDetailsSpecialPricing = salesOrderDetailsSpecialPricing;
    }

    public String getSalesOrderDetailsPriceOverride() {
        return salesOrderDetailsPriceOverride;
    }

    public void setSalesOrderDetailsPriceOverride(String salesOrderDetailsPriceOverride) {
        this.salesOrderDetailsPriceOverride = salesOrderDetailsPriceOverride;
    }

    public Long getSalesOrderDetailsSpecialTaxRate() {
        return salesOrderDetailsSpecialTaxRate;
    }

    public void setSalesOrderDetailsSpecialTaxRate(Long salesOrderDetailsSpecialTaxRate) {
        this.salesOrderDetailsSpecialTaxRate = salesOrderDetailsSpecialTaxRate;
    }

    public Long getSalesOrderDetailsQty() {
        return salesOrderDetailsQty;
    }

    public void setSalesOrderDetailsQty(Long salesOrderDetailsQty) {
        this.salesOrderDetailsQty = salesOrderDetailsQty;
    }

    public Long getSalesOrderDetailsBqty() {
        return salesOrderDetailsBqty;
    }

    public void setSalesOrderDetailsBqty(Long salesOrderDetailsBqty) {
        this.salesOrderDetailsBqty = salesOrderDetailsBqty;
    }

    public Long getSalesOrderDetailsLineQty() {
        return salesOrderDetailsLineQty;
    }

    public void setSalesOrderDetailsLineQty(Long salesOrderDetailsLineQty) {
        this.salesOrderDetailsLineQty = salesOrderDetailsLineQty;
    }

    public String getSalesOrderDetailsProcCode() {
        return salesOrderDetailsProcCode;
    }

    public void setSalesOrderDetailsProcCode(String salesOrderDetailsProcCode) {
        this.salesOrderDetailsProcCode = salesOrderDetailsProcCode;
    }

    public String getSalesOrderDetailsPriceOption() {
        return salesOrderDetailsPriceOption;
    }

    public void setSalesOrderDetailsPriceOption(String salesOrderDetailsPriceOption) {
        this.salesOrderDetailsPriceOption = salesOrderDetailsPriceOption;
    }

    public String getSalesOrderDetailsModifier1() {
        return salesOrderDetailsModifier1;
    }

    public void setSalesOrderDetailsModifier1(String salesOrderDetailsModifier1) {
        this.salesOrderDetailsModifier1 = salesOrderDetailsModifier1;
    }

    public String getSalesOrderDetailsModifier2() {
        return salesOrderDetailsModifier2;
    }

    public void setSalesOrderDetailsModifier2(String salesOrderDetailsModifier2) {
        this.salesOrderDetailsModifier2 = salesOrderDetailsModifier2;
    }

    public String getSalesOrderDetailsModifier3() {
        return salesOrderDetailsModifier3;
    }

    public void setSalesOrderDetailsModifier3(String salesOrderDetailsModifier3) {
        this.salesOrderDetailsModifier3 = salesOrderDetailsModifier3;
    }

    public String getSalesOrderDetailsModifier4() {
        return salesOrderDetailsModifier4;
    }

    public void setSalesOrderDetailsModifier4(String salesOrderDetailsModifier4) {
        this.salesOrderDetailsModifier4 = salesOrderDetailsModifier4;
    }

    public Double getSalesOrderDetailsChargeAmt() {
        return salesOrderDetailsChargeAmt;
    }

    public void setSalesOrderDetailsChargeAmt(Double salesOrderDetailsChargeAmt) {
        this.salesOrderDetailsChargeAmt = salesOrderDetailsChargeAmt;
    }

    public Double getSalesOrderDetailsAllowedAmt() {
        return salesOrderDetailsAllowedAmt;
    }

    public void setSalesOrderDetailsAllowedAmt(Double salesOrderDetailsAllowedAmt) {
        this.salesOrderDetailsAllowedAmt = salesOrderDetailsAllowedAmt;
    }

    public String getSalesOrderDetailsTaxable() {
        return salesOrderDetailsTaxable;
    }

    public void setSalesOrderDetailsTaxable(String salesOrderDetailsTaxable) {
        this.salesOrderDetailsTaxable = salesOrderDetailsTaxable;
    }

    public String getSalesOrderDetailsAbn() {
        return salesOrderDetailsAbn;
    }

    public void setSalesOrderDetailsAbn(String salesOrderDetailsAbn) {
        this.salesOrderDetailsAbn = salesOrderDetailsAbn;
    }

    public String getSalesOrderDetailsAbnUpgrade() {
        return salesOrderDetailsAbnUpgrade;
    }

    public void setSalesOrderDetailsAbnUpgrade(String salesOrderDetailsAbnUpgrade) {
        this.salesOrderDetailsAbnUpgrade = salesOrderDetailsAbnUpgrade;
    }

    public LocalDate getSalesOrderDetailsAbnPrintDate() {
        return salesOrderDetailsAbnPrintDate;
    }

    public void setSalesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
        this.salesOrderDetailsAbnPrintDate = salesOrderDetailsAbnPrintDate;
    }

    public String getSalesOrderDetailsAbnItem() {
        return salesOrderDetailsAbnItem;
    }

    public void setSalesOrderDetailsAbnItem(String salesOrderDetailsAbnItem) {
        this.salesOrderDetailsAbnItem = salesOrderDetailsAbnItem;
    }

    public String getSalesOrderDetailsAbnProcCode() {
        return salesOrderDetailsAbnProcCode;
    }

    public void setSalesOrderDetailsAbnProcCode(String salesOrderDetailsAbnProcCode) {
        this.salesOrderDetailsAbnProcCode = salesOrderDetailsAbnProcCode;
    }

    public String getSalesOrderDetailsAbnAllow() {
        return salesOrderDetailsAbnAllow;
    }

    public void setSalesOrderDetailsAbnAllow(String salesOrderDetailsAbnAllow) {
        this.salesOrderDetailsAbnAllow = salesOrderDetailsAbnAllow;
    }

    public Double getSalesOrderDetailsAbnCharge() {
        return salesOrderDetailsAbnCharge;
    }

    public void setSalesOrderDetailsAbnCharge(Double salesOrderDetailsAbnCharge) {
        this.salesOrderDetailsAbnCharge = salesOrderDetailsAbnCharge;
    }

    public String getSalesOrderDetailsAbnModifier1() {
        return salesOrderDetailsAbnModifier1;
    }

    public void setSalesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
        this.salesOrderDetailsAbnModifier1 = salesOrderDetailsAbnModifier1;
    }

    public String getSalesOrderDetailsAbnModifier2() {
        return salesOrderDetailsAbnModifier2;
    }

    public void setSalesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
        this.salesOrderDetailsAbnModifier2 = salesOrderDetailsAbnModifier2;
    }

    public Long getSalesOrderDetailsTaxRate() {
        return salesOrderDetailsTaxRate;
    }

    public void setSalesOrderDetailsTaxRate(Long salesOrderDetailsTaxRate) {
        this.salesOrderDetailsTaxRate = salesOrderDetailsTaxRate;
    }

    public String getSalesOrderDetailsTaxZone() {
        return salesOrderDetailsTaxZone;
    }

    public void setSalesOrderDetailsTaxZone(String salesOrderDetailsTaxZone) {
        this.salesOrderDetailsTaxZone = salesOrderDetailsTaxZone;
    }

    public String getSalesOrderDetailsNonTaxReason() {
        return salesOrderDetailsNonTaxReason;
    }

    public void setSalesOrderDetailsNonTaxReason(String salesOrderDetailsNonTaxReason) {
        this.salesOrderDetailsNonTaxReason = salesOrderDetailsNonTaxReason;
    }

    public String getSalesOrderDetailsNote() {
        return salesOrderDetailsNote;
    }

    public void setSalesOrderDetailsNote(String salesOrderDetailsNote) {
        this.salesOrderDetailsNote = salesOrderDetailsNote;
    }

    public String getSalesOrderDetailsSaleType() {
        return salesOrderDetailsSaleType;
    }

    public void setSalesOrderDetailsSaleType(String salesOrderDetailsSaleType) {
        this.salesOrderDetailsSaleType = salesOrderDetailsSaleType;
    }

    public String getSalesOrderDetailsItemGroup() {
        return salesOrderDetailsItemGroup;
    }

    public void setSalesOrderDetailsItemGroup(String salesOrderDetailsItemGroup) {
        this.salesOrderDetailsItemGroup = salesOrderDetailsItemGroup;
    }

    public String getSalesOrderDetailsItemUser1() {
        return salesOrderDetailsItemUser1;
    }

    public void setSalesOrderDetailsItemUser1(String salesOrderDetailsItemUser1) {
        this.salesOrderDetailsItemUser1 = salesOrderDetailsItemUser1;
    }

    public String getSalesOrderDetailsItemUser2() {
        return salesOrderDetailsItemUser2;
    }

    public void setSalesOrderDetailsItemUser2(String salesOrderDetailsItemUser2) {
        this.salesOrderDetailsItemUser2 = salesOrderDetailsItemUser2;
    }

    public String getSalesOrderDetailsItemUser3() {
        return salesOrderDetailsItemUser3;
    }

    public void setSalesOrderDetailsItemUser3(String salesOrderDetailsItemUser3) {
        this.salesOrderDetailsItemUser3 = salesOrderDetailsItemUser3;
    }

    public String getSalesOrderDetailsItemUser4() {
        return salesOrderDetailsItemUser4;
    }

    public void setSalesOrderDetailsItemUser4(String salesOrderDetailsItemUser4) {
        this.salesOrderDetailsItemUser4 = salesOrderDetailsItemUser4;
    }

    public String getSalesOrderDetailsConvertedToPurchase() {
        return salesOrderDetailsConvertedToPurchase;
    }

    public void setSalesOrderDetailsConvertedToPurchase(String salesOrderDetailsConvertedToPurchase) {
        this.salesOrderDetailsConvertedToPurchase = salesOrderDetailsConvertedToPurchase;
    }

    public String getSalesOrderDetailsManualConvertToPurchaseMctp() {
        return salesOrderDetailsManualConvertToPurchaseMctp;
    }

    public void setSalesOrderDetailsManualConvertToPurchaseMctp(String salesOrderDetailsManualConvertToPurchaseMctp) {
        this.salesOrderDetailsManualConvertToPurchaseMctp = salesOrderDetailsManualConvertToPurchaseMctp;
    }

    public Double getSalesOrderDetailsMctpChargeAmt() {
        return salesOrderDetailsMctpChargeAmt;
    }

    public void setSalesOrderDetailsMctpChargeAmt(Double salesOrderDetailsMctpChargeAmt) {
        this.salesOrderDetailsMctpChargeAmt = salesOrderDetailsMctpChargeAmt;
    }

    public Double getSalesOrderDetailsMctpAllowedAmt() {
        return salesOrderDetailsMctpAllowedAmt;
    }

    public void setSalesOrderDetailsMctpAllowedAmt(Double salesOrderDetailsMctpAllowedAmt) {
        this.salesOrderDetailsMctpAllowedAmt = salesOrderDetailsMctpAllowedAmt;
    }

    public String getSalesOrderDetailsMctpModifier1() {
        return salesOrderDetailsMctpModifier1;
    }

    public void setSalesOrderDetailsMctpModifier1(String salesOrderDetailsMctpModifier1) {
        this.salesOrderDetailsMctpModifier1 = salesOrderDetailsMctpModifier1;
    }

    public String getSalesOrderDetailsMctpModifier2() {
        return salesOrderDetailsMctpModifier2;
    }

    public void setSalesOrderDetailsMctpModifier2(String salesOrderDetailsMctpModifier2) {
        this.salesOrderDetailsMctpModifier2 = salesOrderDetailsMctpModifier2;
    }

    public String getSalesOrderDetailsMctpModifier3() {
        return salesOrderDetailsMctpModifier3;
    }

    public void setSalesOrderDetailsMctpModifier3(String salesOrderDetailsMctpModifier3) {
        this.salesOrderDetailsMctpModifier3 = salesOrderDetailsMctpModifier3;
    }

    public String getSalesOrderDetailsMctpModifier4() {
        return salesOrderDetailsMctpModifier4;
    }

    public void setSalesOrderDetailsMctpModifier4(String salesOrderDetailsMctpModifier4) {
        this.salesOrderDetailsMctpModifier4 = salesOrderDetailsMctpModifier4;
    }

    public Long getSalesOrderDetailsMctpPeriod() {
        return salesOrderDetailsMctpPeriod;
    }

    public void setSalesOrderDetailsMctpPeriod(Long salesOrderDetailsMctpPeriod) {
        this.salesOrderDetailsMctpPeriod = salesOrderDetailsMctpPeriod;
    }

    public String getSalesOrderDetailsAddtlModifier1() {
        return salesOrderDetailsAddtlModifier1;
    }

    public void setSalesOrderDetailsAddtlModifier1(String salesOrderDetailsAddtlModifier1) {
        this.salesOrderDetailsAddtlModifier1 = salesOrderDetailsAddtlModifier1;
    }

    public String getSalesOrderDetailsAddtlModifier2() {
        return salesOrderDetailsAddtlModifier2;
    }

    public void setSalesOrderDetailsAddtlModifier2(String salesOrderDetailsAddtlModifier2) {
        this.salesOrderDetailsAddtlModifier2 = salesOrderDetailsAddtlModifier2;
    }

    public String getSalesOrderDetailsAddtlModifier3() {
        return salesOrderDetailsAddtlModifier3;
    }

    public void setSalesOrderDetailsAddtlModifier3(String salesOrderDetailsAddtlModifier3) {
        this.salesOrderDetailsAddtlModifier3 = salesOrderDetailsAddtlModifier3;
    }

    public String getSalesOrderDetailsAddtlModifier4() {
        return salesOrderDetailsAddtlModifier4;
    }

    public void setSalesOrderDetailsAddtlModifier4(String salesOrderDetailsAddtlModifier4) {
        this.salesOrderDetailsAddtlModifier4 = salesOrderDetailsAddtlModifier4;
    }

    public LocalDate getSalesOrderDetailsNextDateOfService() {
        return salesOrderDetailsNextDateOfService;
    }

    public void setSalesOrderDetailsNextDateOfService(LocalDate salesOrderDetailsNextDateOfService) {
        this.salesOrderDetailsNextDateOfService = salesOrderDetailsNextDateOfService;
    }

    public String getSalesOrderDetailsPriceTable() {
        return salesOrderDetailsPriceTable;
    }

    public void setSalesOrderDetailsPriceTable(String salesOrderDetailsPriceTable) {
        this.salesOrderDetailsPriceTable = salesOrderDetailsPriceTable;
    }

    public String getSalesOrderDetailsPriceOptionName() {
        return salesOrderDetailsPriceOptionName;
    }

    public void setSalesOrderDetailsPriceOptionName(String salesOrderDetailsPriceOptionName) {
        this.salesOrderDetailsPriceOptionName = salesOrderDetailsPriceOptionName;
    }

    public Double getSalesOrderDetailsExtendedChargeAmount() {
        return salesOrderDetailsExtendedChargeAmount;
    }

    public void setSalesOrderDetailsExtendedChargeAmount(Double salesOrderDetailsExtendedChargeAmount) {
        this.salesOrderDetailsExtendedChargeAmount = salesOrderDetailsExtendedChargeAmount;
    }

    public Double getSalesOrderDetailsExtendedAllowanceAmount() {
        return salesOrderDetailsExtendedAllowanceAmount;
    }

    public void setSalesOrderDetailsExtendedAllowanceAmount(Double salesOrderDetailsExtendedAllowanceAmount) {
        this.salesOrderDetailsExtendedAllowanceAmount = salesOrderDetailsExtendedAllowanceAmount;
    }

    public String getSalesOrderDetailsItemNdcCode() {
        return salesOrderDetailsItemNdcCode;
    }

    public void setSalesOrderDetailsItemNdcCode(String salesOrderDetailsItemNdcCode) {
        this.salesOrderDetailsItemNdcCode = salesOrderDetailsItemNdcCode;
    }

    public String getSalesOrderDetailsManufacturer() {
        return salesOrderDetailsManufacturer;
    }

    public void setSalesOrderDetailsManufacturer(String salesOrderDetailsManufacturer) {
        this.salesOrderDetailsManufacturer = salesOrderDetailsManufacturer;
    }

    public String getSalesOrderDetailsCbPricing() {
        return salesOrderDetailsCbPricing;
    }

    public void setSalesOrderDetailsCbPricing(String salesOrderDetailsCbPricing) {
        this.salesOrderDetailsCbPricing = salesOrderDetailsCbPricing;
    }

    public String getSalesOrderDetailsCbPriceTableOverride() {
        return salesOrderDetailsCbPriceTableOverride;
    }

    public void setSalesOrderDetailsCbPriceTableOverride(String salesOrderDetailsCbPriceTableOverride) {
        this.salesOrderDetailsCbPriceTableOverride = salesOrderDetailsCbPriceTableOverride;
    }

    public String getSalesOrderDetailsCbOverride() {
        return salesOrderDetailsCbOverride;
    }

    public void setSalesOrderDetailsCbOverride(String salesOrderDetailsCbOverride) {
        this.salesOrderDetailsCbOverride = salesOrderDetailsCbOverride;
    }

    public String getSalesOrderDetailsMessages() {
        return salesOrderDetailsMessages;
    }

    public void setSalesOrderDetailsMessages(String salesOrderDetailsMessages) {
        this.salesOrderDetailsMessages = salesOrderDetailsMessages;
    }

    public Long getSalesOrderDetailsLocation() {
        return salesOrderDetailsLocation;
    }

    public void setSalesOrderDetailsLocation(Long salesOrderDetailsLocation) {
        this.salesOrderDetailsLocation = salesOrderDetailsLocation;
    }

    public Long getSalesOrderDetailsCaloriesPerDay() {
        return salesOrderDetailsCaloriesPerDay;
    }

    public void setSalesOrderDetailsCaloriesPerDay(Long salesOrderDetailsCaloriesPerDay) {
        this.salesOrderDetailsCaloriesPerDay = salesOrderDetailsCaloriesPerDay;
    }

    public String getSalesOrderDetailsSecondaryBillingProcudureCode() {
        return salesOrderDetailsSecondaryBillingProcudureCode;
    }

    public void setSalesOrderDetailsSecondaryBillingProcudureCode(String salesOrderDetailsSecondaryBillingProcudureCode) {
        this.salesOrderDetailsSecondaryBillingProcudureCode = salesOrderDetailsSecondaryBillingProcudureCode;
    }

    public String getSalesOrderDetailsSecondaryBillingPriceOption() {
        return salesOrderDetailsSecondaryBillingPriceOption;
    }

    public void setSalesOrderDetailsSecondaryBillingPriceOption(String salesOrderDetailsSecondaryBillingPriceOption) {
        this.salesOrderDetailsSecondaryBillingPriceOption = salesOrderDetailsSecondaryBillingPriceOption;
    }

    public String getSalesOrderDetailsSecondaryBillingPriceOptionName() {
        return salesOrderDetailsSecondaryBillingPriceOptionName;
    }

    public void setSalesOrderDetailsSecondaryBillingPriceOptionName(String salesOrderDetailsSecondaryBillingPriceOptionName) {
        this.salesOrderDetailsSecondaryBillingPriceOptionName = salesOrderDetailsSecondaryBillingPriceOptionName;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier1() {
        return salesOrderDetailsSecondaryBillingModifier1;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier1(String salesOrderDetailsSecondaryBillingModifier1) {
        this.salesOrderDetailsSecondaryBillingModifier1 = salesOrderDetailsSecondaryBillingModifier1;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier2() {
        return salesOrderDetailsSecondaryBillingModifier2;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier2(String salesOrderDetailsSecondaryBillingModifier2) {
        this.salesOrderDetailsSecondaryBillingModifier2 = salesOrderDetailsSecondaryBillingModifier2;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier3() {
        return salesOrderDetailsSecondaryBillingModifier3;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier3(String salesOrderDetailsSecondaryBillingModifier3) {
        this.salesOrderDetailsSecondaryBillingModifier3 = salesOrderDetailsSecondaryBillingModifier3;
    }

    public String getSalesOrderDetailsSecondaryBillingModifier4() {
        return salesOrderDetailsSecondaryBillingModifier4;
    }

    public void setSalesOrderDetailsSecondaryBillingModifier4(String salesOrderDetailsSecondaryBillingModifier4) {
        this.salesOrderDetailsSecondaryBillingModifier4 = salesOrderDetailsSecondaryBillingModifier4;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier1() {
        return salesOrderDetailsSecondaryBillingAdditionalModifier1;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier1(String salesOrderDetailsSecondaryBillingAdditionalModifier1) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier1 = salesOrderDetailsSecondaryBillingAdditionalModifier1;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier2() {
        return salesOrderDetailsSecondaryBillingAdditionalModifier2;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier2(String salesOrderDetailsSecondaryBillingAdditionalModifier2) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier2 = salesOrderDetailsSecondaryBillingAdditionalModifier2;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier3() {
        return salesOrderDetailsSecondaryBillingAdditionalModifier3;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier3(String salesOrderDetailsSecondaryBillingAdditionalModifier3) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier3 = salesOrderDetailsSecondaryBillingAdditionalModifier3;
    }

    public String getSalesOrderDetailsSecondaryBillingAdditionalModifier4() {
        return salesOrderDetailsSecondaryBillingAdditionalModifier4;
    }

    public void setSalesOrderDetailsSecondaryBillingAdditionalModifier4(String salesOrderDetailsSecondaryBillingAdditionalModifier4) {
        this.salesOrderDetailsSecondaryBillingAdditionalModifier4 = salesOrderDetailsSecondaryBillingAdditionalModifier4;
    }

    public String getSalesOrderDetailsSecondaryBillingIgnore() {
        return salesOrderDetailsSecondaryBillingIgnore;
    }

    public void setSalesOrderDetailsSecondaryBillingIgnore(String salesOrderDetailsSecondaryBillingIgnore) {
        this.salesOrderDetailsSecondaryBillingIgnore = salesOrderDetailsSecondaryBillingIgnore;
    }

    public String getSalesOrderDetailsSecondarySpecialBilling() {
        return salesOrderDetailsSecondarySpecialBilling;
    }

    public void setSalesOrderDetailsSecondarySpecialBilling(String salesOrderDetailsSecondarySpecialBilling) {
        this.salesOrderDetailsSecondarySpecialBilling = salesOrderDetailsSecondarySpecialBilling;
    }

    public String getSalesOrderDetailsSpanDateSplitBilling() {
        return salesOrderDetailsSpanDateSplitBilling;
    }

    public void setSalesOrderDetailsSpanDateSplitBilling(String salesOrderDetailsSpanDateSplitBilling) {
        this.salesOrderDetailsSpanDateSplitBilling = salesOrderDetailsSpanDateSplitBilling;
    }

    public Long getSalesOrderDetailsCmnparCmnFormId() {
        return salesOrderDetailsCmnparCmnFormId;
    }

    public void setSalesOrderDetailsCmnparCmnFormId(Long salesOrderDetailsCmnparCmnFormId) {
        this.salesOrderDetailsCmnparCmnFormId = salesOrderDetailsCmnparCmnFormId;
    }

    public String getSalesOrderDetailsCmnparCmnKey() {
        return salesOrderDetailsCmnparCmnKey;
    }

    public void setSalesOrderDetailsCmnparCmnKey(String salesOrderDetailsCmnparCmnKey) {
        this.salesOrderDetailsCmnparCmnKey = salesOrderDetailsCmnparCmnKey;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnCreateDate() {
        return salesOrderDetailsCmnparCmnCreateDate;
    }

    public void setSalesOrderDetailsCmnparCmnCreateDate(LocalDate salesOrderDetailsCmnparCmnCreateDate) {
        this.salesOrderDetailsCmnparCmnCreateDate = salesOrderDetailsCmnparCmnCreateDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnExpirationDate() {
        return salesOrderDetailsCmnparCmnExpirationDate;
    }

    public void setSalesOrderDetailsCmnparCmnExpirationDate(LocalDate salesOrderDetailsCmnparCmnExpirationDate) {
        this.salesOrderDetailsCmnparCmnExpirationDate = salesOrderDetailsCmnparCmnExpirationDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnInitialDate() {
        return salesOrderDetailsCmnparCmnInitialDate;
    }

    public void setSalesOrderDetailsCmnparCmnInitialDate(LocalDate salesOrderDetailsCmnparCmnInitialDate) {
        this.salesOrderDetailsCmnparCmnInitialDate = salesOrderDetailsCmnparCmnInitialDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnRenewalDate() {
        return salesOrderDetailsCmnparCmnRenewalDate;
    }

    public void setSalesOrderDetailsCmnparCmnRenewalDate(LocalDate salesOrderDetailsCmnparCmnRenewalDate) {
        this.salesOrderDetailsCmnparCmnRenewalDate = salesOrderDetailsCmnparCmnRenewalDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnRecertificationDate() {
        return salesOrderDetailsCmnparCmnRecertificationDate;
    }

    public void setSalesOrderDetailsCmnparCmnRecertificationDate(LocalDate salesOrderDetailsCmnparCmnRecertificationDate) {
        this.salesOrderDetailsCmnparCmnRecertificationDate = salesOrderDetailsCmnparCmnRecertificationDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnPhysicianDate() {
        return salesOrderDetailsCmnparCmnPhysicianDate;
    }

    public void setSalesOrderDetailsCmnparCmnPhysicianDate(LocalDate salesOrderDetailsCmnparCmnPhysicianDate) {
        this.salesOrderDetailsCmnparCmnPhysicianDate = salesOrderDetailsCmnparCmnPhysicianDate;
    }

    public String getSalesOrderDetailsCmnparCmnStatus() {
        return salesOrderDetailsCmnparCmnStatus;
    }

    public void setSalesOrderDetailsCmnparCmnStatus(String salesOrderDetailsCmnparCmnStatus) {
        this.salesOrderDetailsCmnparCmnStatus = salesOrderDetailsCmnparCmnStatus;
    }

    public String getSalesOrderDetailsCmnparParId() {
        return salesOrderDetailsCmnparParId;
    }

    public void setSalesOrderDetailsCmnparParId(String salesOrderDetailsCmnparParId) {
        this.salesOrderDetailsCmnparParId = salesOrderDetailsCmnparParId;
    }

    public String getSalesOrderDetailsCmnparParDescr() {
        return salesOrderDetailsCmnparParDescr;
    }

    public void setSalesOrderDetailsCmnparParDescr(String salesOrderDetailsCmnparParDescr) {
        this.salesOrderDetailsCmnparParDescr = salesOrderDetailsCmnparParDescr;
    }

    public LocalDate getSalesOrderDetailsCmnparParInitialDate() {
        return salesOrderDetailsCmnparParInitialDate;
    }

    public void setSalesOrderDetailsCmnparParInitialDate(LocalDate salesOrderDetailsCmnparParInitialDate) {
        this.salesOrderDetailsCmnparParInitialDate = salesOrderDetailsCmnparParInitialDate;
    }

    public LocalDate getSalesOrderDetailsCmnparParExpirationDate() {
        return salesOrderDetailsCmnparParExpirationDate;
    }

    public void setSalesOrderDetailsCmnparParExpirationDate(LocalDate salesOrderDetailsCmnparParExpirationDate) {
        this.salesOrderDetailsCmnparParExpirationDate = salesOrderDetailsCmnparParExpirationDate;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnLogDate() {
        return salesOrderDetailsCmnparCmnLogDate;
    }

    public void setSalesOrderDetailsCmnparCmnLogDate(LocalDate salesOrderDetailsCmnparCmnLogDate) {
        this.salesOrderDetailsCmnparCmnLogDate = salesOrderDetailsCmnparCmnLogDate;
    }

    public Long getSalesOrderDetailsCmnparCmnLengthOfNeed() {
        return salesOrderDetailsCmnparCmnLengthOfNeed;
    }

    public void setSalesOrderDetailsCmnparCmnLengthOfNeed(Long salesOrderDetailsCmnparCmnLengthOfNeed) {
        this.salesOrderDetailsCmnparCmnLengthOfNeed = salesOrderDetailsCmnparCmnLengthOfNeed;
    }

    public LocalDate getSalesOrderDetailsCmnparCmnPrintedDate() {
        return salesOrderDetailsCmnparCmnPrintedDate;
    }

    public void setSalesOrderDetailsCmnparCmnPrintedDate(LocalDate salesOrderDetailsCmnparCmnPrintedDate) {
        this.salesOrderDetailsCmnparCmnPrintedDate = salesOrderDetailsCmnparCmnPrintedDate;
    }

    public String getSalesOrderDetailsCmnparCmnPrintedBy() {
        return salesOrderDetailsCmnparCmnPrintedBy;
    }

    public void setSalesOrderDetailsCmnparCmnPrintedBy(String salesOrderDetailsCmnparCmnPrintedBy) {
        this.salesOrderDetailsCmnparCmnPrintedBy = salesOrderDetailsCmnparCmnPrintedBy;
    }

    public LocalDate getSalesOrderDetailsCmnparFaxedDate() {
        return salesOrderDetailsCmnparFaxedDate;
    }

    public void setSalesOrderDetailsCmnparFaxedDate(LocalDate salesOrderDetailsCmnparFaxedDate) {
        this.salesOrderDetailsCmnparFaxedDate = salesOrderDetailsCmnparFaxedDate;
    }

    public String getSalesOrderDetailsCmnparCmnPlaceholder() {
        return salesOrderDetailsCmnparCmnPlaceholder;
    }

    public void setSalesOrderDetailsCmnparCmnPlaceholder(String salesOrderDetailsCmnparCmnPlaceholder) {
        this.salesOrderDetailsCmnparCmnPlaceholder = salesOrderDetailsCmnparCmnPlaceholder;
    }

    public String getSalesOrderDetailsCmnparCmnFaxedBy() {
        return salesOrderDetailsCmnparCmnFaxedBy;
    }

    public void setSalesOrderDetailsCmnparCmnFaxedBy(String salesOrderDetailsCmnparCmnFaxedBy) {
        this.salesOrderDetailsCmnparCmnFaxedBy = salesOrderDetailsCmnparCmnFaxedBy;
    }

    public String getSalesOrderDetailsCmnparCmnLoggedBy() {
        return salesOrderDetailsCmnparCmnLoggedBy;
    }

    public void setSalesOrderDetailsCmnparCmnLoggedBy(String salesOrderDetailsCmnparCmnLoggedBy) {
        this.salesOrderDetailsCmnparCmnLoggedBy = salesOrderDetailsCmnparCmnLoggedBy;
    }

    public Long getSalesOrderDetailsCmnparNumberOfRefills() {
        return salesOrderDetailsCmnparNumberOfRefills;
    }

    public void setSalesOrderDetailsCmnparNumberOfRefills(Long salesOrderDetailsCmnparNumberOfRefills) {
        this.salesOrderDetailsCmnparNumberOfRefills = salesOrderDetailsCmnparNumberOfRefills;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSalesOrderDetailsManufacturerItemIdNumber() {
        return salesOrderDetailsManufacturerItemIdNumber;
    }

    public void setSalesOrderDetailsManufacturerItemIdNumber(String salesOrderDetailsManufacturerItemIdNumber) {
        this.salesOrderDetailsManufacturerItemIdNumber = salesOrderDetailsManufacturerItemIdNumber;
    }

    public Long getCmnId() {
        return cmnId;
    }

    public void setCmnId(Long cmnId) {
        this.cmnId = cmnId;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSalesOrderItemDetailsUuid() {
        return salesOrderItemDetailsUuid;
    }

    public void setSalesOrderItemDetailsUuid(UUID salesOrderItemDetailsUuid) {
        this.salesOrderItemDetailsUuid = salesOrderItemDetailsUuid;
    }

    public String getSalesOrderItemNumber() {
        return salesOrderItemNumber;
    }

    public void setSalesOrderItemNumber(String salesOrderItemNumber) {
        this.salesOrderItemNumber = salesOrderItemNumber;
    }

    public String getIsAssetTagged() {
        return isAssetTagged;
    }

    public void setIsAssetTagged(String isAssetTagged) {
        this.isAssetTagged = isAssetTagged;
    }

    public Long getItemSerialNo() {
        return itemSerialNo;
    }

    public void setItemSerialNo(Long itemSerialNo) {
        this.itemSerialNo = itemSerialNo;
    }

    public String getSalesOrderDetailsIcdPointer() {
        return salesOrderDetailsIcdPointer;
    }

    public void setSalesOrderDetailsIcdPointer(String salesOrderDetailsIcdPointer) {
        this.salesOrderDetailsIcdPointer = salesOrderDetailsIcdPointer;
    }

    public String getProcedureIdentifier() {
        return procedureIdentifier;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getParNo() {
        return parNo;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getWhetherSerialised() {
        return whetherSerialised;
    }

    public void setWhetherSerialised(String whetherSerialised) {
        this.whetherSerialised = whetherSerialised;
    }

    public String getPickupExchangeNo() {
        return pickupExchangeNo;
    }

    public void setPickupExchangeNo(String pickupExchangeNo) {
        this.pickupExchangeNo = pickupExchangeNo;
    }

    public String getSalesOrderAbnUserResponse() {
        return salesOrderAbnUserResponse;
    }

    public void setSalesOrderAbnUserResponse(String salesOrderAbnUserResponse) {
        this.salesOrderAbnUserResponse = salesOrderAbnUserResponse;
    }

    public String getIsDropshipAllowed() {
        return isDropshipAllowed;
    }

    public void setIsDropshipAllowed(String isDropshipAllowed) {
        this.isDropshipAllowed = isDropshipAllowed;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public UUID getPurchaseOrderUuid() {
        return purchaseOrderUuid;
    }

    public void setPurchaseOrderUuid(UUID purchaseOrderUuid) {
        this.purchaseOrderUuid = purchaseOrderUuid;
    }

    public String getIsResupplyType() {
        return isResupplyType;
    }

    public void setIsResupplyType(String isResupplyType) {
        this.isResupplyType = isResupplyType;
    }

    public Long getFrequencyCount() {
        return frequencyCount;
    }

    public void setFrequencyCount(Long frequencyCount) {
        this.frequencyCount = frequencyCount;
    }

    public Long getFrequencyInterval() {
        return frequencyInterval;
    }

    public void setFrequencyInterval(Long frequencyInterval) {
        this.frequencyInterval = frequencyInterval;
    }

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderItemDetailsDTO)) {
            return false;
        }

        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = (SalesOrderItemDetailsDTO) o;
        if (this.salesOrderItemDetailsId == null) {
            return false;
        }
        return Objects.equals(this.salesOrderItemDetailsId, salesOrderItemDetailsDTO.salesOrderItemDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.salesOrderItemDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderItemDetailsDTO{" +
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
