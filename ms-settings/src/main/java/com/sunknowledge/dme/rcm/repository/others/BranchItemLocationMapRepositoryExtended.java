package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMap;
import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchItemLocationMapRepositoryExtended extends BranchItemLocationMapRepository {
    BranchItemLocationMap findByItemLocationIdAndBranchIdAndStatusIgnoreCase(Long itemLocationId, Long branchId, String active);

    List<BranchItemLocationMap> findByStatusIgnoreCase(String active);

    List<BranchItemLocationMap> findByBranchIdAndStatusIgnoreCase(Long branchId, String active);

    List<BranchItemLocationMap> findByItemLocationIdAndStatusIgnoreCase(Long itemLocationId, String active);

    BranchItemLocationMap findByBranchItemLocationMapId(Long id);

    Optional<BranchItemLocationMap> findByBranchItemLocationMapUuid(UUID uuid);
}
