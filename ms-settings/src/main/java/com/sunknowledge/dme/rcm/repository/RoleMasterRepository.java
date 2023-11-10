package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.RoleMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoleMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Long> {}
