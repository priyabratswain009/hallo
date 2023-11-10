package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderItemDetailsEntryParameterDTO {
    private UUID salesOrderItemDetailsUuid;

    //    private Long salesOrderItemDetailsId;
    @NotNull(message = "SalesOrder must be selected.")
    private UUID salesOrderUUID;

//    private Long patientId;  //-------- assigned by "fetchMasterClinicalIns" service -----

    //---------------- Form item.itemsearchforaddinso() -------------------
    @Min(value = 1, message = "Item_Location_Id must be greater than equals to 1.")
    @NotNull(message = "Item_Location_Id must be provided")
    private Long itemLocationId;
    @NotNull(message = "Location_Name must be provided")
    @NotBlank(message = "Location_Name must be provided")
    private String locationName;
    @Min(value = 1, message = "Item_Id must be greater than equals to 1.")
    @NotNull(message = "Item_Id must be provided")
    private Long salesOrderDetailsItemId;
    @NotNull(message = "Item_Name must be provided")
    @NotBlank(message = "Item_Name must be provided")
    private String salesOrderDetailsItemName;
    @NotNull(message = "Stocking_Uom must be provided")
    @NotBlank(message = "Stocking_Uom must be provided")
    private String salesOrderDetailsStockingUom;
    @NotNull(message = "Item_Description must be provided")
    @NotBlank(message = "Item_Description must be provided")
    private String salesOrderDetailsItemDescription;
    @NotNull(message = "Item_Number must be provided")
    @NotBlank(message = "Item_Number must be provided")
    private String salesOrderItemNumber;
    //    @NotNull(message = "Is_Asset_Tagged must be provided")
//    @NotBlank(message = "Is_Asset_Tagged must be provided")
    private String isAssetTagged;
    @NotNull(message = "Whether_Serialised must be provided")
    @NotBlank(message = "Whether_Serialised must be provided")
    private String whetherSerialised;
    @NotNull(message = "Manufacturer must be provided")
    @NotBlank(message = "Manufacturer must be provided")
    private String salesOrderDetailsManufacturer;
    @NotNull(message = "Item_Group must be provided")
    @NotBlank(message = "Item_Group must be provided")
    private String salesOrderDetailsItemGroup;
    //---------------- Form item.itemsearchforaddinso() -------------------

    @NotNull(message = "Sale_Type must be provided")
    @NotBlank(message = "Sale_Type must be provided")
    private String salesOrderDetailsSaleType;
    //    @NotNull(message = "Converted_To_Purchase must be provided")
//    @NotBlank(message = "Converted_To_Purchase must be provided")
    private String salesOrderDetailsConvertedToPurchase;  //---- Checkbox

//    private String itemAssetNo; // --------- Updated during delivery [Ranjan Sir]
//    private Long itemSerialNo;  // --------- Updated during delivery [Ranjan Sir]

//    private String salesOrderDetailsDefaultVendor;    //---- I will come from "/getItemVendorMapByItemId" [Dropdown]

    @Min(value = 0, message = "On_Hand_Qty must be greater than equals to 0.")
    @NotNull(message = "On_Hand_Qty must be provided")
    private Long onhandQty;
    @Min(value = 0, message = "Inorder_Qty must be greater than equals to 0.")
    @NotNull(message = "Inorder_Qty must be provided")
    private Long inorderQty;
    @Min(value = 0, message = "Committed_Qty must be greater than equals to 0.")
    @NotNull(message = "Committed_Qty must be provided")
    private Long committedQty;
    @Min(value = 1, message = "Quantity must be greater than equals to 1.")
    @NotNull(message = "Quantity must be provided")
    private Long salesOrderDetailsQty; //---- Value assigned to below variables from client-side
    @Min(value = 1, message = "Billing_Quantity must be greater than equals to 1.")
    @NotNull(message = "Billing_Quantity must be provided")
    private Long salesOrderDetailsBqty; //-- assigned by salesOrderDetailsQty [In Client Side]
//    private Long salesOrderDetailsLineQty; //-- assigned by salesOrderDetailsQty

    //----------- From item.itemsearchforpricing() --------------------
    @NotNull(message = "HCPCS_Code must be provided")
    @NotBlank(message = "HCPCS_Code must be provided")
    private String salesOrderDetailsProcCode;
    @NotNull(message = "At least one Modifier (Modifier1) must be provided")
    @NotBlank(message = "At least one Modifier (Modifier1) must be provided")
    private String salesOrderDetailsModifier1;
    private String salesOrderDetailsModifier2;
    private String salesOrderDetailsModifier3;
    private String salesOrderDetailsModifier4;
    //    @NotNull(message = "Price_Option must be provided")
//    @NotBlank(message = "Price_Option must be provided")
    private String salesOrderDetailsPriceOption;
    @Min(value = 1, message = "Charge_Amt must be greater than equals to 1.")
    @NotNull(message = "Charge_Amt must be provided")
    private Double salesOrderDetailsChargeAmt;
    @Min(value = 1, message = "Allowed_Amt must be greater than equals to 1.")
    @NotNull(message = "Allowed_Amt must be provided")
    private Double salesOrderDetailsAllowedAmt;
    private String salesOrderDetailsTaxable;
    //----------- From item.itemsearchforpricing() --------------------

    //----------------- Related to Date of Service -----
    @NotNull(message = "Date_of_Service_From must be provided")
    private LocalDate salesOrderDetailsOriginalDos; //-------------- DOS from Date ---------------
    //    private LocalDate salesOrderDetailsPreviousBillingDate;
//    private LocalDate salesOrderDetailsNextBillingDate;
    @NotNull(message = "Date_of_Service_To must be provided")
    private LocalDate salesOrderDetailsDosTo;   //-------------- DOS to Date ---------------
//    private String salesOrderDetailsNextPeriod;
    //------------------ Related to Date of Service -----

//    private String salesOrderDetailsSpecialPricing;
//    private String salesOrderDetailsPriceOverride;
//    private Long salesOrderDetailsSpecialTaxRate;

    //------------ ABN -------------------------
//    private String salesOrderDetailsAbn;     ----- Updated from ABN service ------
//    private String salesOrderDetailsAbnUpgrade;
//    private LocalDate salesOrderDetailsAbnPrintDate;
//    private String salesOrderDetailsAbnItem;
//    private String salesOrderDetailsAbnProcCode;
//    private String salesOrderDetailsAbnAllow;
//    private Double salesOrderDetailsAbnCharge;
//    private String salesOrderDetailsAbnModifier1;
//    private String salesOrderDetailsAbnModifier2;
//    private String salesOrderAbnUserResponse;
    //------------ ABN -------------------------

    @Min(value = 1, message = "Tax_Rate must be greater than equals to 1.")
    @NotNull(message = "Tax_Rate must be provided")
    private Long salesOrderDetailsTaxRate;
    @NotNull(message = "Tax_Zone must be provided")
    @NotBlank(message = "Tax_Zone must be provided")
    private String salesOrderDetailsTaxZone;
    private String salesOrderDetailsNonTaxReason;
    private String salesOrderDetailsNote;
    private String salesOrderDetailsItemUser1;
    private String salesOrderDetailsItemUser2;
    private String salesOrderDetailsItemUser3;
    private String salesOrderDetailsItemUser4;

    //----------- Skipped Fields --------------------
//    private String salesOrderDetailsManualConvertToPurchaseMctp;
//    private Double salesOrderDetailsMctpChargeAmt;
//    private Double salesOrderDetailsMctpAllowedAmt;
//    private String salesOrderDetailsMctpModifier1;
//    private String salesOrderDetailsMctpModifier2;
//    private String salesOrderDetailsMctpModifier3;
//    private String salesOrderDetailsMctpModifier4;
//    private Long salesOrderDetailsMctpPeriod;
//    private String salesOrderDetailsAddtlModifier1;
//    private String salesOrderDetailsAddtlModifier2;
//    private String salesOrderDetailsAddtlModifier3;
//    private String salesOrderDetailsAddtlModifier4;
//    private LocalDate salesOrderDetailsNextDateOfService;
//    private String salesOrderDetailsPriceTable;
//    private String salesOrderDetailsPriceOptionName;
//    private Double salesOrderDetailsExtendedChargeAmount;
//    private Double salesOrderDetailsExtendedAllowanceAmount;
//    private String salesOrderDetailsItemNdcCode;
//    private String salesOrderDetailsCbPricing;
//    private String salesOrderDetailsCbPriceTableOverride;
//    private String salesOrderDetailsCbOverride;
//    private String salesOrderDetailsMessages;
//    private Long salesOrderDetailsLocation;
//    private Long salesOrderDetailsCaloriesPerDay;
//    private String salesOrderDetailsSecondaryBillingProcudureCode;
//    private String salesOrderDetailsSecondaryBillingPriceOption;
//    private String salesOrderDetailsSecondaryBillingPriceOptionName;
//    private String salesOrderDetailsSecondaryBillingModifier1;
//    private String salesOrderDetailsSecondaryBillingModifier2;
//    private String salesOrderDetailsSecondaryBillingModifier3;
//    private String salesOrderDetailsSecondaryBillingModifier4;
//    private String salesOrderDetailsSecondaryBillingAdditionalModifier1;
//    private String salesOrderDetailsSecondaryBillingAdditionalModifier2;
//    private String salesOrderDetailsSecondaryBillingAdditionalModifier3;
//    private String salesOrderDetailsSecondaryBillingAdditionalModifier4;
//    private String salesOrderDetailsSecondaryBillingIgnore;
//    private String salesOrderDetailsSecondarySpecialBilling;
//    private String salesOrderDetailsSpanDateSplitBilling;
    //----------- Skipped Fields --------------------

    //----- CMN --------------------------------------
//    private Long cmnId;    ----------------------------------> Updated by CMN Subroutine -----
//    private Long salesOrderDetailsCmnparCmnFormId;
//    private String salesOrderDetailsCmnparCmnKey; ---------> Updated by CMN Subroutine -----
//    private LocalDate salesOrderDetailsCmnparCmnCreateDate;
//    private LocalDate salesOrderDetailsCmnparCmnExpirationDate;
//    private LocalDate salesOrderDetailsCmnparCmnInitialDate;
//    private LocalDate salesOrderDetailsCmnparCmnRenewalDate;
//    private LocalDate salesOrderDetailsCmnparCmnRecertificationDate;
//    private LocalDate salesOrderDetailsCmnparCmnPhysicianDate;
//    private String salesOrderDetailsCmnparCmnStatus;
//    private LocalDate salesOrderDetailsCmnparCmnLogDate;
//    private Long salesOrderDetailsCmnparCmnLengthOfNeed;
//    private LocalDate salesOrderDetailsCmnparCmnPrintedDate;
//    private String salesOrderDetailsCmnparCmnPrintedBy;
//    private LocalDate salesOrderDetailsCmnparFaxedDate;
//    private String salesOrderDetailsCmnparCmnPlaceholder;
//    private String salesOrderDetailsCmnparCmnFaxedBy;
//    private String salesOrderDetailsCmnparCmnLoggedBy;
//    private Long salesOrderDetailsCmnparNumberOfRefills;
    //----- CMN --------------------------------------

    //----- PAR --------------------------------------
//    private String parNo;    -----------------------> Updated by PAR Subroutine -----
//    private String salesOrderDetailsCmnparParId;   -----> Updated by PAR Subroutine -----
//    private String salesOrderDetailsCmnparParDescr;
//    private LocalDate salesOrderDetailsCmnparParInitialDate;
//    private LocalDate salesOrderDetailsCmnparParExpirationDate;
    private String useExistingPAR;
    //----- PAR --------------------------------------

    //----- Created By, etc --------------------------------------
//    private Long createdById;    ----- by system --------
//    private LocalDate createdDate; ---- by system ---------
//    private String createdByName;  ---- by system ---------
//    private Long updatedById;   ---- by system ---------
//    private LocalDate updatedDate;  ---- by system ---------
//    private String updatedByName;   ---- by system ---------
    //----- Created By, etc --------------------------------------

//    private String salesOrderDetailsManufacturerItemIdNumber;

    //------- Hardcoded Value -----------------
//    private String status; // value = active
//    private String salesOrderDetailsIcdPointer; // value = 1
//    private String procedureIdentifier; // value = H
//    private String pickupExchangeNo;  // --- Updated by Pickup Exchange Service (Arijit) ----

    //------- Hardcoded Value -----------------

    //------------ Used during dropship creation -------------------
//    private String isDropshipAllowed;
//    private String poNumber;
//    private UUID purchaseOrderUuid;
    //------------ Used during dropship creation -------------------

    private String isResupplyType;
    private Long frequencyCount;   //-- User Input --
    private Long frequencyInterval;  //-- By default assigned by "12" in client side if isResupplyType='Y' and editable.
    private Long vendorId;
    private Long itemGroupId;
}
