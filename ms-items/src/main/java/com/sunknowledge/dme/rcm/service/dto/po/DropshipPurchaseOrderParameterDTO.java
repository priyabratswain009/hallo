package com.sunknowledge.dme.rcm.service.dto.po;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropshipPurchaseOrderParameterDTO {
    @NotNull(message="SO_Id must be provided.")
    @Min(value=1, message="SO_Id must be greater than and equals to 1.")
    Long soId;
    @NotNull(message="SO_No must be provided.")
    @NotBlank(message="SO_No must be provided.")
    String soNo;
    @NotNull(message="Vendor_Id must be provided.")
    @Min(value=1, message="Vendor_Id must be greater than and equals to 1.")
    Long vendorId;
    @NotNull(message="Branch_Id must be provided.")
    @Min(value=1, message="Branch_Id must be greater than and equals to 1.")
    Long branchId;
    @NotNull(message="Branch_Name must be provided.")
    @NotBlank(message="Branch_Name must be provided.")
    String branchName;
    @NotNull(message="Billing_Address_Line1 must be provided.")
    @NotBlank(message="Billing_Address_Line1 must be provided.")
    String billingAddressLine1;
    String billingAddressLine2;
    @NotNull(message="Billing_Address_City must be provided.")
    @NotBlank(message="Billing_Address_City must be provided.")
    String billingAddressCity;
    @NotNull(message="Billing_Address_State must be provided.")
    @NotBlank(message="Billing_Address_State must be provided.")
    String billingAddressState;
    @NotNull(message="Billing_Address_Zip must be provided.")
    @NotBlank(message="Billing_Address_Zip must be provided.")
    String billingAddressZip;
    @NotNull(message="Billing_Contact_No must be provided.")
    @NotBlank(message="Billing_Contact_No must be provided.")
    String billingContactNo;
//    @NotNull(message="Billing_Contact_Name must be provided.")
//    @NotBlank(message="Billing_Contact_Name must be provided.")
    String billingContactName;

    String billingContactEmail;
    @NotNull(message="Delivery_Address_Line_1 must be provided.")
    @NotBlank(message="Delivery_Address_Line_1 must be provided.")
    String deliveryAddressLine1;
    String deliveryAddressLine2;
    @NotNull(message="Delivery_Address_City must be provided.")
    @NotBlank(message="Delivery_Address_City must be provided.")
    String deliveryAddressCity;
    @NotNull(message="Delivery_Address_State must be provided.")
    @NotBlank(message="Delivery_Address_State must be provided.")
    String deliveryAddressState;
    @NotNull(message="Delivery_Address_Zip must be provided.")
    @NotBlank(message="Delivery_Address_Zip must be provided.")
    String deliveryAddressZip;
    @NotNull(message="Delivery_Contact_No must be provided.")
    @NotBlank(message="Delivery_Contact_No must be provided.")
    String deliveryContactNo;
//    @NotNull(message="Delivery_Contact_Name must be provided.")
//    @NotBlank(message="Delivery_Contact_Name must be provided.")
    String deliveryContactName;

    String deliveryContactEmail;
    @NotNull(message="Purchase_Order_Item must be selected.")
    @Min(value=1, message="Purchase_Order_Item must be greater than and equals to 1.")
    Long itemId;
    @NotNull(message="Purchase_Order_Item_Quantitie must be provided.")
    @Min(value=1, message="Purchase_Order_Item_Quantitie must be greater than and equals to 1.")
    Long itemQty;
    @NotNull(message="Purchase_Order_Item_Prices must be provided.")
    @NotBlank(message="Purchase_Order_Item_Prices must be provided.")
    String itemprices;
    @NotNull(message="Serialised_Or_Not must be provided.")
    @NotBlank(message="Serialised_Or_Not must be provided.")
    String whetherSerialised;
    String notes;
    @NotNull(message="Location_Id must be selected.")
    @Min(value=1, message="Location_Id must be greater than and equals to 1.")
    Long locationId;
}
