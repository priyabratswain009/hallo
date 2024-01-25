package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PosMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.PosMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PosMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PosMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PosMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PosMasterAuditLog}.
 */
@Service
@Transactional
public class PosMasterAuditLogServiceImpl implements PosMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PosMasterAuditLogServiceImpl.class);

    private final PosMasterAuditLogRepository posMasterAuditLogRepository;

    private final PosMasterAuditLogMapper posMasterAuditLogMapper;

    public PosMasterAuditLogServiceImpl(
        PosMasterAuditLogRepository posMasterAuditLogRepository,
        PosMasterAuditLogMapper posMasterAuditLogMapper
    ) {
        this.posMasterAuditLogRepository = posMasterAuditLogRepository;
        this.posMasterAuditLogMapper = posMasterAuditLogMapper;
    }

    @Override
    public PosMasterAuditLogDTO save(PosMasterAuditLogDTO posMasterAuditLogDTO) {
        log.debug("Request to save PosMasterAuditLog : {}", posMasterAuditLogDTO);
        PosMasterAuditLog posMasterAuditLog = posMasterAuditLogMapper.toEntity(posMasterAuditLogDTO);
        posMasterAuditLog = posMasterAuditLogRepository.save(posMasterAuditLog);
        return posMasterAuditLogMapper.toDto(posMasterAuditLog);
    }

    @Override
    public PosMasterAuditLogDTO update(PosMasterAuditLogDTO posMasterAuditLogDTO) {
        log.debug("Request to update PosMasterAuditLog : {}", posMasterAuditLogDTO);
        PosMasterAuditLog posMasterAuditLog = posMasterAuditLogMapper.toEntity(posMasterAuditLogDTO);
        posMasterAuditLog = posMasterAuditLogRepository.save(posMasterAuditLog);
        return posMasterAuditLogMapper.toDto(posMasterAuditLog);
    }

    @Override
    public Optional<PosMasterAuditLogDTO> partialUpdate(PosMasterAuditLogDTO posMasterAuditLogDTO) {
        log.debug("Request to partially update PosMasterAuditLog : {}", posMasterAuditLogDTO);

        return posMasterAuditLogRepository
            .findById(posMasterAuditLogDTO.getId())
            .map(existingPosMasterAuditLog -> {
                posMasterAuditLogMapper.partialUpdate(existingPosMasterAuditLog, posMasterAuditLogDTO);

                return existingPosMasterAuditLog;
            })
            .map(posMasterAuditLogRepository::save)
            .map(posMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PosMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PosMasterAuditLogs");
        return posMasterAuditLogRepository.findAll(pageable).map(posMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PosMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PosMasterAuditLog : {}", id);
        return posMasterAuditLogRepository.findById(id).map(posMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PosMasterAuditLog : {}", id);
        posMasterAuditLogRepository.deleteById(id);
    }
}
