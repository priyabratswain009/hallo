package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimProgramMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimProgramMasterRepository extends JpaRepository<ClaimProgramMaster, Long> {}
