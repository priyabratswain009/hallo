package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FunctionalityMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FunctionalityMasterRepository extends JpaRepository<FunctionalityMaster, Long> {}
