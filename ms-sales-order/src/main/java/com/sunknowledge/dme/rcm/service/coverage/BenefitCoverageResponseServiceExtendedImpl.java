package com.sunknowledge.dme.rcm.service.coverage;

import com.sunknowledge.dme.rcm.repository.Availity.BenefitCoverageResponseRepo;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.BenefitCoverageResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Service
public class BenefitCoverageResponseServiceExtendedImpl implements BenefitCoverageResponseServiceExtended{

    @Autowired
    BenefitCoverageResponseRepo benefitCoverageResponseRepo;
    @Autowired
    BenefitCoverageResponseMapper benefitCoverageResponseMapper;

    @Override
    public Mono<BenefitCoverageResponseDTO> save(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        return null;
    }

    @Override
    public Mono<BenefitCoverageResponseDTO> update(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        return null;
    }

    @Override
    public Mono<BenefitCoverageResponseDTO> partialUpdate(BenefitCoverageResponseDTO benefitCoverageResponseDTO) {
        return null;
    }

    @Override
    public Flux<BenefitCoverageResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<BenefitCoverageResponseDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<BenefitCoverageResponseDTO> getAllPatientBenefitCoverage() {
        return benefitCoverageResponseRepo.findAll()
            .map(benefitCoverageResponseMapper::toDto);
    }

    @Override
    public Flux<String> getPatientBenefitCoverageByMemberId(String memberId) {
        return benefitCoverageResponseRepo.findAllByMemberId(memberId)
            .map(benefitCoverageResponseMapper::toDto).map(data->data.getResponseJsonText());
    }
}
