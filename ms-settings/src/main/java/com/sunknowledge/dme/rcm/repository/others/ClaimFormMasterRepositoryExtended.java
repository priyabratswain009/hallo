package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.ClaimFormMaster;
import com.sunknowledge.dme.rcm.repository.ClaimFormMasterRepository;

import java.util.List;
import java.util.UUID;

public interface ClaimFormMasterRepositoryExtended extends ClaimFormMasterRepository {
    List<ClaimFormMaster> findByStatusIgnoreCase(String active);

    ClaimFormMaster findByClaimFormMasterUuid(UUID claimFormMasterUuid);

    List<ClaimFormMaster> findByClaimFormMasterUuidNot(UUID claimFormMasterUuid);
}
