package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatusAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemInventoryStatusAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemInventoryStatusAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemInventoryStatusAuditLog}.
 */
@Service
@Transactional
public class ItemInventoryStatusAuditLogServiceImpl implements ItemInventoryStatusAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemInventoryStatusAuditLogServiceImpl.class);

    private final ItemInventoryStatusAuditLogRepository itemInventoryStatusAuditLogRepository;

    private final ItemInventoryStatusAuditLogMapper itemInventoryStatusAuditLogMapper;

    public ItemInventoryStatusAuditLogServiceImpl(
        ItemInventoryStatusAuditLogRepository itemInventoryStatusAuditLogRepository,
        ItemInventoryStatusAuditLogMapper itemInventoryStatusAuditLogMapper
    ) {
        this.itemInventoryStatusAuditLogRepository = itemInventoryStatusAuditLogRepository;
        this.itemInventoryStatusAuditLogMapper = itemInventoryStatusAuditLogMapper;
    }

    @Override
    public ItemInventoryStatusAuditLogDTO save(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO) {
        log.debug("Request to save ItemInventoryStatusAuditLog : {}", itemInventoryStatusAuditLogDTO);
        ItemInventoryStatusAuditLog itemInventoryStatusAuditLog = itemInventoryStatusAuditLogMapper.toEntity(
            itemInventoryStatusAuditLogDTO
        );
        itemInventoryStatusAuditLog = itemInventoryStatusAuditLogRepository.save(itemInventoryStatusAuditLog);
        return itemInventoryStatusAuditLogMapper.toDto(itemInventoryStatusAuditLog);
    }

    @Override
    public ItemInventoryStatusAuditLogDTO update(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO) {
        log.debug("Request to update ItemInventoryStatusAuditLog : {}", itemInventoryStatusAuditLogDTO);
        ItemInventoryStatusAuditLog itemInventoryStatusAuditLog = itemInventoryStatusAuditLogMapper.toEntity(
            itemInventoryStatusAuditLogDTO
        );
        itemInventoryStatusAuditLog = itemInventoryStatusAuditLogRepository.save(itemInventoryStatusAuditLog);
        return itemInventoryStatusAuditLogMapper.toDto(itemInventoryStatusAuditLog);
    }

    @Override
    public Optional<ItemInventoryStatusAuditLogDTO> partialUpdate(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO) {
        log.debug("Request to partially update ItemInventoryStatusAuditLog : {}", itemInventoryStatusAuditLogDTO);

        return itemInventoryStatusAuditLogRepository
            .findById(itemInventoryStatusAuditLogDTO.getId())
            .map(existingItemInventoryStatusAuditLog -> {
                itemInventoryStatusAuditLogMapper.partialUpdate(existingItemInventoryStatusAuditLog, itemInventoryStatusAuditLogDTO);

                return existingItemInventoryStatusAuditLog;
            })
            .map(itemInventoryStatusAuditLogRepository::save)
            .map(itemInventoryStatusAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemInventoryStatusAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemInventoryStatusAuditLogs");
        return itemInventoryStatusAuditLogRepository.findAll(pageable).map(itemInventoryStatusAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemInventoryStatusAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemInventoryStatusAuditLog : {}", id);
        return itemInventoryStatusAuditLogRepository.findById(id).map(itemInventoryStatusAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemInventoryStatusAuditLog : {}", id);
        itemInventoryStatusAuditLogRepository.deleteById(id);
    }
}
