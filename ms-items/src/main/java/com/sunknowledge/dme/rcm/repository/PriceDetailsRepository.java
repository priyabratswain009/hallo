package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PriceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceDetailsRepository extends JpaRepository<PriceDetails, Long> {}
