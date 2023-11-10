package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PriceMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.PriceMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PriceMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PriceMasterAuditLog}.
 */
@Service
@Transactional
public class PriceMasterAuditLogServiceImpl implements PriceMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PriceMasterAuditLogServiceImpl.class);

    private final PriceMasterAuditLogRepository priceMasterAuditLogRepository;

    private final PriceMasterAuditLogMapper priceMasterAuditLogMapper;

    public PriceMasterAuditLogServiceImpl(
        PriceMasterAuditLogRepository priceMasterAuditLogRepository,
        PriceMasterAuditLogMapper priceMasterAuditLogMapper
    ) {
        this.priceMasterAuditLogRepository = priceMasterAuditLogRepository;
        this.priceMasterAuditLogMapper = priceMasterAuditLogMapper;
    }

    @Override
    public PriceMasterAuditLogDTO save(PriceMasterAuditLogDTO priceMasterAuditLogDTO) {
        log.debug("Request to save PriceMasterAuditLog : {}", priceMasterAuditLogDTO);
        PriceMasterAuditLog priceMasterAuditLog = priceMasterAuditLogMapper.toEntity(priceMasterAuditLogDTO);
        priceMasterAuditLog = priceMasterAuditLogRepository.save(priceMasterAuditLog);
        return priceMasterAuditLogMapper.toDto(priceMasterAuditLog);
    }

    @Override
    public PriceMasterAuditLogDTO update(PriceMasterAuditLogDTO priceMasterAuditLogDTO) {
        log.debug("Request to update PriceMasterAuditLog : {}", priceMasterAuditLogDTO);
        PriceMasterAuditLog priceMasterAuditLog = priceMasterAuditLogMapper.toEntity(priceMasterAuditLogDTO);
        priceMasterAuditLog = priceMasterAuditLogRepository.save(priceMasterAuditLog);
        return priceMasterAuditLogMapper.toDto(priceMasterAuditLog);
    }

    @Override
    public Optional<PriceMasterAuditLogDTO> partialUpdate(PriceMasterAuditLogDTO priceMasterAuditLogDTO) {
        log.debug("Request to partially update PriceMasterAuditLog : {}", priceMasterAuditLogDTO);

        return priceMasterAuditLogRepository
            .findById(priceMasterAuditLogDTO.getId())
            .map(existingPriceMasterAuditLog -> {
                priceMasterAuditLogMapper.partialUpdate(existingPriceMasterAuditLog, priceMasterAuditLogDTO);

                return existingPriceMasterAuditLog;
            })
            .map(priceMasterAuditLogRepository::save)
            .map(priceMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PriceMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriceMasterAuditLogs");
        return priceMasterAuditLogRepository.findAll(pageable).map(priceMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PriceMasterAuditLog : {}", id);
        return priceMasterAuditLogRepository.findById(id).map(priceMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PriceMasterAuditLog : {}", id);
        priceMasterAuditLogRepository.deleteById(id);
    }
}
