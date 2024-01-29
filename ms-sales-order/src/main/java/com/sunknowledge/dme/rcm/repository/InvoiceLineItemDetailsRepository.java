package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the InvoiceLineItemDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceLineItemDetailsRepository
    extends ReactiveCrudRepository<InvoiceLineItemDetails, Long>, InvoiceLineItemDetailsRepositoryInternal {
    Flux<InvoiceLineItemDetails> findAllBy(Pageable pageable);

    @Override
    <S extends InvoiceLineItemDetails> Mono<S> save(S entity);

    @Override
    Flux<InvoiceLineItemDetails> findAll();

    @Override
    Mono<InvoiceLineItemDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface InvoiceLineItemDetailsRepositoryInternal {
    <S extends InvoiceLineItemDetails> Mono<S> save(S entity);

    Flux<InvoiceLineItemDetails> findAllBy(Pageable pageable);

    Flux<InvoiceLineItemDetails> findAll();

    Mono<InvoiceLineItemDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<InvoiceLineItemDetails> findAllBy(Pageable pageable, Criteria criteria);

}
