package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap;
import com.sunknowledge.dme.rcm.repository.DocumentReferenceFileMapRepository;
import com.sunknowledge.dme.rcm.service.DocumentReferenceFileMapService;
import com.sunknowledge.dme.rcm.service.dto.DocumentReferenceFileMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.DocumentReferenceFileMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link DocumentReferenceFileMap}.
 */
@Service
@Transactional
public class DocumentReferenceFileMapServiceImpl implements DocumentReferenceFileMapService {

    private final Logger log = LoggerFactory.getLogger(DocumentReferenceFileMapServiceImpl.class);

    private final DocumentReferenceFileMapRepository documentReferenceFileMapRepository;

    private final DocumentReferenceFileMapMapper documentReferenceFileMapMapper;

    public DocumentReferenceFileMapServiceImpl(
        DocumentReferenceFileMapRepository documentReferenceFileMapRepository,
        DocumentReferenceFileMapMapper documentReferenceFileMapMapper
    ) {
        this.documentReferenceFileMapRepository = documentReferenceFileMapRepository;
        this.documentReferenceFileMapMapper = documentReferenceFileMapMapper;
    }

    @Override
    public Mono<DocumentReferenceFileMapDTO> save(DocumentReferenceFileMapDTO documentReferenceFileMapDTO) {
        log.debug("Request to save DocumentReferenceFileMap : {}", documentReferenceFileMapDTO);
        return documentReferenceFileMapRepository
            .save(documentReferenceFileMapMapper.toEntity(documentReferenceFileMapDTO))
            .map(documentReferenceFileMapMapper::toDto);
    }

    @Override
    public Mono<DocumentReferenceFileMapDTO> update(DocumentReferenceFileMapDTO documentReferenceFileMapDTO) {
        log.debug("Request to update DocumentReferenceFileMap : {}", documentReferenceFileMapDTO);
        return documentReferenceFileMapRepository
            .save(documentReferenceFileMapMapper.toEntity(documentReferenceFileMapDTO))
            .map(documentReferenceFileMapMapper::toDto);
    }

    @Override
    public Mono<DocumentReferenceFileMapDTO> partialUpdate(DocumentReferenceFileMapDTO documentReferenceFileMapDTO) {
        log.debug("Request to partially update DocumentReferenceFileMap : {}", documentReferenceFileMapDTO);

        return documentReferenceFileMapRepository
            .findById(documentReferenceFileMapDTO.getDocumentReferenceFileMapId())
            .map(existingDocumentReferenceFileMap -> {
                documentReferenceFileMapMapper.partialUpdate(existingDocumentReferenceFileMap, documentReferenceFileMapDTO);

                return existingDocumentReferenceFileMap;
            })
            .flatMap(documentReferenceFileMapRepository::save)
            .map(documentReferenceFileMapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DocumentReferenceFileMapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DocumentReferenceFileMaps");
        return documentReferenceFileMapRepository.findAllBy(pageable).map(documentReferenceFileMapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return documentReferenceFileMapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DocumentReferenceFileMapDTO> findOne(Long id) {
        log.debug("Request to get DocumentReferenceFileMap : {}", id);
        return documentReferenceFileMapRepository.findById(id).map(documentReferenceFileMapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete DocumentReferenceFileMap : {}", id);
        return documentReferenceFileMapRepository.deleteById(id);
    }
}
