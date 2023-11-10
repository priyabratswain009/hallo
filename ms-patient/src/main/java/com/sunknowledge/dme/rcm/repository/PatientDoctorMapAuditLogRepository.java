package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientDoctorMapAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientDoctorMapAuditLogRepository
    extends ReactiveCrudRepository<PatientDoctorMapAuditLog, Long>, PatientDoctorMapAuditLogRepositoryInternal {
    Flux<PatientDoctorMapAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends PatientDoctorMapAuditLog> Mono<S> save(S entity);

    @Override
    Flux<PatientDoctorMapAuditLog> findAll();

    @Override
    Mono<PatientDoctorMapAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientDoctorMapAuditLogRepositoryInternal {
    <S extends PatientDoctorMapAuditLog> Mono<S> save(S entity);

    Flux<PatientDoctorMapAuditLog> findAllBy(Pageable pageable);

    Flux<PatientDoctorMapAuditLog> findAll();

    Mono<PatientDoctorMapAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientDoctorMapAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
