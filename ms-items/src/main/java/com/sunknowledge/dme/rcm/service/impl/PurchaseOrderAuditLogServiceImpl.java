package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderAuditLog;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PurchaseOrderAuditLog}.
 */
@Service
@Transactional
public class PurchaseOrderAuditLogServiceImpl implements PurchaseOrderAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderAuditLogServiceImpl.class);

    private final PurchaseOrderAuditLogRepository purchaseOrderAuditLogRepository;

    private final PurchaseOrderAuditLogMapper purchaseOrderAuditLogMapper;

    public PurchaseOrderAuditLogServiceImpl(
        PurchaseOrderAuditLogRepository purchaseOrderAuditLogRepository,
        PurchaseOrderAuditLogMapper purchaseOrderAuditLogMapper
    ) {
        this.purchaseOrderAuditLogRepository = purchaseOrderAuditLogRepository;
        this.purchaseOrderAuditLogMapper = purchaseOrderAuditLogMapper;
    }

    @Override
    public PurchaseOrderAuditLogDTO save(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO) {
        log.debug("Request to save PurchaseOrderAuditLog : {}", purchaseOrderAuditLogDTO);
        PurchaseOrderAuditLog purchaseOrderAuditLog = purchaseOrderAuditLogMapper.toEntity(purchaseOrderAuditLogDTO);
        purchaseOrderAuditLog = purchaseOrderAuditLogRepository.save(purchaseOrderAuditLog);
        return purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);
    }

    @Override
    public PurchaseOrderAuditLogDTO update(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO) {
        log.debug("Request to update PurchaseOrderAuditLog : {}", purchaseOrderAuditLogDTO);
        PurchaseOrderAuditLog purchaseOrderAuditLog = purchaseOrderAuditLogMapper.toEntity(purchaseOrderAuditLogDTO);
        purchaseOrderAuditLog = purchaseOrderAuditLogRepository.save(purchaseOrderAuditLog);
        return purchaseOrderAuditLogMapper.toDto(purchaseOrderAuditLog);
    }

    @Override
    public Optional<PurchaseOrderAuditLogDTO> partialUpdate(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO) {
        log.debug("Request to partially update PurchaseOrderAuditLog : {}", purchaseOrderAuditLogDTO);

        return purchaseOrderAuditLogRepository
            .findById(purchaseOrderAuditLogDTO.getId())
            .map(existingPurchaseOrderAuditLog -> {
                purchaseOrderAuditLogMapper.partialUpdate(existingPurchaseOrderAuditLog, purchaseOrderAuditLogDTO);

                return existingPurchaseOrderAuditLog;
            })
            .map(purchaseOrderAuditLogRepository::save)
            .map(purchaseOrderAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrderAuditLogs");
        return purchaseOrderAuditLogRepository.findAll(pageable).map(purchaseOrderAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseOrderAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PurchaseOrderAuditLog : {}", id);
        return purchaseOrderAuditLogRepository.findById(id).map(purchaseOrderAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrderAuditLog : {}", id);
        purchaseOrderAuditLogRepository.deleteById(id);
    }
}
