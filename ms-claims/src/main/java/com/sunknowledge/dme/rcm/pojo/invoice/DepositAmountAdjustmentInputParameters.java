package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositAmountAdjustmentInputParameters {
    private String checkOrEFTTraceNumber;
    private String checkIssueOrEFTEffectiveDate;
    private Double receiptAmount;
    private Double adjustmentAmount;
}
