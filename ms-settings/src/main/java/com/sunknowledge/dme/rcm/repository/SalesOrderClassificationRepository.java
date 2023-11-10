package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClassification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SalesOrderClassification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderClassificationRepository extends JpaRepository<SalesOrderClassification, Long> {}
