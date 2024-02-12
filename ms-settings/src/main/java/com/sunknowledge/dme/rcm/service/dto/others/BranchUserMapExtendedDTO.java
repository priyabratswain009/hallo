package com.sunknowledge.dme.rcm.service.dto.others;

import java.time.LocalDate;
import java.util.UUID;


public interface BranchUserMapExtendedDTO {
    String getBranchName();
    String getUserName();
    Long getMapId();

    Long getBranchId();

    Long getUserId();

    String getDescription();

    String getStatus();

    Long getCreatedById();

    LocalDate getCreatedDate();

    LocalDate getUpdatedDate();

    Long getUpdatedById();

    String getUpdatedByName();

    String getCreatedByName();

    UUID getBranchUserMapUuid();
}
