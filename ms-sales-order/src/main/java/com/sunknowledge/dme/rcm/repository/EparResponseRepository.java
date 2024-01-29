package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.EparResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EparResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EparResponseRepository extends ReactiveCrudRepository<EparResponse, Long>, EparResponseRepositoryInternal {
    Flux<EparResponse> findAllBy(Pageable pageable);

    @Override
    <S extends EparResponse> Mono<S> save(S entity);

    @Override
    Flux<EparResponse> findAll();

    @Override
    Mono<EparResponse> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EparResponseRepositoryInternal {
    <S extends EparResponse> Mono<S> save(S entity);

    Flux<EparResponse> findAllBy(Pageable pageable);

    Flux<EparResponse> findAll();

    Mono<EparResponse> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EparResponse> findAllBy(Pageable pageable, Criteria criteria);

}
