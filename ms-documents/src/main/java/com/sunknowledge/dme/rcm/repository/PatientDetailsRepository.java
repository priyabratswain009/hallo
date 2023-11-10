package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientDetailsRepository extends ReactiveCrudRepository<PatientDetails, Long>, PatientDetailsRepositoryInternal {
    @Query("SELECT * FROM patient_details entity WHERE entity.state_master_state_id = :id")
    Flux<PatientDetails> findByStateMaster(Long id);

    @Query("SELECT * FROM patient_details entity WHERE entity.state_master_state_id IS NULL")
    Flux<PatientDetails> findAllWhereStateMasterIsNull();

    @Query("SELECT * FROM patient_details entity WHERE entity.document_type_document_type_id = :id")
    Flux<PatientDetails> findByDocumentType(Long id);

    @Query("SELECT * FROM patient_details entity WHERE entity.document_type_document_type_id IS NULL")
    Flux<PatientDetails> findAllWhereDocumentTypeIsNull();

    @Override
    <S extends PatientDetails> Mono<S> save(S entity);

    @Override
    Flux<PatientDetails> findAll();

    @Override
    Mono<PatientDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientDetailsRepositoryInternal {
    <S extends PatientDetails> Mono<S> save(S entity);

    Flux<PatientDetails> findAllBy(Pageable pageable);

    Flux<PatientDetails> findAll();

    Mono<PatientDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientDetails> findAllBy(Pageable pageable, Criteria criteria);

}
