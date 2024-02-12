package com.sunknowledge.dme.rcm.service.insurance;

import com.sunknowledge.dme.rcm.service.BranchInsuranceMapService;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.BranchInsuranceMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.BranchInsuranceMapExtendedForUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface BranchInsuranceMapServiceExtended extends BranchInsuranceMapService {
    Object bulkUploadForBranchInsurance(MultipartFile documentFile);

    ResponseDTO saveBranchInsuranceMap(BranchInsuranceMapExtendedDTO branchInsuranceMapExtendedDTO);

    List<BranchInsuranceMapDTO> getBranchInsuranceMapByInsuranceId(Long insuranceId);

    List<BranchInsuranceMapDTO> getBranchInsuranceMapByBranchId(Long branchId);

    List<BranchInsuranceMapDTO> getBranchInsuranceMapByInsuranceIdNo(String insuranceIdNo);

    List<BranchInsuranceMapDTO> getAllBranchInsuranceMapData();

    List<BranchInsuranceMapDTO> getBranchInsuranceMapByStatus(String status);

    ResponseDTO updateBranchInsuranceMap(BranchInsuranceMapExtendedForUpdateDTO branchInsuranceMapExtendedForUpdateDTO);

    String deactiveBranchInsurancemapByInsuranceIdAndBranchId(Long insuranceId, Long branchId);

    BranchInsuranceMapDTO getBranchInsuranceMapByBranchInsuranceMapId(Long branchInsuranceMapId);

    ResponseDTO setBranchInsurancemapStatusByUuid(UUID uuid, String status);

    List<BranchInsuranceMapDTO> getBranchInsuranceMapByBranchIdAndInsuranceId(Long branchId, Long insuranceId);
}
