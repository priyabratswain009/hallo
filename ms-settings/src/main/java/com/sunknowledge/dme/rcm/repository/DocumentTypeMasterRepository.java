package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DocumentTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DocumentTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentTypeMasterRepository extends JpaRepository<DocumentTypeMaster, Long> {}
