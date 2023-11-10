package com.sunknowledge.dme.rcm.service.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.service.UszipMasterService;
import com.sunknowledge.dme.rcm.service.dto.common.DropdownDTO;

import java.util.List;
import java.util.Map;

public interface UszipMasterServiceExtended extends UszipMasterService {
    ServiceOutcome getUsZipDetails(Long zipCode);

    ServiceOutcome getUSZipDetailsByZipCode(Long zipCode);

    ServiceOutcome getUSZipDetailByIndividualZipCode(Long zipCode);

    List<Map> getStateDropdown();
}
