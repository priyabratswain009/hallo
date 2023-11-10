package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosis;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientDiagnosis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientDiagnosisRepository extends ReactiveCrudRepository<PatientDiagnosis, Long>, PatientDiagnosisRepositoryInternal {
    Flux<PatientDiagnosis> findAllBy(Pageable pageable);

    @Override
    <S extends PatientDiagnosis> Mono<S> save(S entity);

    @Override
    Flux<PatientDiagnosis> findAll();

    @Override
    Mono<PatientDiagnosis> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientDiagnosisRepositoryInternal {
    <S extends PatientDiagnosis> Mono<S> save(S entity);

    Flux<PatientDiagnosis> findAllBy(Pageable pageable);

    Flux<PatientDiagnosis> findAll();

    Mono<PatientDiagnosis> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientDiagnosis> findAllBy(Pageable pageable, Criteria criteria);

}
