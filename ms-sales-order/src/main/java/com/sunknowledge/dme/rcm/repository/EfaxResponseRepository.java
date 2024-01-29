package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EfaxResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EfaxResponseRepository extends ReactiveCrudRepository<EfaxResponse, Long>, EfaxResponseRepositoryInternal {
    Flux<EfaxResponse> findAllBy(Pageable pageable);

    @Override
    <S extends EfaxResponse> Mono<S> save(S entity);

    @Override
    Flux<EfaxResponse> findAll();

    @Override
    Mono<EfaxResponse> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EfaxResponseRepositoryInternal {
    <S extends EfaxResponse> Mono<S> save(S entity);

    Flux<EfaxResponse> findAllBy(Pageable pageable);

    Flux<EfaxResponse> findAll();

    Mono<EfaxResponse> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EfaxResponse> findAllBy(Pageable pageable, Criteria criteria);

}
