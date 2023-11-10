package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import com.sunknowledge.dme.rcm.repository.items.ItemProcedureCodeMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemProcedureCodeMapServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemProcedureCodeMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Primary
@Service
public class ItemProcedureCodeMapServiceExtendedImpl implements ItemProcedureCodeMapServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemProcedureCodeMapServiceExtendedImpl.class);
    @Autowired
    ItemProcedureCodeMapRepositoryExtended itemProcedureCodeMapRepositoryExtended;
    @Autowired
    ItemProcedureCodeMapMapper itemProcedureCodeMapMapper;

    @Override
    public ItemProcedureCodeMapDTO save(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO) {
        return null;
    }

    @Override
    public ItemProcedureCodeMapDTO update(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO) {
        return null;
    }

    @Override
    public Optional<ItemProcedureCodeMapDTO> partialUpdate(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemProcedureCodeMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemProcedureCodeMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void saveItemProcedureCodeMap(ItemMasterParameterDTO itemMasterParameterDTO, ItemMasterDTO itemMasterDTO) {
        List<ItemProcedureCodeMap> itemProcedureCodeMapList = itemProcedureCodeMapRepositoryExtended.findByItemNoAndStatusIgnoreCase(itemMasterDTO.getItemNumber(), "active");
        //List<String> itemProcValueList =  itemProcedureCodeMapList.stream().map(x -> x.getProcedureCode()).collect(Collectors.toList());
        Long updatedById = 1L; // Should be retrieve from User Login Service
        Long createdById = 1L; // Should be retrieve from User Login Service
        String updatedByName = "Abhijit"; // Should be retrieve from User Login Service
        String createdByName = "Abhijit"; // Should be retrieve from User Login Service

        for (ItemProcedureCodeMap data : itemProcedureCodeMapList) {
            data.setStatus("inactive");
            data.setUpdatedDate(LocalDate.now());
            data.setUpdatedById(updatedById);
            data.setUpdatedName(updatedByName);
            itemProcedureCodeMapRepositoryExtended.save(data);
        }

        List<ItemProcedureCodeMapDTO> itemProcedureCodeMapDTOs = new ArrayList<ItemProcedureCodeMapDTO>();
        List<String> procValueList = itemMasterParameterDTO.getPrimaryProcedureCodeValueList();
        String primaryProcValue = itemMasterParameterDTO.getPrimaryProcedureCodeValue();
        if (procValueList.contains(primaryProcValue)) {
            for (String procVal : procValueList) {
                ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = new ItemProcedureCodeMapDTO();
                itemProcedureCodeMapDTO.setItemMaster(itemMasterDTO);
                itemProcedureCodeMapDTO.setItemName(itemMasterParameterDTO.getItemName());
                itemProcedureCodeMapDTO.setItemNo(itemMasterDTO.getItemNumber());
                itemProcedureCodeMapDTO.setItemDescription(itemMasterParameterDTO.getItemDescription());
                itemProcedureCodeMapDTO.setItemUom(itemMasterParameterDTO.getItemUom());
                itemProcedureCodeMapDTO.setProcedureCode(procVal);
                itemProcedureCodeMapDTO.setModifier1(null);
                itemProcedureCodeMapDTO.setModifier2(null);
                itemProcedureCodeMapDTO.setModifier3(null);
                itemProcedureCodeMapDTO.setModifier4(null);
                itemProcedureCodeMapDTO.setStatus("active");
                itemProcedureCodeMapDTO.setCreatedById(createdById);
                itemProcedureCodeMapDTO.setCreatedByName(createdByName);
                itemProcedureCodeMapDTO.setCreatedDate(LocalDate.now());
                itemProcedureCodeMapDTO.setUpdatedById(null);
                itemProcedureCodeMapDTO.setUpdatedName(null);
                itemProcedureCodeMapDTO.setUpdatedDate(null);
                itemProcedureCodeMapDTO.setItemProcedureCodeMapUuid(UUID.randomUUID());

                itemProcedureCodeMapDTOs.add(itemProcedureCodeMapDTO);
            }
            itemProcedureCodeMapRepositoryExtended.saveAll(itemProcedureCodeMapMapper.toEntity(itemProcedureCodeMapDTOs));
        }
    }


    @Override
    public List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapById(Long itemMasterItemId) {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByItemMasterItemId(itemMasterItemId));
    }

    @Override
    public List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByItemName(String itemName) {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByItemNameLikeIgnoreCaseAndStatusIgnoreCase("%" + itemName + "%", "active"));
    }

    @Override
    public List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByItemNo(String itemNumber) {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByItemNoAndStatusIgnoreCase(itemNumber, "active"));
    }

    @Override
    public List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByItemDescription(String itemDescription) {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByItemDescriptionLikeIgnoreCaseAndStatusIgnoreCase("%" + itemDescription + "%", "active"));
    }

    @Override
    public List<ItemProcedureCodeMapDTO> getAllItemProcedureCodeMapData() {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByStatusIgnoreCase("active"));
    }

    @Override
    public List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByStatus(String status) {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByStatusIgnoreCase(status));
    }

    @Override
    public List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByProcedureCode(String procedureCode) {
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMapRepositoryExtended.findByProcedureCodeIgnoreCase(procedureCode));
    }

    @Override
    public ResponseDTO setItemProcedureCodeMapStatusById(Long itemProcedureCodeMapId, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                ItemProcedureCodeMap itemProcedureCodeMap = itemProcedureCodeMapRepositoryExtended.findByItemProcedureCodeMapId(itemProcedureCodeMapId);
                itemProcedureCodeMap.setStatus(status);
                itemProcedureCodeMapRepositoryExtended.save(itemProcedureCodeMap);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemProcedureCodeMap)));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }

}
