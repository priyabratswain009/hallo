package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.AbnDelivery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AbnDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbnDeliveryRepository extends JpaRepository<AbnDelivery, Long> {}
