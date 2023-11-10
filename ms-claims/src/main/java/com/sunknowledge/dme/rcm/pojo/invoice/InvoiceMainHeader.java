package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceMainHeader {
    private String quantity;
    private String procedureCode;
    private String item;
    private String saleType;
    private String charge;
    private String allowed;
    private String payments;
    private String tax;
    private String adjustment;
    private String balance;
}
