package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaxonomyDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxonomyDetailsRepository extends JpaRepository<TaxonomyDetails, Long> {}
