package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemLocationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemLocationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemLocationAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemLocationAuditLog}.
 */
@Service
@Transactional
public class ItemLocationAuditLogServiceImpl implements ItemLocationAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemLocationAuditLogServiceImpl.class);

    private final ItemLocationAuditLogRepository itemLocationAuditLogRepository;

    private final ItemLocationAuditLogMapper itemLocationAuditLogMapper;

    public ItemLocationAuditLogServiceImpl(
        ItemLocationAuditLogRepository itemLocationAuditLogRepository,
        ItemLocationAuditLogMapper itemLocationAuditLogMapper
    ) {
        this.itemLocationAuditLogRepository = itemLocationAuditLogRepository;
        this.itemLocationAuditLogMapper = itemLocationAuditLogMapper;
    }

    @Override
    public ItemLocationAuditLogDTO save(ItemLocationAuditLogDTO itemLocationAuditLogDTO) {
        log.debug("Request to save ItemLocationAuditLog : {}", itemLocationAuditLogDTO);
        ItemLocationAuditLog itemLocationAuditLog = itemLocationAuditLogMapper.toEntity(itemLocationAuditLogDTO);
        itemLocationAuditLog = itemLocationAuditLogRepository.save(itemLocationAuditLog);
        return itemLocationAuditLogMapper.toDto(itemLocationAuditLog);
    }

    @Override
    public ItemLocationAuditLogDTO update(ItemLocationAuditLogDTO itemLocationAuditLogDTO) {
        log.debug("Request to update ItemLocationAuditLog : {}", itemLocationAuditLogDTO);
        ItemLocationAuditLog itemLocationAuditLog = itemLocationAuditLogMapper.toEntity(itemLocationAuditLogDTO);
        itemLocationAuditLog = itemLocationAuditLogRepository.save(itemLocationAuditLog);
        return itemLocationAuditLogMapper.toDto(itemLocationAuditLog);
    }

    @Override
    public Optional<ItemLocationAuditLogDTO> partialUpdate(ItemLocationAuditLogDTO itemLocationAuditLogDTO) {
        log.debug("Request to partially update ItemLocationAuditLog : {}", itemLocationAuditLogDTO);

        return itemLocationAuditLogRepository
            .findById(itemLocationAuditLogDTO.getId())
            .map(existingItemLocationAuditLog -> {
                itemLocationAuditLogMapper.partialUpdate(existingItemLocationAuditLog, itemLocationAuditLogDTO);

                return existingItemLocationAuditLog;
            })
            .map(itemLocationAuditLogRepository::save)
            .map(itemLocationAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemLocationAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemLocationAuditLogs");
        return itemLocationAuditLogRepository.findAll(pageable).map(itemLocationAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemLocationAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemLocationAuditLog : {}", id);
        return itemLocationAuditLogRepository.findById(id).map(itemLocationAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemLocationAuditLog : {}", id);
        itemLocationAuditLogRepository.deleteById(id);
    }
}
