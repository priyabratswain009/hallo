package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the DeliveryItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryItemsRepository extends ReactiveCrudRepository<DeliveryItems, Long>, DeliveryItemsRepositoryInternal {
    Flux<DeliveryItems> findAllBy(Pageable pageable);

    @Override
    <S extends DeliveryItems> Mono<S> save(S entity);

    @Override
    Flux<DeliveryItems> findAll();

    @Override
    Mono<DeliveryItems> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DeliveryItemsRepositoryInternal {
    <S extends DeliveryItems> Mono<S> save(S entity);

    Flux<DeliveryItems> findAllBy(Pageable pageable);

    Flux<DeliveryItems> findAll();

    Mono<DeliveryItems> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DeliveryItems> findAllBy(Pageable pageable, Criteria criteria);

}
