package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.MemberElligibility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the MemberElligibility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberElligibilityRepository
    extends ReactiveCrudRepository<MemberElligibility, Long>, MemberElligibilityRepositoryInternal {
    Flux<MemberElligibility> findAllBy(Pageable pageable);

    @Override
    <S extends MemberElligibility> Mono<S> save(S entity);

    @Override
    Flux<MemberElligibility> findAll();

    @Override
    Mono<MemberElligibility> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface MemberElligibilityRepositoryInternal {
    <S extends MemberElligibility> Mono<S> save(S entity);

    Flux<MemberElligibility> findAllBy(Pageable pageable);

    Flux<MemberElligibility> findAll();

    Mono<MemberElligibility> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<MemberElligibility> findAllBy(Pageable pageable, Criteria criteria);

}
