package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.PosMasterService;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.PosMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Map;

public interface PosMasterServiceExtended extends PosMasterService {
    List<PosMasterDTO> getAllPOSInfo();

    ResponseDTO savePOSInfo(PosMasterExtendedDTO posMasterExtendedDTO) throws InvalidAttributeValueException;

    List<Map<String, Object>> getPlaceOfServiceForDropdown();
}
