package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderMasterRowMapper;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderMasterRepositoryExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;

import java.util.UUID;

public abstract class SalesOrderMasterRepositoryExtendedImpl extends SalesOrderMasterRepositoryInternalImpl implements SalesOrderMasterRepositoryExtended {

    @Autowired
    private final R2dbcEntityTemplate template;

    private static final Table entityTable = Table.aliased("t_sales_order_master", EntityManager.ENTITY_ALIAS);

    public SalesOrderMasterRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderMasterRowMapper salesordermasterMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesordermasterMapper, entityOperations, converter);
        this.template = template;
    }
    public Flux<SalesOrderMaster> getSOBysalesOrderMasterUuid(UUID salesOrderUUID){
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_master_uuid"), Conditions.just(salesOrderUUID.toString()));
        return createQuery(null, whereClause).all();
    }
}
