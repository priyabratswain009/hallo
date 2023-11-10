package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.repository.RoleUserMapRepository;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RoleUserMapRepositoryExtended extends RoleUserMapRepository {
    RoleUserMap findByRoleIdAndUserIdAndStatusIgnoreCase(Long roleId, Long userId, String active);

    RoleUserMap findByUserIdAndStatusIgnoreCase(Long userId, String active);

    @Query(value = "select Cast(uMas.user_master_uuid as varchar) user_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description \n" +
        " from t_role_user_map rUMap, t_user_master uMas, t_role_master rMas \n" +
        " where rUMap.status = 'Active' and uMas.status = 'active' and rMas.status = 'Active' and  rMas.role_master_uuid = :roleUUID and \n" +
        " uMas.user_id = rUMap.user_id and rUMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleUserDetailsByRoleUUID(@Param("roleUUID") UUID roleUUID);


    @Query(value = "select Cast(uMas.user_master_uuid as varchar) user_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description \n" +
        " from t_role_user_map rUMap, t_user_master uMas, t_role_master rMas \n" +
        " where LOWER(rUMap.status) = 'active' and LOWER(uMas.status) = 'active' and LOWER(rMas.status) = 'active' and  rMas.role_no = :roleNo and \n" +
        " uMas.user_id = rUMap.user_id and rUMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleUserDetailsByRoleNo(@Param("roleNo") String roleNo);

    @Query(value = "select Cast(uMas.user_master_uuid as varchar) user_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description \n" +
        " from t_role_user_map rUMap, t_user_master uMas, t_role_master rMas \n" +
        " where LOWER(rUMap.status) = 'active' and LOWER(uMas.status) = 'active' and LOWER(rMas.status) = 'active' and  uMas.user_no = :userNo and \n" +
        " uMas.user_id = rUMap.user_id and rUMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleUserDetailsByUserNo(@Param("userNo") String userNo);

    @Query(value = "select Cast(uMas.user_master_uuid as varchar) user_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description \n" +
        " from t_role_user_map rUMap, t_user_master uMas, t_role_master rMas \n" +
        " where LOWER(rUMap.status) = 'active' and LOWER(uMas.status) = 'active' and LOWER(rMas.status) = 'active' and  LOWER(rMas.role_name) LIKE LOWER(CONCAT('%',?1,'%')) and \n" +
        " uMas.user_id = rUMap.user_id and rUMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleUserDetailsByRoleName(@Param("roleName") String roleName);

    @Query(value = "select Cast(uMas.user_master_uuid as varchar) user_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description \n" +
        " from t_role_user_map rUMap, t_user_master uMas, t_role_master rMas \n" +
        " where LOWER(rUMap.status) = 'active' and LOWER(uMas.status) = 'active' and LOWER(rMas.status) = 'active' and  " +
        " uMas.user_id = rUMap.user_id and rUMap.role_id = rMas.role_id and \n" +
        " CASE             " +
        " WHEN uMas.middle_name is NULL THEN LOWER(CONCAT(uMas.first_name, ' ', uMas.last_name)) \n" +
        " WHEN uMas.middle_name = '' THEN LOWER(CONCAT(uMas.first_name, ' ', uMas.last_name)) \n" +
        " ELSE LOWER(CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name)) \n" +
        " END LIKE LOWER(CONCAT('%',?1,'%')) \n",nativeQuery = true)
    List<Map> getRoleUserDetailsByUserName(@Param("userName") String userName);

    @Query(value = "select Cast(uMas.user_master_uuid as varchar) user_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, uMas.address_line_1,uMas.address_line_2, uMas.city, uMas.state, uMas.country, uMas.zip, uMas.contact_no_1, \n" +
        " uMas.contact_no_2, uMas.email, uMas.fax, uMas.efax, uMas.job_title,\n" +
        " CASE             WHEN uMas.middle_name is NULL THEN CONCAT(uMas.first_name, ' ', uMas.last_name) " +
        " ELSE CONCAT(uMas.first_name, ' ', uMas.middle_name, ' ', uMas.last_name) " +
        " END as user_name ,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description \n" +
        " from t_role_user_map rUMap, t_user_master uMas, t_role_master rMas \n" +
        " where LOWER(rUMap.status) = 'active' and LOWER(uMas.status) = 'active' and LOWER(rMas.status) = 'active' and  uMas.user_master_uuid = :userUUID and \n" +
        " uMas.user_id = rUMap.user_id and rUMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleUserDetailsByUserUUID(@Param("userUUID") UUID userUUID);
}
