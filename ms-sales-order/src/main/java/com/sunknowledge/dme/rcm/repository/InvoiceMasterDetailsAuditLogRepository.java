package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the InvoiceMasterDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceMasterDetailsAuditLogRepository
    extends ReactiveCrudRepository<InvoiceMasterDetailsAuditLog, Long>, InvoiceMasterDetailsAuditLogRepositoryInternal {
    Flux<InvoiceMasterDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends InvoiceMasterDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<InvoiceMasterDetailsAuditLog> findAll();

    @Override
    Mono<InvoiceMasterDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InvoiceMasterDetailsAuditLogRepositoryInternal {
    <S extends InvoiceMasterDetailsAuditLog> Mono<S> save(S entity);

    Flux<InvoiceMasterDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<InvoiceMasterDetailsAuditLog> findAll();

    Mono<InvoiceMasterDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InvoiceMasterDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
