package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.Claims835CrossoverException;
import com.sunknowledge.dme.rcm.repository.Claims835CrossoverExceptionRepository;
import com.sunknowledge.dme.rcm.service.Claims835CrossoverExceptionService;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverExceptionDTO;
import com.sunknowledge.dme.rcm.service.mapper.Claims835CrossoverExceptionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Claims835CrossoverException}.
 */
@Service
@Transactional
public class Claims835CrossoverExceptionServiceImpl implements Claims835CrossoverExceptionService {

    private final Logger log = LoggerFactory.getLogger(Claims835CrossoverExceptionServiceImpl.class);

    private final Claims835CrossoverExceptionRepository claims835CrossoverExceptionRepository;

    private final Claims835CrossoverExceptionMapper claims835CrossoverExceptionMapper;

    public Claims835CrossoverExceptionServiceImpl(
        Claims835CrossoverExceptionRepository claims835CrossoverExceptionRepository,
        Claims835CrossoverExceptionMapper claims835CrossoverExceptionMapper
    ) {
        this.claims835CrossoverExceptionRepository = claims835CrossoverExceptionRepository;
        this.claims835CrossoverExceptionMapper = claims835CrossoverExceptionMapper;
    }

    @Override
    public Claims835CrossoverExceptionDTO save(Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO) {
        log.debug("Request to save Claims835CrossoverException : {}", claims835CrossoverExceptionDTO);
        Claims835CrossoverException claims835CrossoverException = claims835CrossoverExceptionMapper.toEntity(
            claims835CrossoverExceptionDTO
        );
        claims835CrossoverException = claims835CrossoverExceptionRepository.save(claims835CrossoverException);
        return claims835CrossoverExceptionMapper.toDto(claims835CrossoverException);
    }

    @Override
    public Claims835CrossoverExceptionDTO update(Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO) {
        log.debug("Request to save Claims835CrossoverException : {}", claims835CrossoverExceptionDTO);
        Claims835CrossoverException claims835CrossoverException = claims835CrossoverExceptionMapper.toEntity(
            claims835CrossoverExceptionDTO
        );
        claims835CrossoverException = claims835CrossoverExceptionRepository.save(claims835CrossoverException);
        return claims835CrossoverExceptionMapper.toDto(claims835CrossoverException);
    }

    @Override
    public Optional<Claims835CrossoverExceptionDTO> partialUpdate(Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO) {
        log.debug("Request to partially update Claims835CrossoverException : {}", claims835CrossoverExceptionDTO);

        return claims835CrossoverExceptionRepository
            .findById(claims835CrossoverExceptionDTO.getClaims835CrossoverExceptionId())
            .map(existingClaims835CrossoverException -> {
                claims835CrossoverExceptionMapper.partialUpdate(existingClaims835CrossoverException, claims835CrossoverExceptionDTO);

                return existingClaims835CrossoverException;
            })
            .map(claims835CrossoverExceptionRepository::save)
            .map(claims835CrossoverExceptionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Claims835CrossoverExceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Claims835CrossoverExceptions");
        return claims835CrossoverExceptionRepository.findAll(pageable).map(claims835CrossoverExceptionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Claims835CrossoverExceptionDTO> findOne(Long id) {
        log.debug("Request to get Claims835CrossoverException : {}", id);
        return claims835CrossoverExceptionRepository.findById(id).map(claims835CrossoverExceptionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Claims835CrossoverException : {}", id);
        claims835CrossoverExceptionRepository.deleteById(id);
    }
}
