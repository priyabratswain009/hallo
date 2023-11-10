package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimSubmisionMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimSubmisionMasterAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SecondaryClaimSubmisionMasterAuditLog}.
 */
@Service
@Transactional
public class SecondaryClaimSubmisionMasterAuditLogServiceImpl implements SecondaryClaimSubmisionMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimSubmisionMasterAuditLogServiceImpl.class);

    private final SecondaryClaimSubmisionMasterAuditLogRepository secondaryClaimSubmisionMasterAuditLogRepository;

    private final SecondaryClaimSubmisionMasterAuditLogMapper secondaryClaimSubmisionMasterAuditLogMapper;

    public SecondaryClaimSubmisionMasterAuditLogServiceImpl(
        SecondaryClaimSubmisionMasterAuditLogRepository secondaryClaimSubmisionMasterAuditLogRepository,
        SecondaryClaimSubmisionMasterAuditLogMapper secondaryClaimSubmisionMasterAuditLogMapper
    ) {
        this.secondaryClaimSubmisionMasterAuditLogRepository = secondaryClaimSubmisionMasterAuditLogRepository;
        this.secondaryClaimSubmisionMasterAuditLogMapper = secondaryClaimSubmisionMasterAuditLogMapper;
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterAuditLogDTO> save(
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    ) {
        log.debug("Request to save SecondaryClaimSubmisionMasterAuditLog : {}", secondaryClaimSubmisionMasterAuditLogDTO);
        return secondaryClaimSubmisionMasterAuditLogRepository
            .save(secondaryClaimSubmisionMasterAuditLogMapper.toEntity(secondaryClaimSubmisionMasterAuditLogDTO))
            .map(secondaryClaimSubmisionMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterAuditLogDTO> update(
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    ) {
        log.debug("Request to save SecondaryClaimSubmisionMasterAuditLog : {}", secondaryClaimSubmisionMasterAuditLogDTO);
        return secondaryClaimSubmisionMasterAuditLogRepository
            .save(secondaryClaimSubmisionMasterAuditLogMapper.toEntity(secondaryClaimSubmisionMasterAuditLogDTO))
            .map(secondaryClaimSubmisionMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterAuditLogDTO> partialUpdate(
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    ) {
        log.debug("Request to partially update SecondaryClaimSubmisionMasterAuditLog : {}", secondaryClaimSubmisionMasterAuditLogDTO);

        return secondaryClaimSubmisionMasterAuditLogRepository
            .findById(secondaryClaimSubmisionMasterAuditLogDTO.getChgHealthSconarySubmnMsterId())
            .map(existingSecondaryClaimSubmisionMasterAuditLog -> {
                secondaryClaimSubmisionMasterAuditLogMapper.partialUpdate(
                    existingSecondaryClaimSubmisionMasterAuditLog,
                    secondaryClaimSubmisionMasterAuditLogDTO
                );

                return existingSecondaryClaimSubmisionMasterAuditLog;
            })
            .flatMap(secondaryClaimSubmisionMasterAuditLogRepository::save)
            .map(secondaryClaimSubmisionMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SecondaryClaimSubmisionMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SecondaryClaimSubmisionMasterAuditLogs");
        return secondaryClaimSubmisionMasterAuditLogRepository.findAllBy(pageable).map(secondaryClaimSubmisionMasterAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return secondaryClaimSubmisionMasterAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SecondaryClaimSubmisionMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get SecondaryClaimSubmisionMasterAuditLog : {}", id);
        return secondaryClaimSubmisionMasterAuditLogRepository.findById(id).map(secondaryClaimSubmisionMasterAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SecondaryClaimSubmisionMasterAuditLog : {}", id);
        return secondaryClaimSubmisionMasterAuditLogRepository.deleteById(id);
    }
}
