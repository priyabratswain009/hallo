package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the SalesOrderFinancialDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderFinancialDetailsAuditLogRepository
    extends ReactiveCrudRepository<SalesOrderFinancialDetailsAuditLog, Long>, SalesOrderFinancialDetailsAuditLogRepositoryInternal {
    Flux<SalesOrderFinancialDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderFinancialDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderFinancialDetailsAuditLog> findAll();

    @Override
    Mono<SalesOrderFinancialDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderFinancialDetailsAuditLogRepositoryInternal {
    <S extends SalesOrderFinancialDetailsAuditLog> Mono<S> save(S entity);

    Flux<SalesOrderFinancialDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<SalesOrderFinancialDetailsAuditLog> findAll();

    Mono<SalesOrderFinancialDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderFinancialDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
