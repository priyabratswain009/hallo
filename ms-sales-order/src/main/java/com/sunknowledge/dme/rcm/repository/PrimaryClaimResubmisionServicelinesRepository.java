package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PrimaryClaimResubmisionServicelines entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimaryClaimResubmisionServicelinesRepository
    extends ReactiveCrudRepository<PrimaryClaimResubmisionServicelines, Long>, PrimaryClaimResubmisionServicelinesRepositoryInternal {
    Flux<PrimaryClaimResubmisionServicelines> findAllBy(Pageable pageable);

    @Override
    <S extends PrimaryClaimResubmisionServicelines> Mono<S> save(S entity);

    @Override
    Flux<PrimaryClaimResubmisionServicelines> findAll();

    @Override
    Mono<PrimaryClaimResubmisionServicelines> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PrimaryClaimResubmisionServicelinesRepositoryInternal {
    <S extends PrimaryClaimResubmisionServicelines> Mono<S> save(S entity);

    Flux<PrimaryClaimResubmisionServicelines> findAllBy(Pageable pageable);

    Flux<PrimaryClaimResubmisionServicelines> findAll();

    Mono<PrimaryClaimResubmisionServicelines> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PrimaryClaimResubmisionServicelines> findAllBy(Pageable pageable, Criteria criteria);

}
