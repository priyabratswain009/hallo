package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemGroupAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemGroupAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemGroupAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemGroupAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemGroupAuditLog}.
 */
@Service
@Transactional
public class ItemGroupAuditLogServiceImpl implements ItemGroupAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemGroupAuditLogServiceImpl.class);

    private final ItemGroupAuditLogRepository itemGroupAuditLogRepository;

    private final ItemGroupAuditLogMapper itemGroupAuditLogMapper;

    public ItemGroupAuditLogServiceImpl(
        ItemGroupAuditLogRepository itemGroupAuditLogRepository,
        ItemGroupAuditLogMapper itemGroupAuditLogMapper
    ) {
        this.itemGroupAuditLogRepository = itemGroupAuditLogRepository;
        this.itemGroupAuditLogMapper = itemGroupAuditLogMapper;
    }

    @Override
    public ItemGroupAuditLogDTO save(ItemGroupAuditLogDTO itemGroupAuditLogDTO) {
        log.debug("Request to save ItemGroupAuditLog : {}", itemGroupAuditLogDTO);
        ItemGroupAuditLog itemGroupAuditLog = itemGroupAuditLogMapper.toEntity(itemGroupAuditLogDTO);
        itemGroupAuditLog = itemGroupAuditLogRepository.save(itemGroupAuditLog);
        return itemGroupAuditLogMapper.toDto(itemGroupAuditLog);
    }

    @Override
    public ItemGroupAuditLogDTO update(ItemGroupAuditLogDTO itemGroupAuditLogDTO) {
        log.debug("Request to update ItemGroupAuditLog : {}", itemGroupAuditLogDTO);
        ItemGroupAuditLog itemGroupAuditLog = itemGroupAuditLogMapper.toEntity(itemGroupAuditLogDTO);
        itemGroupAuditLog = itemGroupAuditLogRepository.save(itemGroupAuditLog);
        return itemGroupAuditLogMapper.toDto(itemGroupAuditLog);
    }

    @Override
    public Optional<ItemGroupAuditLogDTO> partialUpdate(ItemGroupAuditLogDTO itemGroupAuditLogDTO) {
        log.debug("Request to partially update ItemGroupAuditLog : {}", itemGroupAuditLogDTO);

        return itemGroupAuditLogRepository
            .findById(itemGroupAuditLogDTO.getId())
            .map(existingItemGroupAuditLog -> {
                itemGroupAuditLogMapper.partialUpdate(existingItemGroupAuditLog, itemGroupAuditLogDTO);

                return existingItemGroupAuditLog;
            })
            .map(itemGroupAuditLogRepository::save)
            .map(itemGroupAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemGroupAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemGroupAuditLogs");
        return itemGroupAuditLogRepository.findAll(pageable).map(itemGroupAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemGroupAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemGroupAuditLog : {}", id);
        return itemGroupAuditLogRepository.findById(id).map(itemGroupAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemGroupAuditLog : {}", id);
        itemGroupAuditLogRepository.deleteById(id);
    }
}
