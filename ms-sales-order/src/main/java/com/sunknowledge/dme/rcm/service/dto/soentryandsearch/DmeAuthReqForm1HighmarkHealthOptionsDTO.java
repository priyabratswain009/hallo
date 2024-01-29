package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmeAuthReqForm1HighmarkHealthOptionsDTO {
    private String date;
    private String memberName;
    private String memberId;
    private String dob;
    private String diagnosis;
    private String ict10Code;
    private String providerName;
    private String providerNpiNumber;
    private String practiceNameAndAddress;
    private String providerPhone;
    private String providerFax;
    private String dmeProviderName;
    private String dmeProviderNpiNumber;
    private String dmeProviderAddress;
    private String dmeProviderPhone;
    private String dmeProviderFax;
    private String contactName;
    private String contactPhone;
    private Boolean purchaseYes;
    private Boolean purchaseNo;
    private Boolean rentalYes;
    private Boolean rentalNo;
    private Boolean initialRequestYes;
    private Boolean initialRequestNo;
    private Boolean replacementYes;
    private Boolean replacementNo;
    private List<ProcedureCodeDataDTO> procedureCodeData;
    private String clinicalDocumentation;
    private String notes;
}
