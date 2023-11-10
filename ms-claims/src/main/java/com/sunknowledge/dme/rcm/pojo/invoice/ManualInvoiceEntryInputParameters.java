package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManualInvoiceEntryInputParameters {
    private String invoiceNo;
    private String hcpcsCode;
    private Double entryAmount;
    private String postType;
    private String entryDate;
}
