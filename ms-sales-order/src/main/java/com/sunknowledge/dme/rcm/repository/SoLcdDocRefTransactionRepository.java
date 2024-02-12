package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SoLcdDocRefTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoLcdDocRefTransactionRepository
    extends ReactiveCrudRepository<SoLcdDocRefTransaction, Long>, SoLcdDocRefTransactionRepositoryInternal {
    Flux<SoLcdDocRefTransaction> findAllBy(Pageable pageable);

    @Override
    <S extends SoLcdDocRefTransaction> Mono<S> save(S entity);

    @Override
    Flux<SoLcdDocRefTransaction> findAll();

    @Override
    Mono<SoLcdDocRefTransaction> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SoLcdDocRefTransactionRepositoryInternal {
    <S extends SoLcdDocRefTransaction> Mono<S> save(S entity);

    Flux<SoLcdDocRefTransaction> findAllBy(Pageable pageable);

    Flux<SoLcdDocRefTransaction> findAll();

    Mono<SoLcdDocRefTransaction> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SoLcdDocRefTransaction> findAllBy(Pageable pageable, Criteria criteria);

}
