package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderInsuranceDetailsRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class SalesOrderInsuranceDetailsRepositoryExtendedImpl extends SalesOrderInsuranceDetailsRepositoryInternalImpl implements SalesOrderInsuranceDetailsRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_insurance_details", EntityManager.ENTITY_ALIAS);

    public SalesOrderInsuranceDetailsRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderInsuranceDetailsRowMapper salesorderinsurancedetailsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderinsurancedetailsMapper, entityOperations, converter);
    }


    @Override
    public Flux<SalesOrderInsuranceDetails> findBySalesOrderId(Long SOID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_id"), Conditions.just(SOID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsOnInsuranceUUID(UUID sOInsuranceDetailsUUID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_insurance_details_uuid"), Conditions.just(sOInsuranceDetailsUUID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Mono<Long> getIDByUUID(UUID sOInsuranceDetailsUUID) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetails> findSOInsuranceOnSOId(Long SOID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_id"), Conditions.just(SOID.toString()));
        return createQuery(null, whereClause).one();
    }

    @Override
    public Mono<SalesOrderInsuranceDetails> getSoInsuranceDetailsOnSoInsId(Long soInsId) {
        return null;
    }

    @Override
    public Flux<SalesOrderInsuranceDetails> findSOInsuranceBySalesOrderId(Long soid) {
        return null;
    }

}
