package com.sunknowledge.dme.rcm.service.common;

import java.time.LocalDate;
import java.util.UUID;


public interface DoctorMasterAndMedicalInfoProjection {
    Long getDoctorId();
    String getFirstName();

    String getMiddleName();

    String getLastName();

    String getTitle();

    String getSuffix();

    Long getDoctorSpecialityId();

    String getAddlIdentifier();

    UUID getDoctorMasterUuid();

    String getAddressLine1();

    String getAddressLine2();

    String getCity();

    String getState();

    String getCountryName();

    String getZip();

    String getContactNo1();

    String getContactNo2();

    String getFaxNumber();

    String getEfax();

    String getEmail();

    String getRelationship();

    String getModeOfContact();

    String getNpiNumber();

    String getGender();

    String getEnumerationDate();

    String getCountryCode();

    String getAddressPurpose();

    String getAddressType();

    String getPostalCode();

    String getTaxonomyCode();

    String getTaxonomyGroup();

    String getTaxonomyDesc();

    String getPracticeState();

    String getLicenceNo();

    String getStatus();

    Long getCreatedById();

    String getCreatedDate();

    String getCreatedByName();

    String getUpdatedByName();

    Long getUpdatedById();

    String getUpdatedDate();
    Long getMedicalInfoId();

//    Long doctorId;
//
//    String licenceNo;

    String getLicExperiationDate();

    String getDea();

    String getUpin();

    String getStateMedicaidId();

    String getNpi();

    String getCommericialNo();

    Long getLocationId();

//    String taxonomyCode;

    String getPecosCertifiedStatus();

    String getUser1();

    String getUser2();

    String getUser3();

//    String taxonomyGroup;
//
//    String taxonomyDesc;
//
//    String practiceState;

    String getPrimaryTaxonomy();

//    Long createdById;
//
//    String createdByName;
//
//    LocalDate createdDate;
//
//    Long updatedById;
//
//    String updatedByName;
//
//    LocalDate updatedDate;

    UUID getDoctorMedicalInfoUuid();
}
