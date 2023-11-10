package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the SalesOrderClinicalDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderClinicalDetailsRepository
    extends ReactiveCrudRepository<SalesOrderClinicalDetails, Long>, SalesOrderClinicalDetailsRepositoryInternal {
    Flux<SalesOrderClinicalDetails> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderClinicalDetails> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderClinicalDetails> findAll();

    @Override
    Mono<SalesOrderClinicalDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderClinicalDetailsRepositoryInternal {
    <S extends SalesOrderClinicalDetails> Mono<S> save(S entity);

    Flux<SalesOrderClinicalDetails> findAllBy(Pageable pageable);

    Flux<SalesOrderClinicalDetails> findAll();

    Mono<SalesOrderClinicalDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderClinicalDetails> findAllBy(Pageable pageable, Criteria criteria);

}
