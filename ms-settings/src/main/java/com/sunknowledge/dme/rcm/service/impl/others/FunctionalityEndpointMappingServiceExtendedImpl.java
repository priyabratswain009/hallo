package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.*;
import com.sunknowledge.dme.rcm.repository.others.FunctionalityEndpointMappingRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityEndpointMappingDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityEndpointMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityEndpointMapUpdateParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalityEndpointMappingMapper;
import com.sunknowledge.dme.rcm.service.others.EndpointMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.others.FunctionalityEndpointMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.others.FunctionalityMasterServiceExtended;
import lombok.extern.slf4j.Slf4j;
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
public class FunctionalityEndpointMappingServiceExtendedImpl implements FunctionalityEndpointMappingServiceExtended {

    @Autowired
    FunctionalityMasterServiceExtended functionalityMasterServiceExtended;
    @Autowired
    EndpointMasterServiceExtended endpointMasterServiceExtended;
    @Autowired
    FunctionalityEndpointMappingRepositoryExtended functionalityEndpointMappingRepositoryExtended;
    @Autowired
    FunctionalityEndpointMappingMapper functionalityEndpointMappingMapper;

    @Override
    public FunctionalityEndpointMappingDTO save(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO) {
        return null;
    }

    @Override
    public FunctionalityEndpointMappingDTO update(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO) {
        return null;
    }

    @Override
    public Optional<FunctionalityEndpointMappingDTO> partialUpdate(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO) {
        return Optional.empty();
    }

    @Override
    public Page<FunctionalityEndpointMappingDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<FunctionalityEndpointMappingDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveFunctionalityEndpointMap(FunctionalityEndpointMapParameterDTO functionalityEndpointMapParameterDTO) {
        List<Long> functionalityId = functionalityMasterServiceExtended.getActiveIDsByUUIDs(List.of(functionalityEndpointMapParameterDTO.getFunctionalityUUID()));
        List<Long> endpointMasterIdList = endpointMasterServiceExtended.getActiveIDsByUUIDs(functionalityEndpointMapParameterDTO.getEndpointUUIDs());

        if(functionalityId.size()>0) {
            List<String> uniqueRoleFuncList = functionalityEndpointMappingRepositoryExtended.findByFunctionalityIdAndStatusIgnoreCase(functionalityId.get(0),"active")
                .stream().map(x->x.getFunctionalityId()+"_"+x.getEndpointId()).collect(Collectors.toList());
            List<FunctionalityEndpointMappingDTO> functionalityEndpointMappingDTOS = new ArrayList<>();
            for (Long endpointId : endpointMasterIdList) {
                if(!uniqueRoleFuncList.contains(functionalityId.get(0)+"_"+endpointId)){
                    FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = new FunctionalityEndpointMappingDTO();
                    functionalityEndpointMappingDTO.setEndpointId(endpointId);
                    functionalityEndpointMappingDTO.setFunctionalityEndpointMappingUuid(UUID.randomUUID());
                    functionalityEndpointMappingDTO.setFunctionalityId(functionalityId.get(0));
                    functionalityEndpointMappingDTO.setStatus("Active");
                    functionalityEndpointMappingDTO.setMappingDesc(functionalityEndpointMapParameterDTO.getMappingDesc());

                    functionalityEndpointMappingDTO.setCreatedById(1L);
                    functionalityEndpointMappingDTO.setCreatedByName("Abhay Api");
                    functionalityEndpointMappingDTO.setCreatedDate(LocalDate.now());
                    functionalityEndpointMappingDTOS.add(functionalityEndpointMappingDTO);
                }
            }
            if(functionalityEndpointMappingDTOS.size()>0){
                List<FunctionalityEndpointMapping> savedFunctionalityEndpointMapping = functionalityEndpointMappingRepositoryExtended.saveAll(functionalityEndpointMappingMapper.toEntity(functionalityEndpointMappingDTOS));
                return new ResponseDTO(true,"Data Saved Successfully.",functionalityEndpointMappingMapper.toDto(savedFunctionalityEndpointMapping),200);
            }else{
                return new ResponseDTO(false,"Data Not Found/Mapping Already Exist.",null,200);
            }
        }else{
            return new ResponseDTO(false,"Data Not Found.",null,200);
        }
    }

    @Override
    public ResponseDTO updateFunctionalityEndpointMap(FunctionalityEndpointMapUpdateParameterDTO functionalityEndpointMapUpdateParameterDTO) {
        Long endpointId = endpointMasterServiceExtended.getIDByUUID(functionalityEndpointMapUpdateParameterDTO.getEndpointUUID());
        Long functionalityId = functionalityMasterServiceExtended.getIDByUUID(functionalityEndpointMapUpdateParameterDTO.getFunctionalityUUID());

        FunctionalityEndpointMapping updateDTO = functionalityEndpointMappingRepositoryExtended.
            findByEndpointIdAndFunctionalityIdAndStatusIgnoreCase(endpointId, functionalityId, "active");
        if (updateDTO != null) {
            updateDTO.setStatus(functionalityEndpointMapUpdateParameterDTO.getStatus());
            updateDTO.setUpdatedById(1L);
            updateDTO.setUpdatedByName("Abhay Api Update");
            updateDTO.setUpdatedDate(LocalDate.now());
            FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = functionalityEndpointMappingMapper.toDto(functionalityEndpointMappingRepositoryExtended.save(updateDTO));
            return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", (functionalityEndpointMappingDTO),200));
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Data Not Found.", null,200));
        }
    }

    @Override
    public ResponseDTO getFunctionalityEndpointDetailsByNameOrNoOrUUID(String data, String operationType) {
        List<Map> obj = new ArrayList<>();
        switch (operationType){
            case "functionalityUUID":{
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = functionalityMasterServiceExtended.getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                obj = functionalityEndpointMappingRepositoryExtended.getEndpointDetailsByFunctionalityId(id);
                break;
            }
            case "functionalityName":{
                data=data.trim();
                obj = data!=""?functionalityEndpointMappingRepositoryExtended.getEndpointDetailsByFunctionalityName("%"+data+"%")
                    : new ArrayList<>()               ;
                break;
            }
            case "functionalityNo":{
                data=data.trim();
                obj = data!=""?functionalityEndpointMappingRepositoryExtended.getEndpointDetailsByFunctionalityNo(data)
                    : new ArrayList<>()               ;
                break;
            }
            case "endpointName":{
                data=data.trim();
                obj = data!=""?functionalityEndpointMappingRepositoryExtended.getFunctionalityDetailsByEndpointName("%"+data+"%")
                    : new ArrayList<>()               ;
                break;
            }
            case "endpointUUID":{
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = endpointMasterServiceExtended.getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                obj = functionalityEndpointMappingRepositoryExtended.getFunctionalityDetailsByEndpointId(id);
                break;
            }
            default:{
                return new ResponseDTO(false,"Please Give Correct OperationType",null,200);
            }
        }
        return new ResponseDTO<>(obj.size()>0?true:false,obj.size()>0?"":"Data Not Found.",obj,200);
    }

    @Override
    public ResponseDTO setFunctionalityEndpointStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<FunctionalityEndpointMapping> obj = functionalityEndpointMappingRepositoryExtended.findByFunctionalityEndpointMappingUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    functionalityEndpointMappingRepositoryExtended.save(obj.get());
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
    public ResponseDTO getAllFunctionalityEndpointMappingData() {
        List<FunctionalityEndpointMapping> dataList = functionalityEndpointMappingRepositoryExtended.findByStatusIgnoreCase("active");
        return new ResponseDTO<>(dataList.size()>0?true:false,
            dataList.size()>0?"":"Data Not Found.",dataList,200);
    }
}
