package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the InsurancePricetableMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsurancePricetableMapRepository
    extends ReactiveCrudRepository<InsurancePricetableMap, Long>, InsurancePricetableMapRepositoryInternal {
    Flux<InsurancePricetableMap> findAllBy(Pageable pageable);

    @Override
    <S extends InsurancePricetableMap> Mono<S> save(S entity);

    @Override
    Flux<InsurancePricetableMap> findAll();

    @Override
    Mono<InsurancePricetableMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InsurancePricetableMapRepositoryInternal {
    <S extends InsurancePricetableMap> Mono<S> save(S entity);

    Flux<InsurancePricetableMap> findAllBy(Pageable pageable);

    Flux<InsurancePricetableMap> findAll();

    Mono<InsurancePricetableMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InsurancePricetableMap> findAllBy(Pageable pageable, Criteria criteria);

}
