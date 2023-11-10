package com.sunknowledge.dme.rcm.repository.pricetabledata;

import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.dto.priceDetails.ModifiersOutputExtendedDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PriceDetailsMaster;
import com.sunknowledge.dme.rcm.repository.PriceDetailsMasterRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceDetailsMasterRepo extends PriceDetailsMasterRepository {

	@Query(value = "select * from t_price_details")
	Flux<PriceDetailsMaster> getAllPriceTableData();

	@Query(value = "select * from t_price_details where t_price_details.price_type=:priceType")
	Flux<PriceDetailsMaster> getAllPriceTableDataByPriceType(@Param("priceType") Long priceType);

	@Query(value = "select * from so.t_price_details where t_price_details.price_type=:priceType and t_price_details.item_id=:itemId and t_price_details.price_table_id=:priceTableId")
	Flux<PriceDetailsMaster> getPriceTableData(@Param("priceType") String priceType, @Param("itemId") Long itemId,
			@Param("priceTableId") Long priceTableId);

    @Query("select tpd.* from t_sales_order_insurance_details tsoid, t_price_details tpd \n" +
        "where tsoid.price_table_id = tpd.price_table_id and tsoid.sales_order_id = :salesOrderId and tpd.hcpcs = :hcpcsCode")
    Mono<PriceDetailsMaster> getPriceTableDetailsOnSalesOrderNhcpcsCode(@Param("salesOrderId") Long salesOrderId, @Param("hcpcsCode") String hcpcsCode);

    @Query(value = "select * from so.getmodifiersbyitemhcpcs(:hcpcsCode,:itemNo) AS (modifier character varying COLLATE pg_catalog.\"default\")")
    Flux<ModifiersOutputExtendedDTO> getModifiersDataByHcpcsCodeAndItemNo(@Param("hcpcsCode") String hcpcsCode,
                                                                          @Param("itemNo") String itemNo);
}
