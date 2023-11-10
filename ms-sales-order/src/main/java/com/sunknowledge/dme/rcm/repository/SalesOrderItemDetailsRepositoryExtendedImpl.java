package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.repository.rowmapper.ColumnConverter;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderItemDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public abstract class SalesOrderItemDetailsRepositoryExtendedImpl extends SalesOrderItemDetailsRepositoryInternalImpl implements SalesOrderItemDetailsRepositoryExtended{

    @Autowired
    private final R2dbcEntityTemplate template;

    private final ColumnConverter converter;

    private static final Table entityTable = Table.aliased("t_sales_order_item_details", EntityManager.ENTITY_ALIAS);

//    public SalesOrderItemDetailsRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderItemDetailsRowMapper salesorderitemdetailsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
//        super(template, entityManager, salesorderitemdetailsMapper, entityOperations, converter);
//    }

    public SalesOrderItemDetailsRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderItemDetailsRowMapper salesorderitemdetailsMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter, ColumnConverter converter1) {
        super(template, entityManager, salesorderitemdetailsMapper, entityOperations, converter);
        this.template = template;
        this.converter = converter1;
    }


//    public SalesOrderItemDetailsRepositoryExtendedImpl(
//        R2dbcEntityTemplate template,
//        EntityManager entityManager,
//        SalesOrderItemDetailsRowMapper salesorderitemdetailsMapper,
//        R2dbcEntityOperations entityOperations,
//        R2dbcConverter converter
//    ) {
//        super(
//            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(SalesOrderItemDetails.class)),
//            entityOperations,
//            converter
//        );
//        this.db = template.getDatabaseClient();
//        this.r2dbcEntityTemplate = template;
//        this.entityManager = entityManager;
//        this.salesorderitemdetailsMapper = salesorderitemdetailsMapper;
//    }

    @Override
    public Flux<SalesOrderItemDetails> findBySalesOrderId(Long SOID) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_id"), Conditions.just(SOID.toString()));
        return createQuery(null, whereClause).all();
    }

//    @Override
//    public Flux<SalesOrderItemDetails> getSOItemDetailsBySOItemDetailsUUID(UUID sOItemDetailsUUID) {
//        /*Comparison whereClause = Conditions.isEqual(entityTable.column("sales_order_item_details_uuid"), Conditions.just(sOItemDetailsUUID.toString()));
//        return createQuery(null, whereClause).all();*/
//        return null;
//    }
//
//    @Override
//    public Mono<Long> getIDByUUID(UUID sOItemDetailsUUID) {
//        return null;
//    }
//
//    @Override
//    public Mono<SoItemDetailsAndClinicalAndInsuranceResponseData> findSoItemDetailsAndClinicalAndInsuranceDataBySoId(Long soId, String status) {
//        return null;
//    }
//
//    @Override
//    public Mono<ItemDefaultVendorResponseDTO> findItemDefaultVendorByItemId(Long itemId) {
//        return null;
//    }

//    @Override
//    public Mono<ResultDropshipDTO> getSavedPurchaseOrder(Long salesOrderId, Long vendorId, Long salesOrderDetailsItemId, Long salesOrderDetailsQty) {
//        return null;
//    }

    /*@Override
    public Mono<PARsearchInitiatedOrActiveDTO> findPARInitiatedorActive() {
        return null;
    }*/
}
