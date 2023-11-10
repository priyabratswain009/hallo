package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DoctorMedicalInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoctorMedicalInfoRepository extends JpaRepository<DoctorMedicalInfo, Long> {}
