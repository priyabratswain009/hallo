package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SalesOrderItemDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderItemDetailsRepository
    extends ReactiveCrudRepository<SalesOrderItemDetails, Long>, SalesOrderItemDetailsRepositoryInternal {
    Flux<SalesOrderItemDetails> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderItemDetails> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderItemDetails> findAll();

    @Override
    Mono<SalesOrderItemDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderItemDetailsRepositoryInternal {
    <S extends SalesOrderItemDetails> Mono<S> save(S entity);

    Flux<SalesOrderItemDetails> findAllBy(Pageable pageable);

    Flux<SalesOrderItemDetails> findAll();

    Mono<SalesOrderItemDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderItemDetails> findAllBy(Pageable pageable, Criteria criteria);

}
