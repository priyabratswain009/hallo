package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimsCOB835Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsCOB835MasterRepository extends JpaRepository<ClaimsCOB835Master, Long> {}
