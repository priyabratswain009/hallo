package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaskReasonMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaskReasonMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskReasonMasterRepository extends JpaRepository<TaskReasonMaster, Long> {}
