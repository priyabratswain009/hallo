package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PickupExchange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PickupExchangeRepository extends ReactiveCrudRepository<PickupExchange, Long>, PickupExchangeRepositoryInternal {
    Flux<PickupExchange> findAllBy(Pageable pageable);

    @Override
    <S extends PickupExchange> Mono<S> save(S entity);

    @Override
    Flux<PickupExchange> findAll();

    @Override
    Mono<PickupExchange> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PickupExchangeRepositoryInternal {
    <S extends PickupExchange> Mono<S> save(S entity);

    Flux<PickupExchange> findAllBy(Pageable pageable);

    Flux<PickupExchange> findAll();

    Mono<PickupExchange> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PickupExchange> findAllBy(Pageable pageable, Criteria criteria);

}
