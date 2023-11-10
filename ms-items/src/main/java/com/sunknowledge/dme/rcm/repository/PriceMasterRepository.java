package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PriceMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PriceMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceMasterRepository extends JpaRepository<PriceMaster, Long> {}
