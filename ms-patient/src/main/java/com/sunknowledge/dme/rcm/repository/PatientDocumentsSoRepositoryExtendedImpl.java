package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.repository.rowmapper.PatientDocumentRowMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@Primary
public class PatientDocumentsSoRepositoryExtendedImpl extends PatientDocumentRepositoryInternalImpl implements  PatientDocumentsSoRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_patient_document", EntityManager.ENTITY_ALIAS);

    public PatientDocumentsSoRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, PatientDocumentRowMapper patientdocumentMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, patientdocumentMapper, entityOperations, converter);
    }


    @Override
    public Flux<PatientDocument> findByPatientIdNo(String patientIdNo) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_id_no"), Conditions.just(patientIdNo.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<PatientDocument> findByPatientId(String patientId) {
        System.out.println("=======patientId======="+patientId);
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_id"), Conditions.just(patientId.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<PatientDocument> findByPatientDocumentNo(String patientDocumentNo){
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_document_no"), Conditions.just(patientDocumentNo.toString()));
        return createQuery(null, whereClause).all();
    }
}
