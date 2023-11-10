package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientDoctorMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientDoctorMapRepository extends ReactiveCrudRepository<PatientDoctorMap, Long>, PatientDoctorMapRepositoryInternal {
    Flux<PatientDoctorMap> findAllBy(Pageable pageable);

    @Override
    <S extends PatientDoctorMap> Mono<S> save(S entity);

    @Override
    Flux<PatientDoctorMap> findAll();

    @Override
    Mono<PatientDoctorMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientDoctorMapRepositoryInternal {
    <S extends PatientDoctorMap> Mono<S> save(S entity);

    Flux<PatientDoctorMap> findAllBy(Pageable pageable);

    Flux<PatientDoctorMap> findAll();

    Mono<PatientDoctorMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientDoctorMap> findAllBy(Pageable pageable, Criteria criteria);

}
