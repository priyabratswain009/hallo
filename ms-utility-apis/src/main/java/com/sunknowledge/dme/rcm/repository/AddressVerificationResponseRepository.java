package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.AddressVerificationResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AddressVerificationResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressVerificationResponseRepository extends JpaRepository<AddressVerificationResponse, Long> {}
