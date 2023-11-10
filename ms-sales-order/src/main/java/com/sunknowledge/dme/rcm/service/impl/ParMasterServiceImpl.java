package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.repository.ParMasterRepository;
import com.sunknowledge.dme.rcm.service.ParMasterService;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ParMaster}.
 */
@Service
@Transactional
public class ParMasterServiceImpl implements ParMasterService {

    private final Logger log = LoggerFactory.getLogger(ParMasterServiceImpl.class);

    private final ParMasterRepository parMasterRepository;

    private final ParMasterMapper parMasterMapper;

    public ParMasterServiceImpl(ParMasterRepository parMasterRepository, ParMasterMapper parMasterMapper) {
        this.parMasterRepository = parMasterRepository;
        this.parMasterMapper = parMasterMapper;
    }

    @Override
    public Mono<ParMasterDTO> save(ParMasterDTO parMasterDTO) {
        log.debug("Request to save ParMaster : {}", parMasterDTO);
        return parMasterRepository.save(parMasterMapper.toEntity(parMasterDTO)).map(parMasterMapper::toDto);
    }

    @Override
    public Mono<ParMasterDTO> update(ParMasterDTO parMasterDTO) {
        log.debug("Request to save ParMaster : {}", parMasterDTO);
        return parMasterRepository.save(parMasterMapper.toEntity(parMasterDTO)).map(parMasterMapper::toDto);
    }

    @Override
    public Mono<ParMasterDTO> partialUpdate(ParMasterDTO parMasterDTO) {
        log.debug("Request to partially update ParMaster : {}", parMasterDTO);

        return parMasterRepository
            .findById(parMasterDTO.getParId())
            .map(existingParMaster -> {
                parMasterMapper.partialUpdate(existingParMaster, parMasterDTO);

                return existingParMaster;
            })
            .flatMap(parMasterRepository::save)
            .map(parMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ParMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParMasters");
        return parMasterRepository.findAllBy(pageable).map(parMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return parMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ParMasterDTO> findOne(Long id) {
        log.debug("Request to get ParMaster : {}", id);
        return parMasterRepository.findById(id).map(parMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ParMaster : {}", id);
        return parMasterRepository.deleteById(id);
    }
}
