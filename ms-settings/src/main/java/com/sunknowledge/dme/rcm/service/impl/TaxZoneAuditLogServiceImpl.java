package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog;
import com.sunknowledge.dme.rcm.repository.TaxZoneAuditLogRepository;
import com.sunknowledge.dme.rcm.service.TaxZoneAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxZoneAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaxZoneAuditLog}.
 */
@Service
@Transactional
public class TaxZoneAuditLogServiceImpl implements TaxZoneAuditLogService {

    private final Logger log = LoggerFactory.getLogger(TaxZoneAuditLogServiceImpl.class);

    private final TaxZoneAuditLogRepository taxZoneAuditLogRepository;

    private final TaxZoneAuditLogMapper taxZoneAuditLogMapper;

    public TaxZoneAuditLogServiceImpl(TaxZoneAuditLogRepository taxZoneAuditLogRepository, TaxZoneAuditLogMapper taxZoneAuditLogMapper) {
        this.taxZoneAuditLogRepository = taxZoneAuditLogRepository;
        this.taxZoneAuditLogMapper = taxZoneAuditLogMapper;
    }

    @Override
    public TaxZoneAuditLogDTO save(TaxZoneAuditLogDTO taxZoneAuditLogDTO) {
        log.debug("Request to save TaxZoneAuditLog : {}", taxZoneAuditLogDTO);
        TaxZoneAuditLog taxZoneAuditLog = taxZoneAuditLogMapper.toEntity(taxZoneAuditLogDTO);
        taxZoneAuditLog = taxZoneAuditLogRepository.save(taxZoneAuditLog);
        return taxZoneAuditLogMapper.toDto(taxZoneAuditLog);
    }

    @Override
    public TaxZoneAuditLogDTO update(TaxZoneAuditLogDTO taxZoneAuditLogDTO) {
        log.debug("Request to save TaxZoneAuditLog : {}", taxZoneAuditLogDTO);
        TaxZoneAuditLog taxZoneAuditLog = taxZoneAuditLogMapper.toEntity(taxZoneAuditLogDTO);
        taxZoneAuditLog = taxZoneAuditLogRepository.save(taxZoneAuditLog);
        return taxZoneAuditLogMapper.toDto(taxZoneAuditLog);
    }

    @Override
    public Optional<TaxZoneAuditLogDTO> partialUpdate(TaxZoneAuditLogDTO taxZoneAuditLogDTO) {
        log.debug("Request to partially update TaxZoneAuditLog : {}", taxZoneAuditLogDTO);

        return taxZoneAuditLogRepository
            .findById(taxZoneAuditLogDTO.getId())
            .map(existingTaxZoneAuditLog -> {
                taxZoneAuditLogMapper.partialUpdate(existingTaxZoneAuditLog, taxZoneAuditLogDTO);

                return existingTaxZoneAuditLog;
            })
            .map(taxZoneAuditLogRepository::save)
            .map(taxZoneAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxZoneAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaxZoneAuditLogs");
        return taxZoneAuditLogRepository.findAll(pageable).map(taxZoneAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaxZoneAuditLogDTO> findOne(Long id) {
        log.debug("Request to get TaxZoneAuditLog : {}", id);
        return taxZoneAuditLogRepository.findById(id).map(taxZoneAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxZoneAuditLog : {}", id);
        taxZoneAuditLogRepository.deleteById(id);
    }
}
