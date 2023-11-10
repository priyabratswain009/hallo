package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus;
import com.sunknowledge.dme.rcm.repository.ClaimsReportFileProcessStatusRepository;
import com.sunknowledge.dme.rcm.service.ClaimsReportFileProcessStatusService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsReportFileProcessStatusDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsReportFileProcessStatusMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimsReportFileProcessStatus}.
 */
@Service
@Transactional
public class ClaimsReportFileProcessStatusServiceImpl implements ClaimsReportFileProcessStatusService {

    private final Logger log = LoggerFactory.getLogger(ClaimsReportFileProcessStatusServiceImpl.class);

    private final ClaimsReportFileProcessStatusRepository claimsReportFileProcessStatusRepository;

    private final ClaimsReportFileProcessStatusMapper claimsReportFileProcessStatusMapper;

    public ClaimsReportFileProcessStatusServiceImpl(
        ClaimsReportFileProcessStatusRepository claimsReportFileProcessStatusRepository,
        ClaimsReportFileProcessStatusMapper claimsReportFileProcessStatusMapper
    ) {
        this.claimsReportFileProcessStatusRepository = claimsReportFileProcessStatusRepository;
        this.claimsReportFileProcessStatusMapper = claimsReportFileProcessStatusMapper;
    }

    @Override
    public ClaimsReportFileProcessStatusDTO save(ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO) {
        log.debug("Request to save ClaimsReportFileProcessStatus : {}", claimsReportFileProcessStatusDTO);
        ClaimsReportFileProcessStatus claimsReportFileProcessStatus = claimsReportFileProcessStatusMapper.toEntity(
            claimsReportFileProcessStatusDTO
        );
        claimsReportFileProcessStatus = claimsReportFileProcessStatusRepository.save(claimsReportFileProcessStatus);
        return claimsReportFileProcessStatusMapper.toDto(claimsReportFileProcessStatus);
    }

    @Override
    public ClaimsReportFileProcessStatusDTO update(ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO) {
        log.debug("Request to save ClaimsReportFileProcessStatus : {}", claimsReportFileProcessStatusDTO);
        ClaimsReportFileProcessStatus claimsReportFileProcessStatus = claimsReportFileProcessStatusMapper.toEntity(
            claimsReportFileProcessStatusDTO
        );
        claimsReportFileProcessStatus = claimsReportFileProcessStatusRepository.save(claimsReportFileProcessStatus);
        return claimsReportFileProcessStatusMapper.toDto(claimsReportFileProcessStatus);
    }

    @Override
    public Optional<ClaimsReportFileProcessStatusDTO> partialUpdate(ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO) {
        log.debug("Request to partially update ClaimsReportFileProcessStatus : {}", claimsReportFileProcessStatusDTO);

        return claimsReportFileProcessStatusRepository
            .findById(claimsReportFileProcessStatusDTO.getFileStatusId())
            .map(existingClaimsReportFileProcessStatus -> {
                claimsReportFileProcessStatusMapper.partialUpdate(existingClaimsReportFileProcessStatus, claimsReportFileProcessStatusDTO);

                return existingClaimsReportFileProcessStatus;
            })
            .map(claimsReportFileProcessStatusRepository::save)
            .map(claimsReportFileProcessStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimsReportFileProcessStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsReportFileProcessStatuses");
        return claimsReportFileProcessStatusRepository.findAll(pageable).map(claimsReportFileProcessStatusMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimsReportFileProcessStatusDTO> findOne(Long id) {
        log.debug("Request to get ClaimsReportFileProcessStatus : {}", id);
        return claimsReportFileProcessStatusRepository.findById(id).map(claimsReportFileProcessStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimsReportFileProcessStatus : {}", id);
        claimsReportFileProcessStatusRepository.deleteById(id);
    }
}
