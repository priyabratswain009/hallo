package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.RoleMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleMasterRepositoryExtended extends RoleMasterRepository {

    @Query(value = "select settings.f_get_role_no()" ,nativeQuery = true)
    String getRolNumber();

    RoleMaster findByRoleNo(String data);

    List<RoleMaster> findByRoleNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select settings.get_t_role_master_id_by_uuid(:roleMasterUUID)" ,nativeQuery = true)
    Long getIDByUUID(@Param("roleMasterUUID") UUID roleMasterUUID);

    Optional<RoleMaster> findByRoleIdAndStatusIgnoreCase(Long id, String active);

    List<RoleMaster> findByRoleMasterUuidInAndStatusIgnoreCase(List<UUID> roleUUID, String active);

    RoleMaster findByRoleNoAndStatusIgnoreCase(String data, String active);

    List<RoleMaster> findByStatusIgnoreCase(String active);

    @Query(value = "select * from settings.inactive_role_functionality_user_map_relatedinfo(:roleId)",nativeQuery = true)
    Long setInactiveRoleRelatedInfo(@Param("roleId") long roleId);

    Optional<RoleMaster> findByRoleMasterUuid(UUID uuid);
    @Query(value = "SELECT a.* FROM settings.t_role_master a INNER JOIN settings.t_role_user_map b ON a.role_id = b.role_id WHERE b.user_id = :userId\n" +
        "AND lower(a.status) = :status AND lower(b.status) = :status", nativeQuery = true)
    RoleMaster findRoleNameByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);

    RoleMaster findByRoleMasterUuidAndStatusIgnoreCase(UUID uuid, String status);
}
