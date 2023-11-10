package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Transaction835MasterDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Transaction835MasterDetailsRepository extends JpaRepository<Transaction835MasterDetails, Long> {}
