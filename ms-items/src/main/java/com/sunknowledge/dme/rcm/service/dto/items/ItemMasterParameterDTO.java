package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMasterParameterDTO {
    @NotNull(message = "Item_Id must be provided.")
    @Min(value=1, message="Item_Id must be greater than or equal to 1")
    private Long itemId;

    private String itemName;

    private String itemDescription;

    @NotNull(message = "Primary_Procedure_Code must be provided.")
    @NotBlank(message = "Primary_Procedure_Code must be provided.")
    private String primaryProcedureCodeValue;

    private List<String> primaryProcedureCodeValueList;

    private Long itemTypeId;

    private Long itemGroupId;

    private String saleType;

    private String coverageType;

    private String weight;

    private String lotStatus;

    private String kitStatus;

    private String kitType;

    private Double itemPricingDefaultRentalAmt;

    private Double itemPricingDefaultPurchaseAmt;

    private String autoReorderStatus;

    private String excludePoStatus;

    private String excludeSoStatus;

    private String ndc;

    private String ndcUnitOfMeasurement;

    private String manufacturerName;

    private Long manufacturerId;

    private String manufacturerBarcode;

    private Long defaultVendorId;

    private String excludeStandardPriceingStat;

    private String userField1;

    private String userField2;

    private String userField3;

    private String billingMultiplier;

    private String claimNote;

    private String itemUom;

    private String status;

    private String itemGroupName;

    private String itemTypeName;

    private String defaultVendorName;

    private String resupplyTypeStatus;
}
