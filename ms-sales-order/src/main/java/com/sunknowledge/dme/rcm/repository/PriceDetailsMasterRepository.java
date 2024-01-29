package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PriceDetailsMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PriceDetailsMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceDetailsMasterRepository
    extends ReactiveCrudRepository<PriceDetailsMaster, Long>, PriceDetailsMasterRepositoryInternal {
    Flux<PriceDetailsMaster> findAllBy(Pageable pageable);

    @Override
    <S extends PriceDetailsMaster> Mono<S> save(S entity);

    @Override
    Flux<PriceDetailsMaster> findAll();

    @Override
    Mono<PriceDetailsMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PriceDetailsMasterRepositoryInternal {
    <S extends PriceDetailsMaster> Mono<S> save(S entity);

    Flux<PriceDetailsMaster> findAllBy(Pageable pageable);

    Flux<PriceDetailsMaster> findAll();

    Mono<PriceDetailsMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PriceDetailsMaster> findAllBy(Pageable pageable, Criteria criteria);

}
