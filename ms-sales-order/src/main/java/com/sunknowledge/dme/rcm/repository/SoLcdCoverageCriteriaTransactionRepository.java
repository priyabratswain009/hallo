package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SoLcdCoverageCriteriaTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoLcdCoverageCriteriaTransactionRepository
    extends ReactiveCrudRepository<SoLcdCoverageCriteriaTransaction, Long>, SoLcdCoverageCriteriaTransactionRepositoryInternal {
    Flux<SoLcdCoverageCriteriaTransaction> findAllBy(Pageable pageable);

    @Override
    <S extends SoLcdCoverageCriteriaTransaction> Mono<S> save(S entity);

    @Override
    Flux<SoLcdCoverageCriteriaTransaction> findAll();

    @Override
    Mono<SoLcdCoverageCriteriaTransaction> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SoLcdCoverageCriteriaTransactionRepositoryInternal {
    <S extends SoLcdCoverageCriteriaTransaction> Mono<S> save(S entity);

    Flux<SoLcdCoverageCriteriaTransaction> findAllBy(Pageable pageable);

    Flux<SoLcdCoverageCriteriaTransaction> findAll();

    Mono<SoLcdCoverageCriteriaTransaction> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SoLcdCoverageCriteriaTransaction> findAllBy(Pageable pageable, Criteria criteria);

}
