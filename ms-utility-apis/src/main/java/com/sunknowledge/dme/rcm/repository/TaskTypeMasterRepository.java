package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaskTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskTypeMasterRepository extends JpaRepository<TaskTypeMaster, Long> {}
