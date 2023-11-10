package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemTypeAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemTypeAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemTypeAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemTypeAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemTypeAuditLog}.
 */
@Service
@Transactional
public class ItemTypeAuditLogServiceImpl implements ItemTypeAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemTypeAuditLogServiceImpl.class);

    private final ItemTypeAuditLogRepository itemTypeAuditLogRepository;

    private final ItemTypeAuditLogMapper itemTypeAuditLogMapper;

    public ItemTypeAuditLogServiceImpl(
        ItemTypeAuditLogRepository itemTypeAuditLogRepository,
        ItemTypeAuditLogMapper itemTypeAuditLogMapper
    ) {
        this.itemTypeAuditLogRepository = itemTypeAuditLogRepository;
        this.itemTypeAuditLogMapper = itemTypeAuditLogMapper;
    }

    @Override
    public ItemTypeAuditLogDTO save(ItemTypeAuditLogDTO itemTypeAuditLogDTO) {
        log.debug("Request to save ItemTypeAuditLog : {}", itemTypeAuditLogDTO);
        ItemTypeAuditLog itemTypeAuditLog = itemTypeAuditLogMapper.toEntity(itemTypeAuditLogDTO);
        itemTypeAuditLog = itemTypeAuditLogRepository.save(itemTypeAuditLog);
        return itemTypeAuditLogMapper.toDto(itemTypeAuditLog);
    }

    @Override
    public ItemTypeAuditLogDTO update(ItemTypeAuditLogDTO itemTypeAuditLogDTO) {
        log.debug("Request to update ItemTypeAuditLog : {}", itemTypeAuditLogDTO);
        ItemTypeAuditLog itemTypeAuditLog = itemTypeAuditLogMapper.toEntity(itemTypeAuditLogDTO);
        itemTypeAuditLog = itemTypeAuditLogRepository.save(itemTypeAuditLog);
        return itemTypeAuditLogMapper.toDto(itemTypeAuditLog);
    }

    @Override
    public Optional<ItemTypeAuditLogDTO> partialUpdate(ItemTypeAuditLogDTO itemTypeAuditLogDTO) {
        log.debug("Request to partially update ItemTypeAuditLog : {}", itemTypeAuditLogDTO);

        return itemTypeAuditLogRepository
            .findById(itemTypeAuditLogDTO.getId())
            .map(existingItemTypeAuditLog -> {
                itemTypeAuditLogMapper.partialUpdate(existingItemTypeAuditLog, itemTypeAuditLogDTO);

                return existingItemTypeAuditLog;
            })
            .map(itemTypeAuditLogRepository::save)
            .map(itemTypeAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemTypeAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemTypeAuditLogs");
        return itemTypeAuditLogRepository.findAll(pageable).map(itemTypeAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemTypeAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemTypeAuditLog : {}", id);
        return itemTypeAuditLogRepository.findById(id).map(itemTypeAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemTypeAuditLog : {}", id);
        itemTypeAuditLogRepository.deleteById(id);
    }
}
