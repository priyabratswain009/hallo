package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DepreciationMethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DepreciationMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepreciationMethodRepository extends JpaRepository<DepreciationMethod, Long> {}
