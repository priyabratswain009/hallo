package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemVendorMappingAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemVendorMappingAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemVendorMappingAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemVendorMappingAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemVendorMappingAuditLog}.
 */
@Service
@Transactional
public class ItemVendorMappingAuditLogServiceImpl implements ItemVendorMappingAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemVendorMappingAuditLogServiceImpl.class);

    private final ItemVendorMappingAuditLogRepository itemVendorMappingAuditLogRepository;

    private final ItemVendorMappingAuditLogMapper itemVendorMappingAuditLogMapper;

    public ItemVendorMappingAuditLogServiceImpl(
        ItemVendorMappingAuditLogRepository itemVendorMappingAuditLogRepository,
        ItemVendorMappingAuditLogMapper itemVendorMappingAuditLogMapper
    ) {
        this.itemVendorMappingAuditLogRepository = itemVendorMappingAuditLogRepository;
        this.itemVendorMappingAuditLogMapper = itemVendorMappingAuditLogMapper;
    }

    @Override
    public ItemVendorMappingAuditLogDTO save(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO) {
        log.debug("Request to save ItemVendorMappingAuditLog : {}", itemVendorMappingAuditLogDTO);
        ItemVendorMappingAuditLog itemVendorMappingAuditLog = itemVendorMappingAuditLogMapper.toEntity(itemVendorMappingAuditLogDTO);
        itemVendorMappingAuditLog = itemVendorMappingAuditLogRepository.save(itemVendorMappingAuditLog);
        return itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);
    }

    @Override
    public ItemVendorMappingAuditLogDTO update(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO) {
        log.debug("Request to save ItemVendorMappingAuditLog : {}", itemVendorMappingAuditLogDTO);
        ItemVendorMappingAuditLog itemVendorMappingAuditLog = itemVendorMappingAuditLogMapper.toEntity(itemVendorMappingAuditLogDTO);
        itemVendorMappingAuditLog = itemVendorMappingAuditLogRepository.save(itemVendorMappingAuditLog);
        return itemVendorMappingAuditLogMapper.toDto(itemVendorMappingAuditLog);
    }

    @Override
    public Optional<ItemVendorMappingAuditLogDTO> partialUpdate(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO) {
        log.debug("Request to partially update ItemVendorMappingAuditLog : {}", itemVendorMappingAuditLogDTO);

        return itemVendorMappingAuditLogRepository
            .findById(itemVendorMappingAuditLogDTO.getId())
            .map(existingItemVendorMappingAuditLog -> {
                itemVendorMappingAuditLogMapper.partialUpdate(existingItemVendorMappingAuditLog, itemVendorMappingAuditLogDTO);

                return existingItemVendorMappingAuditLog;
            })
            .map(itemVendorMappingAuditLogRepository::save)
            .map(itemVendorMappingAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemVendorMappingAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemVendorMappingAuditLogs");
        return itemVendorMappingAuditLogRepository.findAll(pageable).map(itemVendorMappingAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemVendorMappingAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemVendorMappingAuditLog : {}", id);
        return itemVendorMappingAuditLogRepository.findById(id).map(itemVendorMappingAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemVendorMappingAuditLog : {}", id);
        itemVendorMappingAuditLogRepository.deleteById(id);
    }
}
