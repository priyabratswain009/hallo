package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Details;
import com.sunknowledge.dme.rcm.repository.ClaimsStatus277DetailsRepository;
import com.sunknowledge.dme.rcm.service.ClaimsStatus277DetailsService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277DetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsStatus277DetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ClaimsStatus277Details}.
 */
@Service
@Transactional
public class ClaimsStatus277DetailsServiceImpl implements ClaimsStatus277DetailsService {

    private final Logger log = LoggerFactory.getLogger(ClaimsStatus277DetailsServiceImpl.class);

    private final ClaimsStatus277DetailsRepository claimsStatus277DetailsRepository;

    private final ClaimsStatus277DetailsMapper claimsStatus277DetailsMapper;

    public ClaimsStatus277DetailsServiceImpl(
        ClaimsStatus277DetailsRepository claimsStatus277DetailsRepository,
        ClaimsStatus277DetailsMapper claimsStatus277DetailsMapper
    ) {
        this.claimsStatus277DetailsRepository = claimsStatus277DetailsRepository;
        this.claimsStatus277DetailsMapper = claimsStatus277DetailsMapper;
    }

    @Override
    public ClaimsStatus277DetailsDTO save(ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO) {
        log.debug("Request to save ClaimsStatus277Details : {}", claimsStatus277DetailsDTO);
        ClaimsStatus277Details claimsStatus277Details = claimsStatus277DetailsMapper.toEntity(claimsStatus277DetailsDTO);
        claimsStatus277Details = claimsStatus277DetailsRepository.save(claimsStatus277Details);
        return claimsStatus277DetailsMapper.toDto(claimsStatus277Details);
    }

    @Override
    public ClaimsStatus277DetailsDTO update(ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO) {
        log.debug("Request to save ClaimsStatus277Details : {}", claimsStatus277DetailsDTO);
        ClaimsStatus277Details claimsStatus277Details = claimsStatus277DetailsMapper.toEntity(claimsStatus277DetailsDTO);
        claimsStatus277Details = claimsStatus277DetailsRepository.save(claimsStatus277Details);
        return claimsStatus277DetailsMapper.toDto(claimsStatus277Details);
    }

    @Override
    public Optional<ClaimsStatus277DetailsDTO> partialUpdate(ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO) {
        log.debug("Request to partially update ClaimsStatus277Details : {}", claimsStatus277DetailsDTO);

        return claimsStatus277DetailsRepository
            .findById(claimsStatus277DetailsDTO.getClaimStatus277DetailId())
            .map(existingClaimsStatus277Details -> {
                claimsStatus277DetailsMapper.partialUpdate(existingClaimsStatus277Details, claimsStatus277DetailsDTO);

                return existingClaimsStatus277Details;
            })
            .map(claimsStatus277DetailsRepository::save)
            .map(claimsStatus277DetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimsStatus277DetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsStatus277Details");
        return claimsStatus277DetailsRepository.findAll(pageable).map(claimsStatus277DetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimsStatus277DetailsDTO> findOne(Long id) {
        log.debug("Request to get ClaimsStatus277Details : {}", id);
        return claimsStatus277DetailsRepository.findById(id).map(claimsStatus277DetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimsStatus277Details : {}", id);
        claimsStatus277DetailsRepository.deleteById(id);
    }
}
