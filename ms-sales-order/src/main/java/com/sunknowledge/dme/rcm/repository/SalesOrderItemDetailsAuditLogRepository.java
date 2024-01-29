package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SalesOrderItemDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderItemDetailsAuditLogRepository
    extends ReactiveCrudRepository<SalesOrderItemDetailsAuditLog, Long>, SalesOrderItemDetailsAuditLogRepositoryInternal {
    Flux<SalesOrderItemDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderItemDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderItemDetailsAuditLog> findAll();

    @Override
    Mono<SalesOrderItemDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderItemDetailsAuditLogRepositoryInternal {
    <S extends SalesOrderItemDetailsAuditLog> Mono<S> save(S entity);

    Flux<SalesOrderItemDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<SalesOrderItemDetailsAuditLog> findAll();

    Mono<SalesOrderItemDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderItemDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
