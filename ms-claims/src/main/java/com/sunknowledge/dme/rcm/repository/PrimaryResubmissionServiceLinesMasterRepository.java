package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PrimaryResubmissionServiceLinesMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimaryResubmissionServiceLinesMasterRepository extends JpaRepository<PrimaryResubmissionServiceLinesMaster, Long> {}
