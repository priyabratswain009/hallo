package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EndpointMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EndpointMasterRepository extends JpaRepository<EndpointMaster, Long> {}
