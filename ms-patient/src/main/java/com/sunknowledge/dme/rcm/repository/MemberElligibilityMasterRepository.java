package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the MemberElligibilityMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberElligibilityMasterRepository
    extends ReactiveCrudRepository<MemberElligibilityMaster, Long>, MemberElligibilityMasterRepositoryInternal {
    Flux<MemberElligibilityMaster> findAllBy(Pageable pageable);

    @Override
    <S extends MemberElligibilityMaster> Mono<S> save(S entity);

    @Override
    Flux<MemberElligibilityMaster> findAll();

    @Override
    Mono<MemberElligibilityMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface MemberElligibilityMasterRepositoryInternal {
    <S extends MemberElligibilityMaster> Mono<S> save(S entity);

    Flux<MemberElligibilityMaster> findAllBy(Pageable pageable);

    Flux<MemberElligibilityMaster> findAll();

    Mono<MemberElligibilityMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<MemberElligibilityMaster> findAllBy(Pageable pageable, Criteria criteria);

}
