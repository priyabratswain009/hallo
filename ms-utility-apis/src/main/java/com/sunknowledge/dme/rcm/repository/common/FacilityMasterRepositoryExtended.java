package com.sunknowledge.dme.rcm.repository.common;

import com.sunknowledge.dme.rcm.domain.FacilityMaster;
import com.sunknowledge.dme.rcm.repository.FacilityMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface FacilityMasterRepositoryExtended extends FacilityMasterRepository {
    List<FacilityMaster> findByFacilityNameLikeIgnoreCaseAndStatusIgnoreCase(String data, String active);

    @Query(value="select common.get_t_facility_master_id_by_uuid(:facilityMasterUUID)",nativeQuery = true)
    Long getIDByUUID(@Param("facilityMasterUUID") UUID facilityMasterUUID);

    FacilityMaster findByFacilityNo(String data);

    @Query(value = "select common.f_get_facility_no()" ,nativeQuery = true)
    String getFacilityNumber();

    FacilityMaster findByFacilityIdAndStatusIgnoreCase(Long idByUUID, String active);
}
