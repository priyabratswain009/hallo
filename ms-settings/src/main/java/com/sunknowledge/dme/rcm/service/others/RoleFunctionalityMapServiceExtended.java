package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.RoleFunctionalityMapService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapUpdateParameterDTO;

import java.util.UUID;

public interface RoleFunctionalityMapServiceExtended extends RoleFunctionalityMapService {
    ResponseDTO saveRoleFunctionalityMap(RoleFunctionalityMapParameterDTO roleFunctionalityMapParameterDTO);

    ResponseDTO updateStatusRoleFunctionalityMap(RoleFunctionalityMapUpdateParameterDTO roleUserMapParameterDTO);

    ResponseDTO getRoleFunctionalityMapByNameOrNoOrUUID(String data, String operationType);

    ResponseDTO setRoleFunctionalityMapStatusByUuid(UUID uuid, String status);
}
