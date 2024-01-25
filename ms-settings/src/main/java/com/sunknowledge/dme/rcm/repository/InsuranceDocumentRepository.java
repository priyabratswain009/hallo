package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsuranceDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InsuranceDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceDocumentRepository extends JpaRepository<InsuranceDocument, Long> {}
