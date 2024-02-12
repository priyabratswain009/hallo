package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceGroupMasterExtendedDTO {

    private UUID insuranceGroupMasterUuid;

    private String insuranceGroupMasterName;

    private String insuranceGroupMasterDescription;

    private String insuranceGroupMasterNo;

    //private String status;
}
