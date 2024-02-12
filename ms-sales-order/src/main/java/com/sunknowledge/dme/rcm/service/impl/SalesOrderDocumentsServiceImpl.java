package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsRepository;
import com.sunknowledge.dme.rcm.service.SalesOrderDocumentsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderDocumentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SalesOrderDocuments}.
 */
@Service
@Transactional
public class SalesOrderDocumentsServiceImpl implements SalesOrderDocumentsService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderDocumentsServiceImpl.class);

    private final SalesOrderDocumentsRepository salesOrderDocumentsRepository;

    private final SalesOrderDocumentsMapper salesOrderDocumentsMapper;

    public SalesOrderDocumentsServiceImpl(
        SalesOrderDocumentsRepository salesOrderDocumentsRepository,
        SalesOrderDocumentsMapper salesOrderDocumentsMapper
    ) {
        this.salesOrderDocumentsRepository = salesOrderDocumentsRepository;
        this.salesOrderDocumentsMapper = salesOrderDocumentsMapper;
    }

    @Override
    public Mono<SalesOrderDocumentsDTO> save(SalesOrderDocumentsDTO salesOrderDocumentsDTO) {
        log.debug("Request to save SalesOrderDocuments : {}", salesOrderDocumentsDTO);
        return salesOrderDocumentsRepository
            .save(salesOrderDocumentsMapper.toEntity(salesOrderDocumentsDTO))
            .map(salesOrderDocumentsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderDocumentsDTO> update(SalesOrderDocumentsDTO salesOrderDocumentsDTO) {
        log.debug("Request to update SalesOrderDocuments : {}", salesOrderDocumentsDTO);
        return salesOrderDocumentsRepository
            .save(salesOrderDocumentsMapper.toEntity(salesOrderDocumentsDTO))
            .map(salesOrderDocumentsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderDocumentsDTO> partialUpdate(SalesOrderDocumentsDTO salesOrderDocumentsDTO) {
        log.debug("Request to partially update SalesOrderDocuments : {}", salesOrderDocumentsDTO);

        return salesOrderDocumentsRepository
            .findById(salesOrderDocumentsDTO.getSalesOrderDocumentId())
            .map(existingSalesOrderDocuments -> {
                salesOrderDocumentsMapper.partialUpdate(existingSalesOrderDocuments, salesOrderDocumentsDTO);

                return existingSalesOrderDocuments;
            })
            .flatMap(salesOrderDocumentsRepository::save)
            .map(salesOrderDocumentsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderDocumentsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrderDocuments");
        return salesOrderDocumentsRepository.findAllBy(pageable).map(salesOrderDocumentsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return salesOrderDocumentsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderDocumentsDTO> findOne(Long id) {
        log.debug("Request to get SalesOrderDocuments : {}", id);
        return salesOrderDocumentsRepository.findById(id).map(salesOrderDocumentsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SalesOrderDocuments : {}", id);
        return salesOrderDocumentsRepository.deleteById(id);
    }
}
