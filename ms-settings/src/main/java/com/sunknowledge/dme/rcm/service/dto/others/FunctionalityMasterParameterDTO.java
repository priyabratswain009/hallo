package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionalityMasterParameterDTO {
    private UUID functionalityMasterUUID;
    private String functionalityName;
    private String functionalityDescription;
    //private String status;
}
