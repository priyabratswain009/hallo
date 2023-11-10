package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the DeliveryTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryTicketRepository extends ReactiveCrudRepository<DeliveryTicket, Long>, DeliveryTicketRepositoryInternal {
    Flux<DeliveryTicket> findAllBy(Pageable pageable);

    @Override
    <S extends DeliveryTicket> Mono<S> save(S entity);

    @Override
    Flux<DeliveryTicket> findAll();

    @Override
    Mono<DeliveryTicket> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DeliveryTicketRepositoryInternal {
    <S extends DeliveryTicket> Mono<S> save(S entity);

    Flux<DeliveryTicket> findAllBy(Pageable pageable);

    Flux<DeliveryTicket> findAll();

    Mono<DeliveryTicket> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DeliveryTicket> findAllBy(Pageable pageable, Criteria criteria);

}
