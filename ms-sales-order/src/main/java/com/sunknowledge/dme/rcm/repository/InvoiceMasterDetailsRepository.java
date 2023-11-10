package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the InvoiceMasterDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceMasterDetailsRepository
    extends ReactiveCrudRepository<InvoiceMasterDetails, Long>, InvoiceMasterDetailsRepositoryInternal {
    Flux<InvoiceMasterDetails> findAllBy(Pageable pageable);

    @Override
    <S extends InvoiceMasterDetails> Mono<S> save(S entity);

    @Override
    Flux<InvoiceMasterDetails> findAll();

    @Override
    Mono<InvoiceMasterDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InvoiceMasterDetailsRepositoryInternal {
    <S extends InvoiceMasterDetails> Mono<S> save(S entity);

    Flux<InvoiceMasterDetails> findAllBy(Pageable pageable);

    Flux<InvoiceMasterDetails> findAll();

    Mono<InvoiceMasterDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InvoiceMasterDetails> findAllBy(Pageable pageable, Criteria criteria);

}
