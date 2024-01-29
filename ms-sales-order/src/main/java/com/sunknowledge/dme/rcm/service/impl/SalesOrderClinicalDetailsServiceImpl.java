package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderClinicalDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClinicalDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderClinicalDetails}.
 */
@Service
@Transactional
public class SalesOrderClinicalDetailsServiceImpl implements SalesOrderClinicalDetailsService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderClinicalDetailsServiceImpl.class);

    private final SalesOrderClinicalDetailsRepository salesOrderClinicalDetailsRepository;

    private final SalesOrderClinicalDetailsMapper salesOrderClinicalDetailsMapper;

    public SalesOrderClinicalDetailsServiceImpl(
        SalesOrderClinicalDetailsRepository salesOrderClinicalDetailsRepository,
        SalesOrderClinicalDetailsMapper salesOrderClinicalDetailsMapper
    ) {
        this.salesOrderClinicalDetailsRepository = salesOrderClinicalDetailsRepository;
        this.salesOrderClinicalDetailsMapper = salesOrderClinicalDetailsMapper;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> save(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO) {
        log.debug("Request to save SalesOrderClinicalDetails : {}", salesOrderClinicalDetailsDTO);
        return salesOrderClinicalDetailsRepository
            .save(salesOrderClinicalDetailsMapper.toEntity(salesOrderClinicalDetailsDTO))
            .map(salesOrderClinicalDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> update(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO) {
        log.debug("Request to update SalesOrderClinicalDetails : {}", salesOrderClinicalDetailsDTO);
        return salesOrderClinicalDetailsRepository
            .save(salesOrderClinicalDetailsMapper.toEntity(salesOrderClinicalDetailsDTO))
            .map(salesOrderClinicalDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderClinicalDetailsDTO> partialUpdate(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO) {
        log.debug("Request to partially update SalesOrderClinicalDetails : {}", salesOrderClinicalDetailsDTO);

        return salesOrderClinicalDetailsRepository
            .findById(salesOrderClinicalDetailsDTO.getSalesOrderClinicalDetailsId())
            .map(existingSalesOrderClinicalDetails -> {
                salesOrderClinicalDetailsMapper.partialUpdate(existingSalesOrderClinicalDetails, salesOrderClinicalDetailsDTO);

                return existingSalesOrderClinicalDetails;
            })
            .flatMap(salesOrderClinicalDetailsRepository::save)
            .map(salesOrderClinicalDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderClinicalDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderClinicalDetails");
        return salesOrderClinicalDetailsRepository.findAllBy(pageable).map(salesOrderClinicalDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderClinicalDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderClinicalDetailsDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderClinicalDetails : {}", id);
        return salesOrderClinicalDetailsRepository.findById(id).map(salesOrderClinicalDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderClinicalDetails : {}", id);
        return salesOrderClinicalDetailsRepository.deleteById(id);
    }
}
