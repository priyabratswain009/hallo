package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimFormMasterExtendedDTO {
    private UUID claimFormMasterUuid;

    private String claimFormName;

    //private String status;
}
