package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemMasterAuditLog}.
 */
@Service
@Transactional
public class ItemMasterAuditLogServiceImpl implements ItemMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemMasterAuditLogServiceImpl.class);

    private final ItemMasterAuditLogRepository itemMasterAuditLogRepository;

    private final ItemMasterAuditLogMapper itemMasterAuditLogMapper;

    public ItemMasterAuditLogServiceImpl(
        ItemMasterAuditLogRepository itemMasterAuditLogRepository,
        ItemMasterAuditLogMapper itemMasterAuditLogMapper
    ) {
        this.itemMasterAuditLogRepository = itemMasterAuditLogRepository;
        this.itemMasterAuditLogMapper = itemMasterAuditLogMapper;
    }

    @Override
    public ItemMasterAuditLogDTO save(ItemMasterAuditLogDTO itemMasterAuditLogDTO) {
        log.debug("Request to save ItemMasterAuditLog : {}", itemMasterAuditLogDTO);
        ItemMasterAuditLog itemMasterAuditLog = itemMasterAuditLogMapper.toEntity(itemMasterAuditLogDTO);
        itemMasterAuditLog = itemMasterAuditLogRepository.save(itemMasterAuditLog);
        return itemMasterAuditLogMapper.toDto(itemMasterAuditLog);
    }

    @Override
    public ItemMasterAuditLogDTO update(ItemMasterAuditLogDTO itemMasterAuditLogDTO) {
        log.debug("Request to save ItemMasterAuditLog : {}", itemMasterAuditLogDTO);
        ItemMasterAuditLog itemMasterAuditLog = itemMasterAuditLogMapper.toEntity(itemMasterAuditLogDTO);
        itemMasterAuditLog = itemMasterAuditLogRepository.save(itemMasterAuditLog);
        return itemMasterAuditLogMapper.toDto(itemMasterAuditLog);
    }

    @Override
    public Optional<ItemMasterAuditLogDTO> partialUpdate(ItemMasterAuditLogDTO itemMasterAuditLogDTO) {
        log.debug("Request to partially update ItemMasterAuditLog : {}", itemMasterAuditLogDTO);

        return itemMasterAuditLogRepository
            .findById(itemMasterAuditLogDTO.getId())
            .map(existingItemMasterAuditLog -> {
                itemMasterAuditLogMapper.partialUpdate(existingItemMasterAuditLog, itemMasterAuditLogDTO);

                return existingItemMasterAuditLog;
            })
            .map(itemMasterAuditLogRepository::save)
            .map(itemMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemMasterAuditLogs");
        return itemMasterAuditLogRepository.findAll(pageable).map(itemMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemMasterAuditLog : {}", id);
        return itemMasterAuditLogRepository.findById(id).map(itemMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemMasterAuditLog : {}", id);
        itemMasterAuditLogRepository.deleteById(id);
    }
}
