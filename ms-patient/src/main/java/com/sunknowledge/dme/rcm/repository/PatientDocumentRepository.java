package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientDocumentRepository extends ReactiveCrudRepository<PatientDocument, Long>, PatientDocumentRepositoryInternal {
    Flux<PatientDocument> findAllBy(Pageable pageable);

    @Override
    <S extends PatientDocument> Mono<S> save(S entity);

    @Override
    Flux<PatientDocument> findAll();

    @Override
    Mono<PatientDocument> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientDocumentRepositoryInternal {
    <S extends PatientDocument> Mono<S> save(S entity);

    Flux<PatientDocument> findAllBy(Pageable pageable);

    Flux<PatientDocument> findAll();

    Mono<PatientDocument> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientDocument> findAllBy(Pageable pageable, Criteria criteria);

}
