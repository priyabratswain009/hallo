package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StockAdjustment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockAdjustmentRepository extends JpaRepository<StockAdjustment, Long> {}
