package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.domain.ItemGroup;
import com.sunknowledge.dme.rcm.repository.items.ItemGroupRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemGroupParameterDTO;
import com.sunknowledge.dme.rcm.service.items.ItemGroupServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service("itemGroupServiceExtendedImpl")
public class ItemGroupServiceExtendedImpl implements ItemGroupServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemGroupServiceExtendedImpl.class);
    @Autowired
    ItemGroupRepositoryExtended itemGroupRepositoryExtended;
    @Autowired
    ItemGroupMapper itemGroupMapper;

    @Override
    public ResponseDTO saveItemGroup(ItemGroupParameterDTO itemGroupParameterDTO) {
        try {
            List responseList = new ArrayList<ItemGroup>();
            ItemGroupDTO itemGroupDTO = itemGroupParameterDTO.getItemGroupId()==null? new ItemGroupDTO():
            (itemGroupRepositoryExtended.findById(itemGroupParameterDTO.getItemGroupId()).isEmpty()?
                new ItemGroupDTO() : itemGroupMapper.toDto(
                itemGroupRepositoryExtended.findById(itemGroupParameterDTO.getItemGroupId()).get()));

            BeanUtils.copyProperties(itemGroupParameterDTO, itemGroupDTO);
            if (itemGroupDTO.getItemGroupId() == null || itemGroupDTO.getItemGroupId() == 0) {
                itemGroupDTO.setItemGroupId(null);
                itemGroupDTO.setCreatedDate(LocalDate.now());
                itemGroupDTO.setCreatedById(1L);
                itemGroupDTO.setCreatedByName("Abhijit");
                itemGroupDTO.setItemGroupUuid(UUID.randomUUID());
            } else {
                itemGroupDTO.setUpdatedDate(LocalDate.now());
                itemGroupDTO.setUpdatedById(1L);
                itemGroupDTO.setUpdatedByName("Abhijit");
            }
            ItemGroupDTO savedItemGroupDTO = itemGroupMapper.toDto(
                itemGroupRepositoryExtended.save(itemGroupMapper.toEntity(itemGroupDTO))
            );
            responseList.add(savedItemGroupDTO);
            return new ResponseDTO(true, "Successfully Saved.", responseList, 200);
        } catch (Exception e) {
            log.error("Error=" + e);
            return new ResponseDTO(Boolean.FALSE, "Failed to Save! Data Error.", new ArrayList(), 200);
        }
    }

    @Override
    public ItemGroupDTO getItemGroupById(Long itemGroupId) {
        Optional<ItemGroup> optionalIg = itemGroupRepositoryExtended.findById(itemGroupId);
        if (optionalIg.isPresent()) {
            return itemGroupMapper.toDto(optionalIg.get());
        } else {
            return new ItemGroupDTO();
        }
    }

    @Override
    public List<ItemGroupDTO> getItemGroupByName(String itemGroupName) {
        Optional<List<ItemGroup>> itemGroupListOptional = itemGroupRepositoryExtended.findByItemGroupNameLikeIgnoreCaseAndStatusIgnoreCase("%" + itemGroupName + "%", "active");
        if (itemGroupListOptional.isPresent()) {
            return itemGroupListOptional.get().stream().map(x -> itemGroupMapper.toDto(x)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ItemGroupDTO> getAllItemGroup() {
        List<ItemGroup> itemGroupList = itemGroupRepositoryExtended.findAll();
        if (itemGroupList != null) {
            return itemGroupList.stream().filter(x -> x.getStatus().equalsIgnoreCase("active"))
                .map(x -> itemGroupMapper.toDto(x)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public ResponseDTO setItemGroupStatusById(Long id, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<ItemGroup> itemGroup = itemGroupRepositoryExtended.findById(id);
                itemGroup.get().setStatus(status);
                itemGroupRepositoryExtended.save(itemGroup.get());
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemGroup.get()), 200));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>(), 200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>(), 200));
        }
    }

}
