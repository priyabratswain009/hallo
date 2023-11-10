package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimSubmisionMasterRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimSubmisionMasterService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimSubmisionMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SecondaryClaimSubmisionMaster}.
 */
@Service
@Transactional
public class SecondaryClaimSubmisionMasterServiceImpl implements SecondaryClaimSubmisionMasterService {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimSubmisionMasterServiceImpl.class);

    private final SecondaryClaimSubmisionMasterRepository secondaryClaimSubmisionMasterRepository;

    private final SecondaryClaimSubmisionMasterMapper secondaryClaimSubmisionMasterMapper;

    public SecondaryClaimSubmisionMasterServiceImpl(
        SecondaryClaimSubmisionMasterRepository secondaryClaimSubmisionMasterRepository,
        SecondaryClaimSubmisionMasterMapper secondaryClaimSubmisionMasterMapper
    ) {
        this.secondaryClaimSubmisionMasterRepository = secondaryClaimSubmisionMasterRepository;
        this.secondaryClaimSubmisionMasterMapper = secondaryClaimSubmisionMasterMapper;
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterDTO> save(SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO) {
        log.debug("Request to save SecondaryClaimSubmisionMaster : {}", secondaryClaimSubmisionMasterDTO);
        return secondaryClaimSubmisionMasterRepository
            .save(secondaryClaimSubmisionMasterMapper.toEntity(secondaryClaimSubmisionMasterDTO))
            .map(secondaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterDTO> update(SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO) {
        log.debug("Request to save SecondaryClaimSubmisionMaster : {}", secondaryClaimSubmisionMasterDTO);
        return secondaryClaimSubmisionMasterRepository
            .save(secondaryClaimSubmisionMasterMapper.toEntity(secondaryClaimSubmisionMasterDTO))
            .map(secondaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterDTO> partialUpdate(SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO) {
        log.debug("Request to partially update SecondaryClaimSubmisionMaster : {}", secondaryClaimSubmisionMasterDTO);

        return secondaryClaimSubmisionMasterRepository
            .findById(secondaryClaimSubmisionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
            .map(existingSecondaryClaimSubmisionMaster -> {
                secondaryClaimSubmisionMasterMapper.partialUpdate(existingSecondaryClaimSubmisionMaster, secondaryClaimSubmisionMasterDTO);

                return existingSecondaryClaimSubmisionMaster;
            })
            .flatMap(secondaryClaimSubmisionMasterRepository::save)
            .map(secondaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SecondaryClaimSubmisionMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SecondaryClaimSubmisionMasters");
        return secondaryClaimSubmisionMasterRepository.findAllBy(pageable).map(secondaryClaimSubmisionMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return secondaryClaimSubmisionMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SecondaryClaimSubmisionMasterDTO> findOne(Long id) {
        log.debug("Request to get SecondaryClaimSubmisionMaster : {}", id);
        return secondaryClaimSubmisionMasterRepository.findById(id).map(secondaryClaimSubmisionMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SecondaryClaimSubmisionMaster : {}", id);
        return secondaryClaimSubmisionMasterRepository.deleteById(id);
    }
}
