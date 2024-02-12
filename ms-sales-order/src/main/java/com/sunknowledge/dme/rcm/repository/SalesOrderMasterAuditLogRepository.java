package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SalesOrderMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderMasterAuditLogRepository
    extends ReactiveCrudRepository<SalesOrderMasterAuditLog, Long>, SalesOrderMasterAuditLogRepositoryInternal {
    Flux<SalesOrderMasterAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderMasterAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderMasterAuditLog> findAll();

    @Override
    Mono<SalesOrderMasterAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderMasterAuditLogRepositoryInternal {
    <S extends SalesOrderMasterAuditLog> Mono<S> save(S entity);

    Flux<SalesOrderMasterAuditLog> findAllBy(Pageable pageable);

    Flux<SalesOrderMasterAuditLog> findAll();

    Mono<SalesOrderMasterAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderMasterAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
