package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.FunctionalAbility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the FunctionalAbility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FunctionalAbilityRepository extends ReactiveCrudRepository<FunctionalAbility, Long>, FunctionalAbilityRepositoryInternal {
    Flux<FunctionalAbility> findAllBy(Pageable pageable);

    @Override
    <S extends FunctionalAbility> Mono<S> save(S entity);

    @Override
    Flux<FunctionalAbility> findAll();

    @Override
    Mono<FunctionalAbility> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface FunctionalAbilityRepositoryInternal {
    <S extends FunctionalAbility> Mono<S> save(S entity);

    Flux<FunctionalAbility> findAllBy(Pageable pageable);

    Flux<FunctionalAbility> findAll();

    Mono<FunctionalAbility> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<FunctionalAbility> findAllBy(Pageable pageable, Criteria criteria);

}
