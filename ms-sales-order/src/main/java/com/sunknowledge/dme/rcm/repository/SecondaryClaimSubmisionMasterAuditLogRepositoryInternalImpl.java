package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SecondaryClaimSubmisionMasterAuditLogRowMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.time.LocalDate;
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
 * Spring Data SQL reactive custom repository implementation for the SecondaryClaimSubmisionMasterAuditLog entity.
 */
@SuppressWarnings("unused")
class SecondaryClaimSubmisionMasterAuditLogRepositoryInternalImpl
    extends SimpleR2dbcRepository<SecondaryClaimSubmisionMasterAuditLog, Long>
    implements SecondaryClaimSubmisionMasterAuditLogRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final SecondaryClaimSubmisionMasterAuditLogRowMapper secondaryclaimsubmisionmasterauditlogMapper;

    private static final Table entityTable = Table.aliased("t_secondary_claim_submision_master_audit_log", EntityManager.ENTITY_ALIAS);

    public SecondaryClaimSubmisionMasterAuditLogRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        SecondaryClaimSubmisionMasterAuditLogRowMapper secondaryclaimsubmisionmasterauditlogMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(SecondaryClaimSubmisionMasterAuditLog.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.secondaryclaimsubmisionmasterauditlogMapper = secondaryclaimsubmisionmasterauditlogMapper;
    }

    @Override
    public Flux<SecondaryClaimSubmisionMasterAuditLog> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<SecondaryClaimSubmisionMasterAuditLog> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = SecondaryClaimSubmisionMasterAuditLogSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, SecondaryClaimSubmisionMasterAuditLog.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<SecondaryClaimSubmisionMasterAuditLog> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<SecondaryClaimSubmisionMasterAuditLog> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(
            entityTable.column("chg_health_sconary_submn_mster_id"),
            Conditions.just(id.toString())
        );
        return createQuery(null, whereClause).one();
    }

    private SecondaryClaimSubmisionMasterAuditLog process(Row row, RowMetadata metadata) {
        SecondaryClaimSubmisionMasterAuditLog entity = secondaryclaimsubmisionmasterauditlogMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends SecondaryClaimSubmisionMasterAuditLog> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
