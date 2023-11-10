package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog;
import com.sunknowledge.dme.rcm.repository.FunctionalAbilityAuditLogRepository;
import com.sunknowledge.dme.rcm.service.FunctionalAbilityAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link FunctionalAbilityAuditLog}.
 */
@Service
@Transactional
public class FunctionalAbilityAuditLogServiceImpl implements FunctionalAbilityAuditLogService {

    private final Logger log = LoggerFactory.getLogger(FunctionalAbilityAuditLogServiceImpl.class);

    private final FunctionalAbilityAuditLogRepository functionalAbilityAuditLogRepository;

    private final FunctionalAbilityAuditLogMapper functionalAbilityAuditLogMapper;

    public FunctionalAbilityAuditLogServiceImpl(
        FunctionalAbilityAuditLogRepository functionalAbilityAuditLogRepository,
        FunctionalAbilityAuditLogMapper functionalAbilityAuditLogMapper
    ) {
        this.functionalAbilityAuditLogRepository = functionalAbilityAuditLogRepository;
        this.functionalAbilityAuditLogMapper = functionalAbilityAuditLogMapper;
    }

    @Override
    public Mono<FunctionalAbilityAuditLogDTO> save(FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO) {
        log.debug("Request to save FunctionalAbilityAuditLog : {}", functionalAbilityAuditLogDTO);
        return functionalAbilityAuditLogRepository
            .save(functionalAbilityAuditLogMapper.toEntity(functionalAbilityAuditLogDTO))
            .map(functionalAbilityAuditLogMapper::toDto);
    }

    @Override
    public Mono<FunctionalAbilityAuditLogDTO> update(FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO) {
        log.debug("Request to update FunctionalAbilityAuditLog : {}", functionalAbilityAuditLogDTO);
        return functionalAbilityAuditLogRepository
            .save(functionalAbilityAuditLogMapper.toEntity(functionalAbilityAuditLogDTO))
            .map(functionalAbilityAuditLogMapper::toDto);
    }

    @Override
    public Mono<FunctionalAbilityAuditLogDTO> partialUpdate(FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO) {
        log.debug("Request to partially update FunctionalAbilityAuditLog : {}", functionalAbilityAuditLogDTO);

        return functionalAbilityAuditLogRepository
            .findById(functionalAbilityAuditLogDTO.getId())
            .map(existingFunctionalAbilityAuditLog -> {
                functionalAbilityAuditLogMapper.partialUpdate(existingFunctionalAbilityAuditLog, functionalAbilityAuditLogDTO);

                return existingFunctionalAbilityAuditLog;
            })
            .flatMap(functionalAbilityAuditLogRepository::save)
            .map(functionalAbilityAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FunctionalAbilityAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FunctionalAbilityAuditLogs");
        return functionalAbilityAuditLogRepository.findAllBy(pageable).map(functionalAbilityAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return functionalAbilityAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FunctionalAbilityAuditLogDTO> findOne(Long id) {
        log.debug("Request to get FunctionalAbilityAuditLog : {}", id);
        return functionalAbilityAuditLogRepository.findById(id).map(functionalAbilityAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FunctionalAbilityAuditLog : {}", id);
        return functionalAbilityAuditLogRepository.deleteById(id);
    }
}
