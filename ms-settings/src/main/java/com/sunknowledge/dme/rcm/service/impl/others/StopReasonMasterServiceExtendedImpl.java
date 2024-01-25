package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.Company;
import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.repository.others.StopReasonMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.StopReasonMasterExtendedDTO;

import com.sunknowledge.dme.rcm.service.mapper.StopReasonMasterMapper;
import com.sunknowledge.dme.rcm.service.others.StopReasonMasterServiceExtended;
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
public class StopReasonMasterServiceExtendedImpl implements StopReasonMasterServiceExtended {

    private final Logger log = LoggerFactory.getLogger(StopReasonMasterServiceExtendedImpl.class);

    @Autowired
    StopReasonMasterRepositoryExtended stopReasonMasterRepositoryExtended;

    @Autowired
    StopReasonMasterMapper stopReasonMasterMapper;

    @Override
    public StopReasonMasterDTO save(StopReasonMasterDTO stopReasonMasterDTO) {
        return null;
    }

    @Override
    public StopReasonMasterDTO update(StopReasonMasterDTO stopReasonMasterDTO) {
        return null;
    }

    @Override
    public Optional<StopReasonMasterDTO> partialUpdate(StopReasonMasterDTO stopReasonMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<StopReasonMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StopReasonMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveStopReasonMaster(StopReasonMasterExtendedDTO stopReasonMasterExtendedDTO) throws InvalidAttributeValueException {
        Set uniqueHoldReasonNameSet = stopReasonMasterRepositoryExtended.findByStatusIgnoreCase("active").stream().map(x -> x.getStopReasonName()).collect(Collectors.toSet());
        log.info("=======uniqueHoldReasonNameSet======="+uniqueHoldReasonNameSet);

        if (stopReasonMasterExtendedDTO.getStopReasonName() == null) {
            throw new InvalidAttributeValueException("Invalid Attribute (stopReasonName)");
        } else if (stopReasonMasterExtendedDTO.getStopReasonName().trim().equals("")) {
            throw new InputMismatchException("stopReasonName must be provided");
        }else if(stopReasonMasterExtendedDTO.getStopReasonMasterUuid() == null && uniqueHoldReasonNameSet.contains(stopReasonMasterExtendedDTO.getStopReasonName())){
            ////Duplicate check during save operation
            throw new InputMismatchException("("+ stopReasonMasterExtendedDTO.getStopReasonName() +") "+"stopReasonName already exist");
        }else if(stopReasonMasterExtendedDTO.getStopReasonMasterUuid() != null && !stopReasonMasterExtendedDTO.getStopReasonMasterUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueHoldReasonNameSetForUpdate = stopReasonMasterRepositoryExtended.findByStopReasonMasterUuidNot(stopReasonMasterExtendedDTO.getStopReasonMasterUuid()).stream().map(x -> x.getStopReasonName()).collect(Collectors.toSet());
            if(uniqueHoldReasonNameSetForUpdate.contains(stopReasonMasterExtendedDTO.getStopReasonName())){
                throw new InputMismatchException("("+ stopReasonMasterExtendedDTO.getStopReasonName() +") "+"stopReasonName already exist");
            }
        }

        ResponseDTO outcome = new ResponseDTO();

        if(stopReasonMasterExtendedDTO.getStopReasonName() != null && !stopReasonMasterExtendedDTO.getStopReasonName().equals("")) {
            StopReasonMasterDTO stopReasonMasterDTO = (stopReasonMasterExtendedDTO.getStopReasonMasterUuid() == null) ? new StopReasonMasterDTO() :
                (stopReasonMasterRepositoryExtended.findByStopReasonMasterUuid(stopReasonMasterExtendedDTO.getStopReasonMasterUuid()) != null?
                    stopReasonMasterMapper.toDto(stopReasonMasterRepositoryExtended.findByStopReasonMasterUuid(stopReasonMasterExtendedDTO.getStopReasonMasterUuid())) :
                    new StopReasonMasterDTO());

            BeanUtils.copyProperties(stopReasonMasterExtendedDTO, stopReasonMasterDTO);
            stopReasonMasterDTO.setStatus("active");
            if (stopReasonMasterDTO.getStopReasonMasterUuid() == null) {
                stopReasonMasterDTO.setStopReasonId(null);
                //holdReasonMasterDTO.setHoldReasonName();
                stopReasonMasterDTO.setCreatedById(31L);
                stopReasonMasterDTO.setCreatedDate(LocalDate.now());
                stopReasonMasterDTO.setCreatedByName("Falguni");
                stopReasonMasterDTO.setStopReasonMasterUuid(UUID.randomUUID());
            } else {
                stopReasonMasterDTO.setUpdatedDate(LocalDate.now());
                stopReasonMasterDTO.setUpdatedById(31L);
                stopReasonMasterDTO.setUpdatedByName("Falguni");
            }
            StopReasonMasterDTO savedStopReasonMasterDTO = stopReasonMasterMapper.toDto(
                stopReasonMasterRepositoryExtended.save(stopReasonMasterMapper.toEntity(stopReasonMasterDTO))
            );

            log.info("=======savedStopReasonMasterDTO======="+savedStopReasonMasterDTO);

            return new ResponseDTO(true, "Successfully Saved.", (savedStopReasonMasterDTO),200);
        }else{
            outcome.setOutcome(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }

    }

    @Override
    public List<StopReasonMasterDTO> getAllStopReasonDetails() {
        List<StopReasonMaster> data = stopReasonMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return stopReasonMasterMapper.toDto(data);
    }

    @Override
    public StopReasonMasterDTO getStopReasonDetailsByUUID(UUID stopReasonMasterUuid) {
        StopReasonMaster data = stopReasonMasterRepositoryExtended.findByStopReasonMasterUuidAndStatusIgnoreCase(stopReasonMasterUuid,"active");
        if(data != null){
            return stopReasonMasterMapper.toDto(data);
        }
        return null;
    }

    @Override
    public ResponseDTO setStopReasonDetailsStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<StopReasonMaster> obj = Optional.ofNullable(stopReasonMasterRepositoryExtended.findByStopReasonMasterUuid(uuid));
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    stopReasonMasterRepositoryExtended.save(obj.get());
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
