package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.repository.StopReasonMasterRepository;

import java.util.List;
import java.util.UUID;

public interface StopReasonMasterRepositoryExtended extends StopReasonMasterRepository {
    List<StopReasonMaster> findByStatusIgnoreCase(String active);

    StopReasonMaster findByStopReasonMasterUuid(UUID stopReasonMasterUuid);

    List<StopReasonMaster> findByStopReasonMasterUuidNot(UUID stopReasonMasterUuid);

    StopReasonMaster findByStopReasonMasterUuidAndStatusIgnoreCase(UUID stopReasonMasterUuid, String active);
}
