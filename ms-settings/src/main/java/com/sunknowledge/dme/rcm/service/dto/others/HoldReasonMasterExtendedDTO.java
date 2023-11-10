package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldReasonMasterExtendedDTO {
    //private Long holdReasonId;

    private UUID holdReasonMasterUuid;

    private String holdReasonName;

    private String status;

    //private Long createdById;

    //private String createdDate;

    //private Long updatedById;

    //private String updatedDate;

    //private String createdByName;

    //private String updatedByName;
}
