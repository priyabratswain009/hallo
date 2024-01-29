package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PickupExchangeItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PickupExchangeItemsRepository
    extends ReactiveCrudRepository<PickupExchangeItems, Long>, PickupExchangeItemsRepositoryInternal {
    Flux<PickupExchangeItems> findAllBy(Pageable pageable);

    @Override
    <S extends PickupExchangeItems> Mono<S> save(S entity);

    @Override
    Flux<PickupExchangeItems> findAll();

    @Override
    Mono<PickupExchangeItems> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PickupExchangeItemsRepositoryInternal {
    <S extends PickupExchangeItems> Mono<S> save(S entity);

    Flux<PickupExchangeItems> findAllBy(Pageable pageable);

    Flux<PickupExchangeItems> findAll();

    Mono<PickupExchangeItems> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PickupExchangeItems> findAllBy(Pageable pageable, Criteria criteria);

}
