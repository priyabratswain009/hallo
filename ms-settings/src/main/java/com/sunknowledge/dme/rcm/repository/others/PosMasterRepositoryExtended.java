package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.domain.PosMaster;
import com.sunknowledge.dme.rcm.repository.PosMasterRepository;

import java.util.List;
import java.util.UUID;

public interface PosMasterRepositoryExtended extends PosMasterRepository {
    List<PosMaster> findByStatusIgnoreCase(String active);

    PosMaster findByPosMasterUuid(UUID posMasterUuid);

    List<PosMaster> findByPosMasterUuidNot(UUID posMasterUuid);
}
