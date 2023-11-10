package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.rowmapper.ColumnConverter;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderMasterRowMapper;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchOutputDTO;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
//import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

@SuppressWarnings("unused")
@Repository
public abstract class SalesOrderMasterSearchRepositoryExtendedImpl extends SalesOrderMasterRepositoryInternalImpl implements SalesOrderMasterSearchRepositoryExtended {

    @Autowired
    private final R2dbcEntityTemplate template;

    private final ColumnConverter converter;

    private static final Table entityTable = Table.aliased("t_sales_order_master", EntityManager.ENTITY_ALIAS);

    public SalesOrderMasterSearchRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, SalesOrderMasterRowMapper salesordermasterMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter, ColumnConverter converter1) {
        super(template, entityManager, salesordermasterMapper, entityOperations, converter);
        this.template = template;
        this.converter = converter1;
    }

    @Override
    public Flux<SalesOrderMaster> findByPatientId(Long patientId) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("patient_id"), Conditions.just(patientId.toString()));
        return createQuery(null, whereClause).all();
    }

    //---- Flux<SalesOrderCommonSearchOutputDTO> ------
    @Override
    public Flux<SalesOrderCommonSearchOutputDTO> getSalesOrderDetailsForSearchParametersBySalesOrderNo(Map<String, Object> conditionalKeyValue, Map<String, String> columnKeyMapper) {
        StringBuilder sqlBuilder = new StringBuilder();
//        sqlBuilder.append("SELECT a.sales_order_no as sales_order_no, a.sales_order_master_uuid::text as sales_order_uuid, \n" +
//            "a.patient_id_no as patient_id_no, \n" +
//            "a.patient_first_name as patient_first_name, a.patient_middle_name as patient_middle_name,  \n" +
//            "a.patient_last_name as patient_last_name, a.created_by_id as created_by_id, a.created_by_name as created_by_name,\n" +
//            "a.created_date as created_date, a.delivery_schedule_datetime as schedule_delivery_date, \n" +
//            "a.delivery_address_line_1 as delivery_address_line_1, a.delivery_address_line_2 as delivery_address_line_2, \n" +
//            "a.delivery_city_name as delivery_city, a.delivery_state_name as delivery_state, \n" +
//            "a.delivery_zip_code as delivery_zip, a.order_status as sales_order_status FROM t_sales_order_master a ");
//        sqlBuilder.append("JOIN t_sales_order_item_details b ON a.sales_order_id = b.sales_order_id ");
//        sqlBuilder.append("JOIN t_sales_order_insurance_details c ON a.sales_order_id = c.sales_order_id ");
//        sqlBuilder.append("WHERE 1 = 1 ");

        Flux<SalesOrderCommonSearchOutputDTO> result = template
            .getDatabaseClient().sql("SELECT sales_order_no, " +
                " order_status FROM t_sales_order_master")
            .map(this::mapToMyEntity).all();

        return result;

//        return this.template
//            .getDatabaseClient()
//            .sql("SELECT sales_order_no as sales_order_no, " +
//                " order_status as sales_order_status FROM t_sales_order_master")
//            .map((row, rowMetadata) -> {
////                SalesOrderCommonSearchOutputDTO dto = new SalesOrderCommonSearchOutputDTO();
////                dto.setSalesOrderNo(row.get("sales_order_no", String.class));
////                dto.setSalesOrderStatus(row.get("sales_order_status", String.class));
////                return dto;
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("id", row.get("id", Long.class));
//                map.put("name", row.get("name", String.class));
//                return map;
//            })
//            .all();

    }

    private SalesOrderCommonSearchOutputDTO mapToMyEntity(Row row, RowMetadata metadata) {
        SalesOrderCommonSearchOutputDTO entity = new SalesOrderCommonSearchOutputDTO();
        entity.setSalesOrderNo(converter.fromRow(row, "sales_order_no", String.class)); //row.get("sales_order_no", String.class)
        entity.setSalesOrderStatus(converter.fromRow(row, "order_status", String.class));
        return entity;
    }
}
