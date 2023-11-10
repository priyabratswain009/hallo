package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.TaxonomyDetailsService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxonomyDetailsParameterDTO;

import java.util.UUID;

public interface TaxonomyDetailsServiceExtended extends TaxonomyDetailsService {
    ResponseDTO saveUpdateTaxonomyDetails(TaxonomyDetailsParameterDTO taxonomyDetailsParameterDTO);

    Long getIDByUUID(UUID taxonomDetailsUUID);

    ResponseDTO getTaxonomyDetailsByNameOrCodeOrUUID(String data, String operationType);
}
