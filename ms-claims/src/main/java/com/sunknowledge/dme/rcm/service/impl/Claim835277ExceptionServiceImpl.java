package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.Claim835277Exception;
import com.sunknowledge.dme.rcm.repository.Claim835277ExceptionRepository;
import com.sunknowledge.dme.rcm.service.Claim835277ExceptionService;
import com.sunknowledge.dme.rcm.service.dto.Claim835277ExceptionDTO;
import com.sunknowledge.dme.rcm.service.mapper.Claim835277ExceptionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Claim835277Exception}.
 */
@Service
@Transactional
public class Claim835277ExceptionServiceImpl implements Claim835277ExceptionService {

    private final Logger log = LoggerFactory.getLogger(Claim835277ExceptionServiceImpl.class);

    private final Claim835277ExceptionRepository claim835277ExceptionRepository;

    private final Claim835277ExceptionMapper claim835277ExceptionMapper;

    public Claim835277ExceptionServiceImpl(
        Claim835277ExceptionRepository claim835277ExceptionRepository,
        Claim835277ExceptionMapper claim835277ExceptionMapper
    ) {
        this.claim835277ExceptionRepository = claim835277ExceptionRepository;
        this.claim835277ExceptionMapper = claim835277ExceptionMapper;
    }

    @Override
    public Claim835277ExceptionDTO save(Claim835277ExceptionDTO claim835277ExceptionDTO) {
        log.debug("Request to save Claim835277Exception : {}", claim835277ExceptionDTO);
        Claim835277Exception claim835277Exception = claim835277ExceptionMapper.toEntity(claim835277ExceptionDTO);
        claim835277Exception = claim835277ExceptionRepository.save(claim835277Exception);
        return claim835277ExceptionMapper.toDto(claim835277Exception);
    }

    @Override
    public Claim835277ExceptionDTO update(Claim835277ExceptionDTO claim835277ExceptionDTO) {
        log.debug("Request to save Claim835277Exception : {}", claim835277ExceptionDTO);
        Claim835277Exception claim835277Exception = claim835277ExceptionMapper.toEntity(claim835277ExceptionDTO);
        claim835277Exception = claim835277ExceptionRepository.save(claim835277Exception);
        return claim835277ExceptionMapper.toDto(claim835277Exception);
    }

    @Override
    public Optional<Claim835277ExceptionDTO> partialUpdate(Claim835277ExceptionDTO claim835277ExceptionDTO) {
        log.debug("Request to partially update Claim835277Exception : {}", claim835277ExceptionDTO);

        return claim835277ExceptionRepository
            .findById(claim835277ExceptionDTO.getExceptionId())
            .map(existingClaim835277Exception -> {
                claim835277ExceptionMapper.partialUpdate(existingClaim835277Exception, claim835277ExceptionDTO);

                return existingClaim835277Exception;
            })
            .map(claim835277ExceptionRepository::save)
            .map(claim835277ExceptionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Claim835277ExceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Claim835277Exceptions");
        return claim835277ExceptionRepository.findAll(pageable).map(claim835277ExceptionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Claim835277ExceptionDTO> findOne(Long id) {
        log.debug("Request to get Claim835277Exception : {}", id);
        return claim835277ExceptionRepository.findById(id).map(claim835277ExceptionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Claim835277Exception : {}", id);
        claim835277ExceptionRepository.deleteById(id);
    }
}
