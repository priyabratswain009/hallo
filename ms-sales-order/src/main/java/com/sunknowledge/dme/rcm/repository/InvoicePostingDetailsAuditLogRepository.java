package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the InvoicePostingDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoicePostingDetailsAuditLogRepository
    extends ReactiveCrudRepository<InvoicePostingDetailsAuditLog, Long>, InvoicePostingDetailsAuditLogRepositoryInternal {
    Flux<InvoicePostingDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends InvoicePostingDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<InvoicePostingDetailsAuditLog> findAll();

    @Override
    Mono<InvoicePostingDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InvoicePostingDetailsAuditLogRepositoryInternal {
    <S extends InvoicePostingDetailsAuditLog> Mono<S> save(S entity);

    Flux<InvoicePostingDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<InvoicePostingDetailsAuditLog> findAll();

    Mono<InvoicePostingDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InvoicePostingDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
