package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;
import com.sunknowledge.dme.rcm.service.BenefitCoverageRequestService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link BenefitCoverageRequest}.
 */
@Service
@Transactional
public class BenefitCoverageRequestServiceImpl implements BenefitCoverageRequestService {

    private final Logger log = LoggerFactory.getLogger(BenefitCoverageRequestServiceImpl.class);

    private final BenefitCoverageRequestRepository benefitCoverageRequestRepository;

    private final BenefitCoverageRequestMapper benefitCoverageRequestMapper;

    public BenefitCoverageRequestServiceImpl(
        BenefitCoverageRequestRepository benefitCoverageRequestRepository,
        BenefitCoverageRequestMapper benefitCoverageRequestMapper
    ) {
        this.benefitCoverageRequestRepository = benefitCoverageRequestRepository;
        this.benefitCoverageRequestMapper = benefitCoverageRequestMapper;
    }

    @Override
    public Mono<BenefitCoverageRequestDTO> save(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        log.debug("Request to save BenefitCoverageRequest : {}", benefitCoverageRequestDTO);
        return benefitCoverageRequestRepository
            .save(benefitCoverageRequestMapper.toEntity(benefitCoverageRequestDTO))
            .map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    public Mono<BenefitCoverageRequestDTO> update(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        log.debug("Request to save BenefitCoverageRequest : {}", benefitCoverageRequestDTO);
        return benefitCoverageRequestRepository
            .save(benefitCoverageRequestMapper.toEntity(benefitCoverageRequestDTO))
            .map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    public Mono<BenefitCoverageRequestDTO> partialUpdate(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        log.debug("Request to partially update BenefitCoverageRequest : {}", benefitCoverageRequestDTO);

        return benefitCoverageRequestRepository
            .findById(benefitCoverageRequestDTO.getBenefitCoverageRequestId())
            .map(existingBenefitCoverageRequest -> {
                benefitCoverageRequestMapper.partialUpdate(existingBenefitCoverageRequest, benefitCoverageRequestDTO);

                return existingBenefitCoverageRequest;
            })
            .flatMap(benefitCoverageRequestRepository::save)
            .map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<BenefitCoverageRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BenefitCoverageRequests");
        return benefitCoverageRequestRepository.findAllBy(pageable).map(benefitCoverageRequestMapper::toDto);
    }

    public Mono<Long> countAll() {
        return benefitCoverageRequestRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<BenefitCoverageRequestDTO> findOne(Long id) {
        log.debug("Request to get BenefitCoverageRequest : {}", id);
        return benefitCoverageRequestRepository.findById(id).map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete BenefitCoverageRequest : {}", id);
        return benefitCoverageRequestRepository.deleteById(id);
    }
}
