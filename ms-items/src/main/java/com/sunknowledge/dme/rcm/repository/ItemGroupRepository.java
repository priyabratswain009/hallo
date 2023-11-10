package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {}
