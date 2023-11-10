package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BenefitCoverageResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenefitCoverageResponseRepository extends JpaRepository<BenefitCoverageResponse, Long> {}
