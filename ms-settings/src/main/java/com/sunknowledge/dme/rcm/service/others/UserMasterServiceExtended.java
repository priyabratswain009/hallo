package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.UserMasterService;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UserMasterParameterDTO;

import java.util.List;
import java.util.UUID;

public interface UserMasterServiceExtended extends UserMasterService {
    ResponseDTO saveUserMaster(UserMasterParameterDTO userMasterParameterDTO);

    List<UserMasterDTO> findByUserIdIn(List<Long> userIdList);

    UserMasterDTO getDataByActiveId(Long userId);

    Long getIDByUUID(UUID roleUUID);
}
