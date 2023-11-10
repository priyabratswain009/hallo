package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryClaimsReSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.PrimaryClaimsReSubmissionMasterService;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimsReSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PrimaryClaimsReSubmissionMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PrimaryClaimsReSubmissionMaster}.
 */
@Service
@Transactional
public class PrimaryClaimsReSubmissionMasterServiceImpl implements PrimaryClaimsReSubmissionMasterService {

    private final Logger log = LoggerFactory.getLogger(PrimaryClaimsReSubmissionMasterServiceImpl.class);

    private final PrimaryClaimsReSubmissionMasterRepository primaryClaimsReSubmissionMasterRepository;

    private final PrimaryClaimsReSubmissionMasterMapper primaryClaimsReSubmissionMasterMapper;

    public PrimaryClaimsReSubmissionMasterServiceImpl(
        PrimaryClaimsReSubmissionMasterRepository primaryClaimsReSubmissionMasterRepository,
        PrimaryClaimsReSubmissionMasterMapper primaryClaimsReSubmissionMasterMapper
    ) {
        this.primaryClaimsReSubmissionMasterRepository = primaryClaimsReSubmissionMasterRepository;
        this.primaryClaimsReSubmissionMasterMapper = primaryClaimsReSubmissionMasterMapper;
    }

    @Override
    public PrimaryClaimsReSubmissionMasterDTO save(PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO) {
        log.debug("Request to save PrimaryClaimsReSubmissionMaster : {}", primaryClaimsReSubmissionMasterDTO);
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterMapper.toEntity(
            primaryClaimsReSubmissionMasterDTO
        );
        primaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterRepository.save(primaryClaimsReSubmissionMaster);
        return primaryClaimsReSubmissionMasterMapper.toDto(primaryClaimsReSubmissionMaster);
    }

    @Override
    public PrimaryClaimsReSubmissionMasterDTO update(PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO) {
        log.debug("Request to save PrimaryClaimsReSubmissionMaster : {}", primaryClaimsReSubmissionMasterDTO);
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterMapper.toEntity(
            primaryClaimsReSubmissionMasterDTO
        );
        primaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMasterRepository.save(primaryClaimsReSubmissionMaster);
        return primaryClaimsReSubmissionMasterMapper.toDto(primaryClaimsReSubmissionMaster);
    }

    @Override
    public Optional<PrimaryClaimsReSubmissionMasterDTO> partialUpdate(
        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO
    ) {
        log.debug("Request to partially update PrimaryClaimsReSubmissionMaster : {}", primaryClaimsReSubmissionMasterDTO);

        return primaryClaimsReSubmissionMasterRepository
            .findById(primaryClaimsReSubmissionMasterDTO.getChangeHealthPrimaryResubmisionMasterId())
            .map(existingPrimaryClaimsReSubmissionMaster -> {
                primaryClaimsReSubmissionMasterMapper.partialUpdate(
                    existingPrimaryClaimsReSubmissionMaster,
                    primaryClaimsReSubmissionMasterDTO
                );

                return existingPrimaryClaimsReSubmissionMaster;
            })
            .map(primaryClaimsReSubmissionMasterRepository::save)
            .map(primaryClaimsReSubmissionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrimaryClaimsReSubmissionMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrimaryClaimsReSubmissionMasters");
        return primaryClaimsReSubmissionMasterRepository.findAll(pageable).map(primaryClaimsReSubmissionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PrimaryClaimsReSubmissionMasterDTO> findOne(Long id) {
        log.debug("Request to get PrimaryClaimsReSubmissionMaster : {}", id);
        return primaryClaimsReSubmissionMasterRepository.findById(id).map(primaryClaimsReSubmissionMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrimaryClaimsReSubmissionMaster : {}", id);
        primaryClaimsReSubmissionMasterRepository.deleteById(id);
    }
}
