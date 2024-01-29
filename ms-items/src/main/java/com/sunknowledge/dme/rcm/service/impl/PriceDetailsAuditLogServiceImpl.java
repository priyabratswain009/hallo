package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PriceDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.PriceDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PriceDetailsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PriceDetailsAuditLog}.
 */
@Service
@Transactional
public class PriceDetailsAuditLogServiceImpl implements PriceDetailsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PriceDetailsAuditLogServiceImpl.class);

    private final PriceDetailsAuditLogRepository priceDetailsAuditLogRepository;

    private final PriceDetailsAuditLogMapper priceDetailsAuditLogMapper;

    public PriceDetailsAuditLogServiceImpl(
        PriceDetailsAuditLogRepository priceDetailsAuditLogRepository,
        PriceDetailsAuditLogMapper priceDetailsAuditLogMapper
    ) {
        this.priceDetailsAuditLogRepository = priceDetailsAuditLogRepository;
        this.priceDetailsAuditLogMapper = priceDetailsAuditLogMapper;
    }

    @Override
    public PriceDetailsAuditLogDTO save(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO) {
        log.debug("Request to save PriceDetailsAuditLog : {}", priceDetailsAuditLogDTO);
        PriceDetailsAuditLog priceDetailsAuditLog = priceDetailsAuditLogMapper.toEntity(priceDetailsAuditLogDTO);
        priceDetailsAuditLog = priceDetailsAuditLogRepository.save(priceDetailsAuditLog);
        return priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);
    }

    @Override
    public PriceDetailsAuditLogDTO update(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO) {
        log.debug("Request to save PriceDetailsAuditLog : {}", priceDetailsAuditLogDTO);
        PriceDetailsAuditLog priceDetailsAuditLog = priceDetailsAuditLogMapper.toEntity(priceDetailsAuditLogDTO);
        priceDetailsAuditLog = priceDetailsAuditLogRepository.save(priceDetailsAuditLog);
        return priceDetailsAuditLogMapper.toDto(priceDetailsAuditLog);
    }

    @Override
    public Optional<PriceDetailsAuditLogDTO> partialUpdate(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO) {
        log.debug("Request to partially update PriceDetailsAuditLog : {}", priceDetailsAuditLogDTO);

        return priceDetailsAuditLogRepository
            .findById(priceDetailsAuditLogDTO.getId())
            .map(existingPriceDetailsAuditLog -> {
                priceDetailsAuditLogMapper.partialUpdate(existingPriceDetailsAuditLog, priceDetailsAuditLogDTO);

                return existingPriceDetailsAuditLog;
            })
            .map(priceDetailsAuditLogRepository::save)
            .map(priceDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PriceDetailsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceDetailsAuditLogs");
        return priceDetailsAuditLogRepository.findAll(pageable).map(priceDetailsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceDetailsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PriceDetailsAuditLog : {}", id);
        return priceDetailsAuditLogRepository.findById(id).map(priceDetailsAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PriceDetailsAuditLog : {}", id);
        priceDetailsAuditLogRepository.deleteById(id);
    }
}
