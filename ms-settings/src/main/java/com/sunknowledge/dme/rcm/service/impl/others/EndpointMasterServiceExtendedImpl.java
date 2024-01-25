package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.Company;
import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.repository.others.EndpointMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.EndpointMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.EndpointMasterMapper;
import com.sunknowledge.dme.rcm.service.others.EndpointMasterServiceExtended;
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
public class EndpointMasterServiceExtendedImpl implements EndpointMasterServiceExtended {

    @Autowired
    EndpointMasterRepositoryExtended endpointMasterRepositoryExtended;
    @Autowired
    EndpointMasterMapper endpointMasterMapper;

    @Override
    public EndpointMasterDTO save(EndpointMasterDTO endpointMasterDTO) {
        return null;
    }

    @Override
    public EndpointMasterDTO update(EndpointMasterDTO endpointMasterDTO) {
        return null;
    }

    @Override
    public Optional<EndpointMasterDTO> partialUpdate(EndpointMasterDTO endpointMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<EndpointMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<EndpointMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveUpdateEndpointMaster(EndpointMasterParameterDTO endpointMasterParameterDTO) {
        ResponseDTO outcome = new ResponseDTO();
        if(endpointMasterParameterDTO.getEndpointName() != null && endpointMasterParameterDTO.getEndpointName() != "") {
            CommonUtilities.dtoTrimmer(endpointMasterParameterDTO);
            List<String> endpointUrls = endpointMasterRepositoryExtended.findByEndpointUrlAndStatusIgnoreCase(endpointMasterParameterDTO.getEndpointUrl(),"active")
                .stream().map(x-> x.getEndpointName()+"_"+x.getEndpointUrl()).collect(Collectors.toList());
            if(!endpointUrls.contains(endpointMasterParameterDTO.getEndpointName()+"_"+endpointMasterParameterDTO.getEndpointUrl())) {
                EndpointMasterDTO endpointMasterDTOData = (endpointMasterParameterDTO.getEndpointMasterUuid() == null ?
                    new EndpointMasterDTO() :
                    (endpointMasterRepositoryExtended.findByEndpointIdAndStatusIgnoreCase(getIDByUUID(endpointMasterParameterDTO.getEndpointMasterUuid()), "active") == null ?
                        new EndpointMasterDTO() : endpointMasterMapper.toDto(
                        endpointMasterRepositoryExtended.findByEndpointIdAndStatusIgnoreCase(getIDByUUID(endpointMasterParameterDTO.getEndpointMasterUuid()), "active"))));
                BeanUtils.copyProperties(endpointMasterParameterDTO, endpointMasterDTOData);
                endpointMasterDTOData.setStatus("active");
                if (endpointMasterDTOData.getEndpointMasterUuid() == null) {
                    endpointMasterDTOData.setEndpointId(null);
                    endpointMasterDTOData.setEndpointMasterUuid(UUID.randomUUID());
                    endpointMasterDTOData.setCreatedDate(LocalDate.now());
                    endpointMasterDTOData.setCreatedById(1L);
                    endpointMasterDTOData.setCreatedByName("Abhijit");

                } else {
                    endpointMasterDTOData.setUpdatedDate(LocalDate.now());
                    endpointMasterDTOData.setUpdatedById(1L);
                    endpointMasterDTOData.setUpdatedByName("Abhijit");
                }
                EndpointMaster savedEndpointMaster = endpointMasterRepositoryExtended.save(endpointMasterMapper.toEntity(endpointMasterDTOData));

                outcome.setData(savedEndpointMaster);
                outcome.setOutcome(true);
                outcome.setMessage("Data Successfully Saved.");
                return outcome;
            }else{

                outcome.setOutcome(false);
                outcome.setMessage("Data Already Exist.");
                return outcome;
            }
        }else{
            outcome.setOutcome(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }
    }

    @Override
    public Long getIDByUUID(UUID endpointMasterUuid) {
        return endpointMasterRepositoryExtended.getIDByUUID(endpointMasterUuid);
    }

    @Override
    public ResponseDTO getEndpointMasterByNameOrGroupOrUUID(String data, String operationType) {
        switch (operationType){
            case "endpointName" : {
                data=data.trim();
                List<EndpointMasterDTO> endpointMasterDTOList = endpointMasterMapper.toDto(endpointMasterRepositoryExtended.
                    findByEndpointNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data+"%","active"));
                return new ResponseDTO(
                    endpointMasterDTOList.size()>0?true:false,
                    endpointMasterDTOList.size()>0?"":"Data Not Found.",
                    endpointMasterDTOList.size()>0?endpointMasterDTOList:null,200
                );
            }
            case "endpointGroup" : {
                data=data.trim();
                List<EndpointMasterDTO> endpointMasterDTOList = endpointMasterMapper.toDto(endpointMasterRepositoryExtended.
                    findByEndpointGroupLikeIgnoreCaseAndStatusIgnoreCase("%"+data+"%","active"));
                return new ResponseDTO(
                    endpointMasterDTOList.size()>0?true:false,
                    endpointMasterDTOList.size()>0?"":"Data Not Found.",
                    endpointMasterDTOList.size()>0?endpointMasterDTOList:null,200
                );
            }
            case "endpointUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                EndpointMaster endpointMasterData = endpointMasterRepositoryExtended.findByEndpointIdAndStatusIgnoreCase(id,"active");
                return new ResponseDTO(endpointMasterData==null?false:true,
                    endpointMasterData==null?"Data Not Found.":"",
                    endpointMasterData==null?null:(endpointMasterMapper.toDto(endpointMasterData)),200
                );
            }
            default:{
                return new ResponseDTO(false, "Please give correct operationType.",null,200);
            }
        }
    }

    @Override
    public List<Long> getActiveIDsByUUIDs(List<UUID> endpointUUIDs) {
        return endpointMasterRepositoryExtended.findByEndpointMasterUuidInAndStatusIgnoreCase(endpointUUIDs,"active")
            .stream().map(x -> x.getEndpointId()).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO setEndpointMasterStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<EndpointMaster> obj = endpointMasterRepositoryExtended.findByEndpointMasterUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    endpointMasterRepositoryExtended.save(obj.get());
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
    public ResponseDTO getAllEndpointMasterData() {
        List<EndpointMaster> endpointMasters = endpointMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return (new ResponseDTO(endpointMasters.size()>0?true:false, endpointMasters.size()>0?"":"Data Not Found", endpointMasters,200));
    }

    @Override
    public List<Map<String, Object>> getEndpointMasterForDropdown() {
        List<EndpointMaster> posMasterList = endpointMasterRepositoryExtended.findByStatusIgnoreCase("active");
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getEndpointMasterUuid());
                map.put("title", p.getEndpointName());
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
