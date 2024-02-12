package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SecondaryClaimSubmisionServicelines entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecondaryClaimSubmisionServicelinesRepository
    extends ReactiveCrudRepository<SecondaryClaimSubmisionServicelines, Long>, SecondaryClaimSubmisionServicelinesRepositoryInternal {
    Flux<SecondaryClaimSubmisionServicelines> findAllBy(Pageable pageable);

    @Override
    <S extends SecondaryClaimSubmisionServicelines> Mono<S> save(S entity);

    @Override
    Flux<SecondaryClaimSubmisionServicelines> findAll();

    @Override
    Mono<SecondaryClaimSubmisionServicelines> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SecondaryClaimSubmisionServicelinesRepositoryInternal {
    <S extends SecondaryClaimSubmisionServicelines> Mono<S> save(S entity);

    Flux<SecondaryClaimSubmisionServicelines> findAllBy(Pageable pageable);

    Flux<SecondaryClaimSubmisionServicelines> findAll();

    Mono<SecondaryClaimSubmisionServicelines> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SecondaryClaimSubmisionServicelines> findAllBy(Pageable pageable, Criteria criteria);

}
