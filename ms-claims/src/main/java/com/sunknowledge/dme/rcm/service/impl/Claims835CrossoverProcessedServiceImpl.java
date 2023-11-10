package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.Claims835CrossoverProcessed;
import com.sunknowledge.dme.rcm.repository.Claims835CrossoverProcessedRepository;
import com.sunknowledge.dme.rcm.service.Claims835CrossoverProcessedService;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverProcessedDTO;
import com.sunknowledge.dme.rcm.service.mapper.Claims835CrossoverProcessedMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Claims835CrossoverProcessed}.
 */
@Service
@Transactional
public class Claims835CrossoverProcessedServiceImpl implements Claims835CrossoverProcessedService {

    private final Logger log = LoggerFactory.getLogger(Claims835CrossoverProcessedServiceImpl.class);

    private final Claims835CrossoverProcessedRepository claims835CrossoverProcessedRepository;

    private final Claims835CrossoverProcessedMapper claims835CrossoverProcessedMapper;

    public Claims835CrossoverProcessedServiceImpl(
        Claims835CrossoverProcessedRepository claims835CrossoverProcessedRepository,
        Claims835CrossoverProcessedMapper claims835CrossoverProcessedMapper
    ) {
        this.claims835CrossoverProcessedRepository = claims835CrossoverProcessedRepository;
        this.claims835CrossoverProcessedMapper = claims835CrossoverProcessedMapper;
    }

    @Override
    public Claims835CrossoverProcessedDTO save(Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO) {
        log.debug("Request to save Claims835CrossoverProcessed : {}", claims835CrossoverProcessedDTO);
        Claims835CrossoverProcessed claims835CrossoverProcessed = claims835CrossoverProcessedMapper.toEntity(
            claims835CrossoverProcessedDTO
        );
        claims835CrossoverProcessed = claims835CrossoverProcessedRepository.save(claims835CrossoverProcessed);
        return claims835CrossoverProcessedMapper.toDto(claims835CrossoverProcessed);
    }

    @Override
    public Claims835CrossoverProcessedDTO update(Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO) {
        log.debug("Request to save Claims835CrossoverProcessed : {}", claims835CrossoverProcessedDTO);
        Claims835CrossoverProcessed claims835CrossoverProcessed = claims835CrossoverProcessedMapper.toEntity(
            claims835CrossoverProcessedDTO
        );
        claims835CrossoverProcessed = claims835CrossoverProcessedRepository.save(claims835CrossoverProcessed);
        return claims835CrossoverProcessedMapper.toDto(claims835CrossoverProcessed);
    }

    @Override
    public Optional<Claims835CrossoverProcessedDTO> partialUpdate(Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO) {
        log.debug("Request to partially update Claims835CrossoverProcessed : {}", claims835CrossoverProcessedDTO);

        return claims835CrossoverProcessedRepository
            .findById(claims835CrossoverProcessedDTO.getClaims835CrossoverProcessedId())
            .map(existingClaims835CrossoverProcessed -> {
                claims835CrossoverProcessedMapper.partialUpdate(existingClaims835CrossoverProcessed, claims835CrossoverProcessedDTO);

                return existingClaims835CrossoverProcessed;
            })
            .map(claims835CrossoverProcessedRepository::save)
            .map(claims835CrossoverProcessedMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Claims835CrossoverProcessedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Claims835CrossoverProcesseds");
        return claims835CrossoverProcessedRepository.findAll(pageable).map(claims835CrossoverProcessedMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Claims835CrossoverProcessedDTO> findOne(Long id) {
        log.debug("Request to get Claims835CrossoverProcessed : {}", id);
        return claims835CrossoverProcessedRepository.findById(id).map(claims835CrossoverProcessedMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Claims835CrossoverProcessed : {}", id);
        claims835CrossoverProcessedRepository.deleteById(id);
    }
}
