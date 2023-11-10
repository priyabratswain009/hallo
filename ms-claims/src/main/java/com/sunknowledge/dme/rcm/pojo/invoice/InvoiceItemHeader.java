package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemHeader {
    private String date;
    private String createdBy;
    private String depositId;
    private String paymentType;
    private String payments;
    private String tax;
    private String adjustment;
}
