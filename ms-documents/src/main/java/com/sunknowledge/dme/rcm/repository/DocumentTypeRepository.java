package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DocumentType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the DocumentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentTypeRepository extends ReactiveCrudRepository<DocumentType, Long>, DocumentTypeRepositoryInternal {
    @Override
    <S extends DocumentType> Mono<S> save(S entity);

    @Override
    Flux<DocumentType> findAll();

    @Override
    Mono<DocumentType> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DocumentTypeRepositoryInternal {
    <S extends DocumentType> Mono<S> save(S entity);

    Flux<DocumentType> findAllBy(Pageable pageable);

    Flux<DocumentType> findAll();

    Mono<DocumentType> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DocumentType> findAllBy(Pageable pageable, Criteria criteria);

}
