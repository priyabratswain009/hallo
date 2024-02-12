package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.UserMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UserMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {}
