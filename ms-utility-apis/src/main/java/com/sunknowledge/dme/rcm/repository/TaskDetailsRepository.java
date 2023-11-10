package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaskDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaskDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskDetailsRepository extends JpaRepository<TaskDetails, Long> {}
