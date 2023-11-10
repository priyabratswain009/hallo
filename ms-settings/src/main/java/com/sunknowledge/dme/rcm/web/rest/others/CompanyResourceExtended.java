package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.CompanyDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationParameterDTO;
import com.sunknowledge.dme.rcm.service.others.CompanyServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class CompanyResourceExtended {
    private final Logger log = LoggerFactory.getLogger(CompanyResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    CompanyServiceExtended companyServiceExtended;

    @PatchMapping(value = "saveCompany", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveCompany(@RequestBody CompanyParameterDTO companyParameterDTO){
        return companyServiceExtended.saveCompany(companyParameterDTO);
    }

    @GetMapping("/getAllCompanyData")
    public ResponseDTO getAllCompanyData(){
        List<CompanyDTO> list = companyServiceExtended.getAllCompanyData();
        return (new ResponseDTO(list.size()>0?true:false, list.size()>0? "Successfully Data Fetched.": "Data Not Found.", list));
    }

}
