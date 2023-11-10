package com.sunknowledge.dme.rcm.service.impl.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.FacilityMaster;
import com.sunknowledge.dme.rcm.repository.common.FacilityMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.common.FacilityMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.FacilityMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.FacilityMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FacilityMasterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@Slf4j
public class FacilityMasterServiceExtendedImpl implements FacilityMasterServiceExtended {

    @Autowired
    FacilityMasterRepositoryExtended facilityMasterRepositoryExtended;
    @Autowired
    FacilityMasterMapper facilityMasterMapper;

    @Override
    public FacilityMasterDTO save(FacilityMasterDTO facilityMasterDTO) {
        return null;
    }

    @Override
    public FacilityMasterDTO update(FacilityMasterDTO facilityMasterDTO) {
        return null;
    }

    @Override
    public Optional<FacilityMasterDTO> partialUpdate(FacilityMasterDTO facilityMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<FacilityMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<FacilityMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ServiceOutcome saveFacilityMaster(FacilityMasterParameterDTO facilityMasterParameterDTO) {
        ServiceOutcome outcome = new ServiceOutcome();
        if(facilityMasterParameterDTO.getFacilityName() != null && facilityMasterParameterDTO.getFacilityName() != "") {
            CommonUtilities.dtoTrimmer(facilityMasterParameterDTO);
            FacilityMasterDTO facilityMasterData = (facilityMasterParameterDTO.getFacilityMasterUuid() == null) ? new FacilityMasterDTO() :
                (facilityMasterRepositoryExtended.findByFacilityIdAndStatusIgnoreCase(getIDByUUID(facilityMasterParameterDTO.getFacilityMasterUuid()),"active") == null ?
                    new FacilityMasterDTO() : facilityMasterMapper.toDto(
                    facilityMasterRepositoryExtended.findByFacilityIdAndStatusIgnoreCase(getIDByUUID(facilityMasterParameterDTO.getFacilityMasterUuid()),"active")));
            BeanUtils.copyProperties(facilityMasterParameterDTO, facilityMasterData);

            if (facilityMasterData.getFacilityMasterUuid() == null) {
                facilityMasterData.setFacilityId(null);
                facilityMasterData.setFacilityMasterUuid(UUID.randomUUID());
                facilityMasterData.setCreatedDate(LocalDate.now());
                facilityMasterData.setCreatedById(1L);
                facilityMasterData.setCreatedByName("Abhijit");
                facilityMasterData.setFacilityNo(facilityMasterRepositoryExtended.getFacilityNumber());
            } else {
                facilityMasterData.setUpdatedDate(LocalDate.now());
                facilityMasterData.setUpdatedById(1L);
                facilityMasterData.setUpdatedByName("Abhijit");
            }
            FacilityMaster facilityMasterSaved = facilityMasterRepositoryExtended.save(facilityMasterMapper.toEntity(facilityMasterData));

            outcome.setData(facilityMasterSaved);
            outcome.setOutcome(true);
            outcome.setMessage("Data Successfully Saved.");
            return outcome;
        }else{
            outcome.setOutcome(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }
    }

    @Override
    public ServiceOutcome getFacilityMasterByNameOrNoOrUUID(String data, String operationType) {
        switch (operationType){
            case "facilityNo" : {
                FacilityMaster facilityMaster = facilityMasterRepositoryExtended.findByFacilityNo(data);
                return new ServiceOutcome(facilityMaster,facilityMaster==null?false:true ,facilityMaster==null?"Data Not Found.":"Data Successfully fetched.");
            }
            case "facilityName" : {
                List<FacilityMaster> facilityMasterList = data.trim()!=""?
                    facilityMasterRepositoryExtended.findByFacilityNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data.trim()+"%","active")
                    :new ArrayList<>();
                return new ServiceOutcome(facilityMasterList,!facilityMasterList.isEmpty() ,facilityMasterList.isEmpty()?"Data Not Found.":"Data Successfully fetched.");
            }
            case "facilityUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                Optional<FacilityMaster> facilityMaster = facilityMasterRepositoryExtended.findById(id);
                return new ServiceOutcome(facilityMaster.isEmpty()?null:facilityMaster.get(),
                    !facilityMaster.isEmpty() ,
                    facilityMaster.isEmpty()?"Data Not Found.":"Data Successfully fetched."
                );
            }
            default:{
                return new ServiceOutcome(null,false ,"Please give correct operationType.");
            }
        }
    }

    @Override
    public Long getIDByUUID(UUID l) {
        return facilityMasterRepositoryExtended.getIDByUUID(l);
    }
}
