package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchGroupAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchGroupAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchGroupAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchGroupAuditLog}.
 */
@Service
@Transactional
public class BranchGroupAuditLogServiceImpl implements BranchGroupAuditLogService {

    private final Logger log = LoggerFactory.getLogger(BranchGroupAuditLogServiceImpl.class);

    private final BranchGroupAuditLogRepository branchGroupAuditLogRepository;

    private final BranchGroupAuditLogMapper branchGroupAuditLogMapper;

    public BranchGroupAuditLogServiceImpl(
        BranchGroupAuditLogRepository branchGroupAuditLogRepository,
        BranchGroupAuditLogMapper branchGroupAuditLogMapper
    ) {
        this.branchGroupAuditLogRepository = branchGroupAuditLogRepository;
        this.branchGroupAuditLogMapper = branchGroupAuditLogMapper;
    }

    @Override
    public BranchGroupAuditLogDTO save(BranchGroupAuditLogDTO branchGroupAuditLogDTO) {
        log.debug("Request to save BranchGroupAuditLog : {}", branchGroupAuditLogDTO);
        BranchGroupAuditLog branchGroupAuditLog = branchGroupAuditLogMapper.toEntity(branchGroupAuditLogDTO);
        branchGroupAuditLog = branchGroupAuditLogRepository.save(branchGroupAuditLog);
        return branchGroupAuditLogMapper.toDto(branchGroupAuditLog);
    }

    @Override
    public BranchGroupAuditLogDTO update(BranchGroupAuditLogDTO branchGroupAuditLogDTO) {
        log.debug("Request to save BranchGroupAuditLog : {}", branchGroupAuditLogDTO);
        BranchGroupAuditLog branchGroupAuditLog = branchGroupAuditLogMapper.toEntity(branchGroupAuditLogDTO);
        branchGroupAuditLog = branchGroupAuditLogRepository.save(branchGroupAuditLog);
        return branchGroupAuditLogMapper.toDto(branchGroupAuditLog);
    }

    @Override
    public Optional<BranchGroupAuditLogDTO> partialUpdate(BranchGroupAuditLogDTO branchGroupAuditLogDTO) {
        log.debug("Request to partially update BranchGroupAuditLog : {}", branchGroupAuditLogDTO);

        return branchGroupAuditLogRepository
            .findById(branchGroupAuditLogDTO.getId())
            .map(existingBranchGroupAuditLog -> {
                branchGroupAuditLogMapper.partialUpdate(existingBranchGroupAuditLog, branchGroupAuditLogDTO);

                return existingBranchGroupAuditLog;
            })
            .map(branchGroupAuditLogRepository::save)
            .map(branchGroupAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchGroupAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchGroupAuditLogs");
        return branchGroupAuditLogRepository.findAll(pageable).map(branchGroupAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchGroupAuditLogDTO> findOne(Long id) {
        log.debug("Request to get BranchGroupAuditLog : {}", id);
        return branchGroupAuditLogRepository.findById(id).map(branchGroupAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchGroupAuditLog : {}", id);
        branchGroupAuditLogRepository.deleteById(id);
    }
}
