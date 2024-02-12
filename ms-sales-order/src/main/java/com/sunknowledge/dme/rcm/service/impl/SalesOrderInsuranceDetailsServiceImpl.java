package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderInsuranceDetails}.
 */
@Service
@Transactional
public class SalesOrderInsuranceDetailsServiceImpl implements SalesOrderInsuranceDetailsService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderInsuranceDetailsServiceImpl.class);

    private final SalesOrderInsuranceDetailsRepository salesOrderInsuranceDetailsRepository;

    private final SalesOrderInsuranceDetailsMapper salesOrderInsuranceDetailsMapper;

    public SalesOrderInsuranceDetailsServiceImpl(
        SalesOrderInsuranceDetailsRepository salesOrderInsuranceDetailsRepository,
        SalesOrderInsuranceDetailsMapper salesOrderInsuranceDetailsMapper
    ) {
        this.salesOrderInsuranceDetailsRepository = salesOrderInsuranceDetailsRepository;
        this.salesOrderInsuranceDetailsMapper = salesOrderInsuranceDetailsMapper;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> save(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        log.debug("Request to save SalesOrderInsuranceDetails : {}", salesOrderInsuranceDetailsDTO);
        return salesOrderInsuranceDetailsRepository
            .save(salesOrderInsuranceDetailsMapper.toEntity(salesOrderInsuranceDetailsDTO))
            .map(salesOrderInsuranceDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> update(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        log.debug("Request to update SalesOrderInsuranceDetails : {}", salesOrderInsuranceDetailsDTO);
        return salesOrderInsuranceDetailsRepository
            .save(salesOrderInsuranceDetailsMapper.toEntity(salesOrderInsuranceDetailsDTO))
            .map(salesOrderInsuranceDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> partialUpdate(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        log.debug("Request to partially update SalesOrderInsuranceDetails : {}", salesOrderInsuranceDetailsDTO);

        return salesOrderInsuranceDetailsRepository
            .findById(salesOrderInsuranceDetailsDTO.getSalesOrderInsuranceDetailsId())
            .map(existingSalesOrderInsuranceDetails -> {
                salesOrderInsuranceDetailsMapper.partialUpdate(existingSalesOrderInsuranceDetails, salesOrderInsuranceDetailsDTO);

                return existingSalesOrderInsuranceDetails;
            })
            .flatMap(salesOrderInsuranceDetailsRepository::save)
            .map(salesOrderInsuranceDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderInsuranceDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderInsuranceDetails");
        return salesOrderInsuranceDetailsRepository.findAllBy(pageable).map(salesOrderInsuranceDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderInsuranceDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderInsuranceDetailsDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderInsuranceDetails : {}", id);
        return salesOrderInsuranceDetailsRepository.findById(id).map(salesOrderInsuranceDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderInsuranceDetails : {}", id);
        return salesOrderInsuranceDetailsRepository.deleteById(id);
    }
}
