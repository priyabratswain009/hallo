package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemSerialNumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemSerialNumberRepository extends JpaRepository<ItemSerialNumber, Long> {}
