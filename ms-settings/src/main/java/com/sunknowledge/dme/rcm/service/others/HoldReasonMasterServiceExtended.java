package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.HoldReasonMasterService;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.HoldReasonMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.UUID;

public interface HoldReasonMasterServiceExtended extends HoldReasonMasterService {

    ResponseDTO saveHoldReasonMaster(HoldReasonMasterExtendedDTO holdReasonMasterExtendedDTO) throws InvalidAttributeValueException;

    List<HoldReasonMasterDTO> getAllHoldReasonDetails();

    HoldReasonMasterDTO getHoldReasonDetailsByUUID(UUID holdReasonMasterUuid);

    ResponseDTO setHoldReasonDetailsStatusByUuid(UUID uuid, String status);
}
