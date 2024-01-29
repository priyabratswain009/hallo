package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.repository.CmnDocumentMasterRepository;
import com.sunknowledge.dme.rcm.service.CmnDocumentMasterService;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.CmnDocumentMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CmnDocumentMaster}.
 */
@Service
@Transactional
public class CmnDocumentMasterServiceImpl implements CmnDocumentMasterService {

    private final Logger log = LoggerFactory.getLogger(CmnDocumentMasterServiceImpl.class);

    private final CmnDocumentMasterRepository cmnDocumentMasterRepository;

    private final CmnDocumentMasterMapper cmnDocumentMasterMapper;

    public CmnDocumentMasterServiceImpl(
        CmnDocumentMasterRepository cmnDocumentMasterRepository,
        CmnDocumentMasterMapper cmnDocumentMasterMapper
    ) {
        this.cmnDocumentMasterRepository = cmnDocumentMasterRepository;
        this.cmnDocumentMasterMapper = cmnDocumentMasterMapper;
    }

    @Override
    public Mono<CmnDocumentMasterDTO> save(CmnDocumentMasterDTO cmnDocumentMasterDTO) {
        log.debug("Request to save CmnDocumentMaster : {}", cmnDocumentMasterDTO);
        return cmnDocumentMasterRepository.save(cmnDocumentMasterMapper.toEntity(cmnDocumentMasterDTO)).map(cmnDocumentMasterMapper::toDto);
    }

    @Override
    public Mono<CmnDocumentMasterDTO> update(CmnDocumentMasterDTO cmnDocumentMasterDTO) {
        log.debug("Request to update CmnDocumentMaster : {}", cmnDocumentMasterDTO);
        return cmnDocumentMasterRepository.save(cmnDocumentMasterMapper.toEntity(cmnDocumentMasterDTO)).map(cmnDocumentMasterMapper::toDto);
    }

    @Override
    public Mono<CmnDocumentMasterDTO> partialUpdate(CmnDocumentMasterDTO cmnDocumentMasterDTO) {
        log.debug("Request to partially update CmnDocumentMaster : {}", cmnDocumentMasterDTO);

        return cmnDocumentMasterRepository
            .findById(cmnDocumentMasterDTO.getCmnDocumentId())
            .map(existingCmnDocumentMaster -> {
                cmnDocumentMasterMapper.partialUpdate(existingCmnDocumentMaster, cmnDocumentMasterDTO);

                return existingCmnDocumentMaster;
            })
            .flatMap(cmnDocumentMasterRepository::save)
            .map(cmnDocumentMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CmnDocumentMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CmnDocumentMasters");
        return cmnDocumentMasterRepository.findAllBy(pageable).map(cmnDocumentMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return cmnDocumentMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<CmnDocumentMasterDTO> findOne(Long id) {
        log.debug("Request to get CmnDocumentMaster : {}", id);
        return cmnDocumentMasterRepository.findById(id).map(cmnDocumentMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CmnDocumentMaster : {}", id);
        return cmnDocumentMasterRepository.deleteById(id);
    }
}
