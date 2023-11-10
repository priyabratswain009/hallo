package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientMasterRepository extends ReactiveCrudRepository<PatientMaster, Long>, PatientMasterRepositoryInternal {
    Flux<PatientMaster> findAllBy(Pageable pageable);

    @Override
    <S extends PatientMaster> Mono<S> save(S entity);

    @Override
    Flux<PatientMaster> findAll();

    @Override
    Mono<PatientMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientMasterRepositoryInternal {
    <S extends PatientMaster> Mono<S> save(S entity);

    Flux<PatientMaster> findAllBy(Pageable pageable);

    Flux<PatientMaster> findAll();

    Mono<PatientMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientMaster> findAllBy(Pageable pageable, Criteria criteria);

}
