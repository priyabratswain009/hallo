package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaskComments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaskComments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskCommentsRepository extends JpaRepository<TaskComments, Long> {}
