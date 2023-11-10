package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderDocumentsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class SalesOrderDocumentsRepositoryExtendedImpl extends SalesOrderDocumentsRepositoryInternalImpl implements SalesOrderDocumentsRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_documents", EntityManager.ENTITY_ALIAS);

    @Autowired
    private final R2dbcEntityTemplate template;

    public SalesOrderDocumentsRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderDocumentsRowMapper salesorderdocumentsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderdocumentsMapper, entityOperations, converter);
        this.template = template;
    }

    @Override
    public Flux<SalesOrderDocuments> findBySalesOrderId(Long SOID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_id"), Conditions.just(SOID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<SalesOrderDocuments> getSODocumentsBySODocumentUUID(UUID sODocumentsUUID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_documents_uuid"), Conditions.just(sODocumentsUUID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Mono<Long> getIDByUUID(UUID sODocumentsUUID) {
        return null;
    }
}
