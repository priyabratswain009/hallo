package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the SalesOrderInsuranceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderInsuranceDetailsRepository
    extends ReactiveCrudRepository<SalesOrderInsuranceDetails, Long>, SalesOrderInsuranceDetailsRepositoryInternal {
    Flux<SalesOrderInsuranceDetails> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderInsuranceDetails> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderInsuranceDetails> findAll();

    @Override
    Mono<SalesOrderInsuranceDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderInsuranceDetailsRepositoryInternal {
    <S extends SalesOrderInsuranceDetails> Mono<S> save(S entity);

    Flux<SalesOrderInsuranceDetails> findAllBy(Pageable pageable);

    Flux<SalesOrderInsuranceDetails> findAll();

    Mono<SalesOrderInsuranceDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderInsuranceDetails> findAllBy(Pageable pageable, Criteria criteria);

}
