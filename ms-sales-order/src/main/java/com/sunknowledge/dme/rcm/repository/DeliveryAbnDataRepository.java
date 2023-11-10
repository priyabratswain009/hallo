package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the DeliveryAbnData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryAbnDataRepository extends ReactiveCrudRepository<DeliveryAbnData, Long>, DeliveryAbnDataRepositoryInternal {
    Flux<DeliveryAbnData> findAllBy(Pageable pageable);

    @Override
    <S extends DeliveryAbnData> Mono<S> save(S entity);

    @Override
    Flux<DeliveryAbnData> findAll();

    @Override
    Mono<DeliveryAbnData> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DeliveryAbnDataRepositoryInternal {
    <S extends DeliveryAbnData> Mono<S> save(S entity);

    Flux<DeliveryAbnData> findAllBy(Pageable pageable);

    Flux<DeliveryAbnData> findAll();

    Mono<DeliveryAbnData> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DeliveryAbnData> findAllBy(Pageable pageable, Criteria criteria);

}
