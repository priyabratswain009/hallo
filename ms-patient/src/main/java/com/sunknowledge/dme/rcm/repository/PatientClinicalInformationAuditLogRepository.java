package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientClinicalInformationAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientClinicalInformationAuditLogRepository
    extends ReactiveCrudRepository<PatientClinicalInformationAuditLog, Long>, PatientClinicalInformationAuditLogRepositoryInternal {
    Flux<PatientClinicalInformationAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends PatientClinicalInformationAuditLog> Mono<S> save(S entity);

    @Override
    Flux<PatientClinicalInformationAuditLog> findAll();

    @Override
    Mono<PatientClinicalInformationAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientClinicalInformationAuditLogRepositoryInternal {
    <S extends PatientClinicalInformationAuditLog> Mono<S> save(S entity);

    Flux<PatientClinicalInformationAuditLog> findAllBy(Pageable pageable);

    Flux<PatientClinicalInformationAuditLog> findAll();

    Mono<PatientClinicalInformationAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientClinicalInformationAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
