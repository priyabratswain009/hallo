package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the ElligibilityResponseStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElligibilityResponseStatusRepository
    extends ReactiveCrudRepository<ElligibilityResponseStatus, Long>, ElligibilityResponseStatusRepositoryInternal {
    Flux<ElligibilityResponseStatus> findAllBy(Pageable pageable);

    @Override
    <S extends ElligibilityResponseStatus> Mono<S> save(S entity);

    @Override
    Flux<ElligibilityResponseStatus> findAll();

    @Override
    Mono<ElligibilityResponseStatus> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ElligibilityResponseStatusRepositoryInternal {
    <S extends ElligibilityResponseStatus> Mono<S> save(S entity);

    Flux<ElligibilityResponseStatus> findAllBy(Pageable pageable);

    Flux<ElligibilityResponseStatus> findAll();

    Mono<ElligibilityResponseStatus> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ElligibilityResponseStatus> findAllBy(Pageable pageable, Criteria criteria);

}
