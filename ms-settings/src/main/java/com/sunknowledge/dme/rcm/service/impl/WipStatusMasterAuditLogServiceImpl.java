package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.WipStatusMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.WipStatusMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.WipStatusMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipStatusMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WipStatusMasterAuditLog}.
 */
@Service
@Transactional
public class WipStatusMasterAuditLogServiceImpl implements WipStatusMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(WipStatusMasterAuditLogServiceImpl.class);

    private final WipStatusMasterAuditLogRepository wipStatusMasterAuditLogRepository;

    private final WipStatusMasterAuditLogMapper wipStatusMasterAuditLogMapper;

    public WipStatusMasterAuditLogServiceImpl(
        WipStatusMasterAuditLogRepository wipStatusMasterAuditLogRepository,
        WipStatusMasterAuditLogMapper wipStatusMasterAuditLogMapper
    ) {
        this.wipStatusMasterAuditLogRepository = wipStatusMasterAuditLogRepository;
        this.wipStatusMasterAuditLogMapper = wipStatusMasterAuditLogMapper;
    }

    @Override
    public WipStatusMasterAuditLogDTO save(WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO) {
        log.debug("Request to save WipStatusMasterAuditLog : {}", wipStatusMasterAuditLogDTO);
        WipStatusMasterAuditLog wipStatusMasterAuditLog = wipStatusMasterAuditLogMapper.toEntity(wipStatusMasterAuditLogDTO);
        wipStatusMasterAuditLog = wipStatusMasterAuditLogRepository.save(wipStatusMasterAuditLog);
        return wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);
    }

    @Override
    public WipStatusMasterAuditLogDTO update(WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO) {
        log.debug("Request to update WipStatusMasterAuditLog : {}", wipStatusMasterAuditLogDTO);
        WipStatusMasterAuditLog wipStatusMasterAuditLog = wipStatusMasterAuditLogMapper.toEntity(wipStatusMasterAuditLogDTO);
        wipStatusMasterAuditLog = wipStatusMasterAuditLogRepository.save(wipStatusMasterAuditLog);
        return wipStatusMasterAuditLogMapper.toDto(wipStatusMasterAuditLog);
    }

    @Override
    public Optional<WipStatusMasterAuditLogDTO> partialUpdate(WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO) {
        log.debug("Request to partially update WipStatusMasterAuditLog : {}", wipStatusMasterAuditLogDTO);

        return wipStatusMasterAuditLogRepository
            .findById(wipStatusMasterAuditLogDTO.getId())
            .map(existingWipStatusMasterAuditLog -> {
                wipStatusMasterAuditLogMapper.partialUpdate(existingWipStatusMasterAuditLog, wipStatusMasterAuditLogDTO);

                return existingWipStatusMasterAuditLog;
            })
            .map(wipStatusMasterAuditLogRepository::save)
            .map(wipStatusMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WipStatusMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WipStatusMasterAuditLogs");
        return wipStatusMasterAuditLogRepository.findAll(pageable).map(wipStatusMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WipStatusMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get WipStatusMasterAuditLog : {}", id);
        return wipStatusMasterAuditLogRepository.findById(id).map(wipStatusMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WipStatusMasterAuditLog : {}", id);
        wipStatusMasterAuditLogRepository.deleteById(id);
    }
}
