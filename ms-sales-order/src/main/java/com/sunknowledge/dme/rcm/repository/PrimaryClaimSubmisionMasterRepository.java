package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PrimaryClaimSubmisionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimaryClaimSubmisionMasterRepository
    extends ReactiveCrudRepository<PrimaryClaimSubmisionMaster, Long>, PrimaryClaimSubmisionMasterRepositoryInternal {
    Flux<PrimaryClaimSubmisionMaster> findAllBy(Pageable pageable);

    @Override
    <S extends PrimaryClaimSubmisionMaster> Mono<S> save(S entity);

    @Override
    Flux<PrimaryClaimSubmisionMaster> findAll();

    @Override
    Mono<PrimaryClaimSubmisionMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PrimaryClaimSubmisionMasterRepositoryInternal {
    <S extends PrimaryClaimSubmisionMaster> Mono<S> save(S entity);

    Flux<PrimaryClaimSubmisionMaster> findAllBy(Pageable pageable);

    Flux<PrimaryClaimSubmisionMaster> findAll();

    Mono<PrimaryClaimSubmisionMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PrimaryClaimSubmisionMaster> findAllBy(Pageable pageable, Criteria criteria);

}
