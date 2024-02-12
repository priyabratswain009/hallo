package com.sunknowledge.dme.rcm.service.others;
import com.sunknowledge.dme.rcm.service.StopReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.StopReasonMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.UUID;

public interface StopReasonMasterServiceExtended extends StopReasonMasterService {
    ResponseDTO saveStopReasonMaster(StopReasonMasterExtendedDTO stopReasonMasterExtendedDTO) throws InvalidAttributeValueException;

    List<StopReasonMasterDTO> getAllStopReasonDetails();

    StopReasonMasterDTO getStopReasonDetailsByUUID(UUID stopReasonMasterUuid);

    ResponseDTO setStopReasonDetailsStatusByUuid(UUID uuid, String status);
}
