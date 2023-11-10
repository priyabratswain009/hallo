package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchUserMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchUserMapRepository extends JpaRepository<BranchUserMap, Long> {}
