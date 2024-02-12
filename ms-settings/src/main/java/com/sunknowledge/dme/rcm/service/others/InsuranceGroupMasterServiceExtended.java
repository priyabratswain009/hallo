package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.InsuranceGroupMasterService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.InsuranceGroupMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InsuranceGroupMasterServiceExtended extends InsuranceGroupMasterService {
    ResponseDTO saveInsuranceGroupMaster(InsuranceGroupMasterExtendedDTO insuranceGroupMasterExtendedDTO) throws InvalidAttributeValueException;

    List<InsuranceGroupMasterDTO> getAllInsuranceGroupMasterInfo();

    InsuranceGroupMasterDTO getInsuranceGroupMasterByUUID(UUID insuranceGroupMasterUuid);

    ResponseDTO setInsuranceGroupMasterStatusByUuid(UUID uuid, String status);

    List<Map<String, Object>> getInsuranceGroupMasterForDropdown();
}
