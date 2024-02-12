package com.sunknowledge.dme.rcm.service.branch;

import com.sunknowledge.dme.rcm.service.BranchOfficeService;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BranchOfficeServiceExtended extends BranchOfficeService {
    ResponseDTO bulkUploadForBranchOffice(MultipartFile documentFile);

    ResponseDTO saveBranchOffice(BranchOfficeParameterDTO branchOfficeParameterDTO);

    BranchOfficeDTO getBranchOfficeById(Long branchId);

    List<BranchOfficeDTO> getBranchOfficeByBranchName(String branchName);

    List<BranchOfficeDTO> getBranchOfficeByBranchNo(String branchNo);

    List<BranchOfficeExtendedDTO> getAllBranchOfficeData();

    List<BranchOfficeDTO> getBranchOfficeByNpi(String npi);

    List<BranchOfficeDTO> getBranchOfficeByStatus(String status);

    List<BranchOfficeDTO> findByBranchIdIn(List<Long> branchIdList);

    BranchOfficeDTO findByBranchId(Long branchId);

    BranchOfficeDTO getActiveBranchOfficeById(Long branchId);

    ResponseDTO setBranchOfficeStatusByUuid(UUID uuid, String status);

    List<Map> getBranchDetailsByUserUUIDorUserName(String param, String optVal);

    List<Map> getUserDetailsByBranchUUIDorBranchName(String parameterValue, String opType);

    List<Map> getLocationDetailsByBranchUUIDorBranchName(String trim, String opType);

    List<Map> getInsuranceDetailsByBranchUUIDorBranchName(String trim, String opType);

    List<Map> getCompanyDetailsByBranchUUIDorBranchName(String trim, String opType);

    ServiceOutcome<BranchOfficeDTO> getBranchOfficeForABNById(Long branchId);
}
