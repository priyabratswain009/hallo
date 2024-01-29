package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DMEAuthorizationMedicareInputDTO {
    private String companyName;
    private String formType;
    private String formName;
    private String requestDate;
    private String numberOfPages;
    private String hcpcsCode;
    private Boolean ltValue;
    private Boolean rtValue;
    private Boolean reviewVoluntaryAccessoryCode;
    private List<String> accessoryHcpcsCodes;
    private Boolean initialValue;
    private Boolean resubmissionValue;
    private Boolean expeditedReview;
    private String submissionTypeComment;
    private String beneficiaryInfoName;
    private String beneficiaryInfoMedicareID;
    private String beneficiaryInfoDOB;
    private String beneficiaryInfoStateOfResidence;
    private String supplierInfoName;
    private String supplierInfoNpi;
    private String supplierInfoPtan;
    private String supplierInfoPhone;
    private String supplierInfoAddress;
    private String supplierInfoFax;
    private String supplierInfoPointOfContact;
    private String treatingPracInfoName;
    private String treatingPracInfoNpi;
    private String treatingPracInfoPhone;
    private String treatingPracInfoAddress;
    private String treatingPracInfoFax;
    private String footerFax;
    private String footerMail;
    private String footerMailAddr;
    private String footerMailCity;
    private String footerMailState;
    private String footerMailZip;
}
