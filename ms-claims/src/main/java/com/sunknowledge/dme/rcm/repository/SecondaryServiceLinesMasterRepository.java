package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SecondaryServiceLinesMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecondaryServiceLinesMasterRepository extends JpaRepository<SecondaryServiceLinesMaster, Long> {}
