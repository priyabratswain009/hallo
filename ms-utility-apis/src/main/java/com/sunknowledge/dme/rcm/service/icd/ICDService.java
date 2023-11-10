package com.sunknowledge.dme.rcm.service.icd;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.icd.ICDResultOutcome;
import com.sunknowledge.dme.rcm.dto.icd.RequestICDInput;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface ICDService {
    String getToken() throws Exception;
    ServiceOutcome<ICDResultOutcome> getICD10Information(RequestICDInput requestICDInput, String accessToken);
    ServiceOutcome<IcdMasterDTO> getICDInformation(String requestICDInput, String icdType);
    IcdMasterDTO getICDDetailsOnCode(String icdCode);
    IcdMasterDTO saveICDInformation(IcdMasterDTO icdMasterDTO);
    ServiceOutcome<List<IcdMasterDTO>> getIcdMasterInfoByIcdCodeOrIcdDescription(String icdCode, String icdDescription, String active) throws InvalidAttributeValueException;
    ServiceOutcome getIcdMasterInfoByIcdTypeAndIcdCodeOrIcdDescription(String icdType, String icdCodeOrDescription, String active);
    ServiceOutcome  getAllIcdCodeType(String status);
    ServiceOutcome getICDMasterById(Long icdMasterId);
    ServiceOutcome getICDMasterByIcdCode(String icdCode);
}
