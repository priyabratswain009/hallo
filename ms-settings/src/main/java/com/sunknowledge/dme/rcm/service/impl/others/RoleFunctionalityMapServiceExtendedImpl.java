package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap;
import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.repository.others.RoleFunctionalityMapRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.others.RoleMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapUpdateParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleFunctionalityMapMapper;
import com.sunknowledge.dme.rcm.service.others.FunctionalityMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.others.RoleFunctionalityMapServiceExtended;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
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
public class RoleFunctionalityMapServiceExtendedImpl implements RoleFunctionalityMapServiceExtended {

    @Autowired
    FunctionalityMasterServiceExtended functionalityMasterServiceExtended;
    @Autowired
    RoleMasterServiceExtended roleMasterServiceExtended;
    @Autowired
    RoleFunctionalityMapRepositoryExtended roleFunctionalityMapRepositoryExtended;
    @Autowired
    RoleFunctionalityMapMapper roleFunctionalityMapMapper;

    @Override
    public RoleFunctionalityMapDTO save(RoleFunctionalityMapDTO roleFunctionalityMapDTO) {
        return null;
    }

    @Override
    public RoleFunctionalityMapDTO update(RoleFunctionalityMapDTO roleFunctionalityMapDTO) {
        return null;
    }

    @Override
    public Optional<RoleFunctionalityMapDTO> partialUpdate(RoleFunctionalityMapDTO roleFunctionalityMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<RoleFunctionalityMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RoleFunctionalityMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveRoleFunctionalityMap(RoleFunctionalityMapParameterDTO roleFunctionalityMapParameterDTO) {
        List<Long> roleId = roleMasterServiceExtended.getActiveIDByUUID(List.of(roleFunctionalityMapParameterDTO.getRoleUUID()));
        List<Long> functionalityIdList = functionalityMasterServiceExtended.getActiveIDsByUUIDs(roleFunctionalityMapParameterDTO.getFunctionalityUUIDs());
        System.out.println("functionalityIdList > "+functionalityIdList);
        if(roleId.size()>0) {
            List<String> uniqueRoleFuncList = roleFunctionalityMapRepositoryExtended.findByRoleIdAndStatusIgnoreCase(roleId.get(0),"active")
                .stream().map(x->x.getRoleId()+"_"+x.getFunctionalityId()).collect(Collectors.toList());
            List<RoleFunctionalityMapDTO> roleFunctionalityMapDTOS = new ArrayList<>();
            for (Long funcId : functionalityIdList) {
                if(!uniqueRoleFuncList.contains(roleId.get(0)+"_"+funcId)){
                    RoleFunctionalityMapDTO functionalityMapDTO = new RoleFunctionalityMapDTO();
                    functionalityMapDTO.setFunctionalityId(funcId);
                    functionalityMapDTO.setRoleFunctionalityMapUuid(UUID.randomUUID());
                    functionalityMapDTO.setRoleId(roleId.get(0));
                    functionalityMapDTO.setStatus("Active");
                    functionalityMapDTO.setDescription(roleFunctionalityMapParameterDTO.getDescription());

                    functionalityMapDTO.setCreatedById(1L);
                    functionalityMapDTO.setCreatedByName("Abhay Api");
                    functionalityMapDTO.setCreatedDate(LocalDate.now());
                    roleFunctionalityMapDTOS.add(functionalityMapDTO);
                }
            }
            if(roleFunctionalityMapDTOS.size()>0){
                List<RoleFunctionalityMap> savedRoleFunctionalityMap = roleFunctionalityMapRepositoryExtended.saveAll(roleFunctionalityMapMapper.toEntity(roleFunctionalityMapDTOS));
                return new ResponseDTO(true,"Data Saved Successfully.",roleFunctionalityMapMapper.toDto(savedRoleFunctionalityMap));
            }else{
                return new ResponseDTO(false,"Data Not Found/Mapping Already Exist.",null);
            }
        }else{
            return new ResponseDTO(false,"Data Not Found.",null);
        }
    }

    @Override
    public ResponseDTO updateStatusRoleFunctionalityMap(RoleFunctionalityMapUpdateParameterDTO roleFunctionalityMapUpdateParameterDTO) {
        Long funcId = functionalityMasterServiceExtended.getIDByUUID(roleFunctionalityMapUpdateParameterDTO.getFunctionalityUUID());
        Long roleId = roleMasterServiceExtended.getIDByUUID(roleFunctionalityMapUpdateParameterDTO.getRoleUUID());
        RoleFunctionalityMap updateDTO = roleFunctionalityMapRepositoryExtended.
            findByRoleIdAndFunctionalityId(roleId, funcId);
        if (updateDTO != null) {
            updateDTO.setStatus(roleFunctionalityMapUpdateParameterDTO.getStatus());
            updateDTO.setUpdatedById(1L);
            updateDTO.setUpdatedByName("Abhijit Update");
            updateDTO.setUpdatedDate(LocalDate.now());
            RoleFunctionalityMapDTO roleFunctionalityMapDTO = roleFunctionalityMapMapper.toDto(roleFunctionalityMapRepositoryExtended.save(updateDTO));
            return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(roleFunctionalityMapDTO)));
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Data Not Found.", null));
        }
    }

    @Override
    public ResponseDTO getRoleFunctionalityMapByNameOrNoOrUUID(String data, String operationType) {
        List<Map> obj = new ArrayList<>();
        switch (operationType){
            case "roleNo":{
                obj = roleFunctionalityMapRepositoryExtended.getRoleFunctionalityDetailsByRoleNo(data);
                break;
            }
            case "functionalityNo":{
                obj = roleFunctionalityMapRepositoryExtended.getRoleFunctionalityDetailsByFunctionalityNo(data);
                break;
            }
            case "roleName":{
                obj = roleFunctionalityMapRepositoryExtended.getRoleFunctionalityDetailsByRoleName(data);
                break;
            }
            case "functionalityName":{
                obj = roleFunctionalityMapRepositoryExtended.getRoleFunctionalityDetailsByFunctionalityName(data);
                break;
            }
            case "roleUUID":{
                obj = roleFunctionalityMapRepositoryExtended.getRoleFunctionalityDetailsByRoleUUID(UUID.fromString(data));
                break;
            }
            case "functionalityUUID":{
                obj = roleFunctionalityMapRepositoryExtended.getRoleFunctionalityDetailsByFunctionalityUUID(UUID.fromString(data));
                break;
            }
            default:{
                return new ResponseDTO(false,"Please Give Correct OperationType",null);
            }
        }
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));
    }
}
