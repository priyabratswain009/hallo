package com.sunknowledge.dme.rcm.repository.branch;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.repository.BranchOfficeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BranchOfficeRepositoryExtended extends BranchOfficeRepository {
    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid,Cast(uMas.user_master_uuid as varchar) user_master_uuid,bOff.branch_name, \n" +
        " bOff.branch_group_id, \n" +
        " bOff.branch_group_name, bOff.npi, bOff.ptan, bOff.taxonomy_code, \n" +
        " bOff.taxonomy_code_description, bOff.branch_no, bOff.billing_address_line_1,\n" +
        " bOff.billing_address_line_2,\n" +
        " bOff.billing_city, bOff.billing_state,bOff.billing_zip_code,\n" +
        " bOff.contact_no_1, bOff.contact_no_2, bOff.billing_email,\n" +
        " bOff.branch_company,bOff.billing_fax,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " uMas.dob, uMas.age, uMas.gender from t_branch_user_map bUMap, t_user_master uMas, t_branch_office bOff \n"+
        " where uMas.status = 'active' and bUMap.status = 'active' and bOff.status = 'active' and uMas.user_id = bUMap.user_id and bUMap.branch_id = bOff.branch_id and " +
        " CASE             WHEN uMas.middle_name is NULL THEN LOWER(CONCAT(uMas.first_name, ' ', uMas.last_name)) " +
        " WHEN uMas.middle_name = '' THEN LOWER(CONCAT(uMas.first_name, ' ', uMas.last_name)) " +
        " ELSE LOWER(CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name)) " +
        " END LIKE LOWER(CONCAT('%',?1,'%')) ",
        nativeQuery = true)
    List<Map> getBranchDetailsByUserName(@Param("userName") String userName);

    @Query(value = "select uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id, bOff.branch_group_name from t_branch_user_map bUMap, t_user_master uMas," +
        " t_branch_office bOff \n" +
        " where uMas.status = 'active' and bUMap.status = 'active' and bOff.status = 'active' and  LOWER(bOff.branch_name) LIKE LOWER(CONCAT('%', ?1,'%')) and uMas.user_id = bUMap.user_id and bUMap.branch_id = bOff.branch_id",nativeQuery = true)
    List<Map> getUserDetailsByBranchName(@Param("branchName") String branchName);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid, Cast(iLoc.item_location_uuid as varchar) item_location_uuid, iLoc.item_location_name, iLoc.description, iLoc.billing_address_line_1, \n" +
        " iLoc.billing_address_line_2, iLoc.billing_city, iLoc.billing_state, iLoc.billing_country, \n" +
        " iLoc.delivery_zip, iLoc.contact_no_1, iLoc.contact_no_2, \n" +
        " iLoc.email, iLoc.fax, bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id, bOff.branch_group_name \n" +
        " from t_branch_item_location_map bIlMap, t_item_location iLoc, t_branch_office bOff " +
        " where bIlMap.status = 'active' and iLoc.status = 'active' and bOff.status = 'active' and  LOWER(bOff.branch_name) LIKE LOWER(CONCAT('%', ?1,'%')) and iLoc.item_location_id = bIlMap.item_location_id and bIlMap.branch_id = bOff.branch_id",nativeQuery = true)
    List<Map> getLocationDetailsByBranchName(@Param("branchName") String branchName);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid, bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id,\n" +
        " bOff.branch_group_name, Cast(iMas.insurance_master_uuid as varchar) insurance_master_uuid , iMas.insurance_name, iMas.insurance_group_id,\n" +
        " iMas.insurance_group_no, iMas.insurance_group_name, iMas.address_line_1, iMas.address_line_2,\n" +
        " iMas.city, iMas.state, iMas.country, iMas.zip,\n" +
        " iMas.contact_no_1, iMas.contact_no_2, iMas.fax, iMas.efax, iMas.email, iMas.insurance_id_no \n" +
        " from t_branch_insurance_map bIMap, t_insurance_master iMas, t_branch_office bOff \n" +
        " where bIMap.status = 'active' and iMas.status = 'active' and bOff.status = 'active' and  LOWER(bOff.branch_name) LIKE LOWER(CONCAT('%', ?1,'%')) and iMas.insurance_id = bIMap.insurance_id and bIMap.branch_id = bOff.branch_id",
        nativeQuery = true)
    List<Map> getInsuranceDetailsByBranchName(@Param("branchName") String branchName);
    @Query(value = "select uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id, bOff.branch_group_name from t_branch_user_map bUMap, t_user_master uMas, t_branch_office bOff \n" +
        " where bUMap.status = 'active' and uMas.status = 'active' and bOff.status = 'active' and  bOff.branch_office_uuid = :branchUuid and uMas.user_id = bUMap.user_id and bUMap.branch_id = bOff.branch_id",nativeQuery = true)
    List<Map> getUserDetailsByBranchUUID(@Param("branchUuid") UUID branchUuid);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid, Cast(iLoc.item_location_uuid as varchar) item_location_uuid, iLoc.item_location_name, iLoc.description, iLoc.billing_address_line_1, \n" +
        " iLoc.billing_address_line_2, iLoc.billing_city, iLoc.billing_state, iLoc.billing_country, \n" +
        " iLoc.delivery_zip, iLoc.contact_no_1, iLoc.contact_no_2, \n" +
        " iLoc.email, iLoc.fax, bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id, bOff.branch_group_name \n" +
        " from t_branch_item_location_map bIlMap, t_item_location iLoc, t_branch_office bOff " +
        " where bIlMap.status = 'active' and iLoc.status = 'active' and bOff.status = 'active' and  bOff.branch_office_uuid = :branchUuid and iLoc.item_location_id = bIlMap.item_location_id and bIlMap.branch_id = bOff.branch_id",nativeQuery = true)
    List<Map> getLocationDetailsByBranchUUID(@Param("branchUuid") UUID branchUuid);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid,Cast(uMas.user_master_uuid as varchar) user_master_uuid,bOff.branch_name, \n" +
        " bOff.branch_group_id, \n" +
        " bOff.branch_group_name, bOff.npi, bOff.ptan, bOff.taxonomy_code, \n" +
        " bOff.taxonomy_code_description, bOff.branch_no, bOff.billing_address_line_1,\n" +
        " bOff.billing_address_line_2,\n" +
        " bOff.billing_city, bOff.billing_state,bOff.billing_zip_code,\n" +
        " bOff.contact_no_1, bOff.contact_no_2, bOff.billing_email,\n" +
        " bOff.branch_company,bOff.billing_fax,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " uMas.dob, uMas.age, uMas.gender from t_branch_user_map bUMap, t_user_master uMas, t_branch_office bOff \n"+
        " where bUMap.status = 'active' and uMas.status = 'active' and bOff.status = 'active' and  uMas.user_master_uuid = :userUuid and uMas.user_id = bUMap.user_id and bUMap.branch_id = bOff.branch_id",
        nativeQuery = true)
    List<Map> getBranchDetailsByUserUuid(@Param("userUuid") UUID userUuid);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid, bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id,\n" +
        " bOff.branch_group_name, Cast(iMas.insurance_master_uuid as varchar) insurance_master_uuid, iMas.insurance_name, iMas.insurance_group_id,\n" +
        " iMas.insurance_group_no, iMas.insurance_group_name, iMas.address_line_1, iMas.address_line_2,\n" +
        " iMas.city, iMas.state, iMas.country, iMas.zip,\n" +
        " iMas.contact_no_1, iMas.contact_no_2, iMas.fax, iMas.efax, iMas.email, iMas.insurance_id_no \n" +
        " from t_branch_insurance_map bIMap, t_insurance_master iMas, t_branch_office bOff \n" +
        " where bIMap.status = 'active' and iMas.status = 'active' and bOff.status = 'active' and  bOff.branch_office_uuid = :branchUuid and iMas.insurance_id = bIMap.insurance_id and bIMap.branch_id = bOff.branch_id",
        nativeQuery = true)
    List<Map> getInsuranceDetailsByBranchUUID(@Param("branchUuid") UUID branchUuid);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid, bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id, bOff.branch_group_name, \n" +
        " Cast(comp.company_uuid as varchar) company_uuid, comp.company_name, comp.primary_contact_name, \n" +
        " comp.address_line_1, comp.address_line_2, comp.city, comp.state, comp.country, comp.zip, \n" +
        " comp.contact_no_1, comp.contact_no_2, comp.fax,\n" +
        " comp.efax, comp.email, comp.company_branch_logo from t_branch_office bOff, t_company comp \n" +
        " where comp.status = 'active' and bOff.status = 'active' and  bOff.branch_office_uuid = :branchUuid and bOff.branch_company_id = comp.company_id ",
    nativeQuery = true)
    List<Map> getCompanyDetailsByBranchUUID(@Param("branchUuid") UUID branchUuid);

    @Query(value = "select Cast(bOff.branch_office_uuid as varchar) branch_office_uuid, bOff.branch_name, bOff.npi, bOff.branch_no, bOff.branch_group_id, bOff.branch_group_name, \n" +
        " Cast(comp.company_uuid as varchar) company_uuid, comp.company_name, comp.primary_contact_name, \n" +
        " comp.address_line_1, comp.address_line_2, comp.city, comp.state, comp.country, comp.zip, \n" +
        " comp.contact_no_1, comp.contact_no_2, comp.fax,\n" +
        " comp.efax, comp.email, comp.company_branch_logo from t_branch_office bOff, t_company comp \n" +
        " where comp.status = 'active' and bOff.status = 'active' and  LOWER(bOff.branch_name) LIKE LOWER(CONCAT('%', ?1,'%')) and bOff.branch_company_id = comp.company_id ",nativeQuery = true)
    List<Map> getCompanyDetailsByBranchName(@Param("branchName") String branchName);


    BranchOffice findByBranchId(Long branchId);

    BranchOffice findByBranchName(String branchName);

    BranchOffice findByBranchNo(String branchNo);

    BranchOffice findByNpi(String npi);

    BranchOffice findByBranchIdAndStatusIgnoreCase(Long branchId, String active);

    BranchOffice findByBranchNameAndStatusIgnoreCase(String branchName, String active);

    BranchOffice findByBranchNoAndStatusIgnoreCase(String branchNo, String active);

    List<BranchOffice> findByStatusIgnoreCase(String active);

    BranchOffice findByNpiAndStatusIgnoreCase(String npi, String active);

    List<BranchOffice> findByBranchIdInAndStatusIgnoreCase(List<Long> branchIdList, String active);

    List<BranchOffice> findByBranchNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select settings.f_get_branch_no()" ,nativeQuery = true)
    String getBranchNumber();

}
