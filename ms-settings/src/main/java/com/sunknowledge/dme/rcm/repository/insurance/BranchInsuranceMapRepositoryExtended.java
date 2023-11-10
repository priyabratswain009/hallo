package com.sunknowledge.dme.rcm.repository.insurance;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMap;
import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapRepository;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.BranchInsuranceMapExtendedForUpdateDTO;

import java.util.List;

public interface BranchInsuranceMapRepositoryExtended extends BranchInsuranceMapRepository {
    List<BranchInsuranceMap> findByInsuranceIdAndStatusIgnoreCase(String insuranceId, String active);

    List<BranchInsuranceMap> findByBranchIdAndStatusIgnoreCase(Long branchId, String active);

    List<BranchInsuranceMap> findByInsuranceIdNoAndStatusIgnoreCase(String insuranceIdNo, String active);

    List<BranchInsuranceMap> findByStatusIgnoreCase(String active);

    BranchInsuranceMap findByInsuranceIdAndBranchIdAndStatusIgnoreCase(Long insuranceId, Long branchId, String active);

    BranchInsuranceMap findByBranchInsuranceMapId(Long branchInsuranceMapId);
}
