package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.FacilityMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FacilityMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityMasterRepository extends JpaRepository<FacilityMaster, Long> {}
