package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManualInvoiceEntryOutputParameters {
    private Long invoiceLineItemPostingId;
    private Long itemId;
    private String postingDate;
    private Long postedById;
    private String postedByName;
    private String postingComment;
    private String postType;
    private Long depositId;
    private Long receiptId;
    private Double postAmount;
    private String postStatus;
    private String invoicePostingDetailsUuid;
    private String invoiceNo;
    private String invoiceDate;
    private String hcpcsCode;
    private String postingNo;
}
