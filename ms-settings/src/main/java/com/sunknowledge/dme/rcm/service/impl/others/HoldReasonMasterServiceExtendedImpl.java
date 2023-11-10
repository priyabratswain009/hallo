package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.repository.others.HoldReasonMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.HoldReasonMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.HoldReasonMasterMapper;
import com.sunknowledge.dme.rcm.service.others.HoldReasonMasterServiceExtended;
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
public class HoldReasonMasterServiceExtendedImpl implements HoldReasonMasterServiceExtended {

    private final Logger log = LoggerFactory.getLogger(HoldReasonMasterServiceExtendedImpl.class);
    @Autowired
    HoldReasonMasterRepositoryExtended holdReasonMasterRepositoryExtended;

    @Autowired
    HoldReasonMasterMapper holdReasonMasterMapper;

    @Override
    public HoldReasonMasterDTO save(HoldReasonMasterDTO holdReasonMasterDTO) {
        return null;
    }

    @Override
    public HoldReasonMasterDTO update(HoldReasonMasterDTO holdReasonMasterDTO) {
        return null;
    }

    @Override
    public Optional<HoldReasonMasterDTO> partialUpdate(HoldReasonMasterDTO holdReasonMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<HoldReasonMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<HoldReasonMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveHoldReasonMaster(HoldReasonMasterExtendedDTO holdReasonMasterExtendedDTO) throws InvalidAttributeValueException {
        Set uniqueHoldReasonNameSet = holdReasonMasterRepositoryExtended.findAll().stream().map(x -> x.getHoldReasonName()).collect(Collectors.toSet());

        if (holdReasonMasterExtendedDTO.getHoldReasonName() == null) {
            throw new InvalidAttributeValueException("Invalid Attribute (holdReasonName)");
        } else if (holdReasonMasterExtendedDTO.getHoldReasonName().trim().equals("")) {
            throw new InputMismatchException("holdReasonName must be provided");
        }else if(holdReasonMasterExtendedDTO.getHoldReasonMasterUuid() == null && uniqueHoldReasonNameSet.contains(holdReasonMasterExtendedDTO.getHoldReasonName())){
            //Duplicate check during save operation
            throw new InputMismatchException("("+ holdReasonMasterExtendedDTO.getHoldReasonName() +") "+"holdReasonName already exist");
        }else if(holdReasonMasterExtendedDTO.getHoldReasonMasterUuid() != null && !holdReasonMasterExtendedDTO.getHoldReasonMasterUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueHoldReasonNameSetForUpdate = holdReasonMasterRepositoryExtended.findByHoldReasonMasterUuidNot(holdReasonMasterExtendedDTO.getHoldReasonMasterUuid()).stream().map(x -> x.getHoldReasonName()).collect(Collectors.toSet());
            if(uniqueHoldReasonNameSetForUpdate.contains(holdReasonMasterExtendedDTO.getHoldReasonName())){
                throw new InputMismatchException("("+ holdReasonMasterExtendedDTO.getHoldReasonName() +") "+"holdReasonName already exist");
            }
        }

        ResponseDTO outcome = new ResponseDTO();
        if(holdReasonMasterExtendedDTO.getStatus().equalsIgnoreCase("active") || holdReasonMasterExtendedDTO.getStatus().equalsIgnoreCase("inActive")){
            if(holdReasonMasterExtendedDTO.getHoldReasonName() != null && !holdReasonMasterExtendedDTO.getHoldReasonName().equals("")) {

                HoldReasonMasterDTO holdReasonMasterDTO = (holdReasonMasterExtendedDTO.getHoldReasonMasterUuid() == null) ? new HoldReasonMasterDTO() :
                    (holdReasonMasterRepositoryExtended.findByHoldReasonMasterUuid(holdReasonMasterExtendedDTO.getHoldReasonMasterUuid()) != null ?
                        holdReasonMasterMapper.toDto(holdReasonMasterRepositoryExtended.findByHoldReasonMasterUuid(holdReasonMasterExtendedDTO.getHoldReasonMasterUuid())) :
                        new HoldReasonMasterDTO());

                BeanUtils.copyProperties(holdReasonMasterExtendedDTO, holdReasonMasterDTO);

                if (holdReasonMasterDTO.getHoldReasonMasterUuid() == null) {
                    holdReasonMasterDTO.setHoldReasonId(null);
                    //holdReasonMasterDTO.setHoldReasonName();
                    holdReasonMasterDTO.setCreatedById(31L);
                    holdReasonMasterDTO.setCreatedDate(LocalDate.now());
                    holdReasonMasterDTO.setCreatedByName("Falguni");
                    holdReasonMasterDTO.setHoldReasonMasterUuid(UUID.randomUUID());
                } else {
                    holdReasonMasterDTO.setUpdatedDate(LocalDate.now());
                    holdReasonMasterDTO.setUpdatedById(31L);
                    holdReasonMasterDTO.setUpdatedByName("Falguni");
                }
                HoldReasonMasterDTO savedHoldReasonMasterDTO = holdReasonMasterMapper.toDto(
                    holdReasonMasterRepositoryExtended.save(holdReasonMasterMapper.toEntity(holdReasonMasterDTO))
                );
                log.info("=======savedHoldReasonMasterDTO======="+savedHoldReasonMasterDTO);

                return new ResponseDTO(true, "Successfully Saved.", List.of(savedHoldReasonMasterDTO));
            }else{
                outcome.setStatus(false);
                outcome.setMessage("Data Not Saved.");
                return outcome;
            }
        }else{
            throw new InputMismatchException("Status Should be active/inactive");
        }
    }

    @Override
    public List<HoldReasonMasterDTO> getAllHoldReasonDetails() {
        List<HoldReasonMaster> data = holdReasonMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return holdReasonMasterMapper.toDto(data);
    }

    @Override
    public List<HoldReasonMasterDTO> getHoldReasonDetailsByUUID(UUID holdReasonMasterUuid) {
        List<HoldReasonMasterDTO> dtoDataList = new ArrayList<HoldReasonMasterDTO>();
        HoldReasonMaster data = holdReasonMasterRepositoryExtended.findByHoldReasonMasterUuid(holdReasonMasterUuid);
        if(data != null){
            dtoDataList.add(holdReasonMasterMapper.toDto(data));
        }
        return dtoDataList;
    }
}
