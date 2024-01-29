package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ParRequestDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParRequestDetailsRepository extends ReactiveCrudRepository<ParRequestDetails, Long>, ParRequestDetailsRepositoryInternal {
    Flux<ParRequestDetails> findAllBy(Pageable pageable);

    @Override
    <S extends ParRequestDetails> Mono<S> save(S entity);

    @Override
    Flux<ParRequestDetails> findAll();

    @Override
    Mono<ParRequestDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ParRequestDetailsRepositoryInternal {
    <S extends ParRequestDetails> Mono<S> save(S entity);

    Flux<ParRequestDetails> findAllBy(Pageable pageable);

    Flux<ParRequestDetails> findAll();

    Mono<ParRequestDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ParRequestDetails> findAllBy(Pageable pageable, Criteria criteria);

}
