package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.NoteReasonMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NoteReasonMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoteReasonMasterRepository extends JpaRepository<NoteReasonMaster, Long> {}
