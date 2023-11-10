package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseOrderItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItems, Long> {}
