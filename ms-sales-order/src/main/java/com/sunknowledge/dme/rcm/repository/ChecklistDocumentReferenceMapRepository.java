package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ChecklistDocumentReferenceMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChecklistDocumentReferenceMapRepository
    extends ReactiveCrudRepository<ChecklistDocumentReferenceMap, Long>, ChecklistDocumentReferenceMapRepositoryInternal {
    Flux<ChecklistDocumentReferenceMap> findAllBy(Pageable pageable);

    @Override
    <S extends ChecklistDocumentReferenceMap> Mono<S> save(S entity);

    @Override
    Flux<ChecklistDocumentReferenceMap> findAll();

    @Override
    Mono<ChecklistDocumentReferenceMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ChecklistDocumentReferenceMapRepositoryInternal {
    <S extends ChecklistDocumentReferenceMap> Mono<S> save(S entity);

    Flux<ChecklistDocumentReferenceMap> findAllBy(Pageable pageable);

    Flux<ChecklistDocumentReferenceMap> findAll();

    Mono<ChecklistDocumentReferenceMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ChecklistDocumentReferenceMap> findAllBy(Pageable pageable, Criteria criteria);

}
