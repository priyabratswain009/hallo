package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryResubmissionServiceLinesMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryResubmissionServiceLinesMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryResubmissionServiceLinesMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryResubmissionServiceLinesMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PrimaryResubmissionServiceLinesMaster}.
 */
@Service
@Transactional
public class PrimaryResubmissionServiceLinesMasterServiceImpl implements PrimaryResubmissionServiceLinesMasterService {

    private final Logger log = LoggerFactory.getLogger(PrimaryResubmissionServiceLinesMasterServiceImpl.class);

    private final PrimaryResubmissionServiceLinesMasterRepository primaryResubmissionServiceLinesMasterRepository;

    private final PrimaryResubmissionServiceLinesMasterMapper primaryResubmissionServiceLinesMasterMapper;

    public PrimaryResubmissionServiceLinesMasterServiceImpl(
        PrimaryResubmissionServiceLinesMasterRepository primaryResubmissionServiceLinesMasterRepository,
        PrimaryResubmissionServiceLinesMasterMapper primaryResubmissionServiceLinesMasterMapper
    ) {
        this.primaryResubmissionServiceLinesMasterRepository = primaryResubmissionServiceLinesMasterRepository;
        this.primaryResubmissionServiceLinesMasterMapper = primaryResubmissionServiceLinesMasterMapper;
    }

    @Override
    public PrimaryResubmissionServiceLinesMasterDTO save(
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    ) {
        log.debug("Request to save PrimaryResubmissionServiceLinesMaster : {}", primaryResubmissionServiceLinesMasterDTO);
        PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterMapper.toEntity(
            primaryResubmissionServiceLinesMasterDTO
        );
        primaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterRepository.save(primaryResubmissionServiceLinesMaster);
        return primaryResubmissionServiceLinesMasterMapper.toDto(primaryResubmissionServiceLinesMaster);
    }

    @Override
    public PrimaryResubmissionServiceLinesMasterDTO update(
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    ) {
        log.debug("Request to save PrimaryResubmissionServiceLinesMaster : {}", primaryResubmissionServiceLinesMasterDTO);
        PrimaryResubmissionServiceLinesMaster primaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterMapper.toEntity(
            primaryResubmissionServiceLinesMasterDTO
        );
        primaryResubmissionServiceLinesMaster = primaryResubmissionServiceLinesMasterRepository.save(primaryResubmissionServiceLinesMaster);
        return primaryResubmissionServiceLinesMasterMapper.toDto(primaryResubmissionServiceLinesMaster);
    }

    @Override
    public Optional<PrimaryResubmissionServiceLinesMasterDTO> partialUpdate(
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    ) {
        log.debug("Request to partially update PrimaryResubmissionServiceLinesMaster : {}", primaryResubmissionServiceLinesMasterDTO);

        return primaryResubmissionServiceLinesMasterRepository
            .findById(primaryResubmissionServiceLinesMasterDTO.getChangeHealthPrimaryResubmisionServicelinesId())
            .map(existingPrimaryResubmissionServiceLinesMaster -> {
                primaryResubmissionServiceLinesMasterMapper.partialUpdate(
                    existingPrimaryResubmissionServiceLinesMaster,
                    primaryResubmissionServiceLinesMasterDTO
                );

                return existingPrimaryResubmissionServiceLinesMaster;
            })
            .map(primaryResubmissionServiceLinesMasterRepository::save)
            .map(primaryResubmissionServiceLinesMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrimaryResubmissionServiceLinesMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrimaryResubmissionServiceLinesMasters");
        return primaryResubmissionServiceLinesMasterRepository.findAll(pageable).map(primaryResubmissionServiceLinesMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PrimaryResubmissionServiceLinesMasterDTO> findOne(Long id) {
        log.debug("Request to get PrimaryResubmissionServiceLinesMaster : {}", id);
        return primaryResubmissionServiceLinesMasterRepository.findById(id).map(primaryResubmissionServiceLinesMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrimaryResubmissionServiceLinesMaster : {}", id);
        primaryResubmissionServiceLinesMasterRepository.deleteById(id);
    }
}
