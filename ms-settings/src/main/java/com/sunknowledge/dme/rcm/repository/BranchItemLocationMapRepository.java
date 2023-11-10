package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchItemLocationMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchItemLocationMapRepository extends JpaRepository<BranchItemLocationMap, Long> {}
