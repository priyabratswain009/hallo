package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderItemDetailsAuditLogRowMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class SalesOrderItemAuditLogRepositoryExtendedImpl extends SalesOrderItemDetailsAuditLogRepositoryInternalImpl implements SalesOrderItemAuditLogRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_item_details_audit_log", EntityManager.ENTITY_ALIAS);


    public SalesOrderItemAuditLogRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderItemDetailsAuditLogRowMapper salesorderitemdetailsauditlogMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderitemdetailsauditlogMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderItemDetailsAuditLog> findBySalsOrdrItemDetailId(List SOItemIDs) {

        ArrayList arrayList = new ArrayList<>();
        for (Object item: SOItemIDs) {
            arrayList.add(Conditions.just(item.toString()));
        };
        Condition whereClause = Conditions.in(entityTable.column("sals_ordr_item_detail_id"), arrayList);
        return createQuery(null, whereClause).all();
    }
}
