package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchInsuranceMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchInsuranceMapRepository extends JpaRepository<BranchInsuranceMap, Long> {}
