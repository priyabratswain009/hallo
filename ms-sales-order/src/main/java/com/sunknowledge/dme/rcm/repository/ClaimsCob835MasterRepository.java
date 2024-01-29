package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Master;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ClaimsCob835Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsCob835MasterRepository
    extends ReactiveCrudRepository<ClaimsCob835Master, Long>, ClaimsCob835MasterRepositoryInternal {
    Flux<ClaimsCob835Master> findAllBy(Pageable pageable);

    @Override
    <S extends ClaimsCob835Master> Mono<S> save(S entity);

    @Override
    Flux<ClaimsCob835Master> findAll();

    @Override
    Mono<ClaimsCob835Master> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ClaimsCob835MasterRepositoryInternal {
    <S extends ClaimsCob835Master> Mono<S> save(S entity);

    Flux<ClaimsCob835Master> findAllBy(Pageable pageable);

    Flux<ClaimsCob835Master> findAll();

    Mono<ClaimsCob835Master> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ClaimsCob835Master> findAllBy(Pageable pageable, Criteria criteria);

}
