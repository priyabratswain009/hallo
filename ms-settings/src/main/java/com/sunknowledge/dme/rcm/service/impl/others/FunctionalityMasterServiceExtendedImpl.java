package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping;
import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.others.FunctionalityMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityMasterMapper;
import com.sunknowledge.dme.rcm.service.others.FunctionalityMasterServiceExtended;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
@Slf4j
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
            functionalityMasterDTOData.setStatus("Active");
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

            outcome.setData(functionalityMasterSaved);
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
    public ResponseDTO getFunctionalityMasterByNameOrNoOrUUID(String data, String operationType) {
        switch (operationType){
            case "functionalityNo" : {
                FunctionalityMasterDTO functionalityMaster = functionalityMasterMapper.toDto(functionalityMasterRepositoryExtended.findByFunctionalityNo(data));
                return new ResponseDTO(functionalityMaster==null?false:true,functionalityMaster==null?"Data Not Found.":"",functionalityMaster==null?null:(functionalityMaster),200);
            }
            case "functionalityName" : {
                List<FunctionalityMasterDTO> functionalityMasterList = data.trim()!=""?
                    functionalityMasterMapper.toDto(functionalityMasterRepositoryExtended.findByFunctionalityNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data.trim()+"%","active"))
                    :new ArrayList<>();
                return new ResponseDTO(!functionalityMasterList.isEmpty(),functionalityMasterList.isEmpty()?"Data Not Found.":"",functionalityMasterList,200);
            }
            case "functionalityUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                Optional<FunctionalityMaster> functionalityMasterData = functionalityMasterRepositoryExtended.findById(id);
                return new ResponseDTO(!functionalityMasterData.isEmpty(),functionalityMasterData.isEmpty()?"Data Not Found.":"",
                    functionalityMasterData.isEmpty()?null:(functionalityMasterMapper.toDto(functionalityMasterData.get())),200
                );
            }
            default:{
                return new ResponseDTO(false, "Please give correct operationType.",null,200);
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

    @Override
    public ResponseDTO setFunctionalityMasterStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<FunctionalityMaster> obj = functionalityMasterRepositoryExtended.findByFunctionalityMasterUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    functionalityMasterRepositoryExtended.save(obj.get());
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

    @Override
    public ResponseDTO getAllFunctionalityMasterData() {
        List<FunctionalityMaster> functionalityMasters = functionalityMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return new ResponseDTO<>(functionalityMasters.size()>0?true:false,
            functionalityMasters.size()>0?"":"Data Not Found.",functionalityMasters,200);
    }

    @Override
    public List<Map<String, Object>> getFunctionalityMasterForDropdown() {
        List<FunctionalityMaster> posMasterList = functionalityMasterRepositoryExtended.findByStatusIgnoreCase("active");
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getFunctionalityMasterUuid());
                map.put("title", p.getFunctionalityName());
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
