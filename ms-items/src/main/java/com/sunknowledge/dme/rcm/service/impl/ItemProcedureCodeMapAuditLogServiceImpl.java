package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMapAuditLog;
import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ItemProcedureCodeMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemProcedureCodeMapAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemProcedureCodeMapAuditLog}.
 */
@Service
@Transactional
public class ItemProcedureCodeMapAuditLogServiceImpl implements ItemProcedureCodeMapAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ItemProcedureCodeMapAuditLogServiceImpl.class);

    private final ItemProcedureCodeMapAuditLogRepository itemProcedureCodeMapAuditLogRepository;

    private final ItemProcedureCodeMapAuditLogMapper itemProcedureCodeMapAuditLogMapper;

    public ItemProcedureCodeMapAuditLogServiceImpl(
        ItemProcedureCodeMapAuditLogRepository itemProcedureCodeMapAuditLogRepository,
        ItemProcedureCodeMapAuditLogMapper itemProcedureCodeMapAuditLogMapper
    ) {
        this.itemProcedureCodeMapAuditLogRepository = itemProcedureCodeMapAuditLogRepository;
        this.itemProcedureCodeMapAuditLogMapper = itemProcedureCodeMapAuditLogMapper;
    }

    @Override
    public ItemProcedureCodeMapAuditLogDTO save(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO) {
        log.debug("Request to save ItemProcedureCodeMapAuditLog : {}", itemProcedureCodeMapAuditLogDTO);
        ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogMapper.toEntity(
            itemProcedureCodeMapAuditLogDTO
        );
        itemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogRepository.save(itemProcedureCodeMapAuditLog);
        return itemProcedureCodeMapAuditLogMapper.toDto(itemProcedureCodeMapAuditLog);
    }

    @Override
    public ItemProcedureCodeMapAuditLogDTO update(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO) {
        log.debug("Request to update ItemProcedureCodeMapAuditLog : {}", itemProcedureCodeMapAuditLogDTO);
        ItemProcedureCodeMapAuditLog itemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogMapper.toEntity(
            itemProcedureCodeMapAuditLogDTO
        );
        itemProcedureCodeMapAuditLog = itemProcedureCodeMapAuditLogRepository.save(itemProcedureCodeMapAuditLog);
        return itemProcedureCodeMapAuditLogMapper.toDto(itemProcedureCodeMapAuditLog);
    }

    @Override
    public Optional<ItemProcedureCodeMapAuditLogDTO> partialUpdate(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO) {
        log.debug("Request to partially update ItemProcedureCodeMapAuditLog : {}", itemProcedureCodeMapAuditLogDTO);

        return itemProcedureCodeMapAuditLogRepository
            .findById(itemProcedureCodeMapAuditLogDTO.getId())
            .map(existingItemProcedureCodeMapAuditLog -> {
                itemProcedureCodeMapAuditLogMapper.partialUpdate(existingItemProcedureCodeMapAuditLog, itemProcedureCodeMapAuditLogDTO);

                return existingItemProcedureCodeMapAuditLog;
            })
            .map(itemProcedureCodeMapAuditLogRepository::save)
            .map(itemProcedureCodeMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemProcedureCodeMapAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemProcedureCodeMapAuditLogs");
        return itemProcedureCodeMapAuditLogRepository.findAll(pageable).map(itemProcedureCodeMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemProcedureCodeMapAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ItemProcedureCodeMapAuditLog : {}", id);
        return itemProcedureCodeMapAuditLogRepository.findById(id).map(itemProcedureCodeMapAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemProcedureCodeMapAuditLog : {}", id);
        itemProcedureCodeMapAuditLogRepository.deleteById(id);
    }
}
