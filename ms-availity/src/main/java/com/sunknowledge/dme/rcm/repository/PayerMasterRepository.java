package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PayerMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayerMasterRepository extends JpaRepository<PayerMaster, Long> {}
