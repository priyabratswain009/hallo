package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsAuditLog;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PurchaseOrderItemsAuditLog}.
 */
@Service
@Transactional
public class PurchaseOrderItemsAuditLogServiceImpl implements PurchaseOrderItemsAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsAuditLogServiceImpl.class);

    private final PurchaseOrderItemsAuditLogRepository purchaseOrderItemsAuditLogRepository;

    private final PurchaseOrderItemsAuditLogMapper purchaseOrderItemsAuditLogMapper;

    public PurchaseOrderItemsAuditLogServiceImpl(
        PurchaseOrderItemsAuditLogRepository purchaseOrderItemsAuditLogRepository,
        PurchaseOrderItemsAuditLogMapper purchaseOrderItemsAuditLogMapper
    ) {
        this.purchaseOrderItemsAuditLogRepository = purchaseOrderItemsAuditLogRepository;
        this.purchaseOrderItemsAuditLogMapper = purchaseOrderItemsAuditLogMapper;
    }

    @Override
    public PurchaseOrderItemsAuditLogDTO save(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO) {
        log.debug("Request to save PurchaseOrderItemsAuditLog : {}", purchaseOrderItemsAuditLogDTO);
        PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogMapper.toEntity(purchaseOrderItemsAuditLogDTO);
        purchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogRepository.save(purchaseOrderItemsAuditLog);
        return purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);
    }

    @Override
    public PurchaseOrderItemsAuditLogDTO update(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO) {
        log.debug("Request to save PurchaseOrderItemsAuditLog : {}", purchaseOrderItemsAuditLogDTO);
        PurchaseOrderItemsAuditLog purchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogMapper.toEntity(purchaseOrderItemsAuditLogDTO);
        purchaseOrderItemsAuditLog = purchaseOrderItemsAuditLogRepository.save(purchaseOrderItemsAuditLog);
        return purchaseOrderItemsAuditLogMapper.toDto(purchaseOrderItemsAuditLog);
    }

    @Override
    public Optional<PurchaseOrderItemsAuditLogDTO> partialUpdate(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO) {
        log.debug("Request to partially update PurchaseOrderItemsAuditLog : {}", purchaseOrderItemsAuditLogDTO);

        return purchaseOrderItemsAuditLogRepository
            .findById(purchaseOrderItemsAuditLogDTO.getId())
            .map(existingPurchaseOrderItemsAuditLog -> {
                purchaseOrderItemsAuditLogMapper.partialUpdate(existingPurchaseOrderItemsAuditLog, purchaseOrderItemsAuditLogDTO);

                return existingPurchaseOrderItemsAuditLog;
            })
            .map(purchaseOrderItemsAuditLogRepository::save)
            .map(purchaseOrderItemsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderItemsAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrderItemsAuditLogs");
        return purchaseOrderItemsAuditLogRepository.findAll(pageable).map(purchaseOrderItemsAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseOrderItemsAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PurchaseOrderItemsAuditLog : {}", id);
        return purchaseOrderItemsAuditLogRepository.findById(id).map(purchaseOrderItemsAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrderItemsAuditLog : {}", id);
        purchaseOrderItemsAuditLogRepository.deleteById(id);
    }
}
