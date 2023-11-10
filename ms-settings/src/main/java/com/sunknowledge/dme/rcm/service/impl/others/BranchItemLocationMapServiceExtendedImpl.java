package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMap;
import com.sunknowledge.dme.rcm.repository.others.BranchItemLocationMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedUpdateDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchItemLocationMapMapper;
import com.sunknowledge.dme.rcm.service.others.BranchItemLocationMapServiceExtended;
import com.sunknowledge.dme.rcm.service.others.ItemLocationServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class BranchItemLocationMapServiceExtendedImpl implements BranchItemLocationMapServiceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchItemLocationMapServiceExtendedImpl.class);
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Autowired
    ItemLocationServiceExtended itemLocationServiceExtended;
    @Autowired
    BranchItemLocationMapRepositoryExtended branchItemLocationMapRepositoryExtended;
    @Autowired
    BranchItemLocationMapMapper branchItemLocationMapMapper;

    @Override
    public BranchItemLocationMapDTO save(BranchItemLocationMapDTO branchItemLocationMapDTO) {
        return null;
    }

    @Override
    public BranchItemLocationMapDTO update(BranchItemLocationMapDTO branchItemLocationMapDTO) {
        return null;
    }

    @Override
    public Optional<BranchItemLocationMapDTO> partialUpdate(BranchItemLocationMapDTO branchItemLocationMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchItemLocationMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchItemLocationMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveBranchItemLocationMap(BranchItemLocationMapExtendedDTO branchItemLocationMapExtendedDTO) {
        try {
            if (branchItemLocationMapExtendedDTO.getItemLocationId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (ItemLocation_Id)");
            }else if (branchItemLocationMapExtendedDTO.getBranchIdList() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Branch_Id)");
            } else if (branchItemLocationMapExtendedDTO.getBranchIdList().size() == 0) {
                throw new InputMismatchException("Branch_Ids must be provided");
            }
            Set<Long> branchIdSet = branchItemLocationMapExtendedDTO.getBranchIdList().stream().filter(x->x!=0 && x!=null).collect(Collectors.toSet());
            List<Long> branchIdList = branchIdSet.stream().collect(Collectors.toList());
            List<BranchOfficeDTO> branchOfficeDTOS = branchOfficeServiceExtended.findByBranchIdIn(branchIdList);
            Optional<ItemLocationDTO> itemLocationDTO = itemLocationServiceExtended.findByItemLocationIdIn(branchItemLocationMapExtendedDTO.getItemLocationId());
            Set uniqueItemLocationId_BranchIdSet = branchItemLocationMapRepositoryExtended.findAll().stream().map(x -> x.getItemLocationId()+"_"+x.getBranchId()).collect(Collectors.toSet());
            List<BranchItemLocationMapDTO> branchItemLocationMapDTOS = new ArrayList<BranchItemLocationMapDTO>();
            Boolean isDataSaved = false;
            for(BranchOfficeDTO data : branchOfficeDTOS){
                if(!uniqueItemLocationId_BranchIdSet.contains(itemLocationDTO.get().getItemLocationId()+"_"+data.getBranchId())) {
                    BranchItemLocationMapDTO branchItemLocationMapDTO = new BranchItemLocationMapDTO();
                    branchItemLocationMapDTO.setBranchId(data.getBranchId());
                    branchItemLocationMapDTO.setItemLocationId(Long.valueOf(branchItemLocationMapExtendedDTO.getItemLocationId()));
                    branchItemLocationMapDTO.setStatus("active");
                    branchItemLocationMapDTO.setCreatedDate(LocalDate.now());
                    branchItemLocationMapDTO.setCreatedById(3L);
                    branchItemLocationMapDTO.setCreatedByName("Ritam");
                    branchItemLocationMapDTO.setUpdatedByName(null);
                    branchItemLocationMapDTO.setUpdatedById(null);

                    branchItemLocationMapDTOS.add(branchItemLocationMapDTO);
                    isDataSaved = true;
                }
            }
            branchItemLocationMapRepositoryExtended.saveAll(branchItemLocationMapMapper.toEntity(branchItemLocationMapDTOS));
            return (new ResponseDTO(isDataSaved,isDataSaved?"Successfully Saved":"Data already exist.", Arrays.asList(branchItemLocationMapDTOS.toArray())));
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO updateBranchItemLocationMap(BranchItemLocationMapExtendedUpdateDTO branchItemLocationMapExtendedUpdateDTO) {
        try {
            if (branchItemLocationMapExtendedUpdateDTO.getItemLocationId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (ItemLocation_Id)");
            }else if (branchItemLocationMapExtendedUpdateDTO.getBranchId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Branch_Id)");
            }

            BranchItemLocationMap updateDTO = branchItemLocationMapRepositoryExtended.findByItemLocationIdAndBranchIdAndStatusIgnoreCase(branchItemLocationMapExtendedUpdateDTO.getItemLocationId(),branchItemLocationMapExtendedUpdateDTO.getBranchId(),"active");
            if(updateDTO != null) {
                updateDTO.setStatus("inactive");
                updateDTO.updatedById(1L);
                updateDTO.updatedByName("Abhijit Update");
                updateDTO.updatedDate(LocalDate.now());
                branchItemLocationMapRepositoryExtended.save(updateDTO);

                BranchItemLocationMapDTO branchItemLocationMapDTO = new BranchItemLocationMapDTO();

                branchItemLocationMapDTO.setBranchId(branchItemLocationMapExtendedUpdateDTO.getBranchId());
                branchItemLocationMapDTO.setItemLocationId(branchItemLocationMapExtendedUpdateDTO.getItemLocationId());
                branchItemLocationMapDTO.setStatus("active");
                if (branchItemLocationMapDTO.getBranchItemLocationMapId() == null || branchItemLocationMapDTO.getBranchItemLocationMapId() == 0) {
                    branchItemLocationMapDTO.setBranchItemLocationMapId(null);
                    branchItemLocationMapDTO.setCreatedDate(LocalDate.now());
                    branchItemLocationMapDTO.setCreatedById(1L);
                    branchItemLocationMapDTO.setCreatedByName("Abhijit");
                    branchItemLocationMapDTO.setBranchItemLocationMapUuid(UUID.randomUUID());
                } else {
                    branchItemLocationMapDTO.setUpdatedDate(LocalDate.now());
                    branchItemLocationMapDTO.setUpdatedById(1L);
                    branchItemLocationMapDTO.setUpdatedByName("Abhijit");
                }
                BranchItemLocationMapDTO savedBranchItemLocationMapDTO = branchItemLocationMapMapper.toDto(
                    branchItemLocationMapRepositoryExtended.save(branchItemLocationMapMapper.toEntity(branchItemLocationMapDTO))
                );
                return (new ResponseDTO(Boolean.TRUE,"Successfully Saved",List.of(savedBranchItemLocationMapDTO)));
            }else {
                return (new ResponseDTO(Boolean.FALSE,"Failed to Save data",new ArrayList<>()));
            }
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BranchItemLocationMapDTO> getBranchItemLocationMapByItemLocationId(Long itemLocationId) {
        List<BranchItemLocationMap> data = branchItemLocationMapRepositoryExtended.findByItemLocationIdAndStatusIgnoreCase(itemLocationId, "active");
        return branchItemLocationMapMapper.toDto(data);
    }

    @Override
    public List<BranchItemLocationMapDTO> getBranchItemLocationMapByBranchId(Long branchId) {
        List<BranchItemLocationMap> data = branchItemLocationMapRepositoryExtended.findByBranchIdAndStatusIgnoreCase(branchId, "active");
        return branchItemLocationMapMapper.toDto(data);
    }

    @Override
    public List<BranchItemLocationMapDTO> getAllBranchItemLocationMapData() {
        List<BranchItemLocationMap> data = branchItemLocationMapRepositoryExtended.findByStatusIgnoreCase("active");
        return branchItemLocationMapMapper.toDto(data);
    }

    @Override
    public List<BranchItemLocationMapDTO> getBranchItemLocationMapByStatus(String status) {
        List<BranchItemLocationMap> data = branchItemLocationMapRepositoryExtended.findByStatusIgnoreCase("active");
        return branchItemLocationMapMapper.toDto(data);
    }

    @Override
    public ResponseDTO deactiveBranchItemLocationMapByItemLocationIdAndBranchId(Long itemLocationId, Long branchId) {
        BranchItemLocationMap updateDTO = branchItemLocationMapRepositoryExtended.findByItemLocationIdAndBranchIdAndStatusIgnoreCase(itemLocationId,branchId,"active");
        if(updateDTO != null) {
            updateDTO.setStatus("inactive");
            BranchItemLocationMapDTO updatedObj = branchItemLocationMapMapper.toDto(branchItemLocationMapRepositoryExtended.save(updateDTO));
            return (new ResponseDTO(true,"Successfully Updated",List.of(updatedObj)));
        }else{
            return (new ResponseDTO(false,"Data Not Found",new ArrayList<>()));
        }
    }

    @Override
    public ResponseDTO setBranchItemLocationMapById(Long id, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                BranchItemLocationMap branchItemLocationMap = branchItemLocationMapRepositoryExtended.findByBranchItemLocationMapId(id);
                branchItemLocationMap.setStatus(status);
                branchItemLocationMapRepositoryExtended.save(branchItemLocationMap);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(branchItemLocationMap)));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }
}
