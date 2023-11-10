package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UspsAddressVerificationResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UspsAddressVerificationResponseRepository extends JpaRepository<UspsAddressVerificationResponse, Long> {}
