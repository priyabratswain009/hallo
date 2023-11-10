package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.repository.PatientDocumentRepository;
import com.sunknowledge.dme.rcm.service.PatientDocumentService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link PatientDocument}.
 */
@Service
@Transactional
public class PatientDocumentServiceImpl implements PatientDocumentService {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentServiceImpl.class);

    private final PatientDocumentRepository patientDocumentRepository;

    private final PatientDocumentMapper patientDocumentMapper;

    public PatientDocumentServiceImpl(PatientDocumentRepository patientDocumentRepository, PatientDocumentMapper patientDocumentMapper) {
        this.patientDocumentRepository = patientDocumentRepository;
        this.patientDocumentMapper = patientDocumentMapper;
    }

    @Override
    public Mono<PatientDocumentDTO> save(PatientDocumentDTO patientDocumentDTO) {
        log.debug("Request to save PatientDocument : {}", patientDocumentDTO);
        return patientDocumentRepository.save(patientDocumentMapper.toEntity(patientDocumentDTO)).map(patientDocumentMapper::toDto);
    }

    @Override
    public Mono<PatientDocumentDTO> update(PatientDocumentDTO patientDocumentDTO) {
        log.debug("Request to update PatientDocument : {}", patientDocumentDTO);
        return patientDocumentRepository.save(patientDocumentMapper.toEntity(patientDocumentDTO)).map(patientDocumentMapper::toDto);
    }

    @Override
    public Mono<PatientDocumentDTO> partialUpdate(PatientDocumentDTO patientDocumentDTO) {
        log.debug("Request to partially update PatientDocument : {}", patientDocumentDTO);

        return patientDocumentRepository
            .findById(patientDocumentDTO.getPatientDocumentId())
            .map(existingPatientDocument -> {
                patientDocumentMapper.partialUpdate(existingPatientDocument, patientDocumentDTO);

                return existingPatientDocument;
            })
            .flatMap(patientDocumentRepository::save)
            .map(patientDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PatientDocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientDocuments");
        return patientDocumentRepository.findAllBy(pageable).map(patientDocumentMapper::toDto);
    }

    public Mono<Long> countAll() {
        return patientDocumentRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PatientDocumentDTO> findOne(Long id) {
        log.debug("Request to get PatientDocument : {}", id);
        return patientDocumentRepository.findById(id).map(patientDocumentMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PatientDocument : {}", id);
        return patientDocumentRepository.deleteById(id);
    }
}
