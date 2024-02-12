package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.ClaimFormMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ClaimFormMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimFormMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimFormMasterAuditLog}.
 */
@Service
@Transactional
public class ClaimFormMasterAuditLogServiceImpl implements ClaimFormMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ClaimFormMasterAuditLogServiceImpl.class);

    private final ClaimFormMasterAuditLogRepository claimFormMasterAuditLogRepository;

    private final ClaimFormMasterAuditLogMapper claimFormMasterAuditLogMapper;

    public ClaimFormMasterAuditLogServiceImpl(
        ClaimFormMasterAuditLogRepository claimFormMasterAuditLogRepository,
        ClaimFormMasterAuditLogMapper claimFormMasterAuditLogMapper
    ) {
        this.claimFormMasterAuditLogRepository = claimFormMasterAuditLogRepository;
        this.claimFormMasterAuditLogMapper = claimFormMasterAuditLogMapper;
    }

    @Override
    public ClaimFormMasterAuditLogDTO save(ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO) {
        log.debug("Request to save ClaimFormMasterAuditLog : {}", claimFormMasterAuditLogDTO);
        ClaimFormMasterAuditLog claimFormMasterAuditLog = claimFormMasterAuditLogMapper.toEntity(claimFormMasterAuditLogDTO);
        claimFormMasterAuditLog = claimFormMasterAuditLogRepository.save(claimFormMasterAuditLog);
        return claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);
    }

    @Override
    public ClaimFormMasterAuditLogDTO update(ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO) {
        log.debug("Request to update ClaimFormMasterAuditLog : {}", claimFormMasterAuditLogDTO);
        ClaimFormMasterAuditLog claimFormMasterAuditLog = claimFormMasterAuditLogMapper.toEntity(claimFormMasterAuditLogDTO);
        claimFormMasterAuditLog = claimFormMasterAuditLogRepository.save(claimFormMasterAuditLog);
        return claimFormMasterAuditLogMapper.toDto(claimFormMasterAuditLog);
    }

    @Override
    public Optional<ClaimFormMasterAuditLogDTO> partialUpdate(ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO) {
        log.debug("Request to partially update ClaimFormMasterAuditLog : {}", claimFormMasterAuditLogDTO);

        return claimFormMasterAuditLogRepository
            .findById(claimFormMasterAuditLogDTO.getId())
            .map(existingClaimFormMasterAuditLog -> {
                claimFormMasterAuditLogMapper.partialUpdate(existingClaimFormMasterAuditLog, claimFormMasterAuditLogDTO);

                return existingClaimFormMasterAuditLog;
            })
            .map(claimFormMasterAuditLogRepository::save)
            .map(claimFormMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimFormMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimFormMasterAuditLogs");
        return claimFormMasterAuditLogRepository.findAll(pageable).map(claimFormMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimFormMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ClaimFormMasterAuditLog : {}", id);
        return claimFormMasterAuditLogRepository.findById(id).map(claimFormMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimFormMasterAuditLog : {}", id);
        claimFormMasterAuditLogRepository.deleteById(id);
    }
}
