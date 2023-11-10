package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details;
import com.sunknowledge.dme.rcm.repository.ClaimsCOB835DetailsRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCOB835DetailsService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835DetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCOB835DetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimsCOB835Details}.
 */
@Service
@Transactional
public class ClaimsCOB835DetailsServiceImpl implements ClaimsCOB835DetailsService {

    private final Logger log = LoggerFactory.getLogger(ClaimsCOB835DetailsServiceImpl.class);

    private final ClaimsCOB835DetailsRepository claimsCOB835DetailsRepository;

    private final ClaimsCOB835DetailsMapper claimsCOB835DetailsMapper;

    public ClaimsCOB835DetailsServiceImpl(
        ClaimsCOB835DetailsRepository claimsCOB835DetailsRepository,
        ClaimsCOB835DetailsMapper claimsCOB835DetailsMapper
    ) {
        this.claimsCOB835DetailsRepository = claimsCOB835DetailsRepository;
        this.claimsCOB835DetailsMapper = claimsCOB835DetailsMapper;
    }

    @Override
    public ClaimsCOB835DetailsDTO save(ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO) {
        log.debug("Request to save ClaimsCOB835Details : {}", claimsCOB835DetailsDTO);
        ClaimsCOB835Details claimsCOB835Details = claimsCOB835DetailsMapper.toEntity(claimsCOB835DetailsDTO);
        claimsCOB835Details = claimsCOB835DetailsRepository.save(claimsCOB835Details);
        return claimsCOB835DetailsMapper.toDto(claimsCOB835Details);
    }

    @Override
    public ClaimsCOB835DetailsDTO update(ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO) {
        log.debug("Request to save ClaimsCOB835Details : {}", claimsCOB835DetailsDTO);
        ClaimsCOB835Details claimsCOB835Details = claimsCOB835DetailsMapper.toEntity(claimsCOB835DetailsDTO);
        claimsCOB835Details = claimsCOB835DetailsRepository.save(claimsCOB835Details);
        return claimsCOB835DetailsMapper.toDto(claimsCOB835Details);
    }

    @Override
    public Optional<ClaimsCOB835DetailsDTO> partialUpdate(ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO) {
        log.debug("Request to partially update ClaimsCOB835Details : {}", claimsCOB835DetailsDTO);

        return claimsCOB835DetailsRepository
            .findById(claimsCOB835DetailsDTO.getClaimCob835DetailId())
            .map(existingClaimsCOB835Details -> {
                claimsCOB835DetailsMapper.partialUpdate(existingClaimsCOB835Details, claimsCOB835DetailsDTO);

                return existingClaimsCOB835Details;
            })
            .map(claimsCOB835DetailsRepository::save)
            .map(claimsCOB835DetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimsCOB835DetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsCOB835Details");
        return claimsCOB835DetailsRepository.findAll(pageable).map(claimsCOB835DetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimsCOB835DetailsDTO> findOne(Long id) {
        log.debug("Request to get ClaimsCOB835Details : {}", id);
        return claimsCOB835DetailsRepository.findById(id).map(claimsCOB835DetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimsCOB835Details : {}", id);
        claimsCOB835DetailsRepository.deleteById(id);
    }
}
