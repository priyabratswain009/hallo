package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.HcpcsDocType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HcpcsDocType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HcpcsDocTypeRepository extends JpaRepository<HcpcsDocType, Long> {}
