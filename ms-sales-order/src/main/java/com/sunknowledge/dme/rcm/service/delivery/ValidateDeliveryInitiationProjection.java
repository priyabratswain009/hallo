package com.sunknowledge.dme.rcm.service.delivery;

public interface ValidateDeliveryInitiationProjection {
    Long getPriceTableId();
    String getParNo();
   String getParId();
    Long getCmnId();
    String getCmnKey();
    Long getItemLocationId();
    Long getSalesOrderDetailsItemId();
    String getSalesOrderDetailsItemName();
    String getSalesOrderDetailsStockingUom();
    String getSalesOrderDetailsItemDescription();
    String getSalesOrderItemNumber();
    String getSalesOrderDetailsSaleType();
    Long getSalesOrderDetailsQty();
    Long getSalesOrderDetailsBqty();
    Long getSalesOrderDetailsLineQty();
    String getSalesOrderDetailsProcCode();
    String getSalesOrderDetailsModifier1();
    String getSalesOrderDetailsPriceOption();
    Double getSalesOrderDetailsChargeAmt();
    Double getSalesOrderDetailsAllowedAmt();
    String getSalesOrderDetailsTaxable();
    String getSalesOrderDetailsOriginalDos();
    String getSalesOrderDetailsDosTo();
    Long getSalesOrderDetailsTaxRate();
}
