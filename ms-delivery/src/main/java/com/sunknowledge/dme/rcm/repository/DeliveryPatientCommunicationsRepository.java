package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryPatientCommunications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryPatientCommunicationsRepository extends JpaRepository<DeliveryPatientCommunications, Long> {}
