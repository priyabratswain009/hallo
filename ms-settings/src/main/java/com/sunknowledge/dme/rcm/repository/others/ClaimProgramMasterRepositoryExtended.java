package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMaster;
import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClaimProgramMasterRepositoryExtended extends ClaimProgramMasterRepository {
    List<ClaimProgramMaster> findByStatusIgnoreCase(String active);

    ClaimProgramMaster findByClaimProgramMasterUuid(UUID claimProgramMasterUuid);

    List<ClaimProgramMaster> findByClaimProgramMasterUuidNot(UUID claimProgramMasterUuid);

    Optional<ClaimProgramMaster> findByClaimProgramMasterIdAndStatusIgnoreCase(long id, String active);
}
