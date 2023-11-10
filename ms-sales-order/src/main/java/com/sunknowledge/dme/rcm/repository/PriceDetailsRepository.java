package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the PriceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceDetailsRepository extends ReactiveCrudRepository<PriceDetails, Long>, PriceDetailsRepositoryInternal {
    Flux<PriceDetails> findAllBy(Pageable pageable);

    @Override
    <S extends PriceDetails> Mono<S> save(S entity);

    @Override
    Flux<PriceDetails> findAll();

    @Override
    Mono<PriceDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PriceDetailsRepositoryInternal {
    <S extends PriceDetails> Mono<S> save(S entity);

    Flux<PriceDetails> findAllBy(Pageable pageable);

    Flux<PriceDetails> findAll();

    Mono<PriceDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PriceDetails> findAllBy(Pageable pageable, Criteria criteria);

}
