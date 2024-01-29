package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.EparRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EparRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EparRequestRepository extends ReactiveCrudRepository<EparRequest, Long>, EparRequestRepositoryInternal {
    Flux<EparRequest> findAllBy(Pageable pageable);

    @Override
    <S extends EparRequest> Mono<S> save(S entity);

    @Override
    Flux<EparRequest> findAll();

    @Override
    Mono<EparRequest> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EparRequestRepositoryInternal {
    <S extends EparRequest> Mono<S> save(S entity);

    Flux<EparRequest> findAllBy(Pageable pageable);

    Flux<EparRequest> findAll();

    Mono<EparRequest> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EparRequest> findAllBy(Pageable pageable, Criteria criteria);

}
