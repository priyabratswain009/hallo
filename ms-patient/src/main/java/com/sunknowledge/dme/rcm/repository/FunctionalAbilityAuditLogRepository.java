package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FunctionalAbilityAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FunctionalAbilityAuditLogRepository
    extends ReactiveCrudRepository<FunctionalAbilityAuditLog, Long>, FunctionalAbilityAuditLogRepositoryInternal {
    Flux<FunctionalAbilityAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends FunctionalAbilityAuditLog> Mono<S> save(S entity);

    @Override
    Flux<FunctionalAbilityAuditLog> findAll();

    @Override
    Mono<FunctionalAbilityAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FunctionalAbilityAuditLogRepositoryInternal {
    <S extends FunctionalAbilityAuditLog> Mono<S> save(S entity);

    Flux<FunctionalAbilityAuditLog> findAllBy(Pageable pageable);

    Flux<FunctionalAbilityAuditLog> findAll();

    Mono<FunctionalAbilityAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FunctionalAbilityAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
