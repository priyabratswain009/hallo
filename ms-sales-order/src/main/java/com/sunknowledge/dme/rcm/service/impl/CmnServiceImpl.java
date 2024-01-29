package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.repository.CmnRepository;
import com.sunknowledge.dme.rcm.service.CmnService;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.mapper.CmnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Cmn}.
 */
@Service
@Transactional
public class CmnServiceImpl implements CmnService {

    private final Logger log = LoggerFactory.getLogger(CmnServiceImpl.class);

    private final CmnRepository cmnRepository;

    private final CmnMapper cmnMapper;

    public CmnServiceImpl(CmnRepository cmnRepository, CmnMapper cmnMapper) {
        this.cmnRepository = cmnRepository;
        this.cmnMapper = cmnMapper;
    }

    @Override
    public Mono<CmnDTO> save(CmnDTO cmnDTO) {
        log.debug("Request to save Cmn : {}", cmnDTO);
        return cmnRepository.save(cmnMapper.toEntity(cmnDTO)).map(cmnMapper::toDto);
    }

    @Override
    public Mono<CmnDTO> update(CmnDTO cmnDTO) {
        log.debug("Request to update Cmn : {}", cmnDTO);
        return cmnRepository.save(cmnMapper.toEntity(cmnDTO)).map(cmnMapper::toDto);
    }

    @Override
    public Mono<CmnDTO> partialUpdate(CmnDTO cmnDTO) {
        log.debug("Request to partially update Cmn : {}", cmnDTO);

        return cmnRepository
            .findById(cmnDTO.getCmnId())
            .map(existingCmn -> {
                cmnMapper.partialUpdate(existingCmn, cmnDTO);

                return existingCmn;
            })
            .flatMap(cmnRepository::save)
            .map(cmnMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<CmnDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cmns");
        return cmnRepository.findAllBy(pageable).map(cmnMapper::toDto);
    }

    public Mono<Long> countAll() {
        return cmnRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<CmnDTO> findOne(Long id) {
        log.debug("Request to get Cmn : {}", id);
        return cmnRepository.findById(id).map(cmnMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Cmn : {}", id);
        return cmnRepository.deleteById(id);
    }
}
