package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;
import com.sunknowledge.dme.rcm.service.BenefitCoverageResponseService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link BenefitCoverageResponse}.
 */
@Service
@Transactional
public class BenefitCoverageResponseServiceImpl implements BenefitCoverageResponseService {

    private final Logger log = LoggerFactory.getLogger(BenefitCoverageResponseServiceImpl.class);

    private final BenefitCoverageResponseRepository benefitCoverageResponseRepository;

    private final BenefitCoverageResponseMapper benefitCoverageResponseMapper;

    public BenefitCoverageResponseServiceImpl(
        BenefitCoverageResponseRepository benefitCoverageResponseRepository,
        BenefitCoverageResponseMapper benefitCoverageResponseMapper
    ) {
        this.benefitCoverageResponseRepository = benefitCoverageResponseRepository;
        this.benefitCoverageResponseMapper = benefitCoverageResponseMapper;
    }

    @Override
    public Mono<BenefitCoverageResponseDTO> save(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        log.debug("Request to save BenefitCoverageResponse : {}", benefitCoverageResponseDTO);
        return benefitCoverageResponseRepository
            .save(benefitCoverageResponseMapper.toEntity(benefitCoverageResponseDTO))
            .map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    public Mono<BenefitCoverageResponseDTO> update(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        log.debug("Request to save BenefitCoverageResponse : {}", benefitCoverageResponseDTO);
        return benefitCoverageResponseRepository
            .save(benefitCoverageResponseMapper.toEntity(benefitCoverageResponseDTO))
            .map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    public Mono<BenefitCoverageResponseDTO> partialUpdate(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        log.debug("Request to partially update BenefitCoverageResponse : {}", benefitCoverageResponseDTO);

        return benefitCoverageResponseRepository
            .findById(benefitCoverageResponseDTO.getBenefitCoverageResponseId())
            .map(existingBenefitCoverageResponse -> {
                benefitCoverageResponseMapper.partialUpdate(existingBenefitCoverageResponse, benefitCoverageResponseDTO);

                return existingBenefitCoverageResponse;
            })
            .flatMap(benefitCoverageResponseRepository::save)
            .map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<BenefitCoverageResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BenefitCoverageResponses");
        return benefitCoverageResponseRepository.findAllBy(pageable).map(benefitCoverageResponseMapper::toDto);
    }

    public Mono<Long> countAll() {
        return benefitCoverageResponseRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<BenefitCoverageResponseDTO> findOne(Long id) {
        log.debug("Request to get BenefitCoverageResponse : {}", id);
        return benefitCoverageResponseRepository.findById(id).map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete BenefitCoverageResponse : {}", id);
        return benefitCoverageResponseRepository.deleteById(id);
    }
}
