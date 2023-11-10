package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemVendorMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemVendorMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemVendorMappingRepository extends JpaRepository<ItemVendorMapping, Long> {}
