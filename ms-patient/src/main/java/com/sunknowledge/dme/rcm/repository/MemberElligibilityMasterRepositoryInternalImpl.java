package com.sunknowledge.dme.rcm.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.repository.rowmapper.MemberElligibilityMasterRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the MemberElligibilityMaster entity.
 */
@SuppressWarnings("unused")
class MemberElligibilityMasterRepositoryInternalImpl
    extends SimpleR2dbcRepository<MemberElligibilityMaster, Long>
    implements MemberElligibilityMasterRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final MemberElligibilityMasterRowMapper memberelligibilitymasterMapper;

    private static final Table entityTable = Table.aliased("t_member_elligibility_master", EntityManager.ENTITY_ALIAS);

    public MemberElligibilityMasterRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        MemberElligibilityMasterRowMapper memberelligibilitymasterMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(MemberElligibilityMaster.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.memberelligibilitymasterMapper = memberelligibilitymasterMapper;
    }

    @Override
    public Flux<MemberElligibilityMaster> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<MemberElligibilityMaster> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = MemberElligibilityMasterSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, MemberElligibilityMaster.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<MemberElligibilityMaster> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<MemberElligibilityMaster> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("claim_elligibility_master_id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private MemberElligibilityMaster process(Row row, RowMetadata metadata) {
        MemberElligibilityMaster entity = memberelligibilitymasterMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends MemberElligibilityMaster> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
