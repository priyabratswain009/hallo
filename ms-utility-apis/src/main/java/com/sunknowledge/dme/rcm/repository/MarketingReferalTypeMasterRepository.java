package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MarketingReferalTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarketingReferalTypeMasterRepository extends JpaRepository<MarketingReferalTypeMaster, Long> {}
