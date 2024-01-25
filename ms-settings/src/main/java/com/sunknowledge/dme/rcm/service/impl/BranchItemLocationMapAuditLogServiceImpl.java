package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchItemLocationMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchItemLocationMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchItemLocationMapAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchItemLocationMapAuditLog}.
 */
@Service
@Transactional
public class BranchItemLocationMapAuditLogServiceImpl implements BranchItemLocationMapAuditLogService {

    private final Logger log = LoggerFactory.getLogger(BranchItemLocationMapAuditLogServiceImpl.class);

    private final BranchItemLocationMapAuditLogRepository branchItemLocationMapAuditLogRepository;

    private final BranchItemLocationMapAuditLogMapper branchItemLocationMapAuditLogMapper;

    public BranchItemLocationMapAuditLogServiceImpl(
        BranchItemLocationMapAuditLogRepository branchItemLocationMapAuditLogRepository,
        BranchItemLocationMapAuditLogMapper branchItemLocationMapAuditLogMapper
    ) {
        this.branchItemLocationMapAuditLogRepository = branchItemLocationMapAuditLogRepository;
        this.branchItemLocationMapAuditLogMapper = branchItemLocationMapAuditLogMapper;
    }

    @Override
    public BranchItemLocationMapAuditLogDTO save(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO) {
        log.debug("Request to save BranchItemLocationMapAuditLog : {}", branchItemLocationMapAuditLogDTO);
        BranchItemLocationMapAuditLog branchItemLocationMapAuditLog = branchItemLocationMapAuditLogMapper.toEntity(
            branchItemLocationMapAuditLogDTO
        );
        branchItemLocationMapAuditLog = branchItemLocationMapAuditLogRepository.save(branchItemLocationMapAuditLog);
        return branchItemLocationMapAuditLogMapper.toDto(branchItemLocationMapAuditLog);
    }

    @Override
    public BranchItemLocationMapAuditLogDTO update(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO) {
        log.debug("Request to update BranchItemLocationMapAuditLog : {}", branchItemLocationMapAuditLogDTO);
        BranchItemLocationMapAuditLog branchItemLocationMapAuditLog = branchItemLocationMapAuditLogMapper.toEntity(
            branchItemLocationMapAuditLogDTO
        );
        branchItemLocationMapAuditLog = branchItemLocationMapAuditLogRepository.save(branchItemLocationMapAuditLog);
        return branchItemLocationMapAuditLogMapper.toDto(branchItemLocationMapAuditLog);
    }

    @Override
    public Optional<BranchItemLocationMapAuditLogDTO> partialUpdate(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO) {
        log.debug("Request to partially update BranchItemLocationMapAuditLog : {}", branchItemLocationMapAuditLogDTO);

        return branchItemLocationMapAuditLogRepository
            .findById(branchItemLocationMapAuditLogDTO.getId())
            .map(existingBranchItemLocationMapAuditLog -> {
                branchItemLocationMapAuditLogMapper.partialUpdate(existingBranchItemLocationMapAuditLog, branchItemLocationMapAuditLogDTO);

                return existingBranchItemLocationMapAuditLog;
            })
            .map(branchItemLocationMapAuditLogRepository::save)
            .map(branchItemLocationMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchItemLocationMapAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchItemLocationMapAuditLogs");
        return branchItemLocationMapAuditLogRepository.findAll(pageable).map(branchItemLocationMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchItemLocationMapAuditLogDTO> findOne(Long id) {
        log.debug("Request to get BranchItemLocationMapAuditLog : {}", id);
        return branchItemLocationMapAuditLogRepository.findById(id).map(branchItemLocationMapAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchItemLocationMapAuditLog : {}", id);
        branchItemLocationMapAuditLogRepository.deleteById(id);
    }
}
