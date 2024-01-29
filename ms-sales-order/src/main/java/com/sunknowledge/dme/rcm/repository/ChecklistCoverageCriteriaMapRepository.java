package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ChecklistCoverageCriteriaMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChecklistCoverageCriteriaMapRepository
    extends ReactiveCrudRepository<ChecklistCoverageCriteriaMap, Long>, ChecklistCoverageCriteriaMapRepositoryInternal {
    Flux<ChecklistCoverageCriteriaMap> findAllBy(Pageable pageable);

    @Override
    <S extends ChecklistCoverageCriteriaMap> Mono<S> save(S entity);

    @Override
    Flux<ChecklistCoverageCriteriaMap> findAll();

    @Override
    Mono<ChecklistCoverageCriteriaMap> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ChecklistCoverageCriteriaMapRepositoryInternal {
    <S extends ChecklistCoverageCriteriaMap> Mono<S> save(S entity);

    Flux<ChecklistCoverageCriteriaMap> findAllBy(Pageable pageable);

    Flux<ChecklistCoverageCriteriaMap> findAll();

    Mono<ChecklistCoverageCriteriaMap> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ChecklistCoverageCriteriaMap> findAllBy(Pageable pageable, Criteria criteria);

}
