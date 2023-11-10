package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryAbnData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryAbnDataRepository extends JpaRepository<DeliveryAbnData, Long> {}
