package com.sunknowledge.dme.rcm.repository.pricetabledata;

import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterRepository;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderMasterRepo extends SalesOrderMasterRepository {

    @Query("select * from t_sales_order_master where t_sales_order_master.sales_order_id=:salesOrderId")
    Mono<SalesOrderMaster> getPriceDetails(@Param("salesOrderId") Long salesOrderId);

    @Query("select tsom.delivery_address_line_1, tsom.delivery_address_line_2, tsom.delivery_city_name, tsom.delivery_state_name, tsom.delivery_zip_code, tsom.delivery_phone_no_1, tsom.delivery_phone_no_2 from t_sales_order_master tsom where sales_order_id = :salesOrderId")
    Mono<DeliveryAddress> getSalesOrderDeliveryAddressOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("select * from t_sales_order_master where t_sales_order_master.sales_order_id=:salesOrderId")
    Mono<SalesOrderMaster> getSalesOrderDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query(value = "select * from so.t_sales_order_master tsom where tsom.sales_order_no = :salesOrderNo \n" +
        " and lower(status)='active' and lower(coalesce(hold_status, '')) <> 'true'")
    Mono<SalesOrderMaster> getSalesOrderDetailsOnSalesOrderNo(@Param("salesOrderNo") String salesOrderNo);

    @Query("select max(sales_order_id) from t_sales_order_master tsom where tsom.patient_id_no = :patientidno")
    Mono<Long> getSalesOrderIdFromPatientIdno(@Param("patientIdNo") String patientIdNo);

    @Query("select * from t_sales_order_master tsom where lower(tsom.status) = 'active' " +
        "and tsom.sales_order_master_uuid = :soUUID")
    Mono<SalesOrderMaster> getSalesOrderDetailsOnUUID(@Param("soUUID") UUID soUUID);
}
