package com.sunknowledge.dme.rcm.service.branch;

import com.sunknowledge.dme.rcm.service.BranchGroupService;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BranchGroupServiceExtended extends BranchGroupService {
    ResponseDTO bulkUploadForBranchGroup(MultipartFile documentFile);

    ResponseDTO saveBranchGroup(BranchGroupParameterDTO branchGroupParameterDTO);

    BranchGroupDTO getBranchGroupById(Long branchGroupId);

    List<BranchGroupDTO> getBranchGroupByBranchGroupName(String branchGroupName);

    List<BranchGroupDTO> getBranchGroupByCompanyId(Long companyId);

    List<BranchGroupDTO> getAllBranchGroupData();

    List<BranchGroupDTO> getBranchGroupByStatus(String status);

    List<BranchGroupDTO> getActiveBranchGroupById(Long branchGroupId);


    ResponseDTO setBranchGroupStatusById(Long id,String status);
}
