package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InsuranceMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceMasterRepository extends JpaRepository<InsuranceMaster, Long> {}
