package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.repository.BranchUserMapRepository;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapExtendedDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BranchUserMapRepositoryExtended extends BranchUserMapRepository {
    BranchUserMap findByBranchIdAndUserIdAndStatusIgnoreCase(Long branchId, Long userId, String active);

    @Query(value = "select distinct b.branch_id as branchId, b.branch_name as branchName from settings.t_branch_user_map as a INNER JOIN settings.t_branch_office as b ON a.branch_id = b.branch_id and a.user_id = :userId and LOWER(a.status)=LOWER(:status)", nativeQuery = true)
    Object findBranchByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);


    List<BranchUserMap> findByStatusIgnoreCase(String status);

    Optional<BranchUserMap> findByMapIdAndStatusIgnoreCase(Long id, String status);

    @Query(value = " select distinct b.user_id as id, \n" +
        " (CASE WHEN b.middle_name is NULL THEN CONCAT(b.first_name, ' ', b.last_name) \n" +
        " ELSE CONCAT(b.first_name, ' ', b.middle_name, ' ', b.last_name) \n" +
        " END) as title from settings.t_branch_user_map as a INNER JOIN \n" +
        " settings.t_user_master as b ON a.user_id = b.user_id and a.branch_id = :branchId and LOWER(a.status)=LOWER(:status)", nativeQuery = true)
    List<Map> findByStatusIgnoreCaseAndBranchId(@Param("status") String status, @Param("branchId") Long branchId);

    @Query(value = "select a.map_id as mapId,a.branch_id as branchId,a.user_id as userId ,a.description ,a.status ,a.created_by_id as createdById," +
        "a.created_date as createdDate ,a.updated_by_name as updatedByName ,\n" +
        "a.created_by_name as createdByName ,cast(a.branch_user_map_uuid as varchar) as branchUserMapUuid,a.updated_date as updatedDate ," +
        "a.updated_by_id as updatedById ,tbo.branch_name as branchName, \n" +
        "        (CASE WHEN b.middle_name is NULL THEN CONCAT(b.first_name, ' ', b.last_name) \n" +
        "         ELSE CONCAT(b.first_name, ' ', b.middle_name, ' ', b.last_name) \n" +
        "         END) as userName from settings.t_branch_user_map as a INNER JOIN \n" +
        "         settings.t_user_master as b ON a.user_id = b.user_id  inner join settings.t_branch_office tbo on tbo.branch_id = a.branch_id \n" +
        "        and LOWER(a.status)=LOWER('active') and LOWER(b.status)=LOWER('active') and LOWER(tbo.status)=LOWER('active')", nativeQuery = true)
    List<BranchUserMapExtendedDTO> getByStatus(@Param("status") String status);

    Optional<BranchUserMap> findByBranchUserMapUuid(UUID uuid);
}
