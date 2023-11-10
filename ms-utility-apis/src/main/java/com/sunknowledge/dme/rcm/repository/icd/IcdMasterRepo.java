package com.sunknowledge.dme.rcm.repository.icd;

import com.sunknowledge.dme.rcm.domain.IcdMaster;
import com.sunknowledge.dme.rcm.repository.IcdMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IcdMasterRepo extends IcdMasterRepository {
    @Query("FROM IcdMaster WHERE icdCode = :icdCode")
    IcdMaster getICDDetailsOnCode(@Param("icdCode") String icdCode);

    @Query("FROM IcdMaster WHERE (LOWER(icd_code) LIKE LOWER(:icdCode) OR LOWER(icd_code_desc) LIKE LOWER(:icdDescription)) AND LOWER(status) = LOWER(:status)")
    List<IcdMaster> findByStatusIgnoreCaseAndIcdCodeLikeIgnoreCaseOrIcdCodeDescLikeIgnoreCase(@Param("status") String status, @Param("icdCode") String icdCode, @Param("icdDescription") String icdDescription);

    @Query(value = "select * from common.t_icd_master where icd_code_type =:icdType \n" +
        "and (icd_code ilike :icdCodeOrDescription or icd_code_desc ilike :icdCodeOrDescription) \n" +
        "and lower(status) = :status" ,nativeQuery = true)
    List<IcdMaster> findByIcdSearchParameters(@Param("icdType") String icdType, @Param("icdCodeOrDescription") String icdCodeOrDescription, @Param("status") String status);

    @Query("SELECT DISTINCT icdCodeType  FROM IcdMaster WHERE LOWER(status) = LOWER(:status)")
    List<String> findByStatusIgnoreCase(@Param("status") String status);

    IcdMaster findByIcdMasterId(Long icdMasterId);

    IcdMaster findByIcdCode(String icdCode);
}
