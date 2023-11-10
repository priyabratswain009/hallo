package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog;
import com.sunknowledge.dme.rcm.repository.UspsAddressVerificationResponseAuditLogRepository;
import com.sunknowledge.dme.rcm.service.UspsAddressVerificationResponseAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.UspsAddressVerificationResponseAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UspsAddressVerificationResponseAuditLog}.
 */
@Service
@Transactional
public class UspsAddressVerificationResponseAuditLogServiceImpl implements UspsAddressVerificationResponseAuditLogService {

    private final Logger log = LoggerFactory.getLogger(UspsAddressVerificationResponseAuditLogServiceImpl.class);

    private final UspsAddressVerificationResponseAuditLogRepository uspsAddressVerificationResponseAuditLogRepository;

    private final UspsAddressVerificationResponseAuditLogMapper uspsAddressVerificationResponseAuditLogMapper;

    public UspsAddressVerificationResponseAuditLogServiceImpl(
        UspsAddressVerificationResponseAuditLogRepository uspsAddressVerificationResponseAuditLogRepository,
        UspsAddressVerificationResponseAuditLogMapper uspsAddressVerificationResponseAuditLogMapper
    ) {
        this.uspsAddressVerificationResponseAuditLogRepository = uspsAddressVerificationResponseAuditLogRepository;
        this.uspsAddressVerificationResponseAuditLogMapper = uspsAddressVerificationResponseAuditLogMapper;
    }

    @Override
    public UspsAddressVerificationResponseAuditLogDTO save(
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    ) {
        log.debug("Request to save UspsAddressVerificationResponseAuditLog : {}", uspsAddressVerificationResponseAuditLogDTO);
        UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogMapper.toEntity(
            uspsAddressVerificationResponseAuditLogDTO
        );
        uspsAddressVerificationResponseAuditLog =
            uspsAddressVerificationResponseAuditLogRepository.save(uspsAddressVerificationResponseAuditLog);
        return uspsAddressVerificationResponseAuditLogMapper.toDto(uspsAddressVerificationResponseAuditLog);
    }

    @Override
    public UspsAddressVerificationResponseAuditLogDTO update(
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    ) {
        log.debug("Request to save UspsAddressVerificationResponseAuditLog : {}", uspsAddressVerificationResponseAuditLogDTO);
        UspsAddressVerificationResponseAuditLog uspsAddressVerificationResponseAuditLog = uspsAddressVerificationResponseAuditLogMapper.toEntity(
            uspsAddressVerificationResponseAuditLogDTO
        );
        uspsAddressVerificationResponseAuditLog =
            uspsAddressVerificationResponseAuditLogRepository.save(uspsAddressVerificationResponseAuditLog);
        return uspsAddressVerificationResponseAuditLogMapper.toDto(uspsAddressVerificationResponseAuditLog);
    }

    @Override
    public Optional<UspsAddressVerificationResponseAuditLogDTO> partialUpdate(
        UspsAddressVerificationResponseAuditLogDTO uspsAddressVerificationResponseAuditLogDTO
    ) {
        log.debug("Request to partially update UspsAddressVerificationResponseAuditLog : {}", uspsAddressVerificationResponseAuditLogDTO);

        return uspsAddressVerificationResponseAuditLogRepository
            .findById(uspsAddressVerificationResponseAuditLogDTO.getId())
            .map(existingUspsAddressVerificationResponseAuditLog -> {
                uspsAddressVerificationResponseAuditLogMapper.partialUpdate(
                    existingUspsAddressVerificationResponseAuditLog,
                    uspsAddressVerificationResponseAuditLogDTO
                );

                return existingUspsAddressVerificationResponseAuditLog;
            })
            .map(uspsAddressVerificationResponseAuditLogRepository::save)
            .map(uspsAddressVerificationResponseAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UspsAddressVerificationResponseAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UspsAddressVerificationResponseAuditLogs");
        return uspsAddressVerificationResponseAuditLogRepository
            .findAll(pageable)
            .map(uspsAddressVerificationResponseAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UspsAddressVerificationResponseAuditLogDTO> findOne(Long id) {
        log.debug("Request to get UspsAddressVerificationResponseAuditLog : {}", id);
        return uspsAddressVerificationResponseAuditLogRepository.findById(id).map(uspsAddressVerificationResponseAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UspsAddressVerificationResponseAuditLog : {}", id);
        uspsAddressVerificationResponseAuditLogRepository.deleteById(id);
    }
}
