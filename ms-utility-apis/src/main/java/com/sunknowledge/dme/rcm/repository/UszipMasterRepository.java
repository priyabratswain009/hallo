package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.UszipMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UszipMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UszipMasterRepository extends JpaRepository<UszipMaster, Long> {}
