package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderMaster}.
 */
@Service
@Transactional
public class SalesOrderMasterServiceImpl implements SalesOrderMasterService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterServiceImpl.class);

    private final SalesOrderMasterRepository salesOrderMasterRepository;

    private final SalesOrderMasterMapper salesOrderMasterMapper;

    public SalesOrderMasterServiceImpl(
        SalesOrderMasterRepository salesOrderMasterRepository,
        SalesOrderMasterMapper salesOrderMasterMapper
    ) {
        this.salesOrderMasterRepository = salesOrderMasterRepository;
        this.salesOrderMasterMapper = salesOrderMasterMapper;
    }

    @Override
    public Mono<SalesOrderMasterDTO> save(SalesOrderMasterDTO salesOrderMasterDTO) {
        log.debug("Request to save SalesOrderMaster : {}", salesOrderMasterDTO);
        return salesOrderMasterRepository.save(salesOrderMasterMapper.toEntity(salesOrderMasterDTO)).map(salesOrderMasterMapper::toDto);
    }

    @Override
    public Mono<SalesOrderMasterDTO> update(SalesOrderMasterDTO salesOrderMasterDTO) {
        log.debug("Request to save SalesOrderMaster : {}", salesOrderMasterDTO);
        return salesOrderMasterRepository.save(salesOrderMasterMapper.toEntity(salesOrderMasterDTO)).map(salesOrderMasterMapper::toDto);
    }

    @Override
    public Mono<SalesOrderMasterDTO> partialUpdate(SalesOrderMasterDTO salesOrderMasterDTO) {
        log.debug("Request to partially update SalesOrderMaster : {}", salesOrderMasterDTO);

        return salesOrderMasterRepository
            .findById(salesOrderMasterDTO.getSalesOrderId())
            .map(existingSalesOrderMaster -> {
                salesOrderMasterMapper.partialUpdate(existingSalesOrderMaster, salesOrderMasterDTO);

                return existingSalesOrderMaster;
            })
            .flatMap(salesOrderMasterRepository::save)
            .map(salesOrderMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderMasters");
        return salesOrderMasterRepository.findAllBy(pageable).map(salesOrderMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderMasterDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderMaster : {}", id);
        return salesOrderMasterRepository.findById(id).map(salesOrderMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderMaster : {}", id);
        return salesOrderMasterRepository.deleteById(id);
    }
}
