package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog;
import com.sunknowledge.dme.rcm.repository.WorkersCompensationAuditLogRepository;
import com.sunknowledge.dme.rcm.service.WorkersCompensationAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationAuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link WorkersCompensationAuditLog}.
 */
@Service
@Transactional
public class WorkersCompensationAuditLogServiceImpl implements WorkersCompensationAuditLogService {

    private final Logger log = LoggerFactory.getLogger(WorkersCompensationAuditLogServiceImpl.class);

    private final WorkersCompensationAuditLogRepository workersCompensationAuditLogRepository;

    private final WorkersCompensationAuditLogMapper workersCompensationAuditLogMapper;

    public WorkersCompensationAuditLogServiceImpl(
        WorkersCompensationAuditLogRepository workersCompensationAuditLogRepository,
        WorkersCompensationAuditLogMapper workersCompensationAuditLogMapper
    ) {
        this.workersCompensationAuditLogRepository = workersCompensationAuditLogRepository;
        this.workersCompensationAuditLogMapper = workersCompensationAuditLogMapper;
    }

    @Override
    public Mono<WorkersCompensationAuditLogDTO> save(WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO) {
        log.debug("Request to save WorkersCompensationAuditLog : {}", workersCompensationAuditLogDTO);
        return workersCompensationAuditLogRepository
            .save(workersCompensationAuditLogMapper.toEntity(workersCompensationAuditLogDTO))
            .map(workersCompensationAuditLogMapper::toDto);
    }

    @Override
    public Mono<WorkersCompensationAuditLogDTO> update(WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO) {
        log.debug("Request to update WorkersCompensationAuditLog : {}", workersCompensationAuditLogDTO);
        return workersCompensationAuditLogRepository
            .save(workersCompensationAuditLogMapper.toEntity(workersCompensationAuditLogDTO))
            .map(workersCompensationAuditLogMapper::toDto);
    }

    @Override
    public Mono<WorkersCompensationAuditLogDTO> partialUpdate(WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO) {
        log.debug("Request to partially update WorkersCompensationAuditLog : {}", workersCompensationAuditLogDTO);

        return workersCompensationAuditLogRepository
            .findById(workersCompensationAuditLogDTO.getId())
            .map(existingWorkersCompensationAuditLog -> {
                workersCompensationAuditLogMapper.partialUpdate(existingWorkersCompensationAuditLog, workersCompensationAuditLogDTO);

                return existingWorkersCompensationAuditLog;
            })
            .flatMap(workersCompensationAuditLogRepository::save)
            .map(workersCompensationAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<WorkersCompensationAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkersCompensationAuditLogs");
        return workersCompensationAuditLogRepository.findAllBy(pageable).map(workersCompensationAuditLogMapper::toDto);
    }

    public Mono<Long> countAll() {
        return workersCompensationAuditLogRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<WorkersCompensationAuditLogDTO> findOne(Long id) {
        log.debug("Request to get WorkersCompensationAuditLog : {}", id);
        return workersCompensationAuditLogRepository.findById(id).map(workersCompensationAuditLogMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete WorkersCompensationAuditLog : {}", id);
        return workersCompensationAuditLogRepository.deleteById(id);
    }
}
