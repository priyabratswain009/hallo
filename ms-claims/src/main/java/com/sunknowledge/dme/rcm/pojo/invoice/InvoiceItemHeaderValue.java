package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemHeaderValue {
    private String quantity;
    private String procedureCode;
    private String itemName;
    private String saleType;
}
