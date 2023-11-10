package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderFinancialDetailsAuditLogRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderFinancialAuditLogRepositoryExtendedImpl extends SalesOrderFinancialDetailsAuditLogRepositoryInternalImpl implements SalesOrderFinancialAuditLogRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_financial_details_audit_log", EntityManager.ENTITY_ALIAS);

    public SalesOrderFinancialAuditLogRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderFinancialDetailsAuditLogRowMapper salesorderfinancialdetailsauditlogMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderfinancialdetailsauditlogMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderFinancialDetailsAuditLog> findBySalsOrdrFincialId(List SOFinancialIDs) {
        ArrayList arrayList = new ArrayList<>();
        for (Object item: SOFinancialIDs) {
            arrayList.add(Conditions.just(item.toString()));
        };
        Condition whereClause = Conditions.in(entityTable.column("sals_ordr_fincial_id"), arrayList);
        return createQuery(null, whereClause).all();
    }
}
