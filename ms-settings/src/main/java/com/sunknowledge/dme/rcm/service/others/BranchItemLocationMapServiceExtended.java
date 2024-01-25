package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.BranchItemLocationMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface BranchItemLocationMapServiceExtended extends BranchItemLocationMapService {
    ResponseDTO saveBranchItemLocationMap(BranchItemLocationMapExtendedDTO branchItemLocationMapExtendedDTO);

    ResponseDTO updateBranchItemLocationMap(BranchItemLocationMapExtendedUpdateDTO branchItemLocationMapExtendedUpdateDTO);

    List<BranchItemLocationMapDTO> getBranchItemLocationMapByItemLocationId(Long itemLocationId);

    List<BranchItemLocationMapDTO> getBranchItemLocationMapByBranchId(Long branchId);

    List<BranchItemLocationMapDTO> getAllBranchItemLocationMapData();

    List<BranchItemLocationMapDTO> getBranchItemLocationMapByStatus(String status);

    ResponseDTO deactiveBranchItemLocationMapByItemLocationIdAndBranchId(Long itemLocationId, Long branchId);

    ResponseDTO setBranchItemLocationMapByUuid(UUID uuid, String status);

    BranchItemLocationMapDTO getBranchItemLocationMapByItemLocationIdAndBranchId(Long itemLocationId, Long branchId);
}
