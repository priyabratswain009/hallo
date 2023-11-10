package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the WorkersCompensationAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkersCompensationAuditLogRepository
    extends ReactiveCrudRepository<WorkersCompensationAuditLog, Long>, WorkersCompensationAuditLogRepositoryInternal {
    Flux<WorkersCompensationAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends WorkersCompensationAuditLog> Mono<S> save(S entity);

    @Override
    Flux<WorkersCompensationAuditLog> findAll();

    @Override
    Mono<WorkersCompensationAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface WorkersCompensationAuditLogRepositoryInternal {
    <S extends WorkersCompensationAuditLog> Mono<S> save(S entity);

    Flux<WorkersCompensationAuditLog> findAllBy(Pageable pageable);

    Flux<WorkersCompensationAuditLog> findAll();

    Mono<WorkersCompensationAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<WorkersCompensationAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
