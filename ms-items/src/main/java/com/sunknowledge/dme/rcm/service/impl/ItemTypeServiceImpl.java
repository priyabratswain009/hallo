package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemType;
import com.sunknowledge.dme.rcm.repository.ItemTypeRepository;
import com.sunknowledge.dme.rcm.service.ItemTypeService;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemType}.
 */
@Service
@Transactional
public class ItemTypeServiceImpl implements ItemTypeService {

    private final Logger log = LoggerFactory.getLogger(ItemTypeServiceImpl.class);

    private final ItemTypeRepository itemTypeRepository;

    private final ItemTypeMapper itemTypeMapper;

    public ItemTypeServiceImpl(ItemTypeRepository itemTypeRepository, ItemTypeMapper itemTypeMapper) {
        this.itemTypeRepository = itemTypeRepository;
        this.itemTypeMapper = itemTypeMapper;
    }

    @Override
    public ItemTypeDTO save(ItemTypeDTO itemTypeDTO) {
        log.debug("Request to save ItemType : {}", itemTypeDTO);
        ItemType itemType = itemTypeMapper.toEntity(itemTypeDTO);
        itemType = itemTypeRepository.save(itemType);
        return itemTypeMapper.toDto(itemType);
    }

    @Override
    public ItemTypeDTO update(ItemTypeDTO itemTypeDTO) {
        log.debug("Request to update ItemType : {}", itemTypeDTO);
        ItemType itemType = itemTypeMapper.toEntity(itemTypeDTO);
        itemType = itemTypeRepository.save(itemType);
        return itemTypeMapper.toDto(itemType);
    }

    @Override
    public Optional<ItemTypeDTO> partialUpdate(ItemTypeDTO itemTypeDTO) {
        log.debug("Request to partially update ItemType : {}", itemTypeDTO);

        return itemTypeRepository
            .findById(itemTypeDTO.getItemTypeId())
            .map(existingItemType -> {
                itemTypeMapper.partialUpdate(existingItemType, itemTypeDTO);

                return existingItemType;
            })
            .map(itemTypeRepository::save)
            .map(itemTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemTypes");
        return itemTypeRepository.findAll(pageable).map(itemTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemTypeDTO> findOne(Long id) {
        log.debug("Request to get ItemType : {}", id);
        return itemTypeRepository.findById(id).map(itemTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemType : {}", id);
        itemTypeRepository.deleteById(id);
    }
}
