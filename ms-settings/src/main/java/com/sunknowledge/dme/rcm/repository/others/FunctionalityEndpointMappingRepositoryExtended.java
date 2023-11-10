package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping;
import com.sunknowledge.dme.rcm.repository.FunctionalityEndpointMappingRepository;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FunctionalityEndpointMappingRepositoryExtended extends FunctionalityEndpointMappingRepository {
    List<FunctionalityEndpointMapping> findByFunctionalityIdAndStatusIgnoreCase(Long aLong, String active);

    FunctionalityEndpointMapping findByEndpointIdAndFunctionalityIdAndStatusIgnoreCase(Long endpointId, Long functionalityId, String active);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, \n" +
        " Cast(eMas.endpoint_master_uuid as varchar) endpoint_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " eMas.endpoint_name, eMas.endpoint_group, eMas.endpoint_desc, eMas.endpoint_url  \n" +
        " from t_functionality_endpoint_mapping fEMap, t_functionality_master fMas, t_endpoint_master eMas  \n" +
        " where LOWER(fEMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(eMas.status) = 'active' and  \n" +
        " fMas.functionality_id = :functionalityId and  \n" +
        " fMas.functionality_id = fEMap.functionality_id and fEMap.endpoint_id = eMas.endpoint_id",nativeQuery = true)
    List<Map> getEndpointDetailsByFunctionalityId(@Param("functionalityId") Long functionalityId);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, \n" +
        " Cast(eMas.endpoint_master_uuid as varchar) endpoint_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " eMas.endpoint_name, eMas.endpoint_group, eMas.endpoint_desc, eMas.endpoint_url  \n" +
        " from t_functionality_endpoint_mapping fEMap, t_functionality_master fMas, t_endpoint_master eMas  \n" +
        " where LOWER(fEMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(eMas.status) = 'active' and  \n" +
        " LOWER(fMas.functionality_name) LIKE LOWER(:functionalityName) and  \n" +
        " fMas.functionality_id = fEMap.functionality_id and fEMap.endpoint_id = eMas.endpoint_id",nativeQuery = true)
    List<Map> getEndpointDetailsByFunctionalityName(@Param("functionalityName") String functionalityName);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, \n" +
        " Cast(eMas.endpoint_master_uuid as varchar) endpoint_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " eMas.endpoint_name, eMas.endpoint_group, eMas.endpoint_desc, eMas.endpoint_url  \n" +
        " from t_functionality_endpoint_mapping fEMap, t_functionality_master fMas, t_endpoint_master eMas  \n" +
        " where LOWER(fEMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(eMas.status) = 'active' and  \n" +
        " fMas.functionality_no = :functionalityNo and  \n" +
        " fMas.functionality_id = fEMap.functionality_id and fEMap.endpoint_id = eMas.endpoint_id",nativeQuery = true)
    List<Map> getEndpointDetailsByFunctionalityNo(@Param("functionalityNo") String functionalityNo);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, \n" +
        " Cast(eMas.endpoint_master_uuid as varchar) endpoint_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " eMas.endpoint_name, eMas.endpoint_group, eMas.endpoint_desc, eMas.endpoint_url  \n" +
        " from t_functionality_endpoint_mapping fEMap, t_functionality_master fMas, t_endpoint_master eMas  \n" +
        " where LOWER(fEMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(eMas.status) = 'active' and  \n" +
        " LOWER(eMas.endpoint_name) LIKE LOWER(:endpointName) and  \n" +
        " fMas.functionality_id = fEMap.functionality_id and fEMap.endpoint_id = eMas.endpoint_id",nativeQuery = true)
    List<Map> getFunctionalityDetailsByEndpointName(@Param("endpointName") String endpointName);

    @Query(value = "select Cast(fMas.functionality_master_uuid as varchar) functionality_master_uuid, \n" +
        " Cast(eMas.endpoint_master_uuid as varchar) endpoint_master_uuid, fMas.functionality_no , fMas.functionality_name, fMas.functionality_description,\n" +
        " eMas.endpoint_name, eMas.endpoint_group, eMas.endpoint_desc, eMas.endpoint_url  \n" +
        " from t_functionality_endpoint_mapping fEMap, t_functionality_master fMas, t_endpoint_master eMas  \n" +
        " where LOWER(fEMap.status) = 'active' and LOWER(fMas.status) = 'active' and LOWER(eMas.status) = 'active' and  \n" +
        " eMas.endpoint_id = :endpointId and  \n" +
        " fMas.functionality_id = fEMap.functionality_id and fEMap.endpoint_id = eMas.endpoint_id",nativeQuery = true)
    List<Map> getFunctionalityDetailsByEndpointId(@Param("endpointId") Long endpointId);
}
