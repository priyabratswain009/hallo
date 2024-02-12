package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.BranchUserMapService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapUpdateDTO;

import java.util.UUID;

public interface BranchUserMapServiceExtended extends BranchUserMapService {
    ResponseDTO saveBranchUserMap(BranchUserMapParameterDTO branchUserMapParameterDTO);

    ResponseDTO updateBranchUserMap(BranchUserMapUpdateDTO branchUserMapUpdateDTO);

    ResponseDTO getBranchByUser(Long userId);
    ResponseDTO setBranchUserMapStatusByUuid(UUID uuid, String status);

    ResponseDTO getAllBranchUserMapData();

    ResponseDTO getBranchUserMapById(Long id);

    ResponseDTO getUserByBranch(Long branchId);
}
