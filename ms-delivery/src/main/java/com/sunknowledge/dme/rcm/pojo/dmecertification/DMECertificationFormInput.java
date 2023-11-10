package com.sunknowledge.dme.rcm.pojo.dmecertification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DMECertificationFormInput {
    private String formName;
    private String deliveryTicketId;
    private String companyName;
    private String name;
    private String patientId;
    private String streetAddress;
    private String city;
    private String telephoneNo;

    private String ProviderName;
    private String pan;
    private String NPIAPI;
    private String tpi;

    private String currentdate;
    private String currentdate_1;

    private String patientSignature;
    private String patientRepresentativeName;
    private String patientRelationship;
    private String reasonNotToSign;
    private String date;

    private String signatureCompRepresentative;
    private String nameCompRepresentative;

    private String dateOfService;
    private List<DeliveryItemDetails> deliveryItemDetailsList;
}
