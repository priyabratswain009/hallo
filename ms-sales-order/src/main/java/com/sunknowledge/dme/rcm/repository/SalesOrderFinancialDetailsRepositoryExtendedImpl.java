package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderFinancialDetailsRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class SalesOrderFinancialDetailsRepositoryExtendedImpl extends SalesOrderFinancialDetailsRepositoryInternalImpl implements SalesOrderFinancialDetailsRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_financial_details", EntityManager.ENTITY_ALIAS);

    public SalesOrderFinancialDetailsRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderFinancialDetailsRowMapper salesorderfinancialdetailsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderfinancialdetailsMapper, entityOperations, converter);
    }

    @Override
    public Flux<SalesOrderFinancialDetails> findBySalesOrderId(Long SOID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_id"), Conditions.just(SOID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<SalesOrderFinancialDetails> getSOFinancialDetailsBySOFinancialDetailsUUID(UUID sOFinancialDetailsUUID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_financial_details_uuid"), Conditions.just(sOFinancialDetailsUUID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Mono<Long> getIDByUUID(UUID sOFinancialDetailsUUID) {
        return null;
    }
}
