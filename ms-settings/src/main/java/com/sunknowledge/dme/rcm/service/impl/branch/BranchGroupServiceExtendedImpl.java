package com.sunknowledge.dme.rcm.service.impl.branch;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.BranchGroup;
import com.sunknowledge.dme.rcm.exception.BranchNotFoundException;
import com.sunknowledge.dme.rcm.repository.branch.BranchGroupRepositoryExtended;
import com.sunknowledge.dme.rcm.service.branch.BranchGroupServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupRejectedDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.helper.branch.BranchGroupHelper;
import com.sunknowledge.dme.rcm.service.mapper.BranchGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class BranchGroupServiceExtendedImpl implements BranchGroupServiceExtended {

    private final Logger log = LoggerFactory.getLogger(BranchGroupServiceExtendedImpl.class);

    @Autowired
    BranchGroupHelper branchGroupHelper;
    @Autowired
    BranchGroupMapper branchGroupMapper;
    @Autowired
    BranchGroupRepositoryExtended branchGroupRepositoryExtended;
    @Override
    public BranchGroupDTO save(BranchGroupDTO branchGroupDTO) {
        return null;
    }
    @Override
    public BranchGroupDTO update(BranchGroupDTO branchGroupDTO) {
        return null;
    }
    @Override
    public Optional<BranchGroupDTO> partialUpdate(BranchGroupDTO branchGroupDTO) {
        return Optional.empty();
    }
    @Override
    public Page<BranchGroupDTO> findAll(Pageable pageable) {
        return null;
    }
    @Override
    public Optional<BranchGroupDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO bulkUploadForBranchGroup(MultipartFile documentFile) {
        Map<String,Object> branchGroupBothData;
        BranchGroupResponseDTO branchGroupResponseDTO = new BranchGroupResponseDTO();
        String message = "";
        try {
            branchGroupBothData = branchGroupHelper.csvToBranchGroupDTOMapper(documentFile.getInputStream());
            List<BranchGroupDTO> branchGroupDTOs = (List<BranchGroupDTO>) branchGroupBothData.get("branchGroupDTOS");
            branchGroupRepositoryExtended.saveAll(branchGroupMapper.toEntity(branchGroupDTOs));
            Integer successfullyUploaded = ((List<BranchGroupDTO>) branchGroupBothData.get("branchGroupDTOS")).size();
            Integer skipped = ((Map<String, BranchGroupRejectedDTO>) branchGroupBothData.get("SkippedBranchGroupDTO")).size();
            message = "Successfully Uploaded ("+successfullyUploaded+")";
            if(skipped > 0){
                message += " and Skipped " + skipped + " Rows";
            }
            return (new ResponseDTO(Boolean.TRUE,message,List.of(branchGroupBothData.get("SkippedBranchGroupDTO")),200));
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO saveBranchGroup(BranchGroupParameterDTO branchGroupParameterDTO) {
        Set uniqueBranchGroupNameSet = branchGroupRepositoryExtended.findAll().stream().map(x -> x.getBranchGroupName()).collect(Collectors.toSet());
        Set uniqueBranchGroupIdSet = branchGroupRepositoryExtended.findAll().stream().map(x -> x.getBranchGroupId()).collect(Collectors.toSet());
        String message = "";
        try {
            if (branchGroupParameterDTO.getBranchGroupName() == null) {
                message = ("Invalid Attribute (Branch_Group_Name)");
            } else if (branchGroupParameterDTO.getBranchGroupName().trim() == "") {
                message = ("Branch_Group_Name must be provided");
            } else if((branchGroupParameterDTO.getBranchGroupId() == null || branchGroupParameterDTO.getBranchGroupId() == 0) && uniqueBranchGroupNameSet.contains(branchGroupParameterDTO.getBranchGroupName())){
                message = "("+ branchGroupParameterDTO.getBranchGroupName() +") "+"Branch_Group_Name already exist";
            } else if (!uniqueBranchGroupIdSet.contains(branchGroupParameterDTO.getBranchGroupId()) &&
                uniqueBranchGroupNameSet.contains(branchGroupParameterDTO.getBranchGroupName())){
                message = ("Branch_Group_Name already exist");
            }
            if(!message.equalsIgnoreCase("")){
                return (new ResponseDTO(Boolean.FALSE,message,null,200));
            }
            BranchGroupDTO branchGroupDTO = (branchGroupParameterDTO.getBranchGroupId() == null ||
                branchGroupParameterDTO.getBranchGroupId() == 0) ? new BranchGroupDTO() :
                (branchGroupRepositoryExtended.findById(branchGroupParameterDTO.getBranchGroupId()).isPresent() ?
                    branchGroupMapper.toDto(branchGroupRepositoryExtended.findById(branchGroupParameterDTO.getBranchGroupId()).get()) :
                    new BranchGroupDTO());

            BeanUtils.copyProperties(branchGroupParameterDTO, branchGroupDTO);
            branchGroupDTO.setStatus("Active");
            if (branchGroupDTO.getBranchGroupId() == null || branchGroupDTO.getBranchGroupId() == 0) {
                branchGroupDTO.setBranchGroupId(null);
                branchGroupDTO.setCreatedDate(LocalDate.now());
                branchGroupDTO.setCreatedById(1L);
                branchGroupDTO.setCreatedByName("Abhijit");
                branchGroupDTO.setBranchGroupUuid(UUID.randomUUID());
            } else {
                branchGroupDTO.setUpdatedDate(LocalDate.now());
                branchGroupDTO.setUpdatedById(1L);
                branchGroupDTO.setUpdatedByName("Abhijit");
            }
            CommonUtilities.dtoTrimmer(branchGroupDTO);
            BranchGroupDTO savedBranchGroupDTO = branchGroupMapper.toDto(
                branchGroupRepositoryExtended.save(branchGroupMapper.toEntity(branchGroupDTO))
            );

            return (new ResponseDTO(Boolean.TRUE,"Successfully Saved",savedBranchGroupDTO,200));
        } catch (BranchNotFoundException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }catch (InputMismatchException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BranchGroupDTO getBranchGroupById(Long branchGroupId) {
        BranchGroup data = branchGroupRepositoryExtended.findByBranchGroupId(branchGroupId);
        if(data != null){
            return branchGroupMapper.toDto(data);
        }
        return null;
    }

    @Override
    public List<BranchGroupDTO> getBranchGroupByBranchGroupName(String branchGroupName) {
        List<BranchGroup> data = branchGroupRepositoryExtended.findByBranchGroupNameLikeIgnoreCaseAndStatusIgnoreCase("%"+branchGroupName+"%", "active");
        return branchGroupMapper.toDto(data);
    }

    @Override
    public List<BranchGroupDTO> getBranchGroupByCompanyId(Long companyId) {
        List<BranchGroup> data = branchGroupRepositoryExtended.findByCompanyIdAndStatusIgnoreCase(companyId, "active");
        return branchGroupMapper.toDto(data);
    }

    @Override
    public List<BranchGroupExtendedDTO> getAllBranchGroupData() {
        List<BranchGroupExtendedDTO> branchGroupList = branchGroupRepositoryExtended.findByStatusIgnoreCase("active");
        return branchGroupList;
    }

    @Override
    public List<BranchGroupExtendedDTO> getBranchGroupByStatus(String status) {
        List<BranchGroupExtendedDTO> branchGroupList = branchGroupRepositoryExtended.findByStatusIgnoreCase(status);
        return branchGroupList;
    }

    @Override
    public List<BranchGroupDTO> getActiveBranchGroupById(Long branchGroupId) {
        List<BranchGroupDTO> dtoDataList = new ArrayList<BranchGroupDTO>();
        BranchGroup data = branchGroupRepositoryExtended.findByBranchGroupIdAndStatusIgnoreCase(branchGroupId,"active");
        if(data != null){
            dtoDataList.add(branchGroupMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public ResponseDTO setBranchGroupStatusByUUID(UUID uuid,String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<BranchGroup> obj = branchGroupRepositoryExtended.findByBranchGroupUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    branchGroupRepositoryExtended.save(obj.get());
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

    public List<Map<String, Object>> getBranchGroupForDropdown() {
        List<BranchGroupExtendedDTO> posMasterList = getAllBranchGroupData();
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getBranchGroupId());
                map.put("title", p.getBranchGroupName());
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }
}
