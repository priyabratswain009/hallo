package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.BranchUserMapService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapUpdateDTO;

public interface BranchUserMapServiceExtended extends BranchUserMapService {
    ResponseDTO saveBranchUserMap(BranchUserMapParameterDTO branchUserMapParameterDTO);

    ResponseDTO updateBranchUserMap(BranchUserMapUpdateDTO branchUserMapUpdateDTO);

    ResponseDTO getBranchByUser(Long userId);
}
