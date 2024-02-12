package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.InsuranceMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.InsuranceMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InsuranceMasterAuditLog}.
 */
@Service
@Transactional
public class InsuranceMasterAuditLogServiceImpl implements InsuranceMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(InsuranceMasterAuditLogServiceImpl.class);

    private final InsuranceMasterAuditLogRepository insuranceMasterAuditLogRepository;

    private final InsuranceMasterAuditLogMapper insuranceMasterAuditLogMapper;

    public InsuranceMasterAuditLogServiceImpl(
        InsuranceMasterAuditLogRepository insuranceMasterAuditLogRepository,
        InsuranceMasterAuditLogMapper insuranceMasterAuditLogMapper
    ) {
        this.insuranceMasterAuditLogRepository = insuranceMasterAuditLogRepository;
        this.insuranceMasterAuditLogMapper = insuranceMasterAuditLogMapper;
    }

    @Override
    public InsuranceMasterAuditLogDTO save(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO) {
        log.debug("Request to save InsuranceMasterAuditLog : {}", insuranceMasterAuditLogDTO);
        InsuranceMasterAuditLog insuranceMasterAuditLog = insuranceMasterAuditLogMapper.toEntity(insuranceMasterAuditLogDTO);
        insuranceMasterAuditLog = insuranceMasterAuditLogRepository.save(insuranceMasterAuditLog);
        return insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);
    }

    @Override
    public InsuranceMasterAuditLogDTO update(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO) {
        log.debug("Request to update InsuranceMasterAuditLog : {}", insuranceMasterAuditLogDTO);
        InsuranceMasterAuditLog insuranceMasterAuditLog = insuranceMasterAuditLogMapper.toEntity(insuranceMasterAuditLogDTO);
        insuranceMasterAuditLog = insuranceMasterAuditLogRepository.save(insuranceMasterAuditLog);
        return insuranceMasterAuditLogMapper.toDto(insuranceMasterAuditLog);
    }

    @Override
    public Optional<InsuranceMasterAuditLogDTO> partialUpdate(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO) {
        log.debug("Request to partially update InsuranceMasterAuditLog : {}", insuranceMasterAuditLogDTO);

        return insuranceMasterAuditLogRepository
            .findById(insuranceMasterAuditLogDTO.getId())
            .map(existingInsuranceMasterAuditLog -> {
                insuranceMasterAuditLogMapper.partialUpdate(existingInsuranceMasterAuditLog, insuranceMasterAuditLogDTO);

                return existingInsuranceMasterAuditLog;
            })
            .map(insuranceMasterAuditLogRepository::save)
            .map(insuranceMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InsuranceMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceMasterAuditLogs");
        return insuranceMasterAuditLogRepository.findAll(pageable).map(insuranceMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get InsuranceMasterAuditLog : {}", id);
        return insuranceMasterAuditLogRepository.findById(id).map(insuranceMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InsuranceMasterAuditLog : {}", id);
        insuranceMasterAuditLogRepository.deleteById(id);
    }
}
