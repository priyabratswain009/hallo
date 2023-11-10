package com.sunknowledge.dme.rcm.repository.common;

import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.repository.UszipMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.common.DropdownDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UszipMasterRepositoryExtended extends UszipMasterRepository {
    UszipMaster findByZipCodeAndStatusIgnoreCase(Long zipCode, String active);

    @Query(value = "SELECT * FROM common.t_uszip_master WHERE CAST(zip_code AS text) LIKE ?1 AND LOWER(status) = LOWER(?2)", nativeQuery = true)
    List<UszipMaster> findByZipCodeAndStatusActive(String zipCode, String status);

    @Query(value = "select distinct state_code as id,state_name as title from common.t_uszip_master where lower(status)='active'", nativeQuery = true)
    List<Map> getDistinctStateCode();
}
