package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.repository.rowmapper.PatientDocumentSoMapRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public abstract class PatientDocumentSoMapRepositoryExtendedImpl extends PatientDocumentSoMapRepositoryInternalImpl implements PatientDocumentSoMapRepositoryExtended {

    private static final Table entityTable = Table.aliased("t_patient_document_so_map", EntityManager.ENTITY_ALIAS);

    public PatientDocumentSoMapRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, PatientDocumentSoMapRowMapper patientdocumentsomapMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, patientdocumentsomapMapper, entityOperations, converter);
    }

    @Override
    public Flux<PatientDocumentSoMap> findBySoNo(String soNo) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("so_no"), Conditions.just(soNo.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<PatientDocumentSoMap> findPatientDocumentsSoDataByPatientIdNo(String patientIdNo) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_id_no"), Conditions.just(patientIdNo.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<PatientDocumentSoMap> findPatientDocumentsSoDataByPatientDocumentId(String patientDocumentId) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_document_id"), Conditions.just(patientDocumentId.toString()));
        return createQuery(null, whereClause).all();
    }
}
