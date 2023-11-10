package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemItemlocationMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemItemlocationMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemItemlocationMapRepository extends JpaRepository<ItemItemlocationMap, Long> {}
