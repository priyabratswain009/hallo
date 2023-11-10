package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.rowmapper.DeliveryAbnDataRowMapper;
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
 * Spring Data SQL reactive custom repository implementation for the DeliveryAbnData entity.
 */
@SuppressWarnings("unused")
class DeliveryAbnDataRepositoryInternalImpl
    extends SimpleR2dbcRepository<DeliveryAbnData, Long>
    implements DeliveryAbnDataRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final DeliveryAbnDataRowMapper deliveryabndataMapper;

    private static final Table entityTable = Table.aliased("t_delivery_abn_data", EntityManager.ENTITY_ALIAS);

    public DeliveryAbnDataRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        DeliveryAbnDataRowMapper deliveryabndataMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(DeliveryAbnData.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.deliveryabndataMapper = deliveryabndataMapper;
    }

    @Override
    public Flux<DeliveryAbnData> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<DeliveryAbnData> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = DeliveryAbnDataSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, DeliveryAbnData.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<DeliveryAbnData> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<DeliveryAbnData> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("delivery_abn_data_id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private DeliveryAbnData process(Row row, RowMetadata metadata) {
        DeliveryAbnData entity = deliveryabndataMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends DeliveryAbnData> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
