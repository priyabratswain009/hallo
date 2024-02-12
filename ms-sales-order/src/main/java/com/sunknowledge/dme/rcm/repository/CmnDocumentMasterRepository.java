package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CmnDocumentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CmnDocumentMasterRepository extends ReactiveCrudRepository<CmnDocumentMaster, Long>, CmnDocumentMasterRepositoryInternal {
    Flux<CmnDocumentMaster> findAllBy(Pageable pageable);

    @Override
    <S extends CmnDocumentMaster> Mono<S> save(S entity);

    @Override
    Flux<CmnDocumentMaster> findAll();

    @Override
    Mono<CmnDocumentMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CmnDocumentMasterRepositoryInternal {
    <S extends CmnDocumentMaster> Mono<S> save(S entity);

    Flux<CmnDocumentMaster> findAllBy(Pageable pageable);

    Flux<CmnDocumentMaster> findAll();

    Mono<CmnDocumentMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CmnDocumentMaster> findAllBy(Pageable pageable, Criteria criteria);

}
