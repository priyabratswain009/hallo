package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCMNInputParameters {
    private String patientIdNo;
    private String itemNo;
    private String dos;
    private String orderingProviderNpi;
    private String salesOrderNo;
    private Long soItemDetailsId;
}
