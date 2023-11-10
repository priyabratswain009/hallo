package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WipQueueDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WipQueueDetailsRepository extends JpaRepository<WipQueueDetails, Long> {}
