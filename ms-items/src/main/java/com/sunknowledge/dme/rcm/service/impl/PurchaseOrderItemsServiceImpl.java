package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PurchaseOrderItems}.
 */
@Service
@Transactional
public class PurchaseOrderItemsServiceImpl implements PurchaseOrderItemsService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemsServiceImpl.class);

    private final PurchaseOrderItemsRepository purchaseOrderItemsRepository;

    private final PurchaseOrderItemsMapper purchaseOrderItemsMapper;

    public PurchaseOrderItemsServiceImpl(
        PurchaseOrderItemsRepository purchaseOrderItemsRepository,
        PurchaseOrderItemsMapper purchaseOrderItemsMapper
    ) {
        this.purchaseOrderItemsRepository = purchaseOrderItemsRepository;
        this.purchaseOrderItemsMapper = purchaseOrderItemsMapper;
    }

    @Override
    public PurchaseOrderItemsDTO save(PurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        log.debug("Request to save PurchaseOrderItems : {}", purchaseOrderItemsDTO);
        PurchaseOrderItems purchaseOrderItems = purchaseOrderItemsMapper.toEntity(purchaseOrderItemsDTO);
        purchaseOrderItems = purchaseOrderItemsRepository.save(purchaseOrderItems);
        return purchaseOrderItemsMapper.toDto(purchaseOrderItems);
    }

    @Override
    public PurchaseOrderItemsDTO update(PurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        log.debug("Request to update PurchaseOrderItems : {}", purchaseOrderItemsDTO);
        PurchaseOrderItems purchaseOrderItems = purchaseOrderItemsMapper.toEntity(purchaseOrderItemsDTO);
        purchaseOrderItems = purchaseOrderItemsRepository.save(purchaseOrderItems);
        return purchaseOrderItemsMapper.toDto(purchaseOrderItems);
    }

    @Override
    public Optional<PurchaseOrderItemsDTO> partialUpdate(PurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        log.debug("Request to partially update PurchaseOrderItems : {}", purchaseOrderItemsDTO);

        return purchaseOrderItemsRepository
            .findById(purchaseOrderItemsDTO.getPoItemsId())
            .map(existingPurchaseOrderItems -> {
                purchaseOrderItemsMapper.partialUpdate(existingPurchaseOrderItems, purchaseOrderItemsDTO);

                return existingPurchaseOrderItems;
            })
            .map(purchaseOrderItemsRepository::save)
            .map(purchaseOrderItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderItemsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrderItems");
        return purchaseOrderItemsRepository.findAll(pageable).map(purchaseOrderItemsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseOrderItemsDTO> findOne(Long id) {
        log.debug("Request to get PurchaseOrderItems : {}", id);
        return purchaseOrderItemsRepository.findById(id).map(purchaseOrderItemsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrderItems : {}", id);
        purchaseOrderItemsRepository.deleteById(id);
    }
}
