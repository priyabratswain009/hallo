package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesRep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SalesRep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {}
