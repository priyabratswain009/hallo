package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.ItemLocation;
import com.sunknowledge.dme.rcm.repository.others.ItemLocationRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationParameterDTO;
import com.sunknowledge.dme.rcm.service.others.ItemLocationServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationRejectedDTO;
import com.sunknowledge.dme.rcm.service.helper.others.ItemLocationHelper;
import com.sunknowledge.dme.rcm.service.mapper.ItemLocationMapper;
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
public class ItemLocationServiceExtendedImpl implements ItemLocationServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemLocationServiceExtendedImpl.class);
    @Autowired
    ItemLocationHelper itemLocationHelper;
    @Autowired
    ItemLocationMapper itemLocationMapper;
    @Autowired
    ItemLocationRepositoryExtended itemLocationRepositoryExtended;

    @Override
    public ItemLocationDTO save(ItemLocationDTO itemLocationDTO) {
        return null;
    }

    @Override
    public ItemLocationDTO update(ItemLocationDTO itemLocationDTO) {
        return null;
    }

    @Override
    public Optional<ItemLocationDTO> partialUpdate(ItemLocationDTO itemLocationDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemLocationDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemLocationDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO bulkUploadForItemLocation(MultipartFile documentFile) {
        Map<String,Object> itemLocationBothData;
        String message = "";
        try {
            itemLocationBothData = itemLocationHelper.csvToItemLocationDTOMapper(documentFile.getInputStream());
            List<ItemLocationDTO> itemLocationDTOS = (List<ItemLocationDTO>) itemLocationBothData.get("itemLocationDTOS");

            itemLocationRepositoryExtended.saveAll(itemLocationMapper.toEntity(itemLocationDTOS));

            Integer successfullyUploaded = ((List<ItemLocationDTO>) itemLocationBothData.get("itemLocationDTOS")).size();
            Integer skipped = ((Map<String, ItemLocationRejectedDTO>) itemLocationBothData.get("SkippedItemLocationDTO")).size();
            message = "Successfully Uploaded ("+successfullyUploaded+")";
            if(skipped > 0){
                message += " and Skipped " + skipped + " Rows";
            }
            return (new ResponseDTO(Boolean.TRUE,message,List.of(itemLocationBothData.get("SkippedItemLocationDTO"))));
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO saveItemLocation(ItemLocationParameterDTO itemLocationParameterDTO) {
        Set uniqueItemLocationNameSet = itemLocationRepositoryExtended.findAll().stream().map(x -> x.getItemLocationName()).collect(Collectors.toSet());
        try {
            if (itemLocationParameterDTO.getItemLocationName() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Item_Location_Name)");
            } else if (itemLocationParameterDTO.getItemLocationName().trim() == "") {
                throw new InputMismatchException("Item_Location_Name must be provided");
            } else if(itemLocationParameterDTO.getItemLocationId() == 0 && uniqueItemLocationNameSet.contains(itemLocationParameterDTO.getItemLocationName())){
                throw new InputMismatchException("("+ itemLocationParameterDTO.getItemLocationName() +") "+"Item_Location_Name already exist");
            }

            ItemLocationDTO itemLocationDTO = (itemLocationParameterDTO.getItemLocationId() == null ||
                itemLocationParameterDTO.getItemLocationId() == 0) ? new ItemLocationDTO() :
                (itemLocationRepositoryExtended.findById(itemLocationParameterDTO.getItemLocationId()).isPresent() ?
                    itemLocationMapper.toDto(itemLocationRepositoryExtended.findById(itemLocationParameterDTO.getItemLocationId()).get()) :
                    new ItemLocationDTO());

            BeanUtils.copyProperties(itemLocationParameterDTO, itemLocationDTO);
            if (itemLocationDTO.getItemLocationId() == null || itemLocationDTO.getItemLocationId() == 0) {
                itemLocationDTO.setItemLocationId(null);
                itemLocationDTO.setCreatedDate(LocalDate.now());
                itemLocationDTO.setCreatedById(1L);
                itemLocationDTO.setCreatedByName("Abhijit");
                itemLocationDTO.setItemLocationUuid(UUID.randomUUID());
            } else {
                itemLocationDTO.setUpdatedDate(LocalDate.now());
                itemLocationDTO.setUpdatedById(1L);
                itemLocationDTO.setUpdatedByName("Abhijit");
            }
            ItemLocationDTO savedItemLocationDTO = itemLocationMapper.toDto(
                itemLocationRepositoryExtended.save(itemLocationMapper.toEntity(itemLocationDTO))
            );
            return new ResponseDTO(true, "Successfully Saved.", List.of(savedItemLocationDTO));
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemLocationDTO> getItemLocationById(Long itemLocationId) {
        List<ItemLocationDTO> dtoDataList = new ArrayList<ItemLocationDTO>();
        ItemLocation data = itemLocationRepositoryExtended.findByItemLocationId(itemLocationId);
        if(data != null){
            dtoDataList.add(itemLocationMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<ItemLocationDTO> getItemLocationByItemLocationName(String itemLocationName) {
        List<ItemLocationDTO> dtoDataList = new ArrayList<ItemLocationDTO>();
        ItemLocation data = itemLocationRepositoryExtended.findByItemLocationNameLikeIgnoreCaseAndStatusIgnoreCase("%"+itemLocationName+"%","active");
        if(data != null){
            dtoDataList.add(itemLocationMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<ItemLocationDTO> getItemLocationByDescription(String description) {
        List<ItemLocation> data = itemLocationRepositoryExtended.findByDescriptionLikeIgnoreCaseAndStatusIgnoreCase("%"+description+"%","active");
        return itemLocationMapper.toDto(data);
    }

    @Override
    public List<ItemLocationDTO> getAllItemLocationData() {
        List<ItemLocation> data = itemLocationRepositoryExtended.findByStatusIgnoreCase("active");
        return itemLocationMapper.toDto(data);
    }

    @Override
    public Optional<ItemLocationDTO> findByItemLocationIdIn(Long itemLocationId) {
        Optional<ItemLocation> itemLocation = itemLocationRepositoryExtended.findById(itemLocationId);
        return Optional.ofNullable(itemLocationMapper.toDto(itemLocation.get()));
    }

    @Override
    public ResponseDTO setItemLocationStatusById(Long itemLocationId, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                ItemLocation itemLocation = itemLocationRepositoryExtended.findByItemLocationId(itemLocationId);
                itemLocation.setStatus(status);
                itemLocationRepositoryExtended.save(itemLocation);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemLocation)));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }

    @Override
    public List<ItemLocationDTO> getItemLocationByStatus(String status) {
        List<ItemLocation> data = itemLocationRepositoryExtended.findByStatusIgnoreCase(status);
        return itemLocationMapper.toDto(data);
    }
}
