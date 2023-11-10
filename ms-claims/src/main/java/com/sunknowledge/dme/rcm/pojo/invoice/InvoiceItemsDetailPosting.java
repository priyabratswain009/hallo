package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemsDetailPosting {
    private InvoiceItemHeaderValue invoiceItemHeaderValue;
    private List<InvoiceItemPostingDetails> invoiceItemPostingDetailsList;
    private InvoiceItemTotals invoiceItemTotals;
}
