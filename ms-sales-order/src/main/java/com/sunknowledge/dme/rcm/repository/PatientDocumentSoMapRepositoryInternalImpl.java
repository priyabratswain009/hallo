package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.repository.rowmapper.PatientDocumentSoMapRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive custom repository implementation for the PatientDocumentSoMap entity.
 */
@SuppressWarnings("unused")
class PatientDocumentSoMapRepositoryInternalImpl
    extends SimpleR2dbcRepository<PatientDocumentSoMap, Long>
    implements PatientDocumentSoMapRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final PatientDocumentSoMapRowMapper patientdocumentsomapMapper;

    private static final Table entityTable = Table.aliased("t_patient_document_so_map", EntityManager.ENTITY_ALIAS);

    public PatientDocumentSoMapRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        PatientDocumentSoMapRowMapper patientdocumentsomapMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(PatientDocumentSoMap.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.patientdocumentsomapMapper = patientdocumentsomapMapper;
    }

    @Override
    public Flux<PatientDocumentSoMap> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<PatientDocumentSoMap> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = PatientDocumentSoMapSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, PatientDocumentSoMap.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<PatientDocumentSoMap> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<PatientDocumentSoMap> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_document_so_map_id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private PatientDocumentSoMap process(Row row, RowMetadata metadata) {
        PatientDocumentSoMap entity = patientdocumentsomapMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends PatientDocumentSoMap> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
