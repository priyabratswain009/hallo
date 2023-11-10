package com.sunknowledge.dme.rcm.repository.nppes;

import com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo;
import com.sunknowledge.dme.rcm.repository.DoctorMedicalInfoRepository;
import com.sunknowledge.dme.rcm.service.common.DoctorMasterAndMedicalInfoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DoctorMedicalInfoRepo extends DoctorMedicalInfoRepository {
    @Query("FROM DoctorMedicalInfo WHERE doctorId = :doctorId")
    List<DoctorMedicalInfo> getDoctorsMedicalInfoListOnDoctor(@Param("doctorId") Long doctorId);


    @Query(value = "select m.medical_info_id as medicalInfoId, m.licence_no as licenceNo, m.lic_experiation_date as licExperiationDate, " +
        " m.dea as dea, m.upin as upin, m.state_medicaid_id as stateMedicaidId, m.npi as npi, " +
        " m.commericial_no as commericialNo, m.location_id as locationId, m.taxonomy_code as taxonomyCode, \n" +
        " m.pecos_certified_status as pecosCertifiedStatus, m.user_1 as user1, m.user_2 as user2, m.notes as notes, cast(m.doctor_medical_info_uuid as varchar) as doctorMedicalInfoUuid, \n" +
        " m.taxonomy_group as taxonomyGroup, m.taxonomy_desc as taxonomyDesc, m.practice_state as practiceState, m.primary_taxonomy as primaryTaxonomy," +
        " d.doctor_id as doctorId, d.first_name as firstName, d.middle_name as middleName, d.last_name as lastName, d.title as title, d.suffix as suffix, d.doctor_speciality_id as doctorSpecialityId," +
        " d.addl_identifier as addlIdentifier, d.status as status, \n" +
        " cast(d.doctor_master_uuid as varchar) as doctorMasterUuid, d.address_line_1 as addressLine1, d.address_line_2 as addressLine2, d.city as city, " +
        " d.state as state, d.country_name as countryName, d.zip as zip, d.contact_no_1 as contactNo1, \n" +
        " d.contact_no_2 as contactNo2, d.fax_number as faxNumber, d.efax as efax, d.email as email, d.relationship as relationship, d.mode_of_contact as modeOfContact, " +
        " d.npi_number as npiNumber, d.gender as gender, d.enumeration_date as enumerationDate, d.country_code as countryCode," +
        " d.address_purpose as addressPurpose, d.address_type as addressType, \n" +
        " d.postal_code as postalCode FROM t_doctor_medical_info m JOIN t_doctor_master d " +
        " on m.doctor_id = d.doctor_id and d.doctor_id = :doctorId and m.primary_taxonomy = 'true' ",
        nativeQuery = true)
    List<DoctorMasterAndMedicalInfoProjection> getDoctorMasterWithMedicalInfo(@Param("doctorId") Long doctorId);
}
