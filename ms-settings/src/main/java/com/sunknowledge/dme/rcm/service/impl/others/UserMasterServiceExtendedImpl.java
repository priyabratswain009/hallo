package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.domain.UserMaster;
import com.sunknowledge.dme.rcm.repository.others.UserMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UserMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.UserMasterMapper;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
public class UserMasterServiceExtendedImpl implements UserMasterServiceExtended {

    private final Logger log = LoggerFactory.getLogger(UserMasterServiceExtendedImpl.class);
    @Autowired
    UserMasterRepositoryExtended userMasterRepositoryExtended;
    @Autowired
    UserMasterMapper userMasterMapper;

    @Override
    public UserMasterDTO save(UserMasterDTO userMasterDTO) {
        return null;
    }

    @Override
    public UserMasterDTO update(UserMasterDTO userMasterDTO) {
        return null;
    }

    @Override
    public Optional<UserMasterDTO> partialUpdate(UserMasterDTO userMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<UserMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UserMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveUserMaster(UserMasterParameterDTO userMasterParameterDTO) {
        //Set uniqueItemLocationNameSet = userMasterRepositoryExtended.findAll().stream().map(x -> x.get()).collect(Collectors.toSet());
        try {
            if (userMasterParameterDTO.getFirstName() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (First_Name)");
            } else if (userMasterParameterDTO.getFirstName().trim() == "") {
                throw new InputMismatchException("Item_Location_Name must be provided");
            }

            UserMasterDTO userMasterDTO = (userMasterParameterDTO.getUserId() == null ||
                userMasterParameterDTO.getUserId() == 0) ? new UserMasterDTO() :
                (userMasterRepositoryExtended.findById(userMasterParameterDTO.getUserId()).isPresent() ?
                    userMasterMapper.toDto(userMasterRepositoryExtended.findById(userMasterParameterDTO.getUserId()).get()) :
                    new UserMasterDTO());

            BeanUtils.copyProperties(userMasterParameterDTO, userMasterDTO);
            if (userMasterDTO.getUserId() == null || userMasterDTO.getUserId() == 0) {
                userMasterDTO.setUserId(null);
                userMasterDTO.setCreatedDate(LocalDate.now());
                userMasterDTO.setCreatedById(1L);
                userMasterDTO.setCreatedByName("Abhijit");
                userMasterDTO.setUserMasterUuid(UUID.randomUUID());
            } else {
                userMasterDTO.setUpdatedDate(LocalDate.now());
                userMasterDTO.setUpdatedById(1L);
                userMasterDTO.setUpdatedByName("Abhijit");
            }
            UserMasterDTO savedUserMasterDTO = userMasterMapper.toDto(
                userMasterRepositoryExtended.save(userMasterMapper.toEntity(userMasterDTO))
            );
            return new ResponseDTO(true, "Successfully Saved.", List.of(savedUserMasterDTO));
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserMasterDTO> findByUserIdIn(List<Long> userIdList) {
        List<UserMaster> data = userMasterRepositoryExtended.findByUserIdInAndStatusIgnoreCase(userIdList, "active");
        return userMasterMapper.toDto(data);
    }

    @Override
    public UserMasterDTO getDataByActiveId(Long userId) {
        return userMasterMapper.toDto(userMasterRepositoryExtended.findByUserIdAndStatusIgnoreCase(userId,"active"));
    }

    @Override
    public Long getIDByUUID(UUID roleUUID) {
        return userMasterRepositoryExtended.getIDByUUID(roleUUID);
    }
}
