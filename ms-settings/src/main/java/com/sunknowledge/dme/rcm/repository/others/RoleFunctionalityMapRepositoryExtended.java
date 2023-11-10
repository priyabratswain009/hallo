package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap;
import com.sunknowledge.dme.rcm.repository.RoleFunctionalityMapRepository;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RoleFunctionalityMapRepositoryExtended extends RoleFunctionalityMapRepository {
    List<RoleFunctionalityMap> findByRoleIdAndStatusIgnoreCase(Long aLong, String active);

    RoleFunctionalityMap findByRoleIdAndFunctionalityIdAndStatusIgnoreCase(Long roleId, Long funcId, String active);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description  \n" +
        " from t_role_functionality_map rFMap, t_functionality_master fMas, t_role_master rMas  \n" +
        " where LOWER(rFMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(rMas.status) = 'active' and  \n" +
        " rMas.role_no = :roleNo and  \n" +
        " fMas.functionality_id = rFMap.functionality_id and rFMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleFunctionalityDetailsByRoleNo(@Param("roleNo") String roleNo);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description  \n" +
        " from t_role_functionality_map rFMap, t_functionality_master fMas, t_role_master rMas  \n" +
        " where LOWER(rFMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(rMas.status) = 'active' and  \n" +
        " fMas.functionality_no = :functionalityNo and  \n" +
        " fMas.functionality_id = rFMap.functionality_id and rFMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleFunctionalityDetailsByFunctionalityNo(@Param("functionalityNo") String functionalityNo);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description  \n" +
        " from t_role_functionality_map rFMap, t_functionality_master fMas, t_role_master rMas  \n" +
        " where LOWER(rFMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(rMas.status) = 'active' and  \n" +
        " LOWER(rMas.role_name) LIKE LOWER(CONCAT('%',?1,'%')) and  \n" +
        " fMas.functionality_id = rFMap.functionality_id and rFMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleFunctionalityDetailsByRoleName(@Param("roleName") String roleName);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description  \n" +
        " from t_role_functionality_map rFMap, t_functionality_master fMas, t_role_master rMas  \n" +
        " where LOWER(rFMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(rMas.status) = 'active' and  \n" +
        " LOWER(fMas.functionality_name) LIKE LOWER(CONCAT('%',?1,'%')) and  \n" +
        " fMas.functionality_id = rFMap.functionality_id and rFMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleFunctionalityDetailsByFunctionalityName(@Param("functionalityName") String functionalityName);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description  \n" +
        " from t_role_functionality_map rFMap, t_functionality_master fMas, t_role_master rMas  \n" +
        " where LOWER(rFMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(rMas.status) = 'active' and  \n" +
        " rMas.role_master_uuid = :roleUUID and  \n" +
        " fMas.functionality_id = rFMap.functionality_id and rFMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleFunctionalityDetailsByRoleUUID(@Param("roleUUID") UUID roleUUID);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, Cast(rMas.role_master_uuid as varchar) role_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " rMas.role_name, rMas.role_code, rMas.role_description  \n" +
        " from t_role_functionality_map rFMap, t_functionality_master fMas, t_role_master rMas  \n" +
        " where LOWER(rFMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(rMas.status) = 'active' and  \n" +
        " fMas.functionality_master_uuid = :functionalityUUID and  \n" +
        " fMas.functionality_id = rFMap.functionality_id and rFMap.role_id = rMas.role_id",nativeQuery = true)
    List<Map> getRoleFunctionalityDetailsByFunctionalityUUID(@Param("functionalityUUID") UUID functionalityUUID);

    RoleFunctionalityMap findByRoleIdAndFunctionalityId(Long roleId, Long funcId);
}
