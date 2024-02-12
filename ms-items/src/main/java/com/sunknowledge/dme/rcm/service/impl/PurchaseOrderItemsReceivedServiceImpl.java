package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsReceivedService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsReceivedMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PurchaseOrderItemsReceived}.
 */
@Service
@Transactional
public class PurchaseOrderItemsReceivedServiceImpl implements PurchaseOrderItemsReceivedService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsReceivedServiceImpl.class);

    private final PurchaseOrderItemsReceivedRepository purchaseOrderItemsReceivedRepository;

    private final PurchaseOrderItemsReceivedMapper purchaseOrderItemsReceivedMapper;

    public PurchaseOrderItemsReceivedServiceImpl(
        PurchaseOrderItemsReceivedRepository purchaseOrderItemsReceivedRepository,
        PurchaseOrderItemsReceivedMapper purchaseOrderItemsReceivedMapper
    ) {
        this.purchaseOrderItemsReceivedRepository = purchaseOrderItemsReceivedRepository;
        this.purchaseOrderItemsReceivedMapper = purchaseOrderItemsReceivedMapper;
    }

    @Override
    public PurchaseOrderItemsReceivedDTO save(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO) {
        log.debug("Request to save PurchaseOrderItemsReceived : {}", purchaseOrderItemsReceivedDTO);
        PurchaseOrderItemsReceived purchaseOrderItemsReceived = purchaseOrderItemsReceivedMapper.toEntity(purchaseOrderItemsReceivedDTO);
        purchaseOrderItemsReceived = purchaseOrderItemsReceivedRepository.save(purchaseOrderItemsReceived);
        return purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);
    }

    @Override
    public PurchaseOrderItemsReceivedDTO update(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO) {
        log.debug("Request to save PurchaseOrderItemsReceived : {}", purchaseOrderItemsReceivedDTO);
        PurchaseOrderItemsReceived purchaseOrderItemsReceived = purchaseOrderItemsReceivedMapper.toEntity(purchaseOrderItemsReceivedDTO);
        purchaseOrderItemsReceived = purchaseOrderItemsReceivedRepository.save(purchaseOrderItemsReceived);
        return purchaseOrderItemsReceivedMapper.toDto(purchaseOrderItemsReceived);
    }

    @Override
    public Optional<PurchaseOrderItemsReceivedDTO> partialUpdate(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO) {
        log.debug("Request to partially update PurchaseOrderItemsReceived : {}", purchaseOrderItemsReceivedDTO);

        return purchaseOrderItemsReceivedRepository
            .findById(purchaseOrderItemsReceivedDTO.getPoItemReceivedId())
            .map(existingPurchaseOrderItemsReceived -> {
                purchaseOrderItemsReceivedMapper.partialUpdate(existingPurchaseOrderItemsReceived, purchaseOrderItemsReceivedDTO);

                return existingPurchaseOrderItemsReceived;
            })
            .map(purchaseOrderItemsReceivedRepository::save)
            .map(purchaseOrderItemsReceivedMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderItemsReceivedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrderItemsReceiveds");
        return purchaseOrderItemsReceivedRepository.findAll(pageable).map(purchaseOrderItemsReceivedMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseOrderItemsReceivedDTO> findOne(Long id) {
        log.debug("Request to get PurchaseOrderItemsReceived : {}", id);
        return purchaseOrderItemsReceivedRepository.findById(id).map(purchaseOrderItemsReceivedMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrderItemsReceived : {}", id);
        purchaseOrderItemsReceivedRepository.deleteById(id);
    }
}
