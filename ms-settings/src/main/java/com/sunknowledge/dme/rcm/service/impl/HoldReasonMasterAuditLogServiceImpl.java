package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.HoldReasonMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.HoldReasonMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.HoldReasonMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HoldReasonMasterAuditLog}.
 */
@Service
@Transactional
public class HoldReasonMasterAuditLogServiceImpl implements HoldReasonMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(HoldReasonMasterAuditLogServiceImpl.class);

    private final HoldReasonMasterAuditLogRepository holdReasonMasterAuditLogRepository;

    private final HoldReasonMasterAuditLogMapper holdReasonMasterAuditLogMapper;

    public HoldReasonMasterAuditLogServiceImpl(
        HoldReasonMasterAuditLogRepository holdReasonMasterAuditLogRepository,
        HoldReasonMasterAuditLogMapper holdReasonMasterAuditLogMapper
    ) {
        this.holdReasonMasterAuditLogRepository = holdReasonMasterAuditLogRepository;
        this.holdReasonMasterAuditLogMapper = holdReasonMasterAuditLogMapper;
    }

    @Override
    public HoldReasonMasterAuditLogDTO save(HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO) {
        log.debug("Request to save HoldReasonMasterAuditLog : {}", holdReasonMasterAuditLogDTO);
        HoldReasonMasterAuditLog holdReasonMasterAuditLog = holdReasonMasterAuditLogMapper.toEntity(holdReasonMasterAuditLogDTO);
        holdReasonMasterAuditLog = holdReasonMasterAuditLogRepository.save(holdReasonMasterAuditLog);
        return holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);
    }

    @Override
    public HoldReasonMasterAuditLogDTO update(HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO) {
        log.debug("Request to update HoldReasonMasterAuditLog : {}", holdReasonMasterAuditLogDTO);
        HoldReasonMasterAuditLog holdReasonMasterAuditLog = holdReasonMasterAuditLogMapper.toEntity(holdReasonMasterAuditLogDTO);
        holdReasonMasterAuditLog = holdReasonMasterAuditLogRepository.save(holdReasonMasterAuditLog);
        return holdReasonMasterAuditLogMapper.toDto(holdReasonMasterAuditLog);
    }

    @Override
    public Optional<HoldReasonMasterAuditLogDTO> partialUpdate(HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO) {
        log.debug("Request to partially update HoldReasonMasterAuditLog : {}", holdReasonMasterAuditLogDTO);

        return holdReasonMasterAuditLogRepository
            .findById(holdReasonMasterAuditLogDTO.getId())
            .map(existingHoldReasonMasterAuditLog -> {
                holdReasonMasterAuditLogMapper.partialUpdate(existingHoldReasonMasterAuditLog, holdReasonMasterAuditLogDTO);

                return existingHoldReasonMasterAuditLog;
            })
            .map(holdReasonMasterAuditLogRepository::save)
            .map(holdReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HoldReasonMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HoldReasonMasterAuditLogs");
        return holdReasonMasterAuditLogRepository.findAll(pageable).map(holdReasonMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HoldReasonMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get HoldReasonMasterAuditLog : {}", id);
        return holdReasonMasterAuditLogRepository.findById(id).map(holdReasonMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HoldReasonMasterAuditLog : {}", id);
        holdReasonMasterAuditLogRepository.deleteById(id);
    }
}
