package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog;
import com.sunknowledge.dme.rcm.repository.InsuranceDocumentAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InsuranceDocumentAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceDocumentAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InsuranceDocumentAuditLog}.
 */
@Service
@Transactional
public class InsuranceDocumentAuditLogServiceImpl implements InsuranceDocumentAuditLogService {

    private final Logger log = LoggerFactory.getLogger(InsuranceDocumentAuditLogServiceImpl.class);

    private final InsuranceDocumentAuditLogRepository insuranceDocumentAuditLogRepository;

    private final InsuranceDocumentAuditLogMapper insuranceDocumentAuditLogMapper;

    public InsuranceDocumentAuditLogServiceImpl(
        InsuranceDocumentAuditLogRepository insuranceDocumentAuditLogRepository,
        InsuranceDocumentAuditLogMapper insuranceDocumentAuditLogMapper
    ) {
        this.insuranceDocumentAuditLogRepository = insuranceDocumentAuditLogRepository;
        this.insuranceDocumentAuditLogMapper = insuranceDocumentAuditLogMapper;
    }

    @Override
    public InsuranceDocumentAuditLogDTO save(InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO) {
        log.debug("Request to save InsuranceDocumentAuditLog : {}", insuranceDocumentAuditLogDTO);
        InsuranceDocumentAuditLog insuranceDocumentAuditLog = insuranceDocumentAuditLogMapper.toEntity(insuranceDocumentAuditLogDTO);
        insuranceDocumentAuditLog = insuranceDocumentAuditLogRepository.save(insuranceDocumentAuditLog);
        return insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);
    }

    @Override
    public InsuranceDocumentAuditLogDTO update(InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO) {
        log.debug("Request to save InsuranceDocumentAuditLog : {}", insuranceDocumentAuditLogDTO);
        InsuranceDocumentAuditLog insuranceDocumentAuditLog = insuranceDocumentAuditLogMapper.toEntity(insuranceDocumentAuditLogDTO);
        insuranceDocumentAuditLog = insuranceDocumentAuditLogRepository.save(insuranceDocumentAuditLog);
        return insuranceDocumentAuditLogMapper.toDto(insuranceDocumentAuditLog);
    }

    @Override
    public Optional<InsuranceDocumentAuditLogDTO> partialUpdate(InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO) {
        log.debug("Request to partially update InsuranceDocumentAuditLog : {}", insuranceDocumentAuditLogDTO);

        return insuranceDocumentAuditLogRepository
            .findById(insuranceDocumentAuditLogDTO.getId())
            .map(existingInsuranceDocumentAuditLog -> {
                insuranceDocumentAuditLogMapper.partialUpdate(existingInsuranceDocumentAuditLog, insuranceDocumentAuditLogDTO);

                return existingInsuranceDocumentAuditLog;
            })
            .map(insuranceDocumentAuditLogRepository::save)
            .map(insuranceDocumentAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InsuranceDocumentAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceDocumentAuditLogs");
        return insuranceDocumentAuditLogRepository.findAll(pageable).map(insuranceDocumentAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceDocumentAuditLogDTO> findOne(Long id) {
        log.debug("Request to get InsuranceDocumentAuditLog : {}", id);
        return insuranceDocumentAuditLogRepository.findById(id).map(insuranceDocumentAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsuranceDocumentAuditLog : {}", id);
        insuranceDocumentAuditLogRepository.deleteById(id);
    }
}
