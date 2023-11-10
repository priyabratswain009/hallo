package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimsCOB835Details entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsCOB835DetailsRepository extends JpaRepository<ClaimsCOB835Details, Long> {}
