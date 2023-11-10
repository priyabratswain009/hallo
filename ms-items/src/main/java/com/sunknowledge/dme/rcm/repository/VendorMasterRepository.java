package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.VendorMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VendorMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMaster, Long> {}
