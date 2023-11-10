package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.IcdMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the IcdMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IcdMasterRepository extends JpaRepository<IcdMaster, Long> {}
