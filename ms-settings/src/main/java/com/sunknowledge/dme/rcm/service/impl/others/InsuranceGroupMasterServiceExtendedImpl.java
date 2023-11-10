package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster;
import com.sunknowledge.dme.rcm.repository.others.HoldReasonMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.others.InsuranceGroupMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.InsuranceGroupMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.HoldReasonMasterMapper;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceGroupMasterMapper;
import com.sunknowledge.dme.rcm.service.others.InsuranceGroupMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
public class InsuranceGroupMasterServiceExtendedImpl implements InsuranceGroupMasterServiceExtended {

    private final Logger log = LoggerFactory.getLogger(InsuranceGroupMasterServiceExtendedImpl.class);

    @Autowired
    InsuranceGroupMasterRepositoryExtended insuranceGroupMasterRepositoryExtended;

    @Autowired
    InsuranceGroupMasterMapper insuranceGroupMasterMapper;

    @Override
    public InsuranceGroupMasterDTO save(InsuranceGroupMasterDTO insuranceGroupMasterDTO) {
        return null;
    }

    @Override
    public InsuranceGroupMasterDTO update(InsuranceGroupMasterDTO insuranceGroupMasterDTO) {
        return null;
    }

    @Override
    public Optional<InsuranceGroupMasterDTO> partialUpdate(InsuranceGroupMasterDTO insuranceGroupMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<InsuranceGroupMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<InsuranceGroupMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveInsuranceGroupMaster(InsuranceGroupMasterExtendedDTO insuranceGroupMasterExtendedDTO) throws InvalidAttributeValueException {
        Set uniqueInsuranceGroupNameSet = insuranceGroupMasterRepositoryExtended.findAll().stream().map(x -> x.getInsuranceGroupMasterName()).collect(Collectors.toSet());

        if (insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName() == null) {
            throw new InvalidAttributeValueException("Invalid Attribute (insuranceGroupMasterName)");
        } else if (insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName().trim().equals("")) {
            throw new InputMismatchException("insuranceGroupMasterName must be provided");
        }else if(uniqueInsuranceGroupNameSet.contains(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName())){
            ////Duplicate check during save operation
            throw new InputMismatchException("("+ insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName() +") "+"insuranceGroupMasterName already exist");
        }else if(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterUuid() != null && !insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueInsuranceGroupForUpdate = insuranceGroupMasterRepositoryExtended.findByInsuranceGroupMasterUuidNot(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterUuid()).stream().map(x -> x.getInsuranceGroupMasterName()).collect(Collectors.toSet());
            if(uniqueInsuranceGroupForUpdate.contains(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName())){
                throw new InputMismatchException("("+ insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName() +") "+"holdReasonName already exist");
            }
        }

        ResponseDTO outcome = new ResponseDTO();
        if(insuranceGroupMasterExtendedDTO.getStatus().equalsIgnoreCase("active") || insuranceGroupMasterExtendedDTO.getStatus().equalsIgnoreCase("inActive")){
            if(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName() != null && !insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterName().equals("")) {

                InsuranceGroupMasterDTO insuranceGroupMasterDTO = (insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterUuid() == null) ? new InsuranceGroupMasterDTO() :
                    (insuranceGroupMasterRepositoryExtended.findByInsuranceGroupMasterUuid(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterUuid()) != null ?
                        insuranceGroupMasterMapper.toDto(insuranceGroupMasterRepositoryExtended.findByInsuranceGroupMasterUuid(insuranceGroupMasterExtendedDTO.getInsuranceGroupMasterUuid())) :
                        new InsuranceGroupMasterDTO());

                BeanUtils.copyProperties(insuranceGroupMasterExtendedDTO, insuranceGroupMasterDTO);

                if (insuranceGroupMasterDTO.getInsuranceGroupMasterUuid() == null) {
                    insuranceGroupMasterDTO.setInsuranceGroupMasterId(null);
                    insuranceGroupMasterDTO.setCreatedById(31L);
                    insuranceGroupMasterDTO.setCreatedDate(LocalDate.now());
                    insuranceGroupMasterDTO.setCreatedByName("Falguni");
                    insuranceGroupMasterDTO.setInsuranceGroupMasterUuid(UUID.randomUUID());
                } else {
                    insuranceGroupMasterDTO.setUpdatedDate(LocalDate.now());
                    insuranceGroupMasterDTO.setUpdatedById(31L);
                    insuranceGroupMasterDTO.setUpdatedByName("Falguni");
                }
                InsuranceGroupMasterDTO savedInsuranceGroupMasterDTO = insuranceGroupMasterMapper.toDto(
                    insuranceGroupMasterRepositoryExtended.save(insuranceGroupMasterMapper.toEntity(insuranceGroupMasterDTO))
                );

                return new ResponseDTO(true, "Successfully Saved.", List.of(savedInsuranceGroupMasterDTO));
            }else{
                outcome.setStatus(false);
                outcome.setMessage("Data Not Saved.");
                return outcome;
            }
        }else{
            throw new InputMismatchException("Status must be provided and should be active/inactive");
        }
    }

    @Override
    public List<InsuranceGroupMasterDTO> getAllInsuranceGroupMasterInfo() {
        List<InsuranceGroupMaster> data = insuranceGroupMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return insuranceGroupMasterMapper.toDto(data);
    }

    @Override
    public List<InsuranceGroupMasterDTO> getInsuranceGroupMasterByUUID(UUID insuranceGroupMasterUuid) {
        List<InsuranceGroupMasterDTO> dtoDataList = new ArrayList<InsuranceGroupMasterDTO>();
        InsuranceGroupMaster data = insuranceGroupMasterRepositoryExtended.findByInsuranceGroupMasterUuid(insuranceGroupMasterUuid);
        if(data != null){
            dtoDataList.add(insuranceGroupMasterMapper.toDto(data));
        }
        return dtoDataList;
    }
}
