package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderInsuranceDetailsRowMapper;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderMasterRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public abstract class SalesOrderInsuranceDetailsSearchRepositoryExtendedImpl extends SalesOrderInsuranceDetailsRepositoryInternalImpl implements SalesOrderInsuranceDetailsSearchRepositoryExtended {

    private static final Table entityTable = Table.aliased("t_sales_order_insurance_details", EntityManager.ENTITY_ALIAS);


    public SalesOrderInsuranceDetailsSearchRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderInsuranceDetailsRowMapper salesorderinsurancedetailsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderinsurancedetailsMapper, entityOperations, converter);
    }
}
