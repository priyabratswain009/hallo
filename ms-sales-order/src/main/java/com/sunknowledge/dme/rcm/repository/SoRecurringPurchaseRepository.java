package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SoRecurringPurchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the SoRecurringPurchase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoRecurringPurchaseRepository
    extends ReactiveCrudRepository<SoRecurringPurchase, Long>, SoRecurringPurchaseRepositoryInternal {
    Flux<SoRecurringPurchase> findAllBy(Pageable pageable);

    @Override
    <S extends SoRecurringPurchase> Mono<S> save(S entity);

    @Override
    Flux<SoRecurringPurchase> findAll();

    @Override
    Mono<SoRecurringPurchase> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SoRecurringPurchaseRepositoryInternal {
    <S extends SoRecurringPurchase> Mono<S> save(S entity);

    Flux<SoRecurringPurchase> findAllBy(Pageable pageable);

    Flux<SoRecurringPurchase> findAll();

    Mono<SoRecurringPurchase> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SoRecurringPurchase> findAllBy(Pageable pageable, Criteria criteria);

}
