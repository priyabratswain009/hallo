package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SoRentalHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SoRentalHelper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoRentalHelperRepository extends ReactiveCrudRepository<SoRentalHelper, Long>, SoRentalHelperRepositoryInternal {
    @Override
    <S extends SoRentalHelper> Mono<S> save(S entity);

    @Override
    Flux<SoRentalHelper> findAll();

    @Override
    Mono<SoRentalHelper> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SoRentalHelperRepositoryInternal {
    <S extends SoRentalHelper> Mono<S> save(S entity);

    Flux<SoRentalHelper> findAllBy(Pageable pageable);

    Flux<SoRentalHelper> findAll();

    Mono<SoRentalHelper> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SoRentalHelper> findAllBy(Pageable pageable, Criteria criteria);

}
