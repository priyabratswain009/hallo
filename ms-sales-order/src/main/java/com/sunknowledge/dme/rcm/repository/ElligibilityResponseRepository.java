package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the ElligibilityResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElligibilityResponseRepository
    extends ReactiveCrudRepository<ElligibilityResponse, Long>, ElligibilityResponseRepositoryInternal {
    Flux<ElligibilityResponse> findAllBy(Pageable pageable);

    @Override
    <S extends ElligibilityResponse> Mono<S> save(S entity);

    @Override
    Flux<ElligibilityResponse> findAll();

    @Override
    Mono<ElligibilityResponse> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ElligibilityResponseRepositoryInternal {
    <S extends ElligibilityResponse> Mono<S> save(S entity);

    Flux<ElligibilityResponse> findAllBy(Pageable pageable);

    Flux<ElligibilityResponse> findAll();

    Mono<ElligibilityResponse> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ElligibilityResponse> findAllBy(Pageable pageable, Criteria criteria);

}
