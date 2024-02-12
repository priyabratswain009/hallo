package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusRepository;
import com.sunknowledge.dme.rcm.service.ItemInventoryStatusService;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemInventoryStatusMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemInventoryStatus}.
 */
@Service
@Transactional
public class ItemInventoryStatusServiceImpl implements ItemInventoryStatusService {

    private final Logger log = LoggerFactory.getLogger(ItemInventoryStatusServiceImpl.class);

    private final ItemInventoryStatusRepository itemInventoryStatusRepository;

    private final ItemInventoryStatusMapper itemInventoryStatusMapper;

    public ItemInventoryStatusServiceImpl(
        ItemInventoryStatusRepository itemInventoryStatusRepository,
        ItemInventoryStatusMapper itemInventoryStatusMapper
    ) {
        this.itemInventoryStatusRepository = itemInventoryStatusRepository;
        this.itemInventoryStatusMapper = itemInventoryStatusMapper;
    }

    @Override
    public ItemInventoryStatusDTO save(ItemInventoryStatusDTO itemInventoryStatusDTO) {
        log.debug("Request to save ItemInventoryStatus : {}", itemInventoryStatusDTO);
        ItemInventoryStatus itemInventoryStatus = itemInventoryStatusMapper.toEntity(itemInventoryStatusDTO);
        itemInventoryStatus = itemInventoryStatusRepository.save(itemInventoryStatus);
        return itemInventoryStatusMapper.toDto(itemInventoryStatus);
    }

    @Override
    public ItemInventoryStatusDTO update(ItemInventoryStatusDTO itemInventoryStatusDTO) {
        log.debug("Request to save ItemInventoryStatus : {}", itemInventoryStatusDTO);
        ItemInventoryStatus itemInventoryStatus = itemInventoryStatusMapper.toEntity(itemInventoryStatusDTO);
        itemInventoryStatus = itemInventoryStatusRepository.save(itemInventoryStatus);
        return itemInventoryStatusMapper.toDto(itemInventoryStatus);
    }

    @Override
    public Optional<ItemInventoryStatusDTO> partialUpdate(ItemInventoryStatusDTO itemInventoryStatusDTO) {
        log.debug("Request to partially update ItemInventoryStatus : {}", itemInventoryStatusDTO);

        return itemInventoryStatusRepository
            .findById(itemInventoryStatusDTO.getItemInventoryStatusId())
            .map(existingItemInventoryStatus -> {
                itemInventoryStatusMapper.partialUpdate(existingItemInventoryStatus, itemInventoryStatusDTO);

                return existingItemInventoryStatus;
            })
            .map(itemInventoryStatusRepository::save)
            .map(itemInventoryStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemInventoryStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemInventoryStatuses");
        return itemInventoryStatusRepository.findAll(pageable).map(itemInventoryStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemInventoryStatusDTO> findOne(Long id) {
        log.debug("Request to get ItemInventoryStatus : {}", id);
        return itemInventoryStatusRepository.findById(id).map(itemInventoryStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemInventoryStatus : {}", id);
        itemInventoryStatusRepository.deleteById(id);
    }
}
