package com.sunknowledge.dme.rcm.repository.salesorder;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalesOrderItemDetailsRepo extends SalesOrderItemDetailsRepository {
    @Query("select * from t_sales_order_item_details tsoid where tsoid.sales_order_id = :salesOrderId and tsoid.sales_order_details_item_id = :itemId")
    Mono<SalesOrderItemDetails> getsalesOrderItemDetailsOnSalesOrderNitem(@Param("salesOrderId") Long salesOrderId, @Param("itemId") Long itemId);

    @Query("select * from t_sales_order_item_details tsoid where tsoid.sales_order_id = :salesOrderId and tsoid.sales_order_details_proc_code = :hcpcsCode")
    Mono<SalesOrderItemDetails> getsalesOrderItemDetailsOnSalesOrderNitem(@Param("salesOrderId") Long salesOrderId, @Param("hcpcsCode") String hcpcsCode);

    @Query("select * from t_sales_order_item_details where sales_order_id = :salesOrderId")
    Flux<SalesOrderItemDetails> getSalesOrderItemDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("select * from t_sales_order_item_details tsoid where tsoid.sales_order_id = :salesOrderId and tsoid.sales_order_item_number = :itemNumber")
    Mono<SalesOrderItemDetails> getsalesOrderItemDetailsOnSalesOrderNItemNumber(@Param("salesOrderId") Long salesOrderId, @Param("itemNumber") String itemNumber);
}
