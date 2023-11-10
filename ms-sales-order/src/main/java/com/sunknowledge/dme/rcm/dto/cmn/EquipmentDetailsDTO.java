package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDetailsDTO {
    private Long sales_order_item_details_id;
    private String sales_order_details_sale_type;
    private String sales_order_details_modifier_1;
    private String sales_order_details_modifier_2;
    private String sales_order_details_modifier_3;
    private String sales_order_details_modifier_4;
    private String sales_order_details_proc_code;
    private Double sales_order_details_charge_amt;
    private String sales_order_details_item_name;
    private Double sales_order_details_qty;
    private Long frequency_count;
    private Long frequency_interval;

//    private Long salesOrderItemDetailsId;
//    private String salesOrderDetailsProcCode;
//    private String salesOrderDetailsItemName;
//    private Double salesOrderDetailsQty;
}
