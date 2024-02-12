package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Master;
import com.sunknowledge.dme.rcm.repository.rowmapper.ClaimsCob835MasterRowMapper;
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
import org.apache.commons.lang3.StringUtils;
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
 * Spring Data R2DBC custom repository implementation for the ClaimsCob835Master entity.
 */
@SuppressWarnings("unused")
class ClaimsCob835MasterRepositoryInternalImpl
    extends SimpleR2dbcRepository<ClaimsCob835Master, Long>
    implements ClaimsCob835MasterRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final ClaimsCob835MasterRowMapper claimscob835masterMapper;

    private static final Table entityTable = Table.aliased("t_claims_cob_835_master", EntityManager.ENTITY_ALIAS);

    public ClaimsCob835MasterRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        ClaimsCob835MasterRowMapper claimscob835masterMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(ClaimsCob835Master.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.claimscob835masterMapper = claimscob835masterMapper;
    }

    @Override
    public Flux<ClaimsCob835Master> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<ClaimsCob835Master> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ClaimsCob835MasterSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, ClaimsCob835Master.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<ClaimsCob835Master> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<ClaimsCob835Master> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("claim_cob_835_master_id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private ClaimsCob835Master process(Row row, RowMetadata metadata) {
        ClaimsCob835Master entity = claimscob835masterMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends ClaimsCob835Master> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
