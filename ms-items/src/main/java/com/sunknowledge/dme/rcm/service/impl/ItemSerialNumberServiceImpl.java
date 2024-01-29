package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.repository.ItemSerialNumberRepository;
import com.sunknowledge.dme.rcm.service.ItemSerialNumberService;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemSerialNumberMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemSerialNumber}.
 */
@Service
@Transactional
public class ItemSerialNumberServiceImpl implements ItemSerialNumberService {

    private final Logger log = LoggerFactory.getLogger(ItemSerialNumberServiceImpl.class);

    private final ItemSerialNumberRepository itemSerialNumberRepository;

    private final ItemSerialNumberMapper itemSerialNumberMapper;

    public ItemSerialNumberServiceImpl(
        ItemSerialNumberRepository itemSerialNumberRepository,
        ItemSerialNumberMapper itemSerialNumberMapper
    ) {
        this.itemSerialNumberRepository = itemSerialNumberRepository;
        this.itemSerialNumberMapper = itemSerialNumberMapper;
    }

    @Override
    public ItemSerialNumberDTO save(ItemSerialNumberDTO itemSerialNumberDTO) {
        log.debug("Request to save ItemSerialNumber : {}", itemSerialNumberDTO);
        ItemSerialNumber itemSerialNumber = itemSerialNumberMapper.toEntity(itemSerialNumberDTO);
        itemSerialNumber = itemSerialNumberRepository.save(itemSerialNumber);
        return itemSerialNumberMapper.toDto(itemSerialNumber);
    }

    @Override
    public ItemSerialNumberDTO update(ItemSerialNumberDTO itemSerialNumberDTO) {
        log.debug("Request to save ItemSerialNumber : {}", itemSerialNumberDTO);
        ItemSerialNumber itemSerialNumber = itemSerialNumberMapper.toEntity(itemSerialNumberDTO);
        itemSerialNumber = itemSerialNumberRepository.save(itemSerialNumber);
        return itemSerialNumberMapper.toDto(itemSerialNumber);
    }

    @Override
    public Optional<ItemSerialNumberDTO> partialUpdate(ItemSerialNumberDTO itemSerialNumberDTO) {
        log.debug("Request to partially update ItemSerialNumber : {}", itemSerialNumberDTO);

        return itemSerialNumberRepository
            .findById(itemSerialNumberDTO.getItemSerialNumberId())
            .map(existingItemSerialNumber -> {
                itemSerialNumberMapper.partialUpdate(existingItemSerialNumber, itemSerialNumberDTO);

                return existingItemSerialNumber;
            })
            .map(itemSerialNumberRepository::save)
            .map(itemSerialNumberMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemSerialNumberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemSerialNumbers");
        return itemSerialNumberRepository.findAll(pageable).map(itemSerialNumberMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemSerialNumberDTO> findOne(Long id) {
        log.debug("Request to get ItemSerialNumber : {}", id);
        return itemSerialNumberRepository.findById(id).map(itemSerialNumberMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemSerialNumber : {}", id);
        itemSerialNumberRepository.deleteById(id);
    }
}
