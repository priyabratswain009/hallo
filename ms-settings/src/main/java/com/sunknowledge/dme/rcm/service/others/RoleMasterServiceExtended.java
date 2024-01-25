package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.service.RoleMasterService;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RoleMasterServiceExtended extends RoleMasterService {
    ResponseDTO saveRoleMaster(RoleMasterParameterDTO roleMasterParameterDTO);

    ResponseDTO getRoleMasterByNameOrNoOrUUID(String data, String operationType);

    Long getIDByUUID(UUID d);

    RoleMasterDTO getActiveDataById(Long id);

    List<Long> getActiveIDByUUID(List<UUID> roleUUID);

    ResponseDTO setRoleMasterStatusByUuid(UUID uuid, String status);

    List<Map<String, Object>> getAllRoleMasterDataForDropdown();

    List<RoleMasterDTO> getAllRoleMasterData();

    ResponseDTO setInactiveRoleRelatedInfo(long id, String status);
}
