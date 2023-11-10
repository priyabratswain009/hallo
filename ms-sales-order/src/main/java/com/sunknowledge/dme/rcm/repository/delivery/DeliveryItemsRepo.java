package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import com.sunknowledge.dme.rcm.service.dto.delivery.validateDeliveryInitiationSOItemDetailsResponseDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryItemsRepo extends DeliveryItemsRepository {
    @Query(value = "select * from t_delivery_items where delivery_ticket_id = :deliveryTicketId and status in('Active', 'active')")
    Flux<DeliveryItems> getDeliveryItemsListOnDeliveryTicket(@Param("deliveryTicketId") Long deliveryTicketId);

    @Query(value = "select a.primary_insurer_price_table_id, b.par_no, b.sales_order_details_cmnpar_par_id as par_id, b.cmn_id, b.sales_order_details_cmnpar_cmn_key as cmn_key,\n" +
        "b.item_location_id, b.sales_order_details_item_id, b.sales_order_details_item_name,b.sales_order_details_stocking_uom,\n" +
        "b.sales_order_details_item_description, b.sales_order_item_number, b.sales_order_details_sale_type, b.sales_order_details_qty,\n" +
        "b.sales_order_details_bqty, b.sales_order_details_line_qty, b.sales_order_details_proc_code, b.sales_order_details_modifier_1 as sales_order_details_modifier1,\n" +
        "b.sales_order_details_price_option, b.sales_order_details_charge_amt, b.sales_order_details_allowed_amt, b.sales_order_details_taxable,\n" +
        "cast(b.sales_order_details_original_dos as varchar), cast(b.sales_order_details_dos_to as varchar), b.sales_order_details_tax_rate,\n" +
        "b.is_dropship_allowed as dropship_status\n" +
        "from so.t_sales_order_master a\n" +
        "INNER JOIN so.t_sales_order_item_details b on a.sales_order_id = b.sales_order_id\n" +
        "where a.sales_order_id = :soId")
    Flux<validateDeliveryInitiationSOItemDetailsResponseDTO> getSoMasterAndSoItemDetails(@Param("soId") Long soId);

    @Query(value = "select *  from so.t_sales_order_insurance_details \n" +
        "where sales_order_id = :soId limit 1")
    Mono<SalesOrderInsuranceDetails> getSoInsuranceDetails(@Param("soId") Long soId);
}
