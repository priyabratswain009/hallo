package com.sunknowledge.dme.rcm.service.insurance;

import com.sunknowledge.dme.rcm.service.InsuranceMasterService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.insurance.InsuranceMasterParameterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuranceMasterServiceExtended extends InsuranceMasterService {
    ResponseDTO bulkUploadForInsuranceMaster(MultipartFile documentFile);

    ResponseDTO saveInsuranceMaster(InsuranceMasterParameterDTO insuranceMasterDTO);

    InsuranceMasterDTO getInsuranceMasterById(Long insuranceId);

    List<InsuranceMasterDTO> getInsuranceMasterByInsuranceName(String insuranceName);

    List<InsuranceMasterDTO> getInsuranceMasterByInsuranceIdNo(String insuranceIdNo);

    List<InsuranceMasterDTO> getAllInsuranceMasterData();

    Optional<InsuranceMasterDTO> findByInsuranceId(Long insuranceId);

    ResponseDTO setInsuranceMasterStatus(UUID uuid, String status);

    List<InsuranceMasterDTO> getInsuranceMasterByStatus(String status);

    List<InsuranceMasterDTO> getInsuranceMasterByInsuranceIdList(String insuranceIds);
    ServiceOutcome getInsuranceByInsuranceNameForDropdown();

    List<InsuranceMasterDTO> fetchInsuranceByCmsCrossoverInsuranceIdNo(String cmsCrossoverInsuranceIdNo);
}
