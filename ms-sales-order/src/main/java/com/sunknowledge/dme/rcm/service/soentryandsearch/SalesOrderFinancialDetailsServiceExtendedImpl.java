package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderFinancialDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Primary
@Service
public class SalesOrderFinancialDetailsServiceExtendedImpl implements SalesOrderFinancialDetailsServiceExtended{

    @Autowired
    SalesOrderFinancialDetailsRepositoryExtended salesOrderFinancialDetailsRepositoryExtended;

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> save(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> update(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> partialUpdate(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderFinancialDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<SalesOrderFinancialDetails> findBySalesOrderId(Long SOID) {
        return salesOrderFinancialDetailsRepositoryExtended.findBySalesOrderId(SOID);
    }

    @Override
    public Flux<SalesOrderFinancialDetails> getSOFinancialDetailsBySOFinancialDetailsUUID(UUID sOFinancialDetailsUUID) {
        return salesOrderFinancialDetailsRepositoryExtended.getSOFinancialDetailsBySOFinancialDetailsUUID(sOFinancialDetailsUUID);
    }

    @Override
    public Long getIDByUUID(UUID sOFinancialDetailsUUID) {
        try {
            return salesOrderFinancialDetailsRepositoryExtended.getIDByUUID(sOFinancialDetailsUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<SalesOrderFinancialDetails> findById(Long id) {
        return salesOrderFinancialDetailsRepositoryExtended.findById(id);
    }
}
