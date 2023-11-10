package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.RoleMasterRepository;
import com.sunknowledge.dme.rcm.repository.others.RoleMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleMasterMapper;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service
public class RoleMasterServiceExtendedImpl implements RoleMasterServiceExtended {

    @Autowired
    RoleMasterRepositoryExtended roleMasterRepositoryExtended;
    @Autowired
    RoleMasterMapper roleMasterMapper;


    @Override
    public RoleMasterDTO save(RoleMasterDTO roleMasterDTO) {
        return null;
    }

    @Override
    public RoleMasterDTO update(RoleMasterDTO roleMasterDTO) {
        return null;
    }

    @Override
    public Optional<RoleMasterDTO> partialUpdate(RoleMasterDTO roleMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<RoleMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RoleMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveRoleMaster(RoleMasterParameterDTO roleMasterParameterDTO) {
        ResponseDTO outcome = new ResponseDTO();
        if(roleMasterParameterDTO.getRoleName() != null && roleMasterParameterDTO.getRoleName() != "") {
            CommonUtilities.dtoTrimmer(roleMasterParameterDTO);
            RoleMasterDTO roleMasterDTOData = (roleMasterParameterDTO.getRoleMasterUuid() == null) ? new RoleMasterDTO() :
                (roleMasterRepositoryExtended.findByRoleIdAndStatusIgnoreCase(getIDByUUID(roleMasterParameterDTO.getRoleMasterUuid()),"active") == null ?
                    new RoleMasterDTO() : roleMasterMapper.toDto(
                    roleMasterRepositoryExtended.findByRoleIdAndStatusIgnoreCase(getIDByUUID(roleMasterParameterDTO.getRoleMasterUuid()),"active")));
            BeanUtils.copyProperties(roleMasterParameterDTO, roleMasterDTOData);

            if (roleMasterDTOData.getRoleMasterUuid() == null) {
                roleMasterDTOData.setRoleId(null);
                roleMasterDTOData.setRoleMasterUuid(UUID.randomUUID());
                roleMasterDTOData.setCreatedDate(LocalDate.now());
                roleMasterDTOData.setCreatedById(1L);
                roleMasterDTOData.setCreatedByName("Abhijit");
                roleMasterDTOData.setRoleNo(roleMasterRepositoryExtended.getRolNumber());
            } else {
                roleMasterDTOData.setUpdatedDate(LocalDate.now());
                roleMasterDTOData.setUpdatedById(1L);
                roleMasterDTOData.setUpdatedByName("Abhijit");
            }
            RoleMaster roleMasterSaved = roleMasterRepositoryExtended.save(roleMasterMapper.toEntity(roleMasterDTOData));

            outcome.setData(List.of(roleMasterSaved));
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
    public ResponseDTO getRoleMasterByNameOrNoOrUUID(String data, String operationType) {
        switch (operationType){
            case "roleNo" : {
                RoleMasterDTO roleMaster = roleMasterMapper.toDto(roleMasterRepositoryExtended.findByRoleNo(data));
                return new ResponseDTO(roleMaster==null?false:true,roleMaster==null?"Data Not Found.":"Data Successfully fetched.",roleMaster==null?null:List.of(roleMaster));
            }
            case "roleName" : {
                List<RoleMasterDTO> roleMasterList = data.trim()!=""?
                    roleMasterMapper.toDto(roleMasterRepositoryExtended.findByRoleNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data.trim()+"%","active"))
                    :new ArrayList<>();
                return new ResponseDTO(!roleMasterList.isEmpty(),roleMasterList.isEmpty()?"Data Not Found.":"Data Successfully fetched.",roleMasterList);
            }
            case "roleUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                Optional<RoleMaster> roleMaster = roleMasterRepositoryExtended.findById(id);
                return new ResponseDTO(!roleMaster.isEmpty(),roleMaster.isEmpty()?"Data Not Found.":"Data Successfully fetched.",
                    roleMaster.isEmpty()?null:List.of(roleMasterMapper.toDto(roleMaster.get()))
                );
            }
            default:{
                return new ResponseDTO(false, "Please give correct operationType.",null);
            }
        }
    }

    @Override
    public Long getIDByUUID(UUID d) {
        return roleMasterRepositoryExtended.getIDByUUID(d);
    }

    @Override
    public RoleMasterDTO getActiveDataById(Long id) {
        return roleMasterMapper.toDto(roleMasterRepositoryExtended.findByRoleIdAndStatusIgnoreCase(id,"active"));
    }

    @Override
    public List<Long> getActiveIDByUUID(List<UUID> roleUUID) {
        return roleMasterRepositoryExtended.findByRoleMasterUuidInAndStatusIgnoreCase(roleUUID, "active").stream().map(x->x.getRoleId()).collect(Collectors.toList());
    }
}
