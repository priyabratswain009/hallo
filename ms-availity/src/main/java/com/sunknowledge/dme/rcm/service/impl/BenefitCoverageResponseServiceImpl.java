package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;
import com.sunknowledge.dme.rcm.service.BenefitCoverageResponseService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageResponseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BenefitCoverageResponseDTO save(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        log.debug("Request to save BenefitCoverageResponse : {}", benefitCoverageResponseDTO);
        BenefitCoverageResponse benefitCoverageResponse = benefitCoverageResponseMapper.toEntity(benefitCoverageResponseDTO);
        benefitCoverageResponse = benefitCoverageResponseRepository.save(benefitCoverageResponse);
        return benefitCoverageResponseMapper.toDto(benefitCoverageResponse);
    }

    @Override
    public BenefitCoverageResponseDTO update(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        log.debug("Request to save BenefitCoverageResponse : {}", benefitCoverageResponseDTO);
        BenefitCoverageResponse benefitCoverageResponse = benefitCoverageResponseMapper.toEntity(benefitCoverageResponseDTO);
        benefitCoverageResponse = benefitCoverageResponseRepository.save(benefitCoverageResponse);
        return benefitCoverageResponseMapper.toDto(benefitCoverageResponse);
    }

    @Override
    public Optional<BenefitCoverageResponseDTO> partialUpdate(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        log.debug("Request to partially update BenefitCoverageResponse : {}", benefitCoverageResponseDTO);

        return benefitCoverageResponseRepository
            .findById(benefitCoverageResponseDTO.getBenefitCoverageResponseId())
            .map(existingBenefitCoverageResponse -> {
                benefitCoverageResponseMapper.partialUpdate(existingBenefitCoverageResponse, benefitCoverageResponseDTO);

                return existingBenefitCoverageResponse;
            })
            .map(benefitCoverageResponseRepository::save)
            .map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BenefitCoverageResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BenefitCoverageResponses");
        return benefitCoverageResponseRepository.findAll(pageable).map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BenefitCoverageResponseDTO> findOne(Long id) {
        log.debug("Request to get BenefitCoverageResponse : {}", id);
        return benefitCoverageResponseRepository.findById(id).map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BenefitCoverageResponse : {}", id);
        benefitCoverageResponseRepository.deleteById(id);
    }
}
