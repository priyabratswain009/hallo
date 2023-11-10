package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaxZone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaxZone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxZoneRepository extends JpaRepository<TaxZone, Long> {}
