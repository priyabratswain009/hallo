package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SecondaryClaimSubmisionMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecondaryClaimSubmisionMasterAuditLogRepository
    extends ReactiveCrudRepository<SecondaryClaimSubmisionMasterAuditLog, Long>, SecondaryClaimSubmisionMasterAuditLogRepositoryInternal {
    Flux<SecondaryClaimSubmisionMasterAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SecondaryClaimSubmisionMasterAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SecondaryClaimSubmisionMasterAuditLog> findAll();

    @Override
    Mono<SecondaryClaimSubmisionMasterAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SecondaryClaimSubmisionMasterAuditLogRepositoryInternal {
    <S extends SecondaryClaimSubmisionMasterAuditLog> Mono<S> save(S entity);

    Flux<SecondaryClaimSubmisionMasterAuditLog> findAllBy(Pageable pageable);

    Flux<SecondaryClaimSubmisionMasterAuditLog> findAll();

    Mono<SecondaryClaimSubmisionMasterAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SecondaryClaimSubmisionMasterAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
