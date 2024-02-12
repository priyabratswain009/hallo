package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.ClaimFormMaster;
import com.sunknowledge.dme.rcm.repository.others.ClaimFormMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ClaimFormMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimFormMasterMapper;
import com.sunknowledge.dme.rcm.service.others.ClaimFormMasterServiceExtended;
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
public class ClaimFormMasterServiceExtendedImpl implements ClaimFormMasterServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ClaimFormMasterServiceExtendedImpl.class);
    @Autowired
    ClaimFormMasterRepositoryExtended claimFormMasterRepositoryExtended;

    @Autowired
    ClaimFormMasterMapper claimFormMasterMapper;

    @Override
    public ClaimFormMasterDTO save(ClaimFormMasterDTO claimFormMasterDTO) {
        return null;
    }

    @Override
    public ClaimFormMasterDTO update(ClaimFormMasterDTO claimFormMasterDTO) {
        return null;
    }

    @Override
    public Optional<ClaimFormMasterDTO> partialUpdate(ClaimFormMasterDTO claimFormMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ClaimFormMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ClaimFormMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveClaimFormMaster(ClaimFormMasterExtendedDTO claimFormMasterExtendedDTO) throws InvalidAttributeValueException {
        Set uniqueClaimFormNameSet = claimFormMasterRepositoryExtended.findAll().stream().map(x -> x.getClaimFormName()).collect(Collectors.toSet());

        if (claimFormMasterExtendedDTO.getClaimFormName() == null) {
            return new ResponseDTO(false, "Invalid Attribute (ClaimFormName)", null,200);
        } else if (claimFormMasterExtendedDTO.getClaimFormName().trim().equals("")) {
            return new ResponseDTO(false, "ClaimFormName must be provided", null,200);
        }else if(claimFormMasterExtendedDTO.getClaimFormMasterUuid()==null && uniqueClaimFormNameSet.contains(claimFormMasterExtendedDTO.getClaimFormName())){
            //Duplicate check during save operation
            return new ResponseDTO(false, "("+ claimFormMasterExtendedDTO.getClaimFormName() +") "+"claimFormName already exist", null,200);
        }else if(claimFormMasterExtendedDTO.getClaimFormMasterUuid() != null && !claimFormMasterExtendedDTO.getClaimFormMasterUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueClaimProgramValueSetForUpdate = claimFormMasterRepositoryExtended.findByClaimFormMasterUuidNot(claimFormMasterExtendedDTO.getClaimFormMasterUuid()).stream().map(x -> x.getClaimFormName()).collect(Collectors.toSet());
            if(uniqueClaimProgramValueSetForUpdate.contains(claimFormMasterExtendedDTO.getClaimFormName())){
                return new ResponseDTO(false, "("+ claimFormMasterExtendedDTO.getClaimFormName() +") "+"claimFormName already exist", null,200);
            }
        }
        ClaimFormMasterDTO claimFormMasterDTO = (claimFormMasterExtendedDTO.getClaimFormMasterUuid() == null) ? new ClaimFormMasterDTO() :
            (claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(claimFormMasterExtendedDTO.getClaimFormMasterUuid()) != null ?
                claimFormMasterMapper.toDto(claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(claimFormMasterExtendedDTO.getClaimFormMasterUuid())) :
                new ClaimFormMasterDTO());

        BeanUtils.copyProperties(claimFormMasterExtendedDTO, claimFormMasterDTO);
        claimFormMasterDTO.setStatus("active");
        if (claimFormMasterDTO.getClaimFormMasterUuid() == null) {
            claimFormMasterDTO.setClaimFormId(null);
            //holdReasonMasterDTO.setHoldReasonName();
            claimFormMasterDTO.setCreatedById(31L);
            claimFormMasterDTO.setCreatedDate(LocalDate.now());
            claimFormMasterDTO.setCreatedByName("Falguni");
            claimFormMasterDTO.setClaimFormMasterUuid(UUID.randomUUID());
        } else {
            claimFormMasterDTO.setUpdatedDate(LocalDate.now());
            claimFormMasterDTO.setUpdatedById(31L);
            claimFormMasterDTO.setUpdatedByName("Falguni");
        }
        ClaimFormMasterDTO savedClaimFormMasterDTO = claimFormMasterMapper.toDto(
            claimFormMasterRepositoryExtended.save(claimFormMasterMapper.toEntity(claimFormMasterDTO))
        );
        log.info("=======savedClaimFormMasterDTO======="+savedClaimFormMasterDTO);

        return new ResponseDTO(true, "Successfully Saved.", (savedClaimFormMasterDTO),200);
    }

    @Override
    public List<ClaimFormMasterDTO> getAllClaimFormMasterInfo() {
        List<ClaimFormMaster> data = claimFormMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return claimFormMasterMapper.toDto(data);
    }

    @Override
    public ClaimFormMasterDTO getClaimFormMasterInfoByUUID(UUID claimFormMasterUuid) {
        ClaimFormMaster data = claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(claimFormMasterUuid);
        if(data != null){
            return (claimFormMasterMapper.toDto(data));
        }
        return null;
    }

    @Override
    public ResponseDTO setClaimFormMasterStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<ClaimFormMaster> obj = Optional.ofNullable(claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(uuid));
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    claimFormMasterRepositoryExtended.save(obj.get());
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
