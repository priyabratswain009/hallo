package com.sunknowledge.dme.rcm.pojo.invoice;

public interface ClaimedInvoiceDetailsProjection {
    Long getInvoiceId();
    String getInvoiceNo();
    String getInvoiceDate();
    String getInvoiceToEntity();
    String getInvoiceToPayorName();
    Long getSalesOrderId();
    String getSalesOrderNo();
    String getPatientName();
    Integer getItemQtyTotal();
    Double getChargedAmountTotal();
    Double getAllowAmountTotal();
    String getInvoiceMasterDetailsUuid();
}
