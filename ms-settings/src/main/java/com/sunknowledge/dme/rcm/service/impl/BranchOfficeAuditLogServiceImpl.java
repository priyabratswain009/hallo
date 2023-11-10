package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchOfficeAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchOfficeAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchOfficeAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchOfficeAuditLog}.
 */
@Service
@Transactional
public class BranchOfficeAuditLogServiceImpl implements BranchOfficeAuditLogService {

    private final Logger log = LoggerFactory.getLogger(BranchOfficeAuditLogServiceImpl.class);

    private final BranchOfficeAuditLogRepository branchOfficeAuditLogRepository;

    private final BranchOfficeAuditLogMapper branchOfficeAuditLogMapper;

    public BranchOfficeAuditLogServiceImpl(
        BranchOfficeAuditLogRepository branchOfficeAuditLogRepository,
        BranchOfficeAuditLogMapper branchOfficeAuditLogMapper
    ) {
        this.branchOfficeAuditLogRepository = branchOfficeAuditLogRepository;
        this.branchOfficeAuditLogMapper = branchOfficeAuditLogMapper;
    }

    @Override
    public BranchOfficeAuditLogDTO save(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO) {
        log.debug("Request to save BranchOfficeAuditLog : {}", branchOfficeAuditLogDTO);
        BranchOfficeAuditLog branchOfficeAuditLog = branchOfficeAuditLogMapper.toEntity(branchOfficeAuditLogDTO);
        branchOfficeAuditLog = branchOfficeAuditLogRepository.save(branchOfficeAuditLog);
        return branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);
    }

    @Override
    public BranchOfficeAuditLogDTO update(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO) {
        log.debug("Request to save BranchOfficeAuditLog : {}", branchOfficeAuditLogDTO);
        BranchOfficeAuditLog branchOfficeAuditLog = branchOfficeAuditLogMapper.toEntity(branchOfficeAuditLogDTO);
        branchOfficeAuditLog = branchOfficeAuditLogRepository.save(branchOfficeAuditLog);
        return branchOfficeAuditLogMapper.toDto(branchOfficeAuditLog);
    }

    @Override
    public Optional<BranchOfficeAuditLogDTO> partialUpdate(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO) {
        log.debug("Request to partially update BranchOfficeAuditLog : {}", branchOfficeAuditLogDTO);

        return branchOfficeAuditLogRepository
            .findById(branchOfficeAuditLogDTO.getId())
            .map(existingBranchOfficeAuditLog -> {
                branchOfficeAuditLogMapper.partialUpdate(existingBranchOfficeAuditLog, branchOfficeAuditLogDTO);

                return existingBranchOfficeAuditLog;
            })
            .map(branchOfficeAuditLogRepository::save)
            .map(branchOfficeAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchOfficeAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchOfficeAuditLogs");
        return branchOfficeAuditLogRepository.findAll(pageable).map(branchOfficeAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchOfficeAuditLogDTO> findOne(Long id) {
        log.debug("Request to get BranchOfficeAuditLog : {}", id);
        return branchOfficeAuditLogRepository.findById(id).map(branchOfficeAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchOfficeAuditLog : {}", id);
        branchOfficeAuditLogRepository.deleteById(id);
    }
}
