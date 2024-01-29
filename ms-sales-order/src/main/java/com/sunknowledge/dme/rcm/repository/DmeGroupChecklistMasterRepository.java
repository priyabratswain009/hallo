package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the DmeGroupChecklistMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmeGroupChecklistMasterRepository
    extends ReactiveCrudRepository<DmeGroupChecklistMaster, Long>, DmeGroupChecklistMasterRepositoryInternal {
    Flux<DmeGroupChecklistMaster> findAllBy(Pageable pageable);

    @Override
    <S extends DmeGroupChecklistMaster> Mono<S> save(S entity);

    @Override
    Flux<DmeGroupChecklistMaster> findAll();

    @Override
    Mono<DmeGroupChecklistMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DmeGroupChecklistMasterRepositoryInternal {
    <S extends DmeGroupChecklistMaster> Mono<S> save(S entity);

    Flux<DmeGroupChecklistMaster> findAllBy(Pageable pageable);

    Flux<DmeGroupChecklistMaster> findAll();

    Mono<DmeGroupChecklistMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DmeGroupChecklistMaster> findAllBy(Pageable pageable, Criteria criteria);

}
