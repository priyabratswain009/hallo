package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimFormMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimFormMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimFormMasterRepository extends JpaRepository<ClaimFormMaster, Long> {}
