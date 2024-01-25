package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.repository.others.RoleUserMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleUserMapMapper;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.others.RoleUserMapServiceExtended;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@Slf4j
public class RoleUserMapServiceExtendedImpl implements RoleUserMapServiceExtended {

    @Autowired
    RoleMasterServiceExtended roleMasterServiceExtended;
    @Autowired
    UserMasterServiceExtended userMasterServiceExtended;
    @Autowired
    RoleUserMapRepositoryExtended roleUserMapRepositoryExtended;
    @Autowired
    RoleUserMapMapper roleUserMapMapper;


    @Override
    public RoleUserMapDTO save(RoleUserMapDTO roleUserMapDTO) {
        return null;
    }

    @Override
    public RoleUserMapDTO update(RoleUserMapDTO roleUserMapDTO) {
        return null;
    }

    @Override
    public Optional<RoleUserMapDTO> partialUpdate(RoleUserMapDTO roleUserMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<RoleUserMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RoleUserMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveRoleUserMap(RoleUserMapParameterDTO roleUserMapParameterDTO) {
        Long roleId = roleMasterServiceExtended.getIDByUUID(roleUserMapParameterDTO.getRoleUUID());
        Long userId = userMasterServiceExtended.getIDByUUID(roleUserMapParameterDTO.getUserUUID());
        RoleMasterDTO roleMasterDTO = roleMasterServiceExtended.getActiveDataById(roleId);
        UserMasterDTO userMasterDTO = userMasterServiceExtended.getDataByActiveId(userId);
        if(roleMasterDTO != null && userMasterDTO != null){
            RoleUserMap isduplicate = roleUserMapRepositoryExtended.findByUserIdAndStatusIgnoreCase(userId,"active");
            if(isduplicate == null) {
                RoleUserMapDTO roleUserMapDTO = new RoleUserMapDTO();
                roleUserMapDTO.setRoleId(roleId);
                roleUserMapDTO.setUserId(userId);
                roleUserMapDTO.setDescription(roleUserMapParameterDTO.getDescription());
                roleUserMapDTO.setStatus("Active");

                roleUserMapDTO.setCreatedById(1L);
                roleUserMapDTO.setCreatedByName("Abhijit");
                roleUserMapDTO.setCreatedDate(LocalDate.now());

                RoleUserMap roleUserMapSaved = roleUserMapRepositoryExtended.save(roleUserMapMapper.toEntity(roleUserMapDTO));
                return new ResponseDTO(true, "Data Saved Successfully.", (roleUserMapSaved),200);
            }else{
                return new ResponseDTO(false, "User already mapped with another role.", null,200);
            }
        }else {
            if (roleMasterDTO == null) {
                return new ResponseDTO(false, "Role UUID not found.", new ArrayList<>(),200);
            } else {
                return new ResponseDTO(false, "User UUID not found.", new ArrayList<>(),200);
            }
        }
    }
    @Override
    public ResponseDTO updateStatusRoleUserMap(RoleUserMapParameterDTO roleUserMapParameterDTO, String status) {
        Long roleId = roleMasterServiceExtended.getIDByUUID(roleUserMapParameterDTO.getRoleUUID());
        Long userId = userMasterServiceExtended.getIDByUUID(roleUserMapParameterDTO.getUserUUID());

        RoleUserMap updateDTO = roleUserMapRepositoryExtended.
            findByRoleIdAndUserIdAndStatusIgnoreCase(roleId, userId, "active");
        if (updateDTO != null) {
            updateDTO.setStatus(status);
            updateDTO.setUpdatedById(1L);
            updateDTO.setUpdatedByName("Abhijit Update");
            updateDTO.setUpdatedDate(LocalDate.now());
            RoleUserMapDTO roleUserMap = roleUserMapMapper.toDto(roleUserMapRepositoryExtended.save(updateDTO));
            return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", (roleUserMap),200));
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Data Not Found.", null,200));
        }
    }

    @Override
    public ResponseDTO getRoleUserDetailsByRoleUUID(String data, String operationType) {
        List<Map> obj = new ArrayList<>();
        switch (operationType){
            case "roleNo":{
                obj = roleUserMapRepositoryExtended.getRoleUserDetailsByRoleNo(data);
                break;
            }
            case "userNo":{
                obj = roleUserMapRepositoryExtended.getRoleUserDetailsByUserNo(data);
                break;
            }
            case "roleName":{
                obj = roleUserMapRepositoryExtended.getRoleUserDetailsByRoleName(data);
                break;
            }
            case "userName":{
                obj = roleUserMapRepositoryExtended.getRoleUserDetailsByUserName(data);
                break;
            }
            case "roleUUID":{
                obj = roleUserMapRepositoryExtended.getRoleUserDetailsByRoleUUID(UUID.fromString(data));
                break;
            }
            case "userUUID":{
                obj = roleUserMapRepositoryExtended.getRoleUserDetailsByUserUUID(UUID.fromString(data));
                break;
            }
            default:{
                return new ResponseDTO(false,"Please Give Correct OperationType",null,200);
            }
        }
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @Override
    public ResponseDTO setRoleUserMapStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<RoleUserMap> obj = roleUserMapRepositoryExtended.findByRoleUserMapUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    roleUserMapRepositoryExtended.save(obj.get());
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
