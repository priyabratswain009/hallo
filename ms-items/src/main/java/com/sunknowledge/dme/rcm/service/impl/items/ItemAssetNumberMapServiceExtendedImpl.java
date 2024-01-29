package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap;
import com.sunknowledge.dme.rcm.repository.items.ItemAssetNumberMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemAssetNumberMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.items.ItemAssetNumberMapServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemAssetNumberMapMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("ItemAssetNumberMapServiceExtendedImpl")
public class ItemAssetNumberMapServiceExtendedImpl implements ItemAssetNumberMapServiceExtended {

    private final Logger log = LoggerFactory.getLogger(ItemAssetNumberMapServiceExtendedImpl.class);
    @Autowired
    ItemAssetNumberMapRepositoryExtended itemAssetNumberMapRepositoryExtended;
    @Autowired
    ItemAssetNumberMapMapper itemAssetNumberMapMapper;

    @Override
    public ItemAssetNumberMapDTO save(ItemAssetNumberMapDTO itemAssetNumberMapDTO) {
        return null;
    }

    @Override
    public ItemAssetNumberMapDTO update(ItemAssetNumberMapDTO itemAssetNumberMapDTO) {
        return null;
    }

    @Override
    public Optional<ItemAssetNumberMapDTO> partialUpdate(ItemAssetNumberMapDTO itemAssetNumberMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemAssetNumberMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemAssetNumberMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveItemAssetNumberMap(ItemAssetNumberMapExtendedDTO itemAssetNumberMapExtendedDTO) throws InvalidAttributeValueException {

        ResponseDTO outcome = new ResponseDTO();
        try {
            if (itemAssetNumberMapExtendedDTO.getStatus().equalsIgnoreCase("active") || itemAssetNumberMapExtendedDTO.getStatus().equalsIgnoreCase("inActive")) {
                ItemAssetNumberMapDTO itemAssetNumberMapDTO = (itemAssetNumberMapExtendedDTO.getItemAssetNumberUuid() == null) ? new ItemAssetNumberMapDTO() :
                    (itemAssetNumberMapRepositoryExtended.findByItemAssetNumberUuid(itemAssetNumberMapExtendedDTO.getItemAssetNumberUuid()) != null ?
                        itemAssetNumberMapMapper.toDto(itemAssetNumberMapRepositoryExtended.findByItemAssetNumberUuid(itemAssetNumberMapExtendedDTO.getItemAssetNumberUuid())) :
                        new ItemAssetNumberMapDTO());

                BeanUtils.copyProperties(itemAssetNumberMapExtendedDTO, itemAssetNumberMapDTO);

                if (itemAssetNumberMapDTO.getItemAssetNumberUuid() == null) {
                    itemAssetNumberMapDTO.setItemAssetNumberId(null);
                    itemAssetNumberMapDTO.setCreatedById(31L);
                    itemAssetNumberMapDTO.setCreatedDate(LocalDate.now());
                    itemAssetNumberMapDTO.setCreatedByName("Falguni");
                    itemAssetNumberMapDTO.setItemAssetNumberUuid(UUID.randomUUID());
                } else {
                    itemAssetNumberMapDTO.setUpdatedDate(LocalDate.now());
                    itemAssetNumberMapDTO.setUpdatedById(31L);
                    itemAssetNumberMapDTO.setUpdatedByName("Falguni");
                }
                ItemAssetNumberMapDTO savedItemAssetNumberDTO = itemAssetNumberMapMapper.toDto(
                    itemAssetNumberMapRepositoryExtended.save(itemAssetNumberMapMapper.toEntity(itemAssetNumberMapDTO))
                );

                return new ResponseDTO(true, "Successfully Saved.", List.of(savedItemAssetNumberDTO), 200);
            } else {
               // throw new InputMismatchException("Status must be provided and should be active/inactive");
                return new ResponseDTO(Boolean.FALSE, "Status should be active/inactive", new ArrayList(), 200);
            }
        }catch (Exception e) {
            log.error("Error=" + e);
            return new ResponseDTO(Boolean.FALSE, "Failed to Save! Data Error.", new ArrayList(), 200);
        }
    }

    @Override
    public List<ItemAssetNumberMapDTO> getAllItemAssetNumberMapInfo() {
        List<ItemAssetNumberMap> data = itemAssetNumberMapRepositoryExtended.findByStatusIgnoreCase("active");
        return itemAssetNumberMapMapper.toDto(data);
    }

    @Override
    public List<ItemAssetNumberMapDTO> getItemAssetNumberMapByUUID(UUID itemAssetNumberUuid) {
        List<ItemAssetNumberMapDTO> dtoDataList = new ArrayList<ItemAssetNumberMapDTO>();
        ItemAssetNumberMap data = itemAssetNumberMapRepositoryExtended.findByItemAssetNumberUuid(itemAssetNumberUuid);
        if(data != null){
            dtoDataList.add(itemAssetNumberMapMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<ItemAssetNumberMapDTO> getItemAssetNumberMapByItemIdAndAssetNo(Long itemId, String assetNo) {
        return itemAssetNumberMapMapper.toDto(
            itemAssetNumberMapRepositoryExtended.findByItemIdAndAssetNumberAndStatusIgnoreCase(itemId, assetNo, "active")
        );
    }

    @Override
    public List<ItemAssetNumberMapDTO> getAssetNumberByItemId(Long itemId) {
        return itemAssetNumberMapMapper.toDto(
            itemAssetNumberMapRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active")
        );
    }
}
