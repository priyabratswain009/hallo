package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemAssetNumberMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAssetNumberMapRepository extends JpaRepository<ItemAssetNumberMap, Long> {}
