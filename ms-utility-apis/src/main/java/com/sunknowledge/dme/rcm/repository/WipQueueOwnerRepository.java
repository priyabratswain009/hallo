package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WipQueueOwner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WipQueueOwnerRepository extends JpaRepository<WipQueueOwner, Long> {}
