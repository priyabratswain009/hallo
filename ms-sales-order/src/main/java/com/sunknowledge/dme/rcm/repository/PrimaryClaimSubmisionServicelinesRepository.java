package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the PrimaryClaimSubmisionServicelines entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimaryClaimSubmisionServicelinesRepository
    extends ReactiveCrudRepository<PrimaryClaimSubmisionServicelines, Long>, PrimaryClaimSubmisionServicelinesRepositoryInternal {
    Flux<PrimaryClaimSubmisionServicelines> findAllBy(Pageable pageable);

    @Override
    <S extends PrimaryClaimSubmisionServicelines> Mono<S> save(S entity);

    @Override
    Flux<PrimaryClaimSubmisionServicelines> findAll();

    @Override
    Mono<PrimaryClaimSubmisionServicelines> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PrimaryClaimSubmisionServicelinesRepositoryInternal {
    <S extends PrimaryClaimSubmisionServicelines> Mono<S> save(S entity);

    Flux<PrimaryClaimSubmisionServicelines> findAllBy(Pageable pageable);

    Flux<PrimaryClaimSubmisionServicelines> findAll();

    Mono<PrimaryClaimSubmisionServicelines> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PrimaryClaimSubmisionServicelines> findAllBy(Pageable pageable, Criteria criteria);

}
