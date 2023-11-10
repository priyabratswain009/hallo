package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryItemsRepository extends JpaRepository<DeliveryItems, Long> {}
