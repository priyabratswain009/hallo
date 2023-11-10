package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumberAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemSerialNumberAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemSerialNumberAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemSerialNumberAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemSerialNumberAuditLog}.
 */
@Service
@Transactional
public class ItemSerialNumberAuditLogServiceImpl implements ItemSerialNumberAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemSerialNumberAuditLogServiceImpl.class);

    private final ItemSerialNumberAuditLogRepository itemSerialNumberAuditLogRepository;

    private final ItemSerialNumberAuditLogMapper itemSerialNumberAuditLogMapper;

    public ItemSerialNumberAuditLogServiceImpl(
        ItemSerialNumberAuditLogRepository itemSerialNumberAuditLogRepository,
        ItemSerialNumberAuditLogMapper itemSerialNumberAuditLogMapper
    ) {
        this.itemSerialNumberAuditLogRepository = itemSerialNumberAuditLogRepository;
        this.itemSerialNumberAuditLogMapper = itemSerialNumberAuditLogMapper;
    }

    @Override
    public ItemSerialNumberAuditLogDTO save(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO) {
        log.debug("Request to save ItemSerialNumberAuditLog : {}", itemSerialNumberAuditLogDTO);
        ItemSerialNumberAuditLog itemSerialNumberAuditLog = itemSerialNumberAuditLogMapper.toEntity(itemSerialNumberAuditLogDTO);
        itemSerialNumberAuditLog = itemSerialNumberAuditLogRepository.save(itemSerialNumberAuditLog);
        return itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);
    }

    @Override
    public ItemSerialNumberAuditLogDTO update(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO) {
        log.debug("Request to update ItemSerialNumberAuditLog : {}", itemSerialNumberAuditLogDTO);
        ItemSerialNumberAuditLog itemSerialNumberAuditLog = itemSerialNumberAuditLogMapper.toEntity(itemSerialNumberAuditLogDTO);
        itemSerialNumberAuditLog = itemSerialNumberAuditLogRepository.save(itemSerialNumberAuditLog);
        return itemSerialNumberAuditLogMapper.toDto(itemSerialNumberAuditLog);
    }

    @Override
    public Optional<ItemSerialNumberAuditLogDTO> partialUpdate(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO) {
        log.debug("Request to partially update ItemSerialNumberAuditLog : {}", itemSerialNumberAuditLogDTO);

        return itemSerialNumberAuditLogRepository
            .findById(itemSerialNumberAuditLogDTO.getId())
            .map(existingItemSerialNumberAuditLog -> {
                itemSerialNumberAuditLogMapper.partialUpdate(existingItemSerialNumberAuditLog, itemSerialNumberAuditLogDTO);

                return existingItemSerialNumberAuditLog;
            })
            .map(itemSerialNumberAuditLogRepository::save)
            .map(itemSerialNumberAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemSerialNumberAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemSerialNumberAuditLogs");
        return itemSerialNumberAuditLogRepository.findAll(pageable).map(itemSerialNumberAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemSerialNumberAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemSerialNumberAuditLog : {}", id);
        return itemSerialNumberAuditLogRepository.findById(id).map(itemSerialNumberAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemSerialNumberAuditLog : {}", id);
        itemSerialNumberAuditLogRepository.deleteById(id);
    }
}
