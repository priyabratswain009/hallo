package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimProgramMasterExtendedDTO {
    private UUID claimProgramMasterUuid;

    private String claimProgramMasterKey;

    private String claimProgramMasterValue;

    //private String status;
}
