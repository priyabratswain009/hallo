package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.repository.HoldReasonMasterRepository;

import java.util.List;
import java.util.UUID;

public interface HoldReasonMasterRepositoryExtended extends HoldReasonMasterRepository {
    List<HoldReasonMaster> findByStatusIgnoreCase(String active);

    HoldReasonMaster findByHoldReasonMasterUuid(UUID holdReasonMasterUuid);

    List<HoldReasonMaster> findByHoldReasonMasterUuidNot(UUID holdReasonMasterUuid);
}
