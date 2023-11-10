package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.CompanyService;
import com.sunknowledge.dme.rcm.service.dto.CompanyDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyParameterDTO;

import java.util.List;

public interface CompanyServiceExtended extends CompanyService {
    List<CompanyDTO> getAllCompanyData();

    ResponseDTO saveCompany(CompanyParameterDTO companyParameterDTO);
}
