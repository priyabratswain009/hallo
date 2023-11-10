package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryTicketRepository extends JpaRepository<DeliveryTicket, Long> {}
