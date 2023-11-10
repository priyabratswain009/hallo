package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProcedureCodeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcedureCodeMasterRepository extends JpaRepository<ProcedureCodeMaster, Long> {}
