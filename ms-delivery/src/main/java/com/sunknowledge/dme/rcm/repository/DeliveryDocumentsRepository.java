package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryDocuments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryDocuments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryDocumentsRepository extends JpaRepository<DeliveryDocuments, Long> {}
