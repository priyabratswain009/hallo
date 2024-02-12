package com.sunknowledge.dme.rcm.service.dto.branch;

import java.time.LocalDate;
import java.util.UUID;


public interface BranchGroupExtendedDTO {
    String getCompanyName();
    Long getBranchGroupId();
    String getBranchGroupName();
    Long getCompanyId();
    String getStatus();
    Long getCreatedById();
    LocalDate getCreatedDate();
    LocalDate getUpdatedDate();
    Long getUpdatedById();
    String getCreatedByName();
    String getUpdatedByName();
    String getBranchGroupUuid();
}
