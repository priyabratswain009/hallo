package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ObjectTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjectTypeMasterRepository extends JpaRepository<ObjectTypeMaster, Long> {}
