package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.repository.RoleUserMapRepository;
import com.sunknowledge.dme.rcm.repository.others.RoleUserMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchUserMapDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleUserMapMapper;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.others.RoleUserMapServiceExtended;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Primary
@Service
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
                return new ResponseDTO(true, "Data Saved Successfully.", List.of(roleUserMapSaved));
            }else{
                return new ResponseDTO(false, "User already mapped with another role.", null);
            }
        }else {
            if (roleMasterDTO == null) {
                return new ResponseDTO(false, "Role UUID not found.", new ArrayList<>());
            } else {
                return new ResponseDTO(false, "User UUID not found.", new ArrayList<>());
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
            return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(roleUserMap)));
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Data Not Found.", null));
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
                return new ResponseDTO(false,"Please Give Correct OperationType",null);
            }
        }
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }

}
