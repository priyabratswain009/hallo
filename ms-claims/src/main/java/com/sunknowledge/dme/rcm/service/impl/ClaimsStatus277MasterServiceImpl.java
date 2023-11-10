package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master;
import com.sunknowledge.dme.rcm.repository.ClaimsStatus277MasterRepository;
import com.sunknowledge.dme.rcm.service.ClaimsStatus277MasterService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277MasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsStatus277MasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimsStatus277Master}.
 */
@Service
@Transactional
public class ClaimsStatus277MasterServiceImpl implements ClaimsStatus277MasterService {

    private final Logger log = LoggerFactory.getLogger(ClaimsStatus277MasterServiceImpl.class);

    private final ClaimsStatus277MasterRepository claimsStatus277MasterRepository;

    private final ClaimsStatus277MasterMapper claimsStatus277MasterMapper;

    public ClaimsStatus277MasterServiceImpl(
        ClaimsStatus277MasterRepository claimsStatus277MasterRepository,
        ClaimsStatus277MasterMapper claimsStatus277MasterMapper
    ) {
        this.claimsStatus277MasterRepository = claimsStatus277MasterRepository;
        this.claimsStatus277MasterMapper = claimsStatus277MasterMapper;
    }

    @Override
    public ClaimsStatus277MasterDTO save(ClaimsStatus277MasterDTO claimsStatus277MasterDTO) {
        log.debug("Request to save ClaimsStatus277Master : {}", claimsStatus277MasterDTO);
        ClaimsStatus277Master claimsStatus277Master = claimsStatus277MasterMapper.toEntity(claimsStatus277MasterDTO);
        claimsStatus277Master = claimsStatus277MasterRepository.save(claimsStatus277Master);
        return claimsStatus277MasterMapper.toDto(claimsStatus277Master);
    }

    @Override
    public ClaimsStatus277MasterDTO update(ClaimsStatus277MasterDTO claimsStatus277MasterDTO) {
        log.debug("Request to save ClaimsStatus277Master : {}", claimsStatus277MasterDTO);
        ClaimsStatus277Master claimsStatus277Master = claimsStatus277MasterMapper.toEntity(claimsStatus277MasterDTO);
        claimsStatus277Master = claimsStatus277MasterRepository.save(claimsStatus277Master);
        return claimsStatus277MasterMapper.toDto(claimsStatus277Master);
    }

    @Override
    public Optional<ClaimsStatus277MasterDTO> partialUpdate(ClaimsStatus277MasterDTO claimsStatus277MasterDTO) {
        log.debug("Request to partially update ClaimsStatus277Master : {}", claimsStatus277MasterDTO);

        return claimsStatus277MasterRepository
            .findById(claimsStatus277MasterDTO.getClaimStatus277MasterId())
            .map(existingClaimsStatus277Master -> {
                claimsStatus277MasterMapper.partialUpdate(existingClaimsStatus277Master, claimsStatus277MasterDTO);

                return existingClaimsStatus277Master;
            })
            .map(claimsStatus277MasterRepository::save)
            .map(claimsStatus277MasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimsStatus277MasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsStatus277Masters");
        return claimsStatus277MasterRepository.findAll(pageable).map(claimsStatus277MasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimsStatus277MasterDTO> findOne(Long id) {
        log.debug("Request to get ClaimsStatus277Master : {}", id);
        return claimsStatus277MasterRepository.findById(id).map(claimsStatus277MasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimsStatus277Master : {}", id);
        claimsStatus277MasterRepository.deleteById(id);
    }
}
