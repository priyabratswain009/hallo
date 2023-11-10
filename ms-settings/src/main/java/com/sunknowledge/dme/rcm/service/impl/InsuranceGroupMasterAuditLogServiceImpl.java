package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.InsuranceGroupMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InsuranceGroupMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceGroupMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InsuranceGroupMasterAuditLog}.
 */
@Service
@Transactional
public class InsuranceGroupMasterAuditLogServiceImpl implements InsuranceGroupMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(InsuranceGroupMasterAuditLogServiceImpl.class);

    private final InsuranceGroupMasterAuditLogRepository insuranceGroupMasterAuditLogRepository;

    private final InsuranceGroupMasterAuditLogMapper insuranceGroupMasterAuditLogMapper;

    public InsuranceGroupMasterAuditLogServiceImpl(
        InsuranceGroupMasterAuditLogRepository insuranceGroupMasterAuditLogRepository,
        InsuranceGroupMasterAuditLogMapper insuranceGroupMasterAuditLogMapper
    ) {
        this.insuranceGroupMasterAuditLogRepository = insuranceGroupMasterAuditLogRepository;
        this.insuranceGroupMasterAuditLogMapper = insuranceGroupMasterAuditLogMapper;
    }

    @Override
    public InsuranceGroupMasterAuditLogDTO save(InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO) {
        log.debug("Request to save InsuranceGroupMasterAuditLog : {}", insuranceGroupMasterAuditLogDTO);
        InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogMapper.toEntity(
            insuranceGroupMasterAuditLogDTO
        );
        insuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogRepository.save(insuranceGroupMasterAuditLog);
        return insuranceGroupMasterAuditLogMapper.toDto(insuranceGroupMasterAuditLog);
    }

    @Override
    public InsuranceGroupMasterAuditLogDTO update(InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO) {
        log.debug("Request to save InsuranceGroupMasterAuditLog : {}", insuranceGroupMasterAuditLogDTO);
        InsuranceGroupMasterAuditLog insuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogMapper.toEntity(
            insuranceGroupMasterAuditLogDTO
        );
        insuranceGroupMasterAuditLog = insuranceGroupMasterAuditLogRepository.save(insuranceGroupMasterAuditLog);
        return insuranceGroupMasterAuditLogMapper.toDto(insuranceGroupMasterAuditLog);
    }

    @Override
    public Optional<InsuranceGroupMasterAuditLogDTO> partialUpdate(InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO) {
        log.debug("Request to partially update InsuranceGroupMasterAuditLog : {}", insuranceGroupMasterAuditLogDTO);

        return insuranceGroupMasterAuditLogRepository
            .findById(insuranceGroupMasterAuditLogDTO.getId())
            .map(existingInsuranceGroupMasterAuditLog -> {
                insuranceGroupMasterAuditLogMapper.partialUpdate(existingInsuranceGroupMasterAuditLog, insuranceGroupMasterAuditLogDTO);

                return existingInsuranceGroupMasterAuditLog;
            })
            .map(insuranceGroupMasterAuditLogRepository::save)
            .map(insuranceGroupMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InsuranceGroupMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceGroupMasterAuditLogs");
        return insuranceGroupMasterAuditLogRepository.findAll(pageable).map(insuranceGroupMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceGroupMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get InsuranceGroupMasterAuditLog : {}", id);
        return insuranceGroupMasterAuditLogRepository.findById(id).map(insuranceGroupMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsuranceGroupMasterAuditLog : {}", id);
        insuranceGroupMasterAuditLogRepository.deleteById(id);
    }
}
