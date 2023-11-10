package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderClinicalDetailsAuditLogRowMapper;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderClinicalDetailsRowMapper;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderFinancialDetailsRowMapper;
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

public class SalesOrderClinicalDetailsRepositoryExtendedImpl extends SalesOrderClinicalDetailsRepositoryInternalImpl implements SalesOrderClinicalDetailsRepositoryExtended{

    private static final Table entityTable = Table.aliased("t_sales_order_clinical_details", EntityManager.ENTITY_ALIAS);

    @Autowired
    private final R2dbcEntityTemplate template;

    public SalesOrderClinicalDetailsRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderClinicalDetailsRowMapper salesorderclinicaldetailsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, salesorderclinicaldetailsMapper, entityOperations, converter);
        this.template = template;
    }

    @Override
    public Flux<SalesOrderClinicalDetails> findBySalesOrderId(Long SOID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_id"), Conditions.just(SOID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<SalesOrderClinicalDetails> getSOClinicalByUUID(UUID sOClinicalUUID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_clinical_details_uuid"), Conditions.just(sOClinicalUUID.toString()));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Mono<Long> getIDByUUID(UUID sOClinicalUUID) {
        return null;
    }

    @Override
    public Mono<Object> getDoctorMasterByNpi(String doctorNpiNumber) {
        return null;
    }


}
