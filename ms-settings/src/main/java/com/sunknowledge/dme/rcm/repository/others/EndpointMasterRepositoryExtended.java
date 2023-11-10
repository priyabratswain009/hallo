package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import com.sunknowledge.dme.rcm.repository.EndpointMasterRepository;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface EndpointMasterRepositoryExtended extends EndpointMasterRepository {
    EndpointMaster findByEndpointIdAndStatusIgnoreCase(Long idByUUID, String active);

    List<EndpointMaster> findByEndpointUrlAndStatusIgnoreCase(String endpointUrl, String active);

    List<EndpointMaster> findByEndpointNameLikeIgnoreCaseAndStatusIgnoreCase(String data,String status);

    List<EndpointMaster> findByEndpointGroupLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select settings.get_t_endpoint_master_id_by_uuid(:endpointMasterUuid)",nativeQuery = true)
    Long getIDByUUID(@Param("endpointMasterUuid") UUID endpointMasterUuid);

    List<EndpointMaster> findByEndpointMasterUuidInAndStatusIgnoreCase(List<UUID> endpointUUIDs, String active);
}
