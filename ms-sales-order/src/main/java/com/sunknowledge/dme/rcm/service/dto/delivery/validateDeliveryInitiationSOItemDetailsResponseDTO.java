package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class validateDeliveryInitiationSOItemDetailsResponseDTO {
    Long primaryInsurerPriceTableId;
    String parNo;
    String parId;
    Long cmnId;
    String cmnKey;
    Long itemLocationId;
    Long salesOrderDetailsItemId;
    String salesOrderDetailsItemName;
    String salesOrderDetailsStockingUom;
    String salesOrderDetailsItemDescription;
    String salesOrderItemNumber;
    String salesOrderDetailsSaleType;
    Long salesOrderDetailsQty;
    Long salesOrderDetailsBqty;
    Long salesOrderDetailsLineQty;
    String salesOrderDetailsProcCode;
    String salesOrderDetailsModifier1;
    String salesOrderDetailsPriceOption;
    Double salesOrderDetailsChargeAmt;
    Double salesOrderDetailsAllowedAmt;
    String salesOrderDetailsTaxable;
    String salesOrderDetailsOriginalDos;
    String salesOrderDetailsDosTo;
    Long  salesOrderDetailsTaxRate;
    String dropshipStatus;
}
