package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import com.sunknowledge.dme.rcm.repository.EfaxResponseRepository;
import com.sunknowledge.dme.rcm.service.EfaxResponseService;
import com.sunknowledge.dme.rcm.service.dto.EfaxResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.EfaxResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EfaxResponse}.
 */
@Service
@Transactional
public class EfaxResponseServiceImpl implements EfaxResponseService {

    private final Logger log = LoggerFactory.getLogger(EfaxResponseServiceImpl.class);

    private final EfaxResponseRepository efaxResponseRepository;

    private final EfaxResponseMapper efaxResponseMapper;

    public EfaxResponseServiceImpl(EfaxResponseRepository efaxResponseRepository, EfaxResponseMapper efaxResponseMapper) {
        this.efaxResponseRepository = efaxResponseRepository;
        this.efaxResponseMapper = efaxResponseMapper;
    }

    @Override
    public Mono<EfaxResponseDTO> save(EfaxResponseDTO efaxResponseDTO) {
        log.debug("Request to save EfaxResponse : {}", efaxResponseDTO);
        return efaxResponseRepository.save(efaxResponseMapper.toEntity(efaxResponseDTO)).map(efaxResponseMapper::toDto);
    }

    @Override
    public Mono<EfaxResponseDTO> update(EfaxResponseDTO efaxResponseDTO) {
        log.debug("Request to update EfaxResponse : {}", efaxResponseDTO);
        return efaxResponseRepository.save(efaxResponseMapper.toEntity(efaxResponseDTO)).map(efaxResponseMapper::toDto);
    }

    @Override
    public Mono<EfaxResponseDTO> partialUpdate(EfaxResponseDTO efaxResponseDTO) {
        log.debug("Request to partially update EfaxResponse : {}", efaxResponseDTO);

        return efaxResponseRepository
            .findById(efaxResponseDTO.getEfaxResponseId())
            .map(existingEfaxResponse -> {
                efaxResponseMapper.partialUpdate(existingEfaxResponse, efaxResponseDTO);

                return existingEfaxResponse;
            })
            .flatMap(efaxResponseRepository::save)
            .map(efaxResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<EfaxResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EfaxResponses");
        return efaxResponseRepository.findAllBy(pageable).map(efaxResponseMapper::toDto);
    }

    public Mono<Long> countAll() {
        return efaxResponseRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<EfaxResponseDTO> findOne(Long id) {
        log.debug("Request to get EfaxResponse : {}", id);
        return efaxResponseRepository.findById(id).map(efaxResponseMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EfaxResponse : {}", id);
        return efaxResponseRepository.deleteById(id);
    }
}
