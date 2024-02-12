package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RoleUserMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleUserMapRepository extends JpaRepository<RoleUserMap, Long> {}
