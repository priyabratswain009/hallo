package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PickupExchangeItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PickupExchangeItemsRepository extends JpaRepository<PickupExchangeItems, Long> {}
