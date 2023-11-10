package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ParSoMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the ParSoMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParSoMapRepository extends ReactiveCrudRepository<ParSoMap, Long>, ParSoMapRepositoryInternal {
    Flux<ParSoMap> findAllBy(Pageable pageable);

    @Override
    <S extends ParSoMap> Mono<S> save(S entity);

    @Override
    Flux<ParSoMap> findAll();

    @Override
    Mono<ParSoMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ParSoMapRepositoryInternal {
    <S extends ParSoMap> Mono<S> save(S entity);

    Flux<ParSoMap> findAllBy(Pageable pageable);

    Flux<ParSoMap> findAll();

    Mono<ParSoMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ParSoMap> findAllBy(Pageable pageable, Criteria criteria);

}
