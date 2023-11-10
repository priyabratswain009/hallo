package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.Company;
import com.sunknowledge.dme.rcm.repository.others.CompanyRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.CompanyDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.CompanyMapper;
import com.sunknowledge.dme.rcm.service.others.CompanyServiceExtended;
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
    public List<CompanyDTO> getAllCompanyData() {
        List<Company> list = companyRepositoryExtended.findAll();
        return companyMapper.toDto(list);
    }

    @Override
    public ResponseDTO saveCompany(CompanyParameterDTO companyParameterDTO) {
        Set uniqueCompanyNameSet = companyRepositoryExtended.findAll().stream().map(x -> x.getCompanyName()).collect(Collectors.toSet());
        try {
            if (companyParameterDTO.getCompanyName() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Company_Name)");
            } else if (companyParameterDTO.getCompanyName().trim() == "") {
                throw new InputMismatchException("Company_Name must be provided");
            } else if(companyParameterDTO.getCompanyId() == 0 && uniqueCompanyNameSet.contains(companyParameterDTO.getCompanyName())){
                throw new InputMismatchException("("+ companyParameterDTO.getCompanyName() +") "+"Company_Name already exist");
            }

            CompanyDTO companyDTO = (companyParameterDTO.getCompanyId() == null ||
                companyParameterDTO.getCompanyId() == 0) ? new CompanyDTO() :
                (companyRepositoryExtended.findById(companyParameterDTO.getCompanyId()).isPresent() ?
                    companyMapper.toDto(companyRepositoryExtended.findById(companyParameterDTO.getCompanyId()).get()) :
                    new CompanyDTO());

            BeanUtils.copyProperties(companyParameterDTO, companyDTO);
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
            return new ResponseDTO(true, "Successfully Saved.", List.of(savedCompanyDTO));
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }
}
