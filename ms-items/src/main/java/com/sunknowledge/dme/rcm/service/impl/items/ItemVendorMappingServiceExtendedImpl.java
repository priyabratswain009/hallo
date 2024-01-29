package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.domain.ItemVendorMapping;
import com.sunknowledge.dme.rcm.repository.items.ItemVendorMappingRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemVendorMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.itemothers.VendorMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemVendorMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemVendorMappingMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class ItemVendorMappingServiceExtendedImpl implements ItemVendorMappingServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingServiceExtendedImpl.class);
    @Autowired
    VendorMasterServiceExtended vendorMasterServiceExtended;
    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    ItemVendorMappingRepositoryExtended itemVendorMappingRepositoryExtended;
    @Autowired
    ItemVendorMappingMapper itemVendorMappingMapper;

    @Override
    public ItemVendorMappingDTO save(ItemVendorMappingDTO itemVendorMappingDTO) {
        return null;
    }

    @Override
    public ItemVendorMappingDTO update(ItemVendorMappingDTO itemVendorMappingDTO) {
        return null;
    }

    @Override
    public Optional<ItemVendorMappingDTO> partialUpdate(ItemVendorMappingDTO itemVendorMappingDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemVendorMappingDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemVendorMappingDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveItemVendorMap(ItemVendorMapExtendedDTO itemVendorMapExtendedDTO) {
        try {
            if (itemVendorMapExtendedDTO.getVendorId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Vendor_Id)");
            }else if (itemVendorMapExtendedDTO.getItemIdList() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Item_Id)");
            } else if (itemVendorMapExtendedDTO.getItemIdList().size() == 0) {
                throw new InputMismatchException("Item_Ids must be provided");
            }
            Set<Long> itemSet = itemVendorMapExtendedDTO.getItemIdList().stream().filter(x -> x != 0 && x != null).collect(Collectors.toSet());
            List<Long> itemList = itemSet.stream().collect(Collectors.toList());
            List<ItemMasterDTO> itemMasterDTOS = itemMasterServiceExtended.findByItemIdIn(itemList);
            Optional<VendorMasterDTO> vendorMasterDTO = vendorMasterServiceExtended.findByVendorId(Long.valueOf(itemVendorMapExtendedDTO.getVendorId()));
            if(itemMasterDTOS.size()>0 && vendorMasterDTO.isPresent() && vendorMasterDTO.get().getStatus().equalsIgnoreCase("active") ) {
                Boolean isDataSaved = false;
                Set uniqueItemId_VendorIdSet = itemVendorMappingRepositoryExtended.findAll().stream().map(x -> x.getItemId() + "_" + x.getVendorId()).collect(Collectors.toSet());
                List<ItemVendorMappingDTO> itemVendorMappingDTOS = new ArrayList<ItemVendorMappingDTO>();
                for (ItemMasterDTO data : itemMasterDTOS) {
                    if (!uniqueItemId_VendorIdSet.contains(data.getItemId() + "_" + vendorMasterDTO.get().getVendorId())) {
                        ItemVendorMappingDTO itemVendorMappingDTO = new ItemVendorMappingDTO();

                        itemVendorMappingDTO.setItemId(data.getItemId());
                        itemVendorMappingDTO.setItemName(data.getItemName());
                        itemVendorMappingDTO.setVendorId(vendorMasterDTO.get().getVendorId());
                        itemVendorMappingDTO.setVendorName(vendorMasterDTO.get().getVendorName());
                        itemVendorMappingDTO.setStatus("active");
                        itemVendorMappingDTO.setCreatedById(3L);
                        itemVendorMappingDTO.setCreatedByName("Ritam Test");
                        itemVendorMappingDTO.setCreatedDate(LocalDate.now());
                        itemVendorMappingDTO.setUpdatedById(null);
                        itemVendorMappingDTO.setUpdatedByName(null);
                        itemVendorMappingDTO.setItemVendorMappingUuid(UUID.randomUUID());

                        itemVendorMappingDTOS.add(itemVendorMappingDTO);
                        isDataSaved = true;
                    }else{
                        //return new ResponseDTO(Boolean.FALSE, "Data Already Exist.", new ArrayList<>());
                    }
                }
                List<ItemVendorMappingDTO> savedItemVendorMappingDTOs = itemVendorMappingMapper.toDto(
                    itemVendorMappingRepositoryExtended.saveAll(itemVendorMappingMapper.toEntity(itemVendorMappingDTOS))
                );

                return new ResponseDTO(isDataSaved, isDataSaved?"Successfully Saved.":"Data already exist.", List.of(savedItemVendorMappingDTOs), 200);
            }
            else{
                return new ResponseDTO(Boolean.FALSE, "Data Not Found.", new ArrayList<>(), 200);
            }
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO deactiveItemVendorMap(ItemVendorMapExtendedDTO itemVendorMapExtendedDTO) {
        List<ItemVendorMapping> updateDTOs = itemVendorMappingRepositoryExtended.findByVendorIdAndItemIdInAndStatusIgnoreCase(
            itemVendorMapExtendedDTO.getVendorId(), itemVendorMapExtendedDTO.getItemIdList(),"active");
        if(updateDTOs.size() > 0) {
            updateDTOs.stream().peek(x -> {
                x.setStatus("inactive");
                x.setUpdatedDate(LocalDate.now());
                x.setUpdatedById(1L);
                x.setUpdatedByName("Abhijit");
            }).collect(Collectors.toList());
            List<ItemVendorMappingDTO> deactivatedItemVendorMappingDTOs = itemVendorMappingMapper.toDto(
                itemVendorMappingRepositoryExtended.saveAll(updateDTOs));
            return new ResponseDTO(true, "Successfully Saved.", List.of(deactivatedItemVendorMappingDTOs), 200);
        }else{
            return new ResponseDTO(false, "Data Not Found.", new ArrayList(), 200);
        }
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByItemId(Long itemId) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active");
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByVendorId(Long vendorId) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByVendorIdAndStatusIgnoreCase(vendorId, "active");
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByItemName(String itemName) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByItemNameLikeIgnoreCaseAndStatusIgnoreCase("%"+itemName+"%", "active");
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByVendorName(String vendorName) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByVendorNameLikeIgnoreCaseAndStatusIgnoreCase("%"+vendorName+"%", "active");
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByStatus(String status) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByStatusIgnoreCase(status);
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByItemVendorId(Long itemVendorId) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByItemVendorId(itemVendorId);
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public ResponseDTO setItemVendorStatusById(Long itemVendorId, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<ItemVendorMapping> itemVendorMapping = itemVendorMappingRepositoryExtended.findById(itemVendorId);
                itemVendorMapping.get().setStatus(status);
                itemVendorMappingRepositoryExtended.save(itemVendorMapping.get());
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemVendorMapping.get()), 200));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>(), 200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>(), 200));
        }
    }

    @Override
    public List<ItemVendorMappingDTO> getItemVendorMapByItemIdAndVendorId(Long itemId, Long vendorId) {
        List<ItemVendorMapping> itemVendorMappings = itemVendorMappingRepositoryExtended.findByItemIdAndVendorIdAndStatusIgnoreCase(itemId,vendorId,"active");
        return itemVendorMappingMapper.toDto(itemVendorMappings);
    }

    @Override
    public List<Map> getVendorsByItemIdDropdown(Long itemId) {
        return itemVendorMappingRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId,"active").stream().map(p -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", p.getVendorId());
            map.put("title", p.getVendorName());
            return map;
        }).collect(Collectors.toList());
    }

}
