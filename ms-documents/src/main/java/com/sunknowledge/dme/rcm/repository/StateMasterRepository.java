package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StateMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the StateMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StateMasterRepository extends ReactiveCrudRepository<StateMaster, Long>, StateMasterRepositoryInternal {
    @Override
    <S extends StateMaster> Mono<S> save(S entity);

    @Override
    Flux<StateMaster> findAll();

    @Override
    Mono<StateMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface StateMasterRepositoryInternal {
    <S extends StateMaster> Mono<S> save(S entity);

    Flux<StateMaster> findAllBy(Pageable pageable);

    Flux<StateMaster> findAll();

    Mono<StateMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<StateMaster> findAllBy(Pageable pageable, Criteria criteria);

}
