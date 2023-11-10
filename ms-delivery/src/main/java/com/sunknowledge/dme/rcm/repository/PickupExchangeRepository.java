package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PickupExchange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PickupExchangeRepository extends JpaRepository<PickupExchange, Long> {}
