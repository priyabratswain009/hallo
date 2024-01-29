package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CoverageCriteriaFileMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoverageCriteriaFileMapRepository
    extends ReactiveCrudRepository<CoverageCriteriaFileMap, Long>, CoverageCriteriaFileMapRepositoryInternal {
    Flux<CoverageCriteriaFileMap> findAllBy(Pageable pageable);

    @Override
    <S extends CoverageCriteriaFileMap> Mono<S> save(S entity);

    @Override
    Flux<CoverageCriteriaFileMap> findAll();

    @Override
    Mono<CoverageCriteriaFileMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CoverageCriteriaFileMapRepositoryInternal {
    <S extends CoverageCriteriaFileMap> Mono<S> save(S entity);

    Flux<CoverageCriteriaFileMap> findAllBy(Pageable pageable);

    Flux<CoverageCriteriaFileMap> findAll();

    Mono<CoverageCriteriaFileMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CoverageCriteriaFileMap> findAllBy(Pageable pageable, Criteria criteria);

}
