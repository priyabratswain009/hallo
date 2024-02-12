package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemLocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemLocationRepository extends JpaRepository<ItemLocation, Long> {}
