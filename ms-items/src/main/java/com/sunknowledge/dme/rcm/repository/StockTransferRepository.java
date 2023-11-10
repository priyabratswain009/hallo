package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StockTransfer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StockTransfer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockTransferRepository extends JpaRepository<StockTransfer, Long> {}
