package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.repository.BranchUserMapRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchUserMapRepositoryExtended extends BranchUserMapRepository {
    BranchUserMap findByBranchIdAndUserIdAndStatusIgnoreCase(Long branchId, Long userId, String active);

    @Query(value = "select distinct b.branch_id as branchId, b.branch_name as branchName from settings.t_branch_user_map as a INNER JOIN settings.t_branch_office as b ON a.branch_id = b.branch_id and a.user_id = :userId and LOWER(a.status)=LOWER(:active)", nativeQuery = true)
    Object findBranchByUserIdAndStatusActive(@Param("userId") Long userId, @Param("active") String active);
}
