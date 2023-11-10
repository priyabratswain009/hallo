package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.PatientDetails;
import com.sunknowledge.dme.rcm.domain.StateMaster;
import com.sunknowledge.dme.rcm.repository.rowmapper.DocumentTypeRowMapper;
import com.sunknowledge.dme.rcm.repository.rowmapper.PatientDetailsRowMapper;
import com.sunknowledge.dme.rcm.repository.rowmapper.StateMasterRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.*;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the PatientDetails entity.
 */
@SuppressWarnings("unused")
class PatientDetailsRepositoryInternalImpl extends SimpleR2dbcRepository<PatientDetails, Long> implements PatientDetailsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final StateMasterRowMapper statemasterMapper;
    private final DocumentTypeRowMapper documenttypeMapper;
    private final PatientDetailsRowMapper patientdetailsMapper;

    private static final Table entityTable = Table.aliased("patient_details", EntityManager.ENTITY_ALIAS);
    private static final Table stateMasterTable = Table.aliased("state_master", "stateMaster");
    private static final Table documentTypeTable = Table.aliased("document_type", "documentType");

    public PatientDetailsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        StateMasterRowMapper statemasterMapper,
        DocumentTypeRowMapper documenttypeMapper,
        PatientDetailsRowMapper patientdetailsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(PatientDetails.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.statemasterMapper = statemasterMapper;
        this.documenttypeMapper = documenttypeMapper;
        this.patientdetailsMapper = patientdetailsMapper;
    }

    @Override
    public Flux<PatientDetails> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<PatientDetails> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = PatientDetailsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(StateMasterSqlHelper.getColumns(stateMasterTable, "stateMaster"));
        columns.addAll(DocumentTypeSqlHelper.getColumns(documentTypeTable, "documentType"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(stateMasterTable)
            .on(Column.create("state_master_state_id", entityTable))
            .equals(Column.create("state_id", stateMasterTable))
            .leftOuterJoin(documentTypeTable)
            .on(Column.create("document_type_document_type_id", entityTable))
            .equals(Column.create("document_type_id", documentTypeTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
//        String select = entityManager.createSelect(selectFrom, PatientDetails.class, pageable, whereClause);
        String select = entityManager.createSelect((SelectBuilder.SelectFromAndJoin) selectFrom, PatientDetails.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<PatientDetails> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<PatientDetails> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private PatientDetails process(Row row, RowMetadata metadata) {
        PatientDetails entity = patientdetailsMapper.apply(row, "e");
        entity.setStateMaster(statemasterMapper.apply(row, "stateMaster"));
        entity.setDocumentType(documenttypeMapper.apply(row, "documentType"));
        return entity;
    }

    @Override
    public <S extends PatientDetails> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
