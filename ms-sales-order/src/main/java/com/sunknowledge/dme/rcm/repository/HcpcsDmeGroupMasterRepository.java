package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the HcpcsDmeGroupMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HcpcsDmeGroupMasterRepository
    extends ReactiveCrudRepository<HcpcsDmeGroupMaster, Long>, HcpcsDmeGroupMasterRepositoryInternal {
    Flux<HcpcsDmeGroupMaster> findAllBy(Pageable pageable);

    @Override
    <S extends HcpcsDmeGroupMaster> Mono<S> save(S entity);

    @Override
    Flux<HcpcsDmeGroupMaster> findAll();

    @Override
    Mono<HcpcsDmeGroupMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface HcpcsDmeGroupMasterRepositoryInternal {
    <S extends HcpcsDmeGroupMaster> Mono<S> save(S entity);

    Flux<HcpcsDmeGroupMaster> findAllBy(Pageable pageable);

    Flux<HcpcsDmeGroupMaster> findAll();

    Mono<HcpcsDmeGroupMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<HcpcsDmeGroupMaster> findAllBy(Pageable pageable, Criteria criteria);

}
