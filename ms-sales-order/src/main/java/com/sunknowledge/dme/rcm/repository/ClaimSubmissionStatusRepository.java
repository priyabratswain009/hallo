package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ClaimSubmissionStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimSubmissionStatusRepository
    extends ReactiveCrudRepository<ClaimSubmissionStatus, Long>, ClaimSubmissionStatusRepositoryInternal {
    Flux<ClaimSubmissionStatus> findAllBy(Pageable pageable);

    @Override
    <S extends ClaimSubmissionStatus> Mono<S> save(S entity);

    @Override
    Flux<ClaimSubmissionStatus> findAll();

    @Override
    Mono<ClaimSubmissionStatus> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ClaimSubmissionStatusRepositoryInternal {
    <S extends ClaimSubmissionStatus> Mono<S> save(S entity);

    Flux<ClaimSubmissionStatus> findAllBy(Pageable pageable);

    Flux<ClaimSubmissionStatus> findAll();

    Mono<ClaimSubmissionStatus> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ClaimSubmissionStatus> findAllBy(Pageable pageable, Criteria criteria);

}
