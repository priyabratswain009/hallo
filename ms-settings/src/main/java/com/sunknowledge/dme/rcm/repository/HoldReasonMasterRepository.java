package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the HoldReasonMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoldReasonMasterRepository extends JpaRepository<HoldReasonMaster, Long> {}
