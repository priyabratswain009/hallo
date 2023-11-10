package com.sunknowledge.dme.rcm.service.dto.items;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMasterOutputDTO {

    private Long itemId;


    private String itemName;


    private String itemDescription;


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


    private Long createdById;


    private LocalDate createdDate;


    private String createdByName;


    private String updatedByName;


    private String updatedById;


    private UUID itemMasterUuid;


    private String itemNumber;


    private String itemGroupName;


    private String itemTypeName;


    private String primaryProcedureCodeValue;


    private String defaultVendorName;


    private String resupplyTypeStatus;


    private Set<ItemProcedureCodeMap> procedureDetails = new HashSet<>();
}
