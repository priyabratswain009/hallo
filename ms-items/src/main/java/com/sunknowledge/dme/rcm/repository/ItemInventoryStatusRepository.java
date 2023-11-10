package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemInventoryStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemInventoryStatusRepository extends JpaRepository<ItemInventoryStatus, Long> {}
