package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.others.FunctionalityMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityMasterMapper;
import com.sunknowledge.dme.rcm.service.others.FunctionalityMasterServiceExtended;
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
import java.util.stream.Collectors;

@Primary
@Service
public class FunctionalityMasterServiceExtendedImpl implements FunctionalityMasterServiceExtended {

    @Autowired
    FunctionalityMasterRepositoryExtended functionalityMasterRepositoryExtended;
    @Autowired
    FunctionalityMasterMapper functionalityMasterMapper;

    @Override
    public FunctionalityMasterDTO save(FunctionalityMasterDTO functionalityMasterDTO) {
        return null;
    }

    @Override
    public FunctionalityMasterDTO update(FunctionalityMasterDTO functionalityMasterDTO) {
        return null;
    }

    @Override
    public Optional<FunctionalityMasterDTO> partialUpdate(FunctionalityMasterDTO functionalityMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<FunctionalityMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<FunctionalityMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveFunctionalityMaster(FunctionalityMasterParameterDTO functionalityMasterParameterDTO) {
        ResponseDTO outcome = new ResponseDTO();
        if(functionalityMasterParameterDTO.getFunctionalityName() != null && functionalityMasterParameterDTO.getFunctionalityName() != "") {
            CommonUtilities.dtoTrimmer(functionalityMasterParameterDTO);
            FunctionalityMasterDTO functionalityMasterDTOData = (functionalityMasterParameterDTO.getFunctionalityMasterUUID() == null ?
                new FunctionalityMasterDTO() :
                (functionalityMasterRepositoryExtended.findByFunctionalityIdAndStatusIgnoreCase(getIDByUUID(functionalityMasterParameterDTO.getFunctionalityMasterUUID()),"active").isEmpty() ?
                    new FunctionalityMasterDTO() : functionalityMasterMapper.toDto(
                    functionalityMasterRepositoryExtended.findByFunctionalityIdAndStatusIgnoreCase(getIDByUUID(functionalityMasterParameterDTO.getFunctionalityMasterUUID()),"active").get())));
            BeanUtils.copyProperties(functionalityMasterParameterDTO, functionalityMasterDTOData);

            if (functionalityMasterDTOData.getFunctionalityMasterUuid() == null) {
                functionalityMasterDTOData.setFunctionalityId(null);
                functionalityMasterDTOData.setFunctionalityMasterUuid(UUID.randomUUID());
                functionalityMasterDTOData.setCreatedDate(LocalDate.now());
                functionalityMasterDTOData.setCreatedById(1L);
                functionalityMasterDTOData.setCreatedByName("Abhijit");
                functionalityMasterDTOData.setFunctionalityNo(functionalityMasterRepositoryExtended.getFunctionalityNumber());
            } else {
                functionalityMasterDTOData.setUpdatedDate(LocalDate.now());
                functionalityMasterDTOData.setUpdatedById(1L);
                functionalityMasterDTOData.setUpdatedByName("Abhijit");
            }
            FunctionalityMaster functionalityMasterSaved = functionalityMasterRepositoryExtended.save(functionalityMasterMapper.toEntity(functionalityMasterDTOData));

            outcome.setData(List.of(functionalityMasterSaved));
            outcome.setStatus(true);
            outcome.setMessage("Data Successfully Saved.");
            return outcome;
        }else{
            outcome.setStatus(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }
    }

    @Override
    public ResponseDTO getFunctionalityMasterByNameOrNoOrUUID(String data, String operationType) {
        switch (operationType){
            case "functionalityNo" : {
                FunctionalityMasterDTO functionalityMaster = functionalityMasterMapper.toDto(functionalityMasterRepositoryExtended.findByFunctionalityNo(data));
                return new ResponseDTO(functionalityMaster==null?false:true,functionalityMaster==null?"Data Not Found.":"Data Successfully fetched.",functionalityMaster==null?null:List.of(functionalityMaster));
            }
            case "functionalityName" : {
                List<FunctionalityMasterDTO> functionalityMasterList = data.trim()!=""?
                    functionalityMasterMapper.toDto(functionalityMasterRepositoryExtended.findByFunctionalityNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data.trim()+"%","active"))
                    :new ArrayList<>();
                return new ResponseDTO(!functionalityMasterList.isEmpty(),functionalityMasterList.isEmpty()?"Data Not Found.":"Data Successfully fetched.",functionalityMasterList);
            }
            case "functionalityUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                Optional<FunctionalityMaster> functionalityMasterData = functionalityMasterRepositoryExtended.findById(id);
                return new ResponseDTO(!functionalityMasterData.isEmpty(),functionalityMasterData.isEmpty()?"Data Not Found.":"Data Successfully fetched.",
                    functionalityMasterData.isEmpty()?null:List.of(functionalityMasterMapper.toDto(functionalityMasterData.get()))
                );
            }
            default:{
                return new ResponseDTO(false, "Please give correct operationType.",null);
            }
        }
    }

    @Override
    public Long getIDByUUID(UUID d) {
        return functionalityMasterRepositoryExtended.getIDByUUID(d);
    }

    @Override
    public List<Long> getActiveIDsByUUIDs(List<UUID> functionalityUUIDs) {
        return functionalityMasterRepositoryExtended.findByFunctionalityMasterUuidInAndStatusIgnoreCase(functionalityUUIDs,"active")
            .stream().map(x -> x.getFunctionalityId()).collect(Collectors.toList());
    }

}
