package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapRepository;
import com.sunknowledge.dme.rcm.service.ItemProcedureCodeMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemProcedureCodeMapMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemProcedureCodeMap}.
 */
@Service
@Transactional
public class ItemProcedureCodeMapServiceImpl implements ItemProcedureCodeMapService {

    private final Logger log = LoggerFactory.getLogger(ItemProcedureCodeMapServiceImpl.class);

    private final ItemProcedureCodeMapRepository itemProcedureCodeMapRepository;

    private final ItemProcedureCodeMapMapper itemProcedureCodeMapMapper;

    public ItemProcedureCodeMapServiceImpl(
        ItemProcedureCodeMapRepository itemProcedureCodeMapRepository,
        ItemProcedureCodeMapMapper itemProcedureCodeMapMapper
    ) {
        this.itemProcedureCodeMapRepository = itemProcedureCodeMapRepository;
        this.itemProcedureCodeMapMapper = itemProcedureCodeMapMapper;
    }

    @Override
    public ItemProcedureCodeMapDTO save(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO) {
        log.debug("Request to save ItemProcedureCodeMap : {}", itemProcedureCodeMapDTO);
        ItemProcedureCodeMap itemProcedureCodeMap = itemProcedureCodeMapMapper.toEntity(itemProcedureCodeMapDTO);
        itemProcedureCodeMap = itemProcedureCodeMapRepository.save(itemProcedureCodeMap);
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);
    }

    @Override
    public ItemProcedureCodeMapDTO update(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO) {
        log.debug("Request to update ItemProcedureCodeMap : {}", itemProcedureCodeMapDTO);
        ItemProcedureCodeMap itemProcedureCodeMap = itemProcedureCodeMapMapper.toEntity(itemProcedureCodeMapDTO);
        itemProcedureCodeMap = itemProcedureCodeMapRepository.save(itemProcedureCodeMap);
        return itemProcedureCodeMapMapper.toDto(itemProcedureCodeMap);
    }

    @Override
    public Optional<ItemProcedureCodeMapDTO> partialUpdate(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO) {
        log.debug("Request to partially update ItemProcedureCodeMap : {}", itemProcedureCodeMapDTO);

        return itemProcedureCodeMapRepository
            .findById(itemProcedureCodeMapDTO.getItemProcedureCodeMapId())
            .map(existingItemProcedureCodeMap -> {
                itemProcedureCodeMapMapper.partialUpdate(existingItemProcedureCodeMap, itemProcedureCodeMapDTO);

                return existingItemProcedureCodeMap;
            })
            .map(itemProcedureCodeMapRepository::save)
            .map(itemProcedureCodeMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemProcedureCodeMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemProcedureCodeMaps");
        return itemProcedureCodeMapRepository.findAll(pageable).map(itemProcedureCodeMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemProcedureCodeMapDTO> findOne(Long id) {
        log.debug("Request to get ItemProcedureCodeMap : {}", id);
        return itemProcedureCodeMapRepository.findById(id).map(itemProcedureCodeMapMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemProcedureCodeMap : {}", id);
        itemProcedureCodeMapRepository.deleteById(id);
    }
}
