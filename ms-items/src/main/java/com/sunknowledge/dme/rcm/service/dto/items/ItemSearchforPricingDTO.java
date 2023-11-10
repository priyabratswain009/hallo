package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSearchforPricingDTO {
//    private Long itemId;
//    private String itemName;
//    private String itemNo;
//    private String hcpcsCode;
//    private Double purchaseChargedAmt;
//    private Double purchaseAllowedAmt;
//    private Double rentalChargedAmt;
//    private Double rentalAllowedAmt;
//    private Double retailChargedAmt;
//    private Long onhandQty;
//    private Long onrentQty;
//    private Long committedQty;
//    private Long inorderQty;

    private Long item_id;
    private String item_name;
    private String item_no;
    private String hcpcs_code;
    private Double purchase_charged_amt;
    private Double purchase_allowed_amt;
    private Double rental_charged_amt;
    private Double rental_allowed_amt;
    private Double retail_charged_amt;
    private Long onhand_qty;
    private Long onrent_qty;
    private Long committed_qty;
    private Long inorder_qty;

}
