package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientClinicalInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientClinicalInformationRepository
    extends ReactiveCrudRepository<PatientClinicalInformation, Long>, PatientClinicalInformationRepositoryInternal {
    Flux<PatientClinicalInformation> findAllBy(Pageable pageable);

    @Override
    <S extends PatientClinicalInformation> Mono<S> save(S entity);

    @Override
    Flux<PatientClinicalInformation> findAll();

    @Override
    Mono<PatientClinicalInformation> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientClinicalInformationRepositoryInternal {
    <S extends PatientClinicalInformation> Mono<S> save(S entity);

    Flux<PatientClinicalInformation> findAllBy(Pageable pageable);

    Flux<PatientClinicalInformation> findAll();

    Mono<PatientClinicalInformation> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientClinicalInformation> findAllBy(Pageable pageable, Criteria criteria);

}
