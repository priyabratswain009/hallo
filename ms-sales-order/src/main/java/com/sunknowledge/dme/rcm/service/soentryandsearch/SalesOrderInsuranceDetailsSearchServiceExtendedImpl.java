package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceSearchDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SalesOrderInsuranceDetailsSearchServiceExtendedImpl implements SalesOrderInsuranceDetailsSearchServiceExtended {
    @Autowired
    SalesOrderInsuranceDetailsSearchRepositoryExtended salesOrderInsuranceDetailsSearchRepository;

    @Override
    public Flux<SalesOrderInsuranceSearchDetailsDTO> getSOInsuranceDetailsByInsuranceName(String insuranceName) {
        Flux<SalesOrderInsuranceSearchDetailsDTO> list = salesOrderInsuranceDetailsSearchRepository.findSOInsuranceDetailsByInsuranceName(insuranceName.trim());
        return list;
    }

    @Override
    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByInsuranceId(Long insuranceId) {
        Flux<SalesOrderMasterSearchDetailsDTO> list = salesOrderInsuranceDetailsSearchRepository.findSODetailsByInsuranceId(insuranceId);
        return list;
    }

    @Override
    public Mono<SalesOrderInsuranceDetails> findBySalesOrderId(Long salesOrderID) {
        return salesOrderInsuranceDetailsSearchRepository.findBySalesOrderId(salesOrderID);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> save(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> update(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> partialUpdate(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderInsuranceDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }
}
