package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderFinancialDetailsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderFinancialDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderFinancialDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderFinancialDetails}.
 */
@Service
@Transactional
public class SalesOrderFinancialDetailsServiceImpl implements SalesOrderFinancialDetailsService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderFinancialDetailsServiceImpl.class);

    private final SalesOrderFinancialDetailsRepository salesOrderFinancialDetailsRepository;

    private final SalesOrderFinancialDetailsMapper salesOrderFinancialDetailsMapper;

    public SalesOrderFinancialDetailsServiceImpl(
        SalesOrderFinancialDetailsRepository salesOrderFinancialDetailsRepository,
        SalesOrderFinancialDetailsMapper salesOrderFinancialDetailsMapper
    ) {
        this.salesOrderFinancialDetailsRepository = salesOrderFinancialDetailsRepository;
        this.salesOrderFinancialDetailsMapper = salesOrderFinancialDetailsMapper;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> save(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO) {
        log.debug("Request to save SalesOrderFinancialDetails : {}", salesOrderFinancialDetailsDTO);
        return salesOrderFinancialDetailsRepository
            .save(salesOrderFinancialDetailsMapper.toEntity(salesOrderFinancialDetailsDTO))
            .map(salesOrderFinancialDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> update(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO) {
        log.debug("Request to update SalesOrderFinancialDetails : {}", salesOrderFinancialDetailsDTO);
        return salesOrderFinancialDetailsRepository
            .save(salesOrderFinancialDetailsMapper.toEntity(salesOrderFinancialDetailsDTO))
            .map(salesOrderFinancialDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> partialUpdate(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO) {
        log.debug("Request to partially update SalesOrderFinancialDetails : {}", salesOrderFinancialDetailsDTO);

        return salesOrderFinancialDetailsRepository
            .findById(salesOrderFinancialDetailsDTO.getSalesOrderFinancialId())
            .map(existingSalesOrderFinancialDetails -> {
                salesOrderFinancialDetailsMapper.partialUpdate(existingSalesOrderFinancialDetails, salesOrderFinancialDetailsDTO);

                return existingSalesOrderFinancialDetails;
            })
            .flatMap(salesOrderFinancialDetailsRepository::save)
            .map(salesOrderFinancialDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderFinancialDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderFinancialDetails");
        return salesOrderFinancialDetailsRepository.findAllBy(pageable).map(salesOrderFinancialDetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderFinancialDetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderFinancialDetailsDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderFinancialDetails : {}", id);
        return salesOrderFinancialDetailsRepository.findById(id).map(salesOrderFinancialDetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderFinancialDetails : {}", id);
        return salesOrderFinancialDetailsRepository.deleteById(id);
    }
}
