package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderItemDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderItemDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderItemDetails}.
 */
@Service
@Transactional
public class SalesOrderItemDetailsServiceImpl implements SalesOrderItemDetailsService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderItemDetailsServiceImpl.class);

    private final SalesOrderItemDetailsRepository salesOrderItemDetailsRepository;

    private final SalesOrderItemDetailsMapper salesOrderItemDetailsMapper;

    public SalesOrderItemDetailsServiceImpl(
        SalesOrderItemDetailsRepository salesOrderItemDetailsRepository,
        SalesOrderItemDetailsMapper salesOrderItemDetailsMapper
    ) {
        this.salesOrderItemDetailsRepository = salesOrderItemDetailsRepository;
        this.salesOrderItemDetailsMapper = salesOrderItemDetailsMapper;
    }

    @Override
    public Mono<SalesOrderItemDetailsDTO> save(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO) {
        log.debug("Request to save SalesOrderItemDetails : {}", salesOrderItemDetailsDTO);
        return salesOrderItemDetailsRepository
            .save(salesOrderItemDetailsMapper.toEntity(salesOrderItemDetailsDTO))
            .map(salesOrderItemDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderItemDetailsDTO> update(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO) {
        log.debug("Request to save SalesOrderItemDetails : {}", salesOrderItemDetailsDTO);
        return salesOrderItemDetailsRepository
            .save(salesOrderItemDetailsMapper.toEntity(salesOrderItemDetailsDTO))
            .map(salesOrderItemDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderItemDetailsDTO> partialUpdate(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO) {
        log.debug("Request to partially update SalesOrderItemDetails : {}", salesOrderItemDetailsDTO);

        return salesOrderItemDetailsRepository
            .findById(salesOrderItemDetailsDTO.getSalesOrderItemDetailsId())
            .map(existingSalesOrderItemDetails -> {
                salesOrderItemDetailsMapper.partialUpdate(existingSalesOrderItemDetails, salesOrderItemDetailsDTO);

                return existingSalesOrderItemDetails;
            })
            .flatMap(salesOrderItemDetailsRepository::save)
            .map(salesOrderItemDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderItemDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderItemDetails");
        return salesOrderItemDetailsRepository.findAllBy(pageable).map(salesOrderItemDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderItemDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderItemDetailsDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderItemDetails : {}", id);
        return salesOrderItemDetailsRepository.findById(id).map(salesOrderItemDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderItemDetails : {}", id);
        return salesOrderItemDetailsRepository.deleteById(id);
    }
}
