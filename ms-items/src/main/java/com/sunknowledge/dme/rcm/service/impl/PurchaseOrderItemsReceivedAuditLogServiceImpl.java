package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceivedAuditLog;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedAuditLogRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsReceivedAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsReceivedAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PurchaseOrderItemsReceivedAuditLog}.
 */
@Service
@Transactional
public class PurchaseOrderItemsReceivedAuditLogServiceImpl implements PurchaseOrderItemsReceivedAuditLogService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsReceivedAuditLogServiceImpl.class);

    private final PurchaseOrderItemsReceivedAuditLogRepository purchaseOrderItemsReceivedAuditLogRepository;

    private final PurchaseOrderItemsReceivedAuditLogMapper purchaseOrderItemsReceivedAuditLogMapper;

    public PurchaseOrderItemsReceivedAuditLogServiceImpl(
        PurchaseOrderItemsReceivedAuditLogRepository purchaseOrderItemsReceivedAuditLogRepository,
        PurchaseOrderItemsReceivedAuditLogMapper purchaseOrderItemsReceivedAuditLogMapper
    ) {
        this.purchaseOrderItemsReceivedAuditLogRepository = purchaseOrderItemsReceivedAuditLogRepository;
        this.purchaseOrderItemsReceivedAuditLogMapper = purchaseOrderItemsReceivedAuditLogMapper;
    }

    @Override
    public PurchaseOrderItemsReceivedAuditLogDTO save(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO) {
        log.debug("Request to save PurchaseOrderItemsReceivedAuditLog : {}", purchaseOrderItemsReceivedAuditLogDTO);
        PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogMapper.toEntity(
            purchaseOrderItemsReceivedAuditLogDTO
        );
        purchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogRepository.save(purchaseOrderItemsReceivedAuditLog);
        return purchaseOrderItemsReceivedAuditLogMapper.toDto(purchaseOrderItemsReceivedAuditLog);
    }

    @Override
    public PurchaseOrderItemsReceivedAuditLogDTO update(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO) {
        log.debug("Request to update PurchaseOrderItemsReceivedAuditLog : {}", purchaseOrderItemsReceivedAuditLogDTO);
        PurchaseOrderItemsReceivedAuditLog purchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogMapper.toEntity(
            purchaseOrderItemsReceivedAuditLogDTO
        );
        purchaseOrderItemsReceivedAuditLog = purchaseOrderItemsReceivedAuditLogRepository.save(purchaseOrderItemsReceivedAuditLog);
        return purchaseOrderItemsReceivedAuditLogMapper.toDto(purchaseOrderItemsReceivedAuditLog);
    }

    @Override
    public Optional<PurchaseOrderItemsReceivedAuditLogDTO> partialUpdate(
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO
    ) {
        log.debug("Request to partially update PurchaseOrderItemsReceivedAuditLog : {}", purchaseOrderItemsReceivedAuditLogDTO);

        return purchaseOrderItemsReceivedAuditLogRepository
            .findById(purchaseOrderItemsReceivedAuditLogDTO.getId())
            .map(existingPurchaseOrderItemsReceivedAuditLog -> {
                purchaseOrderItemsReceivedAuditLogMapper.partialUpdate(
                    existingPurchaseOrderItemsReceivedAuditLog,
                    purchaseOrderItemsReceivedAuditLogDTO
                );

                return existingPurchaseOrderItemsReceivedAuditLog;
            })
            .map(purchaseOrderItemsReceivedAuditLogRepository::save)
            .map(purchaseOrderItemsReceivedAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderItemsReceivedAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrderItemsReceivedAuditLogs");
        return purchaseOrderItemsReceivedAuditLogRepository.findAll(pageable).map(purchaseOrderItemsReceivedAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseOrderItemsReceivedAuditLogDTO> findOne(Long id) {
        log.debug("Request to get PurchaseOrderItemsReceivedAuditLog : {}", id);
        return purchaseOrderItemsReceivedAuditLogRepository.findById(id).map(purchaseOrderItemsReceivedAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrderItemsReceivedAuditLog : {}", id);
        purchaseOrderItemsReceivedAuditLogRepository.deleteById(id);
    }
}
