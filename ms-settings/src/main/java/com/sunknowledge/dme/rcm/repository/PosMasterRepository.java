package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PosMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PosMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PosMasterRepository extends JpaRepository<PosMaster, Long> {}
