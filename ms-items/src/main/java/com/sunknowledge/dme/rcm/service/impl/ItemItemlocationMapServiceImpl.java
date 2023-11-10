package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemItemlocationMap;
import com.sunknowledge.dme.rcm.repository.ItemItemlocationMapRepository;
import com.sunknowledge.dme.rcm.service.ItemItemlocationMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemItemlocationMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemItemlocationMap}.
 */
@Service
@Transactional
public class ItemItemlocationMapServiceImpl implements ItemItemlocationMapService {

    private final Logger log = LoggerFactory.getLogger(ItemItemlocationMapServiceImpl.class);

    private final ItemItemlocationMapRepository itemItemlocationMapRepository;

    private final ItemItemlocationMapMapper itemItemlocationMapMapper;

    public ItemItemlocationMapServiceImpl(
        ItemItemlocationMapRepository itemItemlocationMapRepository,
        ItemItemlocationMapMapper itemItemlocationMapMapper
    ) {
        this.itemItemlocationMapRepository = itemItemlocationMapRepository;
        this.itemItemlocationMapMapper = itemItemlocationMapMapper;
    }

    @Override
    public ItemItemlocationMapDTO save(ItemItemlocationMapDTO itemItemlocationMapDTO) {
        log.debug("Request to save ItemItemlocationMap : {}", itemItemlocationMapDTO);
        ItemItemlocationMap itemItemlocationMap = itemItemlocationMapMapper.toEntity(itemItemlocationMapDTO);
        itemItemlocationMap = itemItemlocationMapRepository.save(itemItemlocationMap);
        return itemItemlocationMapMapper.toDto(itemItemlocationMap);
    }

    @Override
    public ItemItemlocationMapDTO update(ItemItemlocationMapDTO itemItemlocationMapDTO) {
        log.debug("Request to update ItemItemlocationMap : {}", itemItemlocationMapDTO);
        ItemItemlocationMap itemItemlocationMap = itemItemlocationMapMapper.toEntity(itemItemlocationMapDTO);
        itemItemlocationMap = itemItemlocationMapRepository.save(itemItemlocationMap);
        return itemItemlocationMapMapper.toDto(itemItemlocationMap);
    }

    @Override
    public Optional<ItemItemlocationMapDTO> partialUpdate(ItemItemlocationMapDTO itemItemlocationMapDTO) {
        log.debug("Request to partially update ItemItemlocationMap : {}", itemItemlocationMapDTO);

        return itemItemlocationMapRepository
            .findById(itemItemlocationMapDTO.getItemItemlocationMapId())
            .map(existingItemItemlocationMap -> {
                itemItemlocationMapMapper.partialUpdate(existingItemItemlocationMap, itemItemlocationMapDTO);

                return existingItemItemlocationMap;
            })
            .map(itemItemlocationMapRepository::save)
            .map(itemItemlocationMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemItemlocationMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemItemlocationMaps");
        return itemItemlocationMapRepository.findAll(pageable).map(itemItemlocationMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemItemlocationMapDTO> findOne(Long id) {
        log.debug("Request to get ItemItemlocationMap : {}", id);
        return itemItemlocationMapRepository.findById(id).map(itemItemlocationMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemItemlocationMap : {}", id);
        itemItemlocationMapRepository.deleteById(id);
    }
}
