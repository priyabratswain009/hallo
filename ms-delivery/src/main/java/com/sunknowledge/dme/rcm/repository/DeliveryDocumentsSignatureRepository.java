package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryDocumentsSignature entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryDocumentsSignatureRepository extends JpaRepository<DeliveryDocumentsSignature, Long> {}
