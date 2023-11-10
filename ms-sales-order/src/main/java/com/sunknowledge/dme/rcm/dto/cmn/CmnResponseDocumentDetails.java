package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnResponseDocumentDetails {
    private Long cmnId;
    private String lengthOfNeed;
    private String refillAuthorized;
    private String frequencyCount;
    private String frequencyInterval;
    private String cmnDocumentType;
}
