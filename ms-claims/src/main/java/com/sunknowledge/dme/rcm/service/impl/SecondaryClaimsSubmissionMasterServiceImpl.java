package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.repository.SecondaryClaimsSubmissionMasterRepository;
import com.sunknowledge.dme.rcm.service.SecondaryClaimsSubmissionMasterService;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimsSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SecondaryClaimsSubmissionMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SecondaryClaimsSubmissionMaster}.
 */
@Service
@Transactional
public class SecondaryClaimsSubmissionMasterServiceImpl implements SecondaryClaimsSubmissionMasterService {

    private final Logger log = LoggerFactory.getLogger(SecondaryClaimsSubmissionMasterServiceImpl.class);

    private final SecondaryClaimsSubmissionMasterRepository secondaryClaimsSubmissionMasterRepository;

    private final SecondaryClaimsSubmissionMasterMapper secondaryClaimsSubmissionMasterMapper;

    public SecondaryClaimsSubmissionMasterServiceImpl(
        SecondaryClaimsSubmissionMasterRepository secondaryClaimsSubmissionMasterRepository,
        SecondaryClaimsSubmissionMasterMapper secondaryClaimsSubmissionMasterMapper
    ) {
        this.secondaryClaimsSubmissionMasterRepository = secondaryClaimsSubmissionMasterRepository;
        this.secondaryClaimsSubmissionMasterMapper = secondaryClaimsSubmissionMasterMapper;
    }

    @Override
    public SecondaryClaimsSubmissionMasterDTO save(SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO) {
        log.debug("Request to save SecondaryClaimsSubmissionMaster : {}", secondaryClaimsSubmissionMasterDTO);
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterMapper.toEntity(
            secondaryClaimsSubmissionMasterDTO
        );
        secondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterRepository.save(secondaryClaimsSubmissionMaster);
        return secondaryClaimsSubmissionMasterMapper.toDto(secondaryClaimsSubmissionMaster);
    }

    @Override
    public SecondaryClaimsSubmissionMasterDTO update(SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO) {
        log.debug("Request to save SecondaryClaimsSubmissionMaster : {}", secondaryClaimsSubmissionMasterDTO);
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterMapper.toEntity(
            secondaryClaimsSubmissionMasterDTO
        );
        secondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterRepository.save(secondaryClaimsSubmissionMaster);
        return secondaryClaimsSubmissionMasterMapper.toDto(secondaryClaimsSubmissionMaster);
    }

    @Override
    public Optional<SecondaryClaimsSubmissionMasterDTO> partialUpdate(
        SecondaryClaimsSubmissionMasterDTO secondaryClaimsSubmissionMasterDTO
    ) {
        log.debug("Request to partially update SecondaryClaimsSubmissionMaster : {}", secondaryClaimsSubmissionMasterDTO);

        return secondaryClaimsSubmissionMasterRepository
            .findById(secondaryClaimsSubmissionMasterDTO.getChangeHealthSecondarySubmisionMasterId())
            .map(existingSecondaryClaimsSubmissionMaster -> {
                secondaryClaimsSubmissionMasterMapper.partialUpdate(
                    existingSecondaryClaimsSubmissionMaster,
                    secondaryClaimsSubmissionMasterDTO
                );

                return existingSecondaryClaimsSubmissionMaster;
            })
            .map(secondaryClaimsSubmissionMasterRepository::save)
            .map(secondaryClaimsSubmissionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SecondaryClaimsSubmissionMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SecondaryClaimsSubmissionMasters");
        return secondaryClaimsSubmissionMasterRepository.findAll(pageable).map(secondaryClaimsSubmissionMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SecondaryClaimsSubmissionMasterDTO> findOne(Long id) {
        log.debug("Request to get SecondaryClaimsSubmissionMaster : {}", id);
        return secondaryClaimsSubmissionMasterRepository.findById(id).map(secondaryClaimsSubmissionMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SecondaryClaimsSubmissionMaster : {}", id);
        secondaryClaimsSubmissionMasterRepository.deleteById(id);
    }
}
