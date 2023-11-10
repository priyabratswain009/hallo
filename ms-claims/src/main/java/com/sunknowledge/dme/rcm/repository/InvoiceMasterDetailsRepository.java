package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InvoiceMasterDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceMasterDetailsRepository extends JpaRepository<InvoiceMasterDetails, Long> {}
