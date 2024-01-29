package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemVendorMapping;
import com.sunknowledge.dme.rcm.repository.ItemVendorMappingRepository;
import com.sunknowledge.dme.rcm.service.ItemVendorMappingService;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemVendorMappingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemVendorMapping}.
 */
@Service
@Transactional
public class ItemVendorMappingServiceImpl implements ItemVendorMappingService {

    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingServiceImpl.class);

    private final ItemVendorMappingRepository itemVendorMappingRepository;

    private final ItemVendorMappingMapper itemVendorMappingMapper;

    public ItemVendorMappingServiceImpl(
        ItemVendorMappingRepository itemVendorMappingRepository,
        ItemVendorMappingMapper itemVendorMappingMapper
    ) {
        this.itemVendorMappingRepository = itemVendorMappingRepository;
        this.itemVendorMappingMapper = itemVendorMappingMapper;
    }

    @Override
    public ItemVendorMappingDTO save(ItemVendorMappingDTO itemVendorMappingDTO) {
        log.debug("Request to save ItemVendorMapping : {}", itemVendorMappingDTO);
        ItemVendorMapping itemVendorMapping = itemVendorMappingMapper.toEntity(itemVendorMappingDTO);
        itemVendorMapping = itemVendorMappingRepository.save(itemVendorMapping);
        return itemVendorMappingMapper.toDto(itemVendorMapping);
    }

    @Override
    public ItemVendorMappingDTO update(ItemVendorMappingDTO itemVendorMappingDTO) {
        log.debug("Request to save ItemVendorMapping : {}", itemVendorMappingDTO);
        ItemVendorMapping itemVendorMapping = itemVendorMappingMapper.toEntity(itemVendorMappingDTO);
        itemVendorMapping = itemVendorMappingRepository.save(itemVendorMapping);
        return itemVendorMappingMapper.toDto(itemVendorMapping);
    }

    @Override
    public Optional<ItemVendorMappingDTO> partialUpdate(ItemVendorMappingDTO itemVendorMappingDTO) {
        log.debug("Request to partially update ItemVendorMapping : {}", itemVendorMappingDTO);

        return itemVendorMappingRepository
            .findById(itemVendorMappingDTO.getItemVendorId())
            .map(existingItemVendorMapping -> {
                itemVendorMappingMapper.partialUpdate(existingItemVendorMapping, itemVendorMappingDTO);

                return existingItemVendorMapping;
            })
            .map(itemVendorMappingRepository::save)
            .map(itemVendorMappingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemVendorMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemVendorMappings");
        return itemVendorMappingRepository.findAll(pageable).map(itemVendorMappingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemVendorMappingDTO> findOne(Long id) {
        log.debug("Request to get ItemVendorMapping : {}", id);
        return itemVendorMappingRepository.findById(id).map(itemVendorMappingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemVendorMapping : {}", id);
        itemVendorMappingRepository.deleteById(id);
    }
}
