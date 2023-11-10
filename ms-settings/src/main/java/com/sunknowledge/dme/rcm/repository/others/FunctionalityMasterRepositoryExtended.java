package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.repository.FunctionalityMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FunctionalityMasterRepositoryExtended extends FunctionalityMasterRepository {

    @Query(value = "select settings.f_get_functionality_no()" ,nativeQuery = true)
    String getFunctionalityNumber();

    FunctionalityMaster findByFunctionalityNo(String data);

    List<FunctionalityMaster> findByFunctionalityNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select settings.get_t_functionality_master_id_by_uuid(:functionalityMasterUUID)" ,nativeQuery = true)
    Long getIDByUUID(@Param("functionalityMasterUUID") UUID functionalityMasterUUID);

    @Query(value = "select functionality_id from settings.mv_functionality_master_uuid where functionality_master_uuid in :functionalityUUIDs",nativeQuery = true)
    List<Long> getIDsByUUIDs(@Param("functionalityUUIDs") List<UUID> functionalityUUIDs);

    List<FunctionalityMaster> findByFunctionalityMasterUuidInAndStatusIgnoreCase(List<UUID> functionalityUUIDs,String status);

    Optional<FunctionalityMaster> findByFunctionalityIdAndStatusIgnoreCase(Long idByUUID, String active);
}
