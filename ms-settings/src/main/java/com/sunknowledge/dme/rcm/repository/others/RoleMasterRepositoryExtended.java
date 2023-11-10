package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.RoleMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleMasterRepositoryExtended extends RoleMasterRepository {

    @Query(value = "select settings.f_get_role_no()" ,nativeQuery = true)
    String getRolNumber();

    RoleMaster findByRoleNo(String data);

    List<RoleMaster> findByRoleNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select settings.get_t_role_master_id_by_uuid(:roleMasterUUID)" ,nativeQuery = true)
    Long getIDByUUID(@Param("roleMasterUUID") UUID roleMasterUUID);

    RoleMaster findByRoleIdAndStatusIgnoreCase(Long id, String active);

    List<RoleMaster> findByRoleMasterUuidInAndStatusIgnoreCase(List<UUID> roleUUID, String active);
}
