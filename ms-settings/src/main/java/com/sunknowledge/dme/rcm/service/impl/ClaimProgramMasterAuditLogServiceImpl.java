package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.ClaimProgramMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.ClaimProgramMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimProgramMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimProgramMasterAuditLog}.
 */
@Service
@Transactional
public class ClaimProgramMasterAuditLogServiceImpl implements ClaimProgramMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(ClaimProgramMasterAuditLogServiceImpl.class);

    private final ClaimProgramMasterAuditLogRepository claimProgramMasterAuditLogRepository;

    private final ClaimProgramMasterAuditLogMapper claimProgramMasterAuditLogMapper;

    public ClaimProgramMasterAuditLogServiceImpl(
        ClaimProgramMasterAuditLogRepository claimProgramMasterAuditLogRepository,
        ClaimProgramMasterAuditLogMapper claimProgramMasterAuditLogMapper
    ) {
        this.claimProgramMasterAuditLogRepository = claimProgramMasterAuditLogRepository;
        this.claimProgramMasterAuditLogMapper = claimProgramMasterAuditLogMapper;
    }

    @Override
    public ClaimProgramMasterAuditLogDTO save(ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO) {
        log.debug("Request to save ClaimProgramMasterAuditLog : {}", claimProgramMasterAuditLogDTO);
        ClaimProgramMasterAuditLog claimProgramMasterAuditLog = claimProgramMasterAuditLogMapper.toEntity(claimProgramMasterAuditLogDTO);
        claimProgramMasterAuditLog = claimProgramMasterAuditLogRepository.save(claimProgramMasterAuditLog);
        return claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);
    }

    @Override
    public ClaimProgramMasterAuditLogDTO update(ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO) {
        log.debug("Request to update ClaimProgramMasterAuditLog : {}", claimProgramMasterAuditLogDTO);
        ClaimProgramMasterAuditLog claimProgramMasterAuditLog = claimProgramMasterAuditLogMapper.toEntity(claimProgramMasterAuditLogDTO);
        claimProgramMasterAuditLog = claimProgramMasterAuditLogRepository.save(claimProgramMasterAuditLog);
        return claimProgramMasterAuditLogMapper.toDto(claimProgramMasterAuditLog);
    }

    @Override
    public Optional<ClaimProgramMasterAuditLogDTO> partialUpdate(ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO) {
        log.debug("Request to partially update ClaimProgramMasterAuditLog : {}", claimProgramMasterAuditLogDTO);

        return claimProgramMasterAuditLogRepository
            .findById(claimProgramMasterAuditLogDTO.getId())
            .map(existingClaimProgramMasterAuditLog -> {
                claimProgramMasterAuditLogMapper.partialUpdate(existingClaimProgramMasterAuditLog, claimProgramMasterAuditLogDTO);

                return existingClaimProgramMasterAuditLog;
            })
            .map(claimProgramMasterAuditLogRepository::save)
            .map(claimProgramMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimProgramMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimProgramMasterAuditLogs");
        return claimProgramMasterAuditLogRepository.findAll(pageable).map(claimProgramMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimProgramMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get ClaimProgramMasterAuditLog : {}", id);
        return claimProgramMasterAuditLogRepository.findById(id).map(claimProgramMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimProgramMasterAuditLog : {}", id);
        claimProgramMasterAuditLogRepository.deleteById(id);
    }
}
