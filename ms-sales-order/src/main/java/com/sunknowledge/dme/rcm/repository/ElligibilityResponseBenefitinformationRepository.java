package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the ElligibilityResponseBenefitinformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElligibilityResponseBenefitinformationRepository
    extends ReactiveCrudRepository<ElligibilityResponseBenefitinformation, Long>, ElligibilityResponseBenefitinformationRepositoryInternal {
    Flux<ElligibilityResponseBenefitinformation> findAllBy(Pageable pageable);

    @Override
    <S extends ElligibilityResponseBenefitinformation> Mono<S> save(S entity);

    @Override
    Flux<ElligibilityResponseBenefitinformation> findAll();

    @Override
    Mono<ElligibilityResponseBenefitinformation> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ElligibilityResponseBenefitinformationRepositoryInternal {
    <S extends ElligibilityResponseBenefitinformation> Mono<S> save(S entity);

    Flux<ElligibilityResponseBenefitinformation> findAllBy(Pageable pageable);

    Flux<ElligibilityResponseBenefitinformation> findAll();

    Mono<ElligibilityResponseBenefitinformation> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ElligibilityResponseBenefitinformation> findAllBy(Pageable pageable, Criteria criteria);

}
