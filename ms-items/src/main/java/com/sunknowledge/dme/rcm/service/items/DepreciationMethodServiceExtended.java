package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.DepreciationMethodService;
import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.DepreciationMethodExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.UUID;

public interface DepreciationMethodServiceExtended extends DepreciationMethodService {
    ResponseDTO saveDepreciationMethod(DepreciationMethodExtendedDTO depreciationMethodExtendedDTO) throws InvalidAttributeValueException;

    List<DepreciationMethodDTO> getAllDepreciationMethodInfo();

    List<DepreciationMethodDTO> getDepreciationMethodByUUID(UUID depreciationMethodUuid);
}
