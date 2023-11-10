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
            throw new InvalidAttributeValueException("Invalid Attribute (ClaimFormName)");
        } else if (claimFormMasterExtendedDTO.getClaimFormName().trim().equals("")) {
            throw new InputMismatchException("ClaimFormName must be provided");
        }else if(claimFormMasterExtendedDTO.getClaimFormMasterUuid()==null && uniqueClaimFormNameSet.contains(claimFormMasterExtendedDTO.getClaimFormName())){
            //Duplicate check during save operation
            throw new InputMismatchException("("+ claimFormMasterExtendedDTO.getClaimFormName() +") "+"claimFormName already exist");
        }else if(claimFormMasterExtendedDTO.getClaimFormMasterUuid() != null && !claimFormMasterExtendedDTO.getClaimFormMasterUuid().equals("")){
            //Duplicate check during update operation
            Set uniqueClaimProgramValueSetForUpdate = claimFormMasterRepositoryExtended.findByClaimFormMasterUuidNot(claimFormMasterExtendedDTO.getClaimFormMasterUuid()).stream().map(x -> x.getClaimFormName()).collect(Collectors.toSet());
            if(uniqueClaimProgramValueSetForUpdate.contains(claimFormMasterExtendedDTO.getClaimFormName())){
                throw new InputMismatchException("("+ claimFormMasterExtendedDTO.getClaimFormName() +") "+"claimFormName already exist");
            }
        }

        ResponseDTO outcome = new ResponseDTO();
        if(claimFormMasterExtendedDTO.getStatus().equalsIgnoreCase("active") || claimFormMasterExtendedDTO.getStatus().equalsIgnoreCase("inActive")){

            ClaimFormMasterDTO claimFormMasterDTO = (claimFormMasterExtendedDTO.getClaimFormMasterUuid() == null) ? new ClaimFormMasterDTO() :
                (claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(claimFormMasterExtendedDTO.getClaimFormMasterUuid()) != null ?
                    claimFormMasterMapper.toDto(claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(claimFormMasterExtendedDTO.getClaimFormMasterUuid())) :
                    new ClaimFormMasterDTO());

            BeanUtils.copyProperties(claimFormMasterExtendedDTO, claimFormMasterDTO);

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

            return new ResponseDTO(true, "Successfully Saved.", List.of(savedClaimFormMasterDTO));
        }else{
            throw new InputMismatchException("Status Should be active/inactive");
        }
    }

    @Override
    public List<ClaimFormMasterDTO> getAllClaimFormMasterInfo() {
        List<ClaimFormMaster> data = claimFormMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return claimFormMasterMapper.toDto(data);
    }

    @Override
    public List<ClaimFormMasterDTO> getClaimFormMasterInfoByUUID(UUID claimFormMasterUuid) {
        List<ClaimFormMasterDTO> dtoDataList = new ArrayList<ClaimFormMasterDTO>();
        ClaimFormMaster data = claimFormMasterRepositoryExtended.findByClaimFormMasterUuid(claimFormMasterUuid);
        if(data != null){
            dtoDataList.add(claimFormMasterMapper.toDto(data));
        }
        return dtoDataList;
    }
}
