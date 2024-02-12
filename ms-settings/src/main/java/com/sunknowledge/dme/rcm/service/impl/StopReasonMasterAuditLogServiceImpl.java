package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.StopReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.StopReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.StopReasonMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StopReasonMasterAuditLog}.
 */
@Service
@Transactional
public class StopReasonMasterAuditLogServiceImpl implements StopReasonMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(StopReasonMasterAuditLogServiceImpl.class);

    private final StopReasonMasterAuditLogRepository stopReasonMasterAuditLogRepository;

    private final StopReasonMasterAuditLogMapper stopReasonMasterAuditLogMapper;

    public StopReasonMasterAuditLogServiceImpl(
        StopReasonMasterAuditLogRepository stopReasonMasterAuditLogRepository,
        StopReasonMasterAuditLogMapper stopReasonMasterAuditLogMapper
    ) {
        this.stopReasonMasterAuditLogRepository = stopReasonMasterAuditLogRepository;
        this.stopReasonMasterAuditLogMapper = stopReasonMasterAuditLogMapper;
    }

    @Override
    public StopReasonMasterAuditLogDTO save(StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO) {
        log.debug("Request to save StopReasonMasterAuditLog : {}", stopReasonMasterAuditLogDTO);
        StopReasonMasterAuditLog stopReasonMasterAuditLog = stopReasonMasterAuditLogMapper.toEntity(stopReasonMasterAuditLogDTO);
        stopReasonMasterAuditLog = stopReasonMasterAuditLogRepository.save(stopReasonMasterAuditLog);
        return stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);
    }

    @Override
    public StopReasonMasterAuditLogDTO update(StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO) {
        log.debug("Request to update StopReasonMasterAuditLog : {}", stopReasonMasterAuditLogDTO);
        StopReasonMasterAuditLog stopReasonMasterAuditLog = stopReasonMasterAuditLogMapper.toEntity(stopReasonMasterAuditLogDTO);
        stopReasonMasterAuditLog = stopReasonMasterAuditLogRepository.save(stopReasonMasterAuditLog);
        return stopReasonMasterAuditLogMapper.toDto(stopReasonMasterAuditLog);
    }

    @Override
    public Optional<StopReasonMasterAuditLogDTO> partialUpdate(StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO) {
        log.debug("Request to partially update StopReasonMasterAuditLog : {}", stopReasonMasterAuditLogDTO);

        return stopReasonMasterAuditLogRepository
            .findById(stopReasonMasterAuditLogDTO.getId())
            .map(existingStopReasonMasterAuditLog -> {
                stopReasonMasterAuditLogMapper.partialUpdate(existingStopReasonMasterAuditLog, stopReasonMasterAuditLogDTO);

                return existingStopReasonMasterAuditLog;
            })
            .map(stopReasonMasterAuditLogRepository::save)
            .map(stopReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StopReasonMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StopReasonMasterAuditLogs");
        return stopReasonMasterAuditLogRepository.findAll(pageable).map(stopReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StopReasonMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get StopReasonMasterAuditLog : {}", id);
        return stopReasonMasterAuditLogRepository.findById(id).map(stopReasonMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StopReasonMasterAuditLog : {}", id);
        stopReasonMasterAuditLogRepository.deleteById(id);
    }
}
