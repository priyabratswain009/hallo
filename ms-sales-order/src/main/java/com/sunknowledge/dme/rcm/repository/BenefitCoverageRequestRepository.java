package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the BenefitCoverageRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenefitCoverageRequestRepository
    extends ReactiveCrudRepository<BenefitCoverageRequest, Long>, BenefitCoverageRequestRepositoryInternal {
    Flux<BenefitCoverageRequest> findAllBy(Pageable pageable);

    @Override
    <S extends BenefitCoverageRequest> Mono<S> save(S entity);

    @Override
    Flux<BenefitCoverageRequest> findAll();

    @Override
    Mono<BenefitCoverageRequest> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BenefitCoverageRequestRepositoryInternal {
    <S extends BenefitCoverageRequest> Mono<S> save(S entity);

    Flux<BenefitCoverageRequest> findAllBy(Pageable pageable);

    Flux<BenefitCoverageRequest> findAll();

    Mono<BenefitCoverageRequest> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<BenefitCoverageRequest> findAllBy(Pageable pageable, Criteria criteria);

}
