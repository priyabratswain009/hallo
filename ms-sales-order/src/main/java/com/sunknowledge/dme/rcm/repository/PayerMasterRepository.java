package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PayerMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayerMasterRepository extends ReactiveCrudRepository<PayerMaster, Long>, PayerMasterRepositoryInternal {
    Flux<PayerMaster> findAllBy(Pageable pageable);

    @Override
    <S extends PayerMaster> Mono<S> save(S entity);

    @Override
    Flux<PayerMaster> findAll();

    @Override
    Mono<PayerMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PayerMasterRepositoryInternal {
    <S extends PayerMaster> Mono<S> save(S entity);

    Flux<PayerMaster> findAllBy(Pageable pageable);

    Flux<PayerMaster> findAll();

    Mono<PayerMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PayerMaster> findAllBy(Pageable pageable, Criteria criteria);

}
