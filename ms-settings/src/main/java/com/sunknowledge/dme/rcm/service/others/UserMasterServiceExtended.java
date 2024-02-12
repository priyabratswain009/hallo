package com.sunknowledge.dme.rcm.service.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.service.UserMasterService;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CreateUserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UpdateUserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UpdateUserPasswordDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserMasterServiceExtended extends UserMasterService {
    ResponseDTO saveUserMaster(CreateUserMasterExtendedDTO userMasterParameterDTO) throws JsonProcessingException, ParseException;
    ResponseDTO updateUserMaster(UpdateUserMasterExtendedDTO userMasterParameterDTO);

    List<UserMasterDTO> findByUserIdIn(List<Long> userIdList);

    UserMasterDTO getDataByActiveId(Long userId);

    Long getIDByUUID(UUID roleUUID);

    ResponseDTO getAllUserMasterData();

    ResponseDTO createPrivatePublicKey();

    String encryptPassword(String plainString);

    String decryptPassword(String encryptedString);

    ResponseDTO updateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO) throws IOException;

    ResponseDTO deleteUserMasterStatusByUuid(UUID uuid, String status);

    ResponseDTO activeOrDeactiveUserByUuid(UUID uuid, Boolean isDeactive);

    ResponseDTO validateLogin(String userName, String password) throws IOException, ParseException;
    ResponseDTO getUserInfoFromKeycloak(String userName);
}
