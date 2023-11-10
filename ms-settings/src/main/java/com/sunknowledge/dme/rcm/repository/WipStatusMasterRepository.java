package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WipStatusMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WipStatusMasterRepository extends JpaRepository<WipStatusMaster, Long> {}
