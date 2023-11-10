package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.DepreciationMethod;
import com.sunknowledge.dme.rcm.repository.DepreciationMethodRepository;

import java.util.List;
import java.util.UUID;

public interface DepreciationMethodRepositoryExtended extends DepreciationMethodRepository {
    List<DepreciationMethod> findByStatusIgnoreCase(String active);
    DepreciationMethod findByDepreciationMethodUuid(UUID insuranceGroupMasterUuid);
    List<DepreciationMethod> findByDepreciationMethodUuidNot(UUID insuranceGroupMasterUuid);
}
