package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.CompanyService;
import com.sunknowledge.dme.rcm.service.dto.CompanyDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyParameterDTO;

import java.util.List;
import java.util.UUID;

public interface CompanyServiceExtended extends CompanyService {
    List<CompanyExtendedDTO> getAllCompanyData();

    ResponseDTO saveCompany(CompanyParameterDTO companyParameterDTO);

    CompanyDTO getCompanyDataById(Long companyId);

    ResponseDTO setCompanyStatusByUuid(UUID uuid, String status);
}
