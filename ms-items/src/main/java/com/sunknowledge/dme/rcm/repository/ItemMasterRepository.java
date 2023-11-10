package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long> {}
