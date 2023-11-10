package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.ClaimProgramMasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ClaimProgramMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.UUID;

public interface ClaimProgramMasterServiceExtended extends ClaimProgramMasterService {

    ResponseDTO saveClaimProgramMaster(ClaimProgramMasterExtendedDTO claimProgramMasterExtendedDTO) throws InvalidAttributeValueException;

    List<ClaimProgramMasterDTO> getAllClaimProgramMasterInfo();

    List<ClaimProgramMasterDTO> getClaimProgramMasterInfoByUUID(UUID claimProgramMasterUuid);
}
