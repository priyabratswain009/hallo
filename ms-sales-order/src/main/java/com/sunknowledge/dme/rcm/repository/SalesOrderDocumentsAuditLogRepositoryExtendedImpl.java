package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderDocumentsAuditLogRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderDocumentsAuditLogRepositoryExtendedImpl extends SalesOrderDocumentsAuditLogRepositoryInternalImpl implements SalesOrderDocumentsAuditLogRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_documents_audit_log", EntityManager.ENTITY_ALIAS);

    public SalesOrderDocumentsAuditLogRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderDocumentsAuditLogRowMapper salesorderdocumentsauditlogMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderdocumentsauditlogMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderDocumentsAuditLog> findBySalesOrderDocId(List SODocumentsIDs) {
        ArrayList arrayList = new ArrayList<>();
        for (Object item: SODocumentsIDs) {
            arrayList.add(Conditions.just(item.toString()));
        };
        Condition whereClause = Conditions.in(entityTable.column("sales_order_doc_id"), arrayList);
        return createQuery(null, whereClause).all();
    }
}
