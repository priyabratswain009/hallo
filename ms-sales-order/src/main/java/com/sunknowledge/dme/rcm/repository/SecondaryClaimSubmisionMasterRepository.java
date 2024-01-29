package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SecondaryClaimSubmisionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecondaryClaimSubmisionMasterRepository
    extends ReactiveCrudRepository<SecondaryClaimSubmisionMaster, Long>, SecondaryClaimSubmisionMasterRepositoryInternal {
    Flux<SecondaryClaimSubmisionMaster> findAllBy(Pageable pageable);

    @Override
    <S extends SecondaryClaimSubmisionMaster> Mono<S> save(S entity);

    @Override
    Flux<SecondaryClaimSubmisionMaster> findAll();

    @Override
    Mono<SecondaryClaimSubmisionMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SecondaryClaimSubmisionMasterRepositoryInternal {
    <S extends SecondaryClaimSubmisionMaster> Mono<S> save(S entity);

    Flux<SecondaryClaimSubmisionMaster> findAllBy(Pageable pageable);

    Flux<SecondaryClaimSubmisionMaster> findAll();

    Mono<SecondaryClaimSubmisionMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SecondaryClaimSubmisionMaster> findAllBy(Pageable pageable, Criteria criteria);

}
