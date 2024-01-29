package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the PatientDto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientDtoRepository extends ReactiveCrudRepository<PatientDto, Long>, PatientDtoRepositoryInternal {
    Flux<PatientDto> findAllBy(Pageable pageable);

    @Override
    <S extends PatientDto> Mono<S> save(S entity);

    @Override
    Flux<PatientDto> findAll();

    @Override
    Mono<PatientDto> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PatientDtoRepositoryInternal {
    <S extends PatientDto> Mono<S> save(S entity);

    Flux<PatientDto> findAllBy(Pageable pageable);

    Flux<PatientDto> findAll();

    Mono<PatientDto> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PatientDto> findAllBy(Pageable pageable, Criteria criteria);

}
