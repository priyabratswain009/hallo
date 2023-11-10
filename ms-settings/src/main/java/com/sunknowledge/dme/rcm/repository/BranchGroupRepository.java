package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchGroupRepository extends JpaRepository<BranchGroup, Long> {}
