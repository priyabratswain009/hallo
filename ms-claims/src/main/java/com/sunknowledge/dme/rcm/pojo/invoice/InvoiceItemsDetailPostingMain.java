package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemsDetailPostingMain {
    private List<InvoiceItemsDetailPosting> invoiceItemsDetailPostingList;
    private InvoiceItemDetailsTotal invoiceItemDetailsTotal;
}
