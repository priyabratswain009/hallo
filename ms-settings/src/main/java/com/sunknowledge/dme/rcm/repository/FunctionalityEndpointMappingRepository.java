package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FunctionalityEndpointMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FunctionalityEndpointMappingRepository extends JpaRepository<FunctionalityEndpointMapping, Long> {}
