package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterRepository;

import java.util.List;
import java.util.UUID;

public interface InsuranceGroupMasterRepositoryExtended extends InsuranceGroupMasterRepository {
    List<InsuranceGroupMaster> findByStatusIgnoreCase(String active);

    InsuranceGroupMaster findByInsuranceGroupMasterUuid(UUID insuranceGroupMasterUuid);

    List<InsuranceGroupMaster> findByInsuranceGroupMasterUuidNot(UUID insuranceGroupMasterUuid);
}
