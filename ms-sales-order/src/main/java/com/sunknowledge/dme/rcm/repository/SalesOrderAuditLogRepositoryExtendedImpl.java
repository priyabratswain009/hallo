package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog;
//import com.sunknowledge.dme.rcm.repository.SalesOrderMasterAuditLogRepositoryInternalImpl;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderMasterAuditLogRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@SuppressWarnings("unused")
@Repository
public abstract class SalesOrderAuditLogRepositoryExtendedImpl extends SalesOrderMasterAuditLogRepositoryInternalImpl implements SalesOrderAuditLogRepositoryExtended {
    private static final Table entityTable = Table.aliased("t_sales_order_master_audit_log", EntityManager.ENTITY_ALIAS);

    public SalesOrderAuditLogRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderMasterAuditLogRowMapper salesordermasterauditlogMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesordermasterauditlogMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderMasterAuditLog> findBySalsOdrId(Long soID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sals_odr_id"), Conditions.just(soID.toString()));
        return createQuery(null, whereClause).all();
    }
}
