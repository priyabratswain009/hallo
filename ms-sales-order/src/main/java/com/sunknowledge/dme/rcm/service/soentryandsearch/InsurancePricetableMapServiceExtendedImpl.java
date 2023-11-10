package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.repository.InsurancePricetableMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import com.sunknowledge.dme.rcm.service.mapper.InsurancePricetableMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Service
public class InsurancePricetableMapServiceExtendedImpl implements InsurancePricetableMapServiceExtended {

    @Autowired
    InsurancePricetableMapRepositoryExtended insurancePricetableMapRepositoryExtended;

    @Autowired
    InsurancePricetableMapMapper insurancePricetableMapMapper;

    @Override
    public Flux<InsurancePricetableMap> findInsurancePricetableMapDetailsByInsuranceIdNo(String insuranceIdNo) {
        return insurancePricetableMapRepositoryExtended.findByInsuranceIdNo(insuranceIdNo);
    }

    @Override
    public Flux<InsurancePricetableMap> findInsurancePricetableMapDetailsByInsuranceId(Long insuranceId) {
        return insurancePricetableMapRepositoryExtended.findByInsuranceId(insuranceId);
    }

    @Override
    public Mono<InsurancePricetableMapDTO> save(InsurancePricetableMapDTO insurancePricetableMapDTO) {
        return null;
    }

    @Override
    public Mono<InsurancePricetableMapDTO> update(InsurancePricetableMapDTO insurancePricetableMapDTO) {
        return null;
    }

    @Override
    public Mono<InsurancePricetableMapDTO> partialUpdate(InsurancePricetableMapDTO insurancePricetableMapDTO) {
        return null;
    }

    @Override
    public Flux<InsurancePricetableMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<InsurancePricetableMapDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }
}
