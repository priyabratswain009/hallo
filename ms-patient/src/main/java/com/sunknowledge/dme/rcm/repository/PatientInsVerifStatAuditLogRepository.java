package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientInsVerifStatAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientInsVerifStatAuditLogRepository
    extends ReactiveCrudRepository<PatientInsVerifStatAuditLog, Long>, PatientInsVerifStatAuditLogRepositoryInternal {
    Flux<PatientInsVerifStatAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends PatientInsVerifStatAuditLog> Mono<S> save(S entity);

    @Override
    Flux<PatientInsVerifStatAuditLog> findAll();

    @Override
    Mono<PatientInsVerifStatAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientInsVerifStatAuditLogRepositoryInternal {
    <S extends PatientInsVerifStatAuditLog> Mono<S> save(S entity);

    Flux<PatientInsVerifStatAuditLog> findAllBy(Pageable pageable);

    Flux<PatientInsVerifStatAuditLog> findAll();

    Mono<PatientInsVerifStatAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientInsVerifStatAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
