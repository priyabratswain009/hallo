package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Details;
import com.sunknowledge.dme.rcm.repository.ClaimsCob835DetailsRepository;
import com.sunknowledge.dme.rcm.service.ClaimsCob835DetailsService;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835DetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCob835DetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ClaimsCob835Details}.
 */
@Service
@Transactional
public class ClaimsCob835DetailsServiceImpl implements ClaimsCob835DetailsService {

    private final Logger log = LoggerFactory.getLogger(ClaimsCob835DetailsServiceImpl.class);

    private final ClaimsCob835DetailsRepository claimsCob835DetailsRepository;

    private final ClaimsCob835DetailsMapper claimsCob835DetailsMapper;

    public ClaimsCob835DetailsServiceImpl(
        ClaimsCob835DetailsRepository claimsCob835DetailsRepository,
        ClaimsCob835DetailsMapper claimsCob835DetailsMapper
    ) {
        this.claimsCob835DetailsRepository = claimsCob835DetailsRepository;
        this.claimsCob835DetailsMapper = claimsCob835DetailsMapper;
    }

    @Override
    public Mono<ClaimsCob835DetailsDTO> save(ClaimsCob835DetailsDTO claimsCob835DetailsDTO) {
        log.debug("Request to save ClaimsCob835Details : {}", claimsCob835DetailsDTO);
        return claimsCob835DetailsRepository
            .save(claimsCob835DetailsMapper.toEntity(claimsCob835DetailsDTO))
            .map(claimsCob835DetailsMapper::toDto);
    }

    @Override
    public Mono<ClaimsCob835DetailsDTO> update(ClaimsCob835DetailsDTO claimsCob835DetailsDTO) {
        log.debug("Request to save ClaimsCob835Details : {}", claimsCob835DetailsDTO);
        return claimsCob835DetailsRepository
            .save(claimsCob835DetailsMapper.toEntity(claimsCob835DetailsDTO))
            .map(claimsCob835DetailsMapper::toDto);
    }

    @Override
    public Mono<ClaimsCob835DetailsDTO> partialUpdate(ClaimsCob835DetailsDTO claimsCob835DetailsDTO) {
        log.debug("Request to partially update ClaimsCob835Details : {}", claimsCob835DetailsDTO);

        return claimsCob835DetailsRepository
            .findById(claimsCob835DetailsDTO.getClaimCob835DetailId())
            .map(existingClaimsCob835Details -> {
                claimsCob835DetailsMapper.partialUpdate(existingClaimsCob835Details, claimsCob835DetailsDTO);

                return existingClaimsCob835Details;
            })
            .flatMap(claimsCob835DetailsRepository::save)
            .map(claimsCob835DetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ClaimsCob835DetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimsCob835Details");
        return claimsCob835DetailsRepository.findAllBy(pageable).map(claimsCob835DetailsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return claimsCob835DetailsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ClaimsCob835DetailsDTO> findOne(Long id) {
        log.debug("Request to get ClaimsCob835Details : {}", id);
        return claimsCob835DetailsRepository.findById(id).map(claimsCob835DetailsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ClaimsCob835Details : {}", id);
        return claimsCob835DetailsRepository.deleteById(id);
    }
}
