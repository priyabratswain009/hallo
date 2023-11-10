package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderInsuranceDetailsAuditLogRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderInsuranceAuditLogRepositoryExtendedImpl extends SalesOrderInsuranceDetailsAuditLogRepositoryInternalImpl implements SalesOrderInsuranceAuditLogRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_insurance_details_audit_log", EntityManager.ENTITY_ALIAS);

    public SalesOrderInsuranceAuditLogRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderInsuranceDetailsAuditLogRowMapper salesorderinsurancedetailsauditlogMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderinsurancedetailsauditlogMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderInsuranceDetailsAuditLog> findBySalsOrdInsranceDetailsId(List SOInsuranceIDs) {
        ArrayList arrayList = new ArrayList<>();
        for (Object item: SOInsuranceIDs) {
            arrayList.add(Conditions.just(item.toString()));
        };
        Condition whereClause = Conditions.in(entityTable.column("sals_ord_insrance_details_id"), arrayList);
        return createQuery(null, whereClause).all();
    }
}
