package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemGroup;
import com.sunknowledge.dme.rcm.repository.ItemGroupRepository;
import com.sunknowledge.dme.rcm.service.ItemGroupService;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemGroupMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemGroup}.
 */
@Service
@Transactional
public class ItemGroupServiceImpl implements ItemGroupService {

    private final Logger log = LoggerFactory.getLogger(ItemGroupServiceImpl.class);

    private final ItemGroupRepository itemGroupRepository;

    private final ItemGroupMapper itemGroupMapper;

    public ItemGroupServiceImpl(ItemGroupRepository itemGroupRepository, ItemGroupMapper itemGroupMapper) {
        this.itemGroupRepository = itemGroupRepository;
        this.itemGroupMapper = itemGroupMapper;
    }

    @Override
    public ItemGroupDTO save(ItemGroupDTO itemGroupDTO) {
        log.debug("Request to save ItemGroup : {}", itemGroupDTO);
        ItemGroup itemGroup = itemGroupMapper.toEntity(itemGroupDTO);
        itemGroup = itemGroupRepository.save(itemGroup);
        return itemGroupMapper.toDto(itemGroup);
    }

    @Override
    public ItemGroupDTO update(ItemGroupDTO itemGroupDTO) {
        log.debug("Request to update ItemGroup : {}", itemGroupDTO);
        ItemGroup itemGroup = itemGroupMapper.toEntity(itemGroupDTO);
        itemGroup = itemGroupRepository.save(itemGroup);
        return itemGroupMapper.toDto(itemGroup);
    }

    @Override
    public Optional<ItemGroupDTO> partialUpdate(ItemGroupDTO itemGroupDTO) {
        log.debug("Request to partially update ItemGroup : {}", itemGroupDTO);

        return itemGroupRepository
            .findById(itemGroupDTO.getItemGroupId())
            .map(existingItemGroup -> {
                itemGroupMapper.partialUpdate(existingItemGroup, itemGroupDTO);

                return existingItemGroup;
            })
            .map(itemGroupRepository::save)
            .map(itemGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemGroups");
        return itemGroupRepository.findAll(pageable).map(itemGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemGroupDTO> findOne(Long id) {
        log.debug("Request to get ItemGroup : {}", id);
        return itemGroupRepository.findById(id).map(itemGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemGroup : {}", id);
        itemGroupRepository.deleteById(id);
    }
}
