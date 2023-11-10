package com.sunknowledge.dme.rcm.pojo.invoice;

public interface PostingAutoAdjustmentSettingsProjection {
    Long getAdjustmentSettingsId();
    Long getBranchId();
    String getBranchNo();
    Double getAdjustmentAmount();
    String getStatus();
}
