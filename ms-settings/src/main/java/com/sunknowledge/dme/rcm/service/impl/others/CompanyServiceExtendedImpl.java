package com.sunknowledge.dme.rcm.service.impl.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.domain.Company;
import com.sunknowledge.dme.rcm.repository.others.CompanyRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.CompanyDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.CompanyMapper;
import com.sunknowledge.dme.rcm.service.others.CompanyServiceExtended;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class CompanyServiceExtendedImpl implements CompanyServiceExtended {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceExtendedImpl.class);
    @Autowired
    CompanyRepositoryExtended companyRepositoryExtended;
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        return null;
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) {
        return null;
    }

    @Override
    public Optional<CompanyDTO> partialUpdate(CompanyDTO companyDTO) {
        return Optional.empty();
    }

    @Override
    public Page<CompanyDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CompanyDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<CompanyExtendedDTO> getAllCompanyData() {
        List<Company> list = companyRepositoryExtended.findByStatusIgnoreCase("active");
        List<CompanyExtendedDTO> companyExtendedDTOS = new ArrayList<>();
        for(Company company:list) {
            CompanyExtendedDTO companyExtendedDTO = new CompanyExtendedDTO();
            BeanUtils.copyProperties(company,companyExtendedDTO);
            companyExtendedDTO.setId(company.getCompanyId());
            companyExtendedDTO.setTitle(company.getCompanyName());
            companyExtendedDTOS.add(companyExtendedDTO);
        }

        /*ObjectMapper mapper = new ObjectMapper();
        try {

            JSONObject loginJson = new JSONObject(mapper.writeValueAsString(list));
            loginJson.
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/


        return companyExtendedDTOS;
    }

    @Override
    public ResponseDTO saveCompany(CompanyParameterDTO companyParameterDTO) {
        Set uniqueCompanyNameSet = companyRepositoryExtended.findAll().stream().map(x -> x.getCompanyName()).collect(Collectors.toSet());
        String message = "";
        if (companyParameterDTO.getCompanyName() == null) {
            message = ("Invalid Attribute (Company_Name)");
        } else if (companyParameterDTO.getCompanyName().trim() == "") {
            message = ("Company_Name must be provided");
        } else if((companyParameterDTO.getCompanyId() == null || companyParameterDTO.getCompanyId() == 0) && uniqueCompanyNameSet.contains(companyParameterDTO.getCompanyName())){
            message = ("("+ companyParameterDTO.getCompanyName() +") "+"Company_Name already exist");
        }

        if(!message.equalsIgnoreCase("")){
            return new ResponseDTO(false, message, null,200);
        }
        CompanyDTO companyDTO = (companyParameterDTO.getCompanyId() == null ||
            companyParameterDTO.getCompanyId() == 0) ? new CompanyDTO() :
            (companyRepositoryExtended.findById(companyParameterDTO.getCompanyId()).isPresent() ?
                companyMapper.toDto(companyRepositoryExtended.findById(companyParameterDTO.getCompanyId()).get()) :
                new CompanyDTO());

        BeanUtils.copyProperties(companyParameterDTO, companyDTO);
        companyDTO.setStatus("active");
        if (companyDTO.getCompanyId() == null || companyDTO.getCompanyId() == 0) {
            companyDTO.setCompanyId(null);
            companyDTO.setCreatedDate(LocalDate.now());
            companyDTO.setCreatedById(1L);
            companyDTO.setCreatedByName("Abhijit");
            companyDTO.setCompanyUuid(UUID.randomUUID());
        } else {
            companyDTO.setUpdatedDate(LocalDate.now());
            companyDTO.setUpdatedById(1L);
            companyDTO.setUpdatedByName("Abhijit");
        }
        CompanyDTO savedCompanyDTO = companyMapper.toDto(
            companyRepositoryExtended.save(companyMapper.toEntity(companyDTO))
        );
        return new ResponseDTO(true, "Successfully Saved.", savedCompanyDTO,200);
    }

    @Override
    public CompanyDTO getCompanyDataById(Long companyId) {
        Company company = companyRepositoryExtended.findByCompanyIdAndStatusIgnoreCase(companyId,"active");
        if(company!=null) {
            return companyMapper.toDto(company);
        }else{
            return null;
        }
    }

    @Override
    public ResponseDTO setCompanyStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<Company> obj = companyRepositoryExtended.findByCompanyUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    companyRepositoryExtended.save(obj.get());
                    return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", obj.get(),200));
                }else{
                    return (new ResponseDTO(Boolean.FALSE, "Data Not Found",null,200));
                }
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",null,200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", null,200));
        }
    }
}
