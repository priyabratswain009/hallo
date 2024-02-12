package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap;
import com.sunknowledge.dme.rcm.repository.ItemAssetNumberMapRepository;
import com.sunknowledge.dme.rcm.service.ItemAssetNumberMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemAssetNumberMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemAssetNumberMap}.
 */
@Service
@Transactional
public class ItemAssetNumberMapServiceImpl implements ItemAssetNumberMapService {

    private final Logger log = LoggerFactory.getLogger(ItemAssetNumberMapServiceImpl.class);

    private final ItemAssetNumberMapRepository itemAssetNumberMapRepository;

    private final ItemAssetNumberMapMapper itemAssetNumberMapMapper;

    public ItemAssetNumberMapServiceImpl(
        ItemAssetNumberMapRepository itemAssetNumberMapRepository,
        ItemAssetNumberMapMapper itemAssetNumberMapMapper
    ) {
        this.itemAssetNumberMapRepository = itemAssetNumberMapRepository;
        this.itemAssetNumberMapMapper = itemAssetNumberMapMapper;
    }

    @Override
    public ItemAssetNumberMapDTO save(ItemAssetNumberMapDTO itemAssetNumberMapDTO) {
        log.debug("Request to save ItemAssetNumberMap : {}", itemAssetNumberMapDTO);
        ItemAssetNumberMap itemAssetNumberMap = itemAssetNumberMapMapper.toEntity(itemAssetNumberMapDTO);
        itemAssetNumberMap = itemAssetNumberMapRepository.save(itemAssetNumberMap);
        return itemAssetNumberMapMapper.toDto(itemAssetNumberMap);
    }

    @Override
    public ItemAssetNumberMapDTO update(ItemAssetNumberMapDTO itemAssetNumberMapDTO) {
        log.debug("Request to save ItemAssetNumberMap : {}", itemAssetNumberMapDTO);
        ItemAssetNumberMap itemAssetNumberMap = itemAssetNumberMapMapper.toEntity(itemAssetNumberMapDTO);
        itemAssetNumberMap = itemAssetNumberMapRepository.save(itemAssetNumberMap);
        return itemAssetNumberMapMapper.toDto(itemAssetNumberMap);
    }

    @Override
    public Optional<ItemAssetNumberMapDTO> partialUpdate(ItemAssetNumberMapDTO itemAssetNumberMapDTO) {
        log.debug("Request to partially update ItemAssetNumberMap : {}", itemAssetNumberMapDTO);

        return itemAssetNumberMapRepository
            .findById(itemAssetNumberMapDTO.getItemAssetNumberId())
            .map(existingItemAssetNumberMap -> {
                itemAssetNumberMapMapper.partialUpdate(existingItemAssetNumberMap, itemAssetNumberMapDTO);

                return existingItemAssetNumberMap;
            })
            .map(itemAssetNumberMapRepository::save)
            .map(itemAssetNumberMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemAssetNumberMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemAssetNumberMaps");
        return itemAssetNumberMapRepository.findAll(pageable).map(itemAssetNumberMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemAssetNumberMapDTO> findOne(Long id) {
        log.debug("Request to get ItemAssetNumberMap : {}", id);
        return itemAssetNumberMapRepository.findById(id).map(itemAssetNumberMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemAssetNumberMap : {}", id);
        itemAssetNumberMapRepository.deleteById(id);
    }
}
