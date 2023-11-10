package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemProcedureCodeMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemProcedureCodeMapRepository extends JpaRepository<ItemProcedureCodeMap, Long> {}
