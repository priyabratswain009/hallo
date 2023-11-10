package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.InvoiceLineItemDetailsAuditLogRowMapper;
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
 * Spring Data SQL reactive custom repository implementation for the InvoiceLineItemDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
class InvoiceLineItemDetailsAuditLogRepositoryInternalImpl
    extends SimpleR2dbcRepository<InvoiceLineItemDetailsAuditLog, Long>
    implements InvoiceLineItemDetailsAuditLogRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final InvoiceLineItemDetailsAuditLogRowMapper invoicelineitemdetailsauditlogMapper;

    private static final Table entityTable = Table.aliased("t_invoice_line_item_details_audit_log", EntityManager.ENTITY_ALIAS);

    public InvoiceLineItemDetailsAuditLogRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        InvoiceLineItemDetailsAuditLogRowMapper invoicelineitemdetailsauditlogMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(InvoiceLineItemDetailsAuditLog.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.invoicelineitemdetailsauditlogMapper = invoicelineitemdetailsauditlogMapper;
    }

    @Override
    public Flux<InvoiceLineItemDetailsAuditLog> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<InvoiceLineItemDetailsAuditLog> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = InvoiceLineItemDetailsAuditLogSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, InvoiceLineItemDetailsAuditLog.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<InvoiceLineItemDetailsAuditLog> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<InvoiceLineItemDetailsAuditLog> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("invce_lin_itm_detil_id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private InvoiceLineItemDetailsAuditLog process(Row row, RowMetadata metadata) {
        InvoiceLineItemDetailsAuditLog entity = invoicelineitemdetailsauditlogMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends InvoiceLineItemDetailsAuditLog> Mono<S> save(S entity) {
        return super.save(entity);
    }
}