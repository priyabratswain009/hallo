package com.sunknowledge.dme.rcm.repository.insurance;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceMasterRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceMasterRepositoryExtended extends InsuranceMasterRepository {
    InsuranceMaster findByInsuranceIdAndStatusIgnoreCase(Long insuranceId, String active);

    List<InsuranceMaster> findByInsuranceNameAndStatusIgnoreCase(String insuranceName, String active);

    List<InsuranceMaster> findByInsuranceIdNoAndStatusIgnoreCase(String insuranceIdNo, String active);

    List<InsuranceMaster> findByStatusIgnoreCase(String active);

    InsuranceMaster findByInsuranceId(Long insuranceId);

    List<InsuranceMaster> findByInsuranceNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select settings.f_get_insurance_id_no()", nativeQuery = true)
    String getInsuranceIdNumber();

//    @Query(value = "select * from settings.t_insurance_master where insurance_id in(:listIns)", nativeQuery = true)
    List<InsuranceMaster> findByInsuranceIdIn(List<Long> listIns);

    List<InsuranceMaster> findByCmsCrossoverInsuranceIdNoAndStatusIgnoreCase(String cmsCrossoverInsuranceIdNo, String active);
}
