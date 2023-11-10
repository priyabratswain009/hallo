package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the BenefitCoverageResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenefitCoverageResponseRepository
    extends ReactiveCrudRepository<BenefitCoverageResponse, Long>, BenefitCoverageResponseRepositoryInternal {
    Flux<BenefitCoverageResponse> findAllBy(Pageable pageable);

    @Override
    <S extends BenefitCoverageResponse> Mono<S> save(S entity);

    @Override
    Flux<BenefitCoverageResponse> findAll();

    @Override
    Mono<BenefitCoverageResponse> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BenefitCoverageResponseRepositoryInternal {
    <S extends BenefitCoverageResponse> Mono<S> save(S entity);

    Flux<BenefitCoverageResponse> findAllBy(Pageable pageable);

    Flux<BenefitCoverageResponse> findAll();

    Mono<BenefitCoverageResponse> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<BenefitCoverageResponse> findAllBy(Pageable pageable, Criteria criteria);

}
