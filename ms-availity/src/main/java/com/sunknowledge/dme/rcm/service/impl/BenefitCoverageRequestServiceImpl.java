package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;
import com.sunknowledge.dme.rcm.service.BenefitCoverageRequestService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageRequestMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BenefitCoverageRequestDTO save(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        log.debug("Request to save BenefitCoverageRequest : {}", benefitCoverageRequestDTO);
        BenefitCoverageRequest benefitCoverageRequest = benefitCoverageRequestMapper.toEntity(benefitCoverageRequestDTO);
        benefitCoverageRequest = benefitCoverageRequestRepository.save(benefitCoverageRequest);
        return benefitCoverageRequestMapper.toDto(benefitCoverageRequest);
    }

    @Override
    public BenefitCoverageRequestDTO update(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        log.debug("Request to save BenefitCoverageRequest : {}", benefitCoverageRequestDTO);
        BenefitCoverageRequest benefitCoverageRequest = benefitCoverageRequestMapper.toEntity(benefitCoverageRequestDTO);
        benefitCoverageRequest = benefitCoverageRequestRepository.save(benefitCoverageRequest);
        return benefitCoverageRequestMapper.toDto(benefitCoverageRequest);
    }

    @Override
    public Optional<BenefitCoverageRequestDTO> partialUpdate(BenefitCoverageRequestDTO benefitCoverageRequestDTO) {
        log.debug("Request to partially update BenefitCoverageRequest : {}", benefitCoverageRequestDTO);

        return benefitCoverageRequestRepository
            .findById(benefitCoverageRequestDTO.getBenefitCoverageRequestId())
            .map(existingBenefitCoverageRequest -> {
                benefitCoverageRequestMapper.partialUpdate(existingBenefitCoverageRequest, benefitCoverageRequestDTO);

                return existingBenefitCoverageRequest;
            })
            .map(benefitCoverageRequestRepository::save)
            .map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BenefitCoverageRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BenefitCoverageRequests");
        return benefitCoverageRequestRepository.findAll(pageable).map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BenefitCoverageRequestDTO> findOne(Long id) {
        log.debug("Request to get BenefitCoverageRequest : {}", id);
        return benefitCoverageRequestRepository.findById(id).map(benefitCoverageRequestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BenefitCoverageRequest : {}", id);
        benefitCoverageRequestRepository.deleteById(id);
    }
}
