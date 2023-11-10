package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.repository.others.EndpointMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.EndpointMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.EndpointMasterMapper;
import com.sunknowledge.dme.rcm.service.others.EndpointMasterServiceExtended;
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

                outcome.setData(List.of(savedEndpointMaster));
                outcome.setStatus(true);
                outcome.setMessage("Data Successfully Saved.");
                return outcome;
            }else{

                outcome.setStatus(false);
                outcome.setMessage("Data Already Exist.");
                return outcome;
            }
        }else{
            outcome.setStatus(false);
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
                    endpointMasterDTOList.size()>0?"Data Successfully fetched.":"Data Not Found.",
                    endpointMasterDTOList.size()>0?endpointMasterDTOList:null
                );
            }
            case "endpointGroup" : {
                data=data.trim();
                List<EndpointMasterDTO> endpointMasterDTOList = endpointMasterMapper.toDto(endpointMasterRepositoryExtended.
                    findByEndpointGroupLikeIgnoreCaseAndStatusIgnoreCase("%"+data+"%","active"));
                return new ResponseDTO(
                    endpointMasterDTOList.size()>0?true:false,
                    endpointMasterDTOList.size()>0?"Data Successfully fetched.":"Data Not Found.",
                    endpointMasterDTOList.size()>0?endpointMasterDTOList:null
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
                    endpointMasterData==null?"Data Not Found.":"Data Successfully fetched.",
                    endpointMasterData==null?null:List.of(endpointMasterMapper.toDto(endpointMasterData))
                );
            }
            default:{
                return new ResponseDTO(false, "Please give correct operationType.",null);
            }
        }
    }

    @Override
    public List<Long> getActiveIDsByUUIDs(List<UUID> endpointUUIDs) {
        return endpointMasterRepositoryExtended.findByEndpointMasterUuidInAndStatusIgnoreCase(endpointUUIDs,"active")
            .stream().map(x -> x.getEndpointId()).collect(Collectors.toList());
    }
}
