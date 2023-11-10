package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderClinicalDetailsAuditLogRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderClinicalAuditLogRepositoryExtendedImpl extends SalesOrderClinicalDetailsAuditLogRepositoryInternalImpl implements SalesOrderClinicalAuditLogRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_clinical_details_audit_log", EntityManager.ENTITY_ALIAS);

    public SalesOrderClinicalAuditLogRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderClinicalDetailsAuditLogRowMapper salesorderclinicaldetailsauditlogMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderclinicaldetailsauditlogMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderClinicalDetailsAuditLog> findBySalsOdrClincalDetilsId(List SOClinicalIDs) {
        ArrayList arrayList = new ArrayList<>();
        for (Object item: SOClinicalIDs) {
            arrayList.add(Conditions.just(item.toString()));
        };
        Condition whereClause = Conditions.in(entityTable.column("sals_odr_clincal_detils_id"), arrayList);
        return createQuery(null, whereClause).all();
    }
}
