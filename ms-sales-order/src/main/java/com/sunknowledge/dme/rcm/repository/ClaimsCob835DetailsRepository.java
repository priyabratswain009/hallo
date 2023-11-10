package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Details;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the ClaimsCob835Details entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsCob835DetailsRepository
    extends ReactiveCrudRepository<ClaimsCob835Details, Long>, ClaimsCob835DetailsRepositoryInternal {
    Flux<ClaimsCob835Details> findAllBy(Pageable pageable);

    @Override
    <S extends ClaimsCob835Details> Mono<S> save(S entity);

    @Override
    Flux<ClaimsCob835Details> findAll();

    @Override
    Mono<ClaimsCob835Details> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ClaimsCob835DetailsRepositoryInternal {
    <S extends ClaimsCob835Details> Mono<S> save(S entity);

    Flux<ClaimsCob835Details> findAllBy(Pageable pageable);

    Flux<ClaimsCob835Details> findAll();

    Mono<ClaimsCob835Details> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ClaimsCob835Details> findAllBy(Pageable pageable, Criteria criteria);

}
