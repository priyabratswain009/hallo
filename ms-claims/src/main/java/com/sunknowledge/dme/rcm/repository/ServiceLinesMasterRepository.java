package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ServiceLinesMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ServiceLinesMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceLinesMasterRepository extends JpaRepository<ServiceLinesMaster, Long> {}
