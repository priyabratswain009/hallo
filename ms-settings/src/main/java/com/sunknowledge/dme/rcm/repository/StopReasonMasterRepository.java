package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StopReasonMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopReasonMasterRepository extends JpaRepository<StopReasonMaster, Long> {}
