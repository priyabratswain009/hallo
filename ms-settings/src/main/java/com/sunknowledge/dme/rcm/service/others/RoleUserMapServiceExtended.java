package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.RoleUserMapService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleUserMapParameterDTO;

public interface RoleUserMapServiceExtended extends RoleUserMapService {
    ResponseDTO saveRoleUserMap(RoleUserMapParameterDTO roleUserMapParameterDTO);
    ResponseDTO updateStatusRoleUserMap(RoleUserMapParameterDTO roleUserMapParameterDTO, String status);

    ResponseDTO getRoleUserDetailsByRoleUUID(String data, String operationType);
}
