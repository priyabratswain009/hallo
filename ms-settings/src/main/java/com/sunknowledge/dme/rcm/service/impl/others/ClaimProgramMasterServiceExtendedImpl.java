package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMaster;
import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.repository.others.ClaimProgramMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ClaimProgramMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimProgramMasterMapper;
import com.sunknowledge.dme.rcm.service.others.ClaimProgramMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
public class ClaimProgramMasterServiceExtendedImpl implements ClaimProgramMasterServiceExtended {

    private final Logger log = LoggerFactory.getLogger(ClaimProgramMasterServiceExtendedImpl.class);
    @Autowired
    ClaimProgramMasterRepositoryExtended claimProgramMasterRepositoryExtended;

    @Autowired
    ClaimProgramMasterMapper claimProgramMasterMapper;

    @Override
    public ClaimProgramMasterDTO save(ClaimProgramMasterDTO claimProgramMasterDTO) {
        return null;
    }

    @Override
    public ClaimProgramMasterDTO update(ClaimProgramMasterDTO claimProgramMasterDTO) {
        return null;
    }

    @Override
    public Optional<ClaimProgramMasterDTO> partialUpdate(ClaimProgramMasterDTO claimProgramMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ClaimProgramMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ClaimProgramMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveClaimProgramMaster(ClaimProgramMasterExtendedDTO claimProgramMasterExtendedDTO) throws InvalidAttributeValueException {
        Set uniqueClaimProgramValueSet = claimProgramMasterRepositoryExtended.findAll().stream().map(x -> x.getClaimProgramMasterValue()).collect(Collectors.toSet());
        String message = "";
        if (claimProgramMasterExtendedDTO.getClaimProgramMasterValue() == null) {
            message = ("Invalid Attribute (claimProgramMasterValue)");
        } else if (claimProgramMasterExtendedDTO.getClaimProgramMasterValue().trim().equals("")) {
            message = ("claimProgramMasterValue must be provided");
        }else if(claimProgramMasterExtendedDTO.getClaimProgramMasterUuid()==null && uniqueClaimProgramValueSet.contains(claimProgramMasterExtendedDTO.getClaimProgramMasterValue())){
            //Duplicate check during save operation
            message = ("("+ claimProgramMasterExtendedDTO.getClaimProgramMasterValue() +") "+"claimProgramMasterValue already exist");
        }else if(claimProgramMasterExtendedDTO.getClaimProgramMasterUuid() != null && !claimProgramMasterExtendedDTO.getClaimProgramMasterUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueClaimProgramValueSetForUpdate = claimProgramMasterRepositoryExtended.findByClaimProgramMasterUuidNot(claimProgramMasterExtendedDTO.getClaimProgramMasterUuid()).stream().map(x -> x.getClaimProgramMasterValue()).collect(Collectors.toSet());
            if(uniqueClaimProgramValueSetForUpdate.contains(claimProgramMasterExtendedDTO.getClaimProgramMasterValue())){
                message = ("("+ claimProgramMasterExtendedDTO.getClaimProgramMasterValue() +") "+"claimProgramMasterValue already exist");
            }
        }
        if(!message.equalsIgnoreCase("")){
            return new ResponseDTO(false, message, null,200);
        }
        ResponseDTO outcome = new ResponseDTO();
        ClaimProgramMasterDTO claimProgramMasterDTO = (claimProgramMasterExtendedDTO.getClaimProgramMasterUuid() == null) ? new ClaimProgramMasterDTO() :
            (claimProgramMasterRepositoryExtended.findByClaimProgramMasterUuid(claimProgramMasterExtendedDTO.getClaimProgramMasterUuid()) != null ?
                claimProgramMasterMapper.toDto(claimProgramMasterRepositoryExtended.findByClaimProgramMasterUuid(claimProgramMasterExtendedDTO.getClaimProgramMasterUuid())) :
                new ClaimProgramMasterDTO());

        BeanUtils.copyProperties(claimProgramMasterExtendedDTO, claimProgramMasterDTO);
        claimProgramMasterDTO.setStatus("active");
        if (claimProgramMasterDTO.getClaimProgramMasterUuid() == null) {
            claimProgramMasterDTO.setClaimProgramMasterId(null);
            //holdReasonMasterDTO.setHoldReasonName();
            claimProgramMasterDTO.setCreatedById(31L);
            claimProgramMasterDTO.setCreatedDate(LocalDate.now());
            claimProgramMasterDTO.setCreatedByName("Falguni");
            claimProgramMasterDTO.setClaimProgramMasterUuid(UUID.randomUUID());
        } else {
            claimProgramMasterDTO.setUpdatedDate(LocalDate.now());
            claimProgramMasterDTO.setUpdatedById(31L);
            claimProgramMasterDTO.setUpdatedByName("Falguni");
        }
        ClaimProgramMasterDTO savedClaimProgramMasterDTO = claimProgramMasterMapper.toDto(
            claimProgramMasterRepositoryExtended.save(claimProgramMasterMapper.toEntity(claimProgramMasterDTO))
        );
        log.info("=======savedClaimProgramMasterDTO======="+savedClaimProgramMasterDTO);

        return new ResponseDTO(true, "Successfully Saved.", (savedClaimProgramMasterDTO),200);
    }

    @Override
    public List<ClaimProgramMasterDTO> getAllClaimProgramMasterInfo() {
        List<ClaimProgramMaster> data = claimProgramMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return claimProgramMasterMapper.toDto(data);
    }

    @Override
    public ClaimProgramMasterDTO getClaimProgramMasterInfoByUUID(UUID holdReasonMasterUuid) {
        ClaimProgramMaster data = claimProgramMasterRepositoryExtended.findByClaimProgramMasterUuid(holdReasonMasterUuid);
        if(data != null){
            return (claimProgramMasterMapper.toDto(data));
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getClaimProgramMasterForDropdown() {
        List<ClaimProgramMasterDTO> posMasterList = getAllClaimProgramMasterInfo();
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getClaimProgramMasterId());
                map.put("title", p.getClaimProgramMasterValue());
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public ResponseDTO setClaimProgramMasterByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<ClaimProgramMaster> obj = Optional.ofNullable(claimProgramMasterRepositoryExtended.findByClaimProgramMasterUuid(uuid));
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    claimProgramMasterRepositoryExtended.save(obj.get());
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
