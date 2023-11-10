package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog;
import com.sunknowledge.dme.rcm.repository.BranchInsuranceMapAuditLogRepository;
import com.sunknowledge.dme.rcm.service.BranchInsuranceMapAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.BranchInsuranceMapAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BranchInsuranceMapAuditLog}.
 */
@Service
@Transactional
public class BranchInsuranceMapAuditLogServiceImpl implements BranchInsuranceMapAuditLogService {

    private final Logger log = LoggerFactory.getLogger(BranchInsuranceMapAuditLogServiceImpl.class);

    private final BranchInsuranceMapAuditLogRepository branchInsuranceMapAuditLogRepository;

    private final BranchInsuranceMapAuditLogMapper branchInsuranceMapAuditLogMapper;

    public BranchInsuranceMapAuditLogServiceImpl(
        BranchInsuranceMapAuditLogRepository branchInsuranceMapAuditLogRepository,
        BranchInsuranceMapAuditLogMapper branchInsuranceMapAuditLogMapper
    ) {
        this.branchInsuranceMapAuditLogRepository = branchInsuranceMapAuditLogRepository;
        this.branchInsuranceMapAuditLogMapper = branchInsuranceMapAuditLogMapper;
    }

    @Override
    public BranchInsuranceMapAuditLogDTO save(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO) {
        log.debug("Request to save BranchInsuranceMapAuditLog : {}", branchInsuranceMapAuditLogDTO);
        BranchInsuranceMapAuditLog branchInsuranceMapAuditLog = branchInsuranceMapAuditLogMapper.toEntity(branchInsuranceMapAuditLogDTO);
        branchInsuranceMapAuditLog = branchInsuranceMapAuditLogRepository.save(branchInsuranceMapAuditLog);
        return branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);
    }

    @Override
    public BranchInsuranceMapAuditLogDTO update(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO) {
        log.debug("Request to save BranchInsuranceMapAuditLog : {}", branchInsuranceMapAuditLogDTO);
        BranchInsuranceMapAuditLog branchInsuranceMapAuditLog = branchInsuranceMapAuditLogMapper.toEntity(branchInsuranceMapAuditLogDTO);
        branchInsuranceMapAuditLog = branchInsuranceMapAuditLogRepository.save(branchInsuranceMapAuditLog);
        return branchInsuranceMapAuditLogMapper.toDto(branchInsuranceMapAuditLog);
    }

    @Override
    public Optional<BranchInsuranceMapAuditLogDTO> partialUpdate(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO) {
        log.debug("Request to partially update BranchInsuranceMapAuditLog : {}", branchInsuranceMapAuditLogDTO);

        return branchInsuranceMapAuditLogRepository
            .findById(branchInsuranceMapAuditLogDTO.getId())
            .map(existingBranchInsuranceMapAuditLog -> {
                branchInsuranceMapAuditLogMapper.partialUpdate(existingBranchInsuranceMapAuditLog, branchInsuranceMapAuditLogDTO);

                return existingBranchInsuranceMapAuditLog;
            })
            .map(branchInsuranceMapAuditLogRepository::save)
            .map(branchInsuranceMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchInsuranceMapAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchInsuranceMapAuditLogs");
        return branchInsuranceMapAuditLogRepository.findAll(pageable).map(branchInsuranceMapAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchInsuranceMapAuditLogDTO> findOne(Long id) {
        log.debug("Request to get BranchInsuranceMapAuditLog : {}", id);
        return branchInsuranceMapAuditLogRepository.findById(id).map(branchInsuranceMapAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchInsuranceMapAuditLog : {}", id);
        branchInsuranceMapAuditLogRepository.deleteById(id);
    }
}
