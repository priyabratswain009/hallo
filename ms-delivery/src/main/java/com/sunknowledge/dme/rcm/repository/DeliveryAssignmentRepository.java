package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryAssignment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryAssignment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment, Long> {}
