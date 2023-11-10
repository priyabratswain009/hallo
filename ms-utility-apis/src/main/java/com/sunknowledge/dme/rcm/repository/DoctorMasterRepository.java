package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DoctorMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoctorMasterRepository extends JpaRepository<DoctorMaster, Long> {}
