package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Long> {}
