package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the DocumentReferenceFileMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentReferenceFileMapRepository
    extends ReactiveCrudRepository<DocumentReferenceFileMap, Long>, DocumentReferenceFileMapRepositoryInternal {
    Flux<DocumentReferenceFileMap> findAllBy(Pageable pageable);

    @Override
    <S extends DocumentReferenceFileMap> Mono<S> save(S entity);

    @Override
    Flux<DocumentReferenceFileMap> findAll();

    @Override
    Mono<DocumentReferenceFileMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DocumentReferenceFileMapRepositoryInternal {
    <S extends DocumentReferenceFileMap> Mono<S> save(S entity);

    Flux<DocumentReferenceFileMap> findAllBy(Pageable pageable);

    Flux<DocumentReferenceFileMap> findAll();

    Mono<DocumentReferenceFileMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DocumentReferenceFileMap> findAllBy(Pageable pageable, Criteria criteria);

}
