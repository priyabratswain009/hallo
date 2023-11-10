package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.domain.DepreciationMethod;
import com.sunknowledge.dme.rcm.repository.items.DepreciationMethodRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.DepreciationMethodExtendedDTO;
import com.sunknowledge.dme.rcm.service.items.DepreciationMethodServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.DepreciationMethodMapper;
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
@Service("DepreciationMethodServiceExtendedImpl")
public class DepreciationMethodServiceExtendedImpl implements DepreciationMethodServiceExtended {
    private final Logger log = LoggerFactory.getLogger(DepreciationMethodServiceExtendedImpl.class);
    @Autowired
    DepreciationMethodRepositoryExtended depreciationMethodRepositoryExtended;
    @Autowired
    DepreciationMethodMapper depreciationMethodMapper;

    @Override
    public ResponseDTO saveDepreciationMethod(DepreciationMethodExtendedDTO depreciationMethodExtendedDTO) throws InvalidAttributeValueException {
        Set uniqueDepreciationMethodNameSet = depreciationMethodRepositoryExtended.findAll().stream().map(x -> x.getDepreciationMethodName()).collect(Collectors.toSet());

        if (depreciationMethodExtendedDTO.getDepreciationMethodName() == null) {
            throw new InvalidAttributeValueException("Invalid Attribute (depreciationMethodName)");
        } else if (depreciationMethodExtendedDTO.getDepreciationMethodName().trim().equals("")) {
            throw new InputMismatchException("depreciationMethodName must be provided");
        }else if(depreciationMethodExtendedDTO.getDepreciationMethodUuid()==null &&uniqueDepreciationMethodNameSet.contains(depreciationMethodExtendedDTO.getDepreciationMethodName())){
            //Duplicate check during save operation
            throw new InputMismatchException("("+ depreciationMethodExtendedDTO.getDepreciationMethodName() +") "+"depreciationMethodName already exist");
        }else if(depreciationMethodExtendedDTO.getDepreciationMethodUuid() != null && !depreciationMethodExtendedDTO.getDepreciationMethodUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueDepreciationMethodNameSetForUpdate = depreciationMethodRepositoryExtended.findByDepreciationMethodUuidNot(depreciationMethodExtendedDTO.getDepreciationMethodUuid()).stream().map(x -> x.getDepreciationMethodName()).collect(Collectors.toSet());
            if(uniqueDepreciationMethodNameSetForUpdate.contains(depreciationMethodExtendedDTO.getDepreciationMethodName())){
                throw new InputMismatchException("("+ depreciationMethodExtendedDTO.getDepreciationMethodName() +") "+"depreciationMethodName already exist");
            }
        }

        ResponseDTO outcome = new ResponseDTO();
        if(depreciationMethodExtendedDTO.getStatus().equalsIgnoreCase("active") || depreciationMethodExtendedDTO.getStatus().equalsIgnoreCase("inActive")){
            if(depreciationMethodExtendedDTO.getDepreciationMethodName() != null && !depreciationMethodExtendedDTO.getDepreciationMethodName().equals("")) {

                DepreciationMethodDTO depreciationMethodDTO = (depreciationMethodExtendedDTO.getDepreciationMethodUuid() == null) ? new DepreciationMethodDTO() :
                    (depreciationMethodRepositoryExtended.findByDepreciationMethodUuid(depreciationMethodExtendedDTO.getDepreciationMethodUuid()) != null ?
                        depreciationMethodMapper.toDto(depreciationMethodRepositoryExtended.findByDepreciationMethodUuid(depreciationMethodExtendedDTO.getDepreciationMethodUuid())) :
                        new DepreciationMethodDTO());

                BeanUtils.copyProperties(depreciationMethodExtendedDTO, depreciationMethodDTO);

                if (depreciationMethodDTO.getDepreciationMethodUuid() == null) {
                    depreciationMethodDTO.setDepreciationMethodId(null);
                    depreciationMethodDTO.setCreatedById(31L);
                    depreciationMethodDTO.setCreatedDate(LocalDate.now());
                    depreciationMethodDTO.setCreatedByName("Falguni");
                    depreciationMethodDTO.setDepreciationMethodUuid(UUID.randomUUID());
                } else {
                    depreciationMethodDTO.setUpdatedDate(LocalDate.now());
                    depreciationMethodDTO.setUpdatedById(31L);
                    depreciationMethodDTO.setUpdatedByName("Falguni");
                }
                DepreciationMethodDTO savedDepreciationMethodDTO = depreciationMethodMapper.toDto(
                    depreciationMethodRepositoryExtended.save(depreciationMethodMapper.toEntity(depreciationMethodDTO))
                );

                return new ResponseDTO(true, "Successfully Saved.", List.of(savedDepreciationMethodDTO));
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
    public List<DepreciationMethodDTO> getAllDepreciationMethodInfo() {
        List<DepreciationMethod> data = depreciationMethodRepositoryExtended.findByStatusIgnoreCase("active");
        return depreciationMethodMapper.toDto(data);
    }

    @Override
    public List<DepreciationMethodDTO> getDepreciationMethodByUUID(UUID insuranceGroupMasterUuid) {
        List<DepreciationMethodDTO> dtoDataList = new ArrayList<DepreciationMethodDTO>();
        DepreciationMethod data = depreciationMethodRepositoryExtended.findByDepreciationMethodUuid(insuranceGroupMasterUuid);
        if(data != null){
            dtoDataList.add(depreciationMethodMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public DepreciationMethodDTO save(DepreciationMethodDTO depreciationMethodDTO) {
        return null;
    }

    @Override
    public DepreciationMethodDTO update(DepreciationMethodDTO depreciationMethodDTO) {
        return null;
    }

    @Override
    public Optional<DepreciationMethodDTO> partialUpdate(DepreciationMethodDTO depreciationMethodDTO) {
        return Optional.empty();
    }

    @Override
    public Page<DepreciationMethodDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<DepreciationMethodDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
