package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SalesOrderClinicalDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderClinicalDetailsAuditLogRepository
    extends ReactiveCrudRepository<SalesOrderClinicalDetailsAuditLog, Long>, SalesOrderClinicalDetailsAuditLogRepositoryInternal {
    Flux<SalesOrderClinicalDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderClinicalDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderClinicalDetailsAuditLog> findAll();

    @Override
    Mono<SalesOrderClinicalDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderClinicalDetailsAuditLogRepositoryInternal {
    <S extends SalesOrderClinicalDetailsAuditLog> Mono<S> save(S entity);

    Flux<SalesOrderClinicalDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<SalesOrderClinicalDetailsAuditLog> findAll();

    Mono<SalesOrderClinicalDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderClinicalDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
