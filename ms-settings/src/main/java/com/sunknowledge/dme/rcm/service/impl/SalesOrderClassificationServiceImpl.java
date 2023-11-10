package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderClassification;
import com.sunknowledge.dme.rcm.repository.SalesOrderClassificationRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClassificationService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClassificationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SalesOrderClassification}.
 */
@Service
@Transactional
public class SalesOrderClassificationServiceImpl implements SalesOrderClassificationService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClassificationServiceImpl.class);

    private final SalesOrderClassificationRepository salesOrderClassificationRepository;

    private final SalesOrderClassificationMapper salesOrderClassificationMapper;

    public SalesOrderClassificationServiceImpl(
        SalesOrderClassificationRepository salesOrderClassificationRepository,
        SalesOrderClassificationMapper salesOrderClassificationMapper
    ) {
        this.salesOrderClassificationRepository = salesOrderClassificationRepository;
        this.salesOrderClassificationMapper = salesOrderClassificationMapper;
    }

    @Override
    public SalesOrderClassificationDTO save(SalesOrderClassificationDTO salesOrderClassificationDTO) {
        log.debug("Request to save SalesOrderClassification : {}", salesOrderClassificationDTO);
        SalesOrderClassification salesOrderClassification = salesOrderClassificationMapper.toEntity(salesOrderClassificationDTO);
        salesOrderClassification = salesOrderClassificationRepository.save(salesOrderClassification);
        return salesOrderClassificationMapper.toDto(salesOrderClassification);
    }

    @Override
    public SalesOrderClassificationDTO update(SalesOrderClassificationDTO salesOrderClassificationDTO) {
        log.debug("Request to save SalesOrderClassification : {}", salesOrderClassificationDTO);
        SalesOrderClassification salesOrderClassification = salesOrderClassificationMapper.toEntity(salesOrderClassificationDTO);
        salesOrderClassification = salesOrderClassificationRepository.save(salesOrderClassification);
        return salesOrderClassificationMapper.toDto(salesOrderClassification);
    }

    @Override
    public Optional<SalesOrderClassificationDTO> partialUpdate(SalesOrderClassificationDTO salesOrderClassificationDTO) {
        log.debug("Request to partially update SalesOrderClassification : {}", salesOrderClassificationDTO);

        return salesOrderClassificationRepository
            .findById(salesOrderClassificationDTO.getOrderClassificationId())
            .map(existingSalesOrderClassification -> {
                salesOrderClassificationMapper.partialUpdate(existingSalesOrderClassification, salesOrderClassificationDTO);

                return existingSalesOrderClassification;
            })
            .map(salesOrderClassificationRepository::save)
            .map(salesOrderClassificationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesOrderClassificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderClassifications");
        return salesOrderClassificationRepository.findAll(pageable).map(salesOrderClassificationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesOrderClassificationDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderClassification : {}", id);
        return salesOrderClassificationRepository.findById(id).map(salesOrderClassificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesOrderClassification : {}", id);
        salesOrderClassificationRepository.deleteById(id);
    }
}
