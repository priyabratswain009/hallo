package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingAutoAdjustmentSettings {
    private Long adjustmentSettingsId;
    private Long branchId;
    private String branchNo;
    private Double adjustmentAmount;
    private String status;
}
