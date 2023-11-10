package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InvoiceLineItemDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceLineItemDetailsRepository extends JpaRepository<InvoiceLineItemDetails, Long> {}
