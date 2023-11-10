package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.ClaimFormMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ClaimFormMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.UUID;

public interface ClaimFormMasterServiceExtended extends ClaimFormMasterService {
    ResponseDTO saveClaimFormMaster(ClaimFormMasterExtendedDTO claimFormMasterExtendedDTO) throws InvalidAttributeValueException;

    List<ClaimFormMasterDTO> getAllClaimFormMasterInfo();

    List<ClaimFormMasterDTO> getClaimFormMasterInfoByUUID(UUID claimFormMasterUuid);
}
