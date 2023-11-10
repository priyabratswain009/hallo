package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ManufacturerAuditLog;
import com.sunknowledge.dme.rcm.repository.ManufacturerAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ManufacturerAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ManufacturerAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ManufacturerAuditLog}.
 */
@Service
@Transactional
public class ManufacturerAuditLogServiceImpl implements ManufacturerAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ManufacturerAuditLogServiceImpl.class);

    private final ManufacturerAuditLogRepository manufacturerAuditLogRepository;

    private final ManufacturerAuditLogMapper manufacturerAuditLogMapper;

    public ManufacturerAuditLogServiceImpl(
        ManufacturerAuditLogRepository manufacturerAuditLogRepository,
        ManufacturerAuditLogMapper manufacturerAuditLogMapper
    ) {
        this.manufacturerAuditLogRepository = manufacturerAuditLogRepository;
        this.manufacturerAuditLogMapper = manufacturerAuditLogMapper;
    }

    @Override
    public ManufacturerAuditLogDTO save(ManufacturerAuditLogDTO manufacturerAuditLogDTO) {
        log.debug("Request to save ManufacturerAuditLog : {}", manufacturerAuditLogDTO);
        ManufacturerAuditLog manufacturerAuditLog = manufacturerAuditLogMapper.toEntity(manufacturerAuditLogDTO);
        manufacturerAuditLog = manufacturerAuditLogRepository.save(manufacturerAuditLog);
        return manufacturerAuditLogMapper.toDto(manufacturerAuditLog);
    }

    @Override
    public ManufacturerAuditLogDTO update(ManufacturerAuditLogDTO manufacturerAuditLogDTO) {
        log.debug("Request to update ManufacturerAuditLog : {}", manufacturerAuditLogDTO);
        ManufacturerAuditLog manufacturerAuditLog = manufacturerAuditLogMapper.toEntity(manufacturerAuditLogDTO);
        manufacturerAuditLog = manufacturerAuditLogRepository.save(manufacturerAuditLog);
        return manufacturerAuditLogMapper.toDto(manufacturerAuditLog);
    }

    @Override
    public Optional<ManufacturerAuditLogDTO> partialUpdate(ManufacturerAuditLogDTO manufacturerAuditLogDTO) {
        log.debug("Request to partially update ManufacturerAuditLog : {}", manufacturerAuditLogDTO);

        return manufacturerAuditLogRepository
            .findById(manufacturerAuditLogDTO.getId())
            .map(existingManufacturerAuditLog -> {
                manufacturerAuditLogMapper.partialUpdate(existingManufacturerAuditLog, manufacturerAuditLogDTO);

                return existingManufacturerAuditLog;
            })
            .map(manufacturerAuditLogRepository::save)
            .map(manufacturerAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ManufacturerAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ManufacturerAuditLogs");
        return manufacturerAuditLogRepository.findAll(pageable).map(manufacturerAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ManufacturerAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ManufacturerAuditLog : {}", id);
        return manufacturerAuditLogRepository.findById(id).map(manufacturerAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ManufacturerAuditLog : {}", id);
        manufacturerAuditLogRepository.deleteById(id);
    }
}
