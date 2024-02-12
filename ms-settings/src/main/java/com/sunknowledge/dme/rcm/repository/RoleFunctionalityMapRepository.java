package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RoleFunctionalityMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleFunctionalityMapRepository extends JpaRepository<RoleFunctionalityMap, Long> {}
