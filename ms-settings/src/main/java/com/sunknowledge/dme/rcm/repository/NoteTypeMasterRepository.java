package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.NoteTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NoteTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoteTypeMasterRepository extends JpaRepository<NoteTypeMaster, Long> {}
