package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

public interface PatientDocumentEntryRepositoryExtended extends PatientDocumentRepository{
    @Query("select patient.f_get_pdd_no()")
    Mono<String> getPDDNumber();
    @Query("select patient.f_get_pid_no()")
    Mono<String> getPIDNumber();
    @Query("select patient.f_get_pmr_no()")
    Mono<String> getPMRNumber();
}
