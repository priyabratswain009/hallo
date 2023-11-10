package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the SalesOrderDocuments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderDocumentsRepository
    extends ReactiveCrudRepository<SalesOrderDocuments, Long>, SalesOrderDocumentsRepositoryInternal {
    Flux<SalesOrderDocuments> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderDocuments> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderDocuments> findAll();

    @Override
    Mono<SalesOrderDocuments> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderDocumentsRepositoryInternal {
    <S extends SalesOrderDocuments> Mono<S> save(S entity);

    Flux<SalesOrderDocuments> findAllBy(Pageable pageable);

    Flux<SalesOrderDocuments> findAll();

    Mono<SalesOrderDocuments> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderDocuments> findAllBy(Pageable pageable, Criteria criteria);

}
