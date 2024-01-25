package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.repository.others.BranchUserMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.*;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapUpdateDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchUserMapMapper;
import com.sunknowledge.dme.rcm.service.others.BranchUserMapServiceExtended;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class BranchUserMapServiceExtendedImpl implements BranchUserMapServiceExtended {

    private final Logger log = LoggerFactory.getLogger(BranchUserMapServiceExtendedImpl.class);
    @Autowired
    BranchUserMapRepositoryExtended branchUserMapRepositoryExtended;
    @Autowired
    BranchUserMapMapper branchUserMapMapper;
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Autowired
    UserMasterServiceExtended userMasterServiceExtended;

    @Override
    public BranchUserMapDTO save(BranchUserMapDTO branchUserMapDTO) {
        return null;
    }

    @Override
    public BranchUserMapDTO update(BranchUserMapDTO branchUserMapDTO) {
        return null;
    }

    @Override
    public Optional<BranchUserMapDTO> partialUpdate(BranchUserMapDTO branchUserMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchUserMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchUserMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveBranchUserMap(BranchUserMapParameterDTO branchUserMapParameterDTO) {
        try {
            if (branchUserMapParameterDTO.getBranchId() == null || branchUserMapParameterDTO.getBranchId() == 0) {
                throw new InvalidAttributeValueException("Invalid Attribute (Branch_Id)");
            } else if (branchUserMapParameterDTO.getUserIdList() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (User_Id)");
            } else if (branchUserMapParameterDTO.getUserIdList().size() == 0) {
                throw new InputMismatchException("User_Ids must be provided");
            }
            Set<Long> userIdSet = branchUserMapParameterDTO.getUserIdList().stream().filter(x -> x != 0 && x != null).collect(Collectors.toSet());
            List<Long> userIdList = userIdSet.stream().collect(Collectors.toList());
            List<UserMasterDTO> userMasterDTOS = userMasterServiceExtended.findByUserIdIn(userIdList);
            BranchOfficeDTO branchOfficeDTO = branchOfficeServiceExtended.findByBranchId(branchUserMapParameterDTO.getBranchId());
            Set uniqueItemBranchId_UserIdSet = branchUserMapRepositoryExtended.findByStatusIgnoreCase("active").stream().map(x -> x.getBranchId() + "_" + x.getUserId()).collect(Collectors.toSet());
            List<BranchUserMapDTO> branchUserMapDTOS = new ArrayList<BranchUserMapDTO>();
            Boolean isDataSaved = false;
            for (UserMasterDTO data : userMasterDTOS) {
                if (!uniqueItemBranchId_UserIdSet.contains(branchOfficeDTO.getBranchId() + "_" + data.getUserId())) {
                    BranchUserMapDTO branchUserMapDTO = new BranchUserMapDTO();
                    branchUserMapDTO.setBranchId(branchOfficeDTO.getBranchId());
                    branchUserMapDTO.setUserId(Long.valueOf(data.getUserId()));
                    branchUserMapDTO.setDescription(""); // need to check
                    branchUserMapDTO.setStatus("active");
                    branchUserMapDTO.setCreatedDate(LocalDate.now());
                    branchUserMapDTO.setCreatedById(3L);
                    branchUserMapDTO.setCreatedByName("Ritam");
                    branchUserMapDTO.setUpdatedByName(null);
                    branchUserMapDTO.setUpdatedById(null);

                    branchUserMapDTOS.add(branchUserMapDTO);
                    isDataSaved = true;
                }
            }
            branchUserMapRepositoryExtended.saveAll(branchUserMapMapper.toEntity(branchUserMapDTOS));
            return (new ResponseDTO(isDataSaved, isDataSaved ? "Successfully Saved" : "Data already exist.", branchUserMapDTOS,200));
        } catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO updateBranchUserMap(BranchUserMapUpdateDTO branchUserMapUpdateDTO) {
        try {
            if (branchUserMapUpdateDTO.getBranchId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Branch_Id)");
            } else if (branchUserMapUpdateDTO.getUserId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (User_Id)");
            }

            BranchUserMap updateDTO = branchUserMapRepositoryExtended.
                findByBranchIdAndUserIdAndStatusIgnoreCase(branchUserMapUpdateDTO.getBranchId(), branchUserMapUpdateDTO.getUserId(), "active");
            if (updateDTO != null) {
                updateDTO.setStatus("inactive");
                updateDTO.updatedById(1L);
                updateDTO.updatedByName("Abhijit Update");
                updateDTO.updatedDate(LocalDate.now());
                branchUserMapRepositoryExtended.save(updateDTO);

                BranchUserMapDTO branchUserMapDTO = new BranchUserMapDTO();

                branchUserMapDTO.setBranchId(branchUserMapUpdateDTO.getBranchId());
                branchUserMapDTO.setUserId(branchUserMapUpdateDTO.getUserId());
                branchUserMapDTO.setStatus("active");
                if (branchUserMapDTO.getMapId() == null || branchUserMapDTO.getMapId() == 0) {
                    branchUserMapDTO.setMapId(null);
                    branchUserMapDTO.setCreatedDate(LocalDate.now());
                    branchUserMapDTO.setCreatedById(1L);
                    branchUserMapDTO.setCreatedByName("Abhijit");
                    branchUserMapDTO.setBranchUserMapUuid(UUID.randomUUID());
                } else {
                    branchUserMapDTO.setUpdatedDate(LocalDate.now());
                    branchUserMapDTO.setUpdatedById(1L);
                    branchUserMapDTO.setUpdatedByName("Abhijit");
                }
                BranchUserMapDTO savedBranchUserMapDTO = branchUserMapMapper.toDto(
                    branchUserMapRepositoryExtended.save(branchUserMapMapper.toEntity(branchUserMapDTO))
                );
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", (savedBranchUserMapDTO),200));
            } else {
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save data", new ArrayList<>(),200));
            }
        } catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO getBranchByUser(Long userId) {
        if (userId != null) {
            Object getBranchInfo = branchUserMapRepositoryExtended.findBranchByUserIdAndStatus(userId, "active");
            if (getBranchInfo != null && getBranchInfo instanceof Object[]) {
                Object[] row = (Object[]) getBranchInfo;
                Long branchId = ((BigInteger) row[0]).longValue();
                String branchName = (String) row[1];

                BranchUserOutputExtendedDTO branchUserOutputDTO = new BranchUserOutputExtendedDTO(branchId, branchName);
                System.out.println("Branch ID: " + branchUserOutputDTO.getBranchId() + ", Branch Name: " + branchUserOutputDTO.getBranchName());
                return (new ResponseDTO(Boolean.TRUE, "", branchUserOutputDTO,200));
            } else {
                return (new ResponseDTO(Boolean.FALSE, "Data Not Found.", null,200));
            }
        } else {
            return (new ResponseDTO(Boolean.FALSE, "Data Not Found data.", null,200));
        }
    }

    @Override
    public ResponseDTO setBranchUserMapStatusByUuid(UUID uuid, String status) {
        if (status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try {
                Optional<BranchUserMap> branchUserMap = branchUserMapRepositoryExtended.findByBranchUserMapUuid(uuid);
                if (branchUserMap.isPresent()) {
                    branchUserMap.get().setStatus(status);
                    branchUserMap.get().setUpdatedById(1l);
                    branchUserMap.get().setUpdatedByName("Updated Test");
                    branchUserMap.get().setUpdatedDate(LocalDate.now());
                    branchUserMapRepositoryExtended.save(branchUserMap.get());
                    return (new ResponseDTO(Boolean.TRUE, "", branchUserMap.get(),200));
                } else {
                    return (new ResponseDTO(Boolean.FALSE, "Data Not Found", null,200));
                }
            } catch (Exception e) {
                log.error("=====>> Error : " + e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error", null,200));
            }
        } else {
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", null,200));
        }
    }

    @Override
    public ResponseDTO getAllBranchUserMapData() {
        List<BranchUserMapExtendedDTO> branchUserMaps = branchUserMapRepositoryExtended.getByStatus("active");
        return new ResponseDTO(branchUserMaps.size() > 0 ? true : false, branchUserMaps.size() > 0 ? "" : "Data Not Found",
            branchUserMaps,200);
    }

    @Override
    public ResponseDTO getBranchUserMapById(Long id) {
        Optional<BranchUserMap> branchUserMap = branchUserMapRepositoryExtended.findByMapIdAndStatusIgnoreCase(id, "active");

        return new ResponseDTO(branchUserMap.isPresent() ? true : false,
            branchUserMap.isPresent() ? "" : "Data Not Found",
            branchUserMap.isPresent() ? branchUserMap.get() : null,200);

    }

    @Override
    public ResponseDTO getUserByBranch(Long branchId) {
        List<Map> branchUserMaps = branchUserMapRepositoryExtended.findByStatusIgnoreCaseAndBranchId("active", branchId);
        return new ResponseDTO(branchUserMaps.size() > 0 ? true : false, branchUserMaps.size() > 0 ? "" : "Data Not Found",
            branchUserMaps,200);

    }
}
