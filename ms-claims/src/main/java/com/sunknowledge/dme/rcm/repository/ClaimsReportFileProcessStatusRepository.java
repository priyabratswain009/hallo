package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimsReportFileProcessStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsReportFileProcessStatusRepository extends JpaRepository<ClaimsReportFileProcessStatus, Long> {}
