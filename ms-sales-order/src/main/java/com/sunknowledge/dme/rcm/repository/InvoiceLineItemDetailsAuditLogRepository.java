package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the InvoiceLineItemDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceLineItemDetailsAuditLogRepository
    extends ReactiveCrudRepository<InvoiceLineItemDetailsAuditLog, Long>, InvoiceLineItemDetailsAuditLogRepositoryInternal {
    Flux<InvoiceLineItemDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends InvoiceLineItemDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<InvoiceLineItemDetailsAuditLog> findAll();

    @Override
    Mono<InvoiceLineItemDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InvoiceLineItemDetailsAuditLogRepositoryInternal {
    <S extends InvoiceLineItemDetailsAuditLog> Mono<S> save(S entity);

    Flux<InvoiceLineItemDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<InvoiceLineItemDetailsAuditLog> findAll();

    Mono<InvoiceLineItemDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InvoiceLineItemDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
