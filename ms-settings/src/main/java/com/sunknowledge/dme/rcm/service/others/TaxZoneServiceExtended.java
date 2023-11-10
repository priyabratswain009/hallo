package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.TaxZoneService;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxZoneParameterDTO;

import java.util.List;

public interface TaxZoneServiceExtended extends TaxZoneService {
    ResponseDTO getTaxZoneWiseRateByParam(String parameterValue, String opType);

    ResponseDTO saveTaxZone(TaxZoneParameterDTO taxZoneParameterDTO);

    List<TaxZoneDTO> getAllTaxZoneInfo();

    TaxZoneDTO getTaxZoneInfoByID(Long taxZoneId);

    ResponseDTO getTaxZoneForDropdown();
}
