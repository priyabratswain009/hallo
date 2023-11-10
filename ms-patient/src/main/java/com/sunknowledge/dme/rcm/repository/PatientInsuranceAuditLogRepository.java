package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientInsuranceAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientInsuranceAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientInsuranceAuditLogRepository
    extends ReactiveCrudRepository<PatientInsuranceAuditLog, Long>, PatientInsuranceAuditLogRepositoryInternal {
    Flux<PatientInsuranceAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends PatientInsuranceAuditLog> Mono<S> save(S entity);

    @Override
    Flux<PatientInsuranceAuditLog> findAll();

    @Override
    Mono<PatientInsuranceAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientInsuranceAuditLogRepositoryInternal {
    <S extends PatientInsuranceAuditLog> Mono<S> save(S entity);

    Flux<PatientInsuranceAuditLog> findAllBy(Pageable pageable);

    Flux<PatientInsuranceAuditLog> findAll();

    Mono<PatientInsuranceAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientInsuranceAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
