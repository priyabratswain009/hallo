package com.sunknowledge.dme.rcm.repository.po;

import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.dto.po.CancelPartialPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.PurchaseOrderParameterDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PurchaseOrderRepositoryExtended extends PurchaseOrderRepository {
    List<PurchaseOrder> findByPoNumberAndStatusIgnoreCase(String poNo, String active);

    @Query(value = "select * from item.itemsearchforvendor(:itemNo,:itemName,:vendorId)\n" +
        "AS (\n" +
        "item_id bigint, \n" +
        "item_name character varying COLLATE pg_catalog.\"default\",\n" +
        " item_no character varying COLLATE pg_catalog.\"default\",\n" +
        " hcpcs_code character varying COLLATE pg_catalog.\"default\",\n" +
        " vendorid bigint,\n" +
        " vendorname character varying COLLATE pg_catalog.\"default\" \n" +
        ")\n", nativeQuery = true)
    List<Map> findByItemNoOrItemNameOrVendorId(@Param("itemNo") String itemNo,
                                               @Param("itemName") String itemName,
                                               @Param("vendorId") Long vendorId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.pocreation(:itemLocationId,:soId,:vendorId,:branchId,:branchName,:billingAddressLine1," +
        ":billingAddressLine2,:billingAddressCity,:billingAddressState,:billingAddressZip,:billingContactNo,:billingContactName,:billingContactEmail," +
        ":deliveryAddressLine1,:deliveryAddressLine2,:deliveryAddressCity,:deliveryAddressState,:deliveryAddressZip,:deliveryContactNo," +
        ":deliveryContactName,:deliveryContactEmail,:itemIds,:itemQtys,:itemprices," +
        ":whetherSerialised,:userId,:userName, :notes)", nativeQuery = true)
    Object savePurchaseOrder(@Param("itemLocationId") Long itemLocationId, @Param("soId") Long soId, @Param("vendorId") Long vendorId,
                             @Param("branchId") Long branchId,@Param("branchName") String branchName,
                             @Param("billingAddressLine1") String billingAddressLine1, @Param("billingAddressLine2") String billingAddressLine2,
                             @Param("billingAddressCity") String billingAddressCity, @Param("billingAddressState") String billingAddressState,
                             @Param("billingAddressZip") String billingAddressZip, @Param("billingContactNo") String billingContactNo,
                             @Param("billingContactName") String billingContactName, @Param("billingContactEmail") String billingContactEmail,
                             @Param("deliveryAddressLine1") String deliveryAddressLine1, @Param("deliveryAddressLine2") String deliveryAddressLine2,
                             @Param("deliveryAddressCity") String deliveryAddressCity, @Param("deliveryAddressState") String deliveryAddressState,
                             @Param("deliveryAddressZip") String deliveryAddressZip, @Param("deliveryContactNo") String deliveryContactNo,
                             @Param("deliveryContactName") String deliveryContactName, @Param("deliveryContactEmail") String deliveryContactEmail,
                             @Param("itemIds") String itemIds, @Param("itemQtys") String itemQtys, @Param("itemprices") String itemprices,
                             @Param("whetherSerialised") String whetherSerialised,
                             @Param("userId") Long userId, @Param("userName") String userName,
                             @Param("notes") String notes);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.pofullcancel(:poId,:userId,:userName)", nativeQuery = true)
    void cancelFullPurchaseOrder(@Param("poId") Long poId,
                                 @Param("userId") Long userId,
                                 @Param("userName") String userName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.popartialcancel(:poId,:itemIds,:itemOrderQtys,:itemCancelQtys,:userId,:userName)", nativeQuery = true)
    void cancelPartialPurchaseOrder(@Param("poId") Long poId,
                                    @Param("itemIds") String itemIds,
                                    @Param("itemOrderQtys") String itemOrderQtys,
                                    @Param("itemCancelQtys") String itemCancelQtys,
                                    @Param("userId") Long userId,
                                    @Param("userName") String userName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.poreceipt(:poId, :locationId, :itemIds, :receivedQtys, :whetherSerialised, " +
        " :serialNos, \n" +
        " :lotNos, :mfgDates, :receivedDate, \n" +
        " :userId, :userName)", nativeQuery = true)
    void receivePurchaseOrder(@Param("poId") Long poId,
                              @Param("locationId") Long locationId,
                              @Param("itemIds") String itemIds,
                              @Param("receivedQtys") String receivedQtys,
                              @Param("whetherSerialised") String whetherSerialised,
                              @Param("serialNos") String serialNos,
                              @Param("lotNos") String lotNos,
                              @Param("mfgDates") String mfgDates,
                              @Param("receivedDate") LocalDate receivedDate,
                              @Param("userId") Long userId,
                              @Param("userName") String userName);

    @Modifying
    @Query(value = "SELECT a.po_id as po_id, a.po_number as po_number, a.po_date as po_date, item_location_id, item_location_name, sales_order_id, sales_order_no, \n" +
        " vendor_id, vendor_name, billing_address_line_1, billing_address_line_2, billing_address_city, \n" +
        " billing_address_state, billing_address_zip, billing_contact_no, billing_contact_name, \n" +
        " billing_contact_email, delivery_address_line_1, delivery_address_line_2, delivery_address_city, \n" +
        " delivery_address_state, delivery_address_zip, delivery_contact_no, delivery_contact_name, \n" +
        " delivery_contact_email, priority, shipping_method, freight_charges, b.status as status, b.notes as notes,\n" +
        " po_items_id, item_id, item_name, item_no, item_uom, ordered_qty, received_qty, \n" +
        " cancelled_qty, unit_price, total_amount,  \n" +
        " b.po_status as po_status, wheather_serialised, lot_no, mfg_date, \n" +
        " received_date, b.created_by_id as created_by_id, b.created_by_name as created_by_name, b.created_date as created_date, \n" +
        " b.updated_by_id as updated_by_id, b.updated_name as updated_name, b.updated_date as updated_date\n" +
        " FROM\n" +
        "(SELECT po_id, po_number, po_date, item_location_id, item_location_name, sales_order_id, sales_order_no, \n" +
        " vendor_id, vendor_name, billing_address_line_1, billing_address_line_2, billing_address_city, \n" +
        " billing_address_state, billing_address_zip, billing_contact_no, billing_contact_name, \n" +
        " billing_contact_email, delivery_address_line_1, delivery_address_line_2, delivery_address_city, \n" +
        " delivery_address_state, delivery_address_zip, delivery_contact_no, delivery_contact_name, \n" +
        " delivery_contact_email, priority, shipping_method, freight_charges, status, notes, \n" +
        " created_by_id, created_by_name, created_date, updated_by_id, updated_name, \n" +
        " updated_date,  po_status, vendor_delivery\n" +
        "\tFROM item.t_purchase_order) AS a,\n" +
        "(SELECT po_items_id, po_id, po_number, item_id, item_name, item_no, item_uom, ordered_qty, received_qty, \n" +
        " cancelled_qty, unit_price, total_amount, status, notes, created_by_id, created_by_name, created_date, \n" +
        " updated_by_id, updated_name, updated_date, po_status, wheather_serialised, lot_no, mfg_date, \n" +
        " received_date\n" +
        "\tFROM item.t_purchase_order_items) b\n" +
        "    WHERE a.po_id=b.po_id and trim(a.po_status)=:poStatus and \n" +
        "    (date(a.created_date) between :fromDate and :toDate) ", nativeQuery = true)
    List<Map> findByDateAndPOStatus(@Param("fromDate") LocalDate fromDate,
                                    @Param("toDate") LocalDate toDate,
                                    @Param("poStatus") String poStatus);

    List<PurchaseOrder> findByVendorIdAndStatusIgnoreCase(Long parseLong, String active);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.pocreation_dropship(:soId,:soNo,:vendorId,:branchId,:branchName,:billingAddressLine1," +
        ":billingAddressLine2,:billingAddressCity,:billingAddressState,:billingAddressZip,:billingContactNo,:billingContactName,:billingContactEmail," +
        ":deliveryAddressLine1,:deliveryAddressLine2,:deliveryAddressCity,:deliveryAddressState,:deliveryAddressZip,:deliveryContactNo," +
        ":deliveryContactName,:deliveryContactEmail,:itemIds,:itemQtys,:itemprices," +
        ":whetherSerialised,:userId,:userName, :notes, :locationId)", nativeQuery = true)
    Object saveDropshipPurchaseOrder(@Param("soId") Long soId,@Param("soNo") String soNo, @Param("vendorId") Long vendorId,
                                     @Param("branchId") Long branchId,@Param("branchName") String branchName,
                                     @Param("billingAddressLine1") String billingAddressLine1, @Param("billingAddressLine2") String billingAddressLine2,
                                     @Param("billingAddressCity") String billingAddressCity, @Param("billingAddressState") String billingAddressState,
                                     @Param("billingAddressZip") String billingAddressZip, @Param("billingContactNo") String billingContactNo,
                                     @Param("billingContactName") String billingContactName, @Param("billingContactEmail") String billingContactEmail,
                                     @Param("deliveryAddressLine1") String deliveryAddressLine1, @Param("deliveryAddressLine2") String deliveryAddressLine2,
                                     @Param("deliveryAddressCity") String deliveryAddressCity, @Param("deliveryAddressState") String deliveryAddressState,
                                     @Param("deliveryAddressZip") String deliveryAddressZip, @Param("deliveryContactNo") String deliveryContactNo,
                                     @Param("deliveryContactName") String deliveryContactName, @Param("deliveryContactEmail") String deliveryContactEmail,
                                     @Param("itemIds") String itemIds, @Param("itemQtys") String itemQtys, @Param("itemprices") String itemprices,
                                     @Param("whetherSerialised") String whetherSerialised,
                                     @Param("userId") Long userId, @Param("userName") String userName,
                                     @Param("notes") String notes, @Param("locationId") Long locationId);

    @Query(value = "select count(a.po_id) from item.t_purchase_order a \n" +
        "left outer join item.t_purchase_order_items b on a.po_id=b.po_id \n" +
        "where a.sales_order_id= :soId and a.vendor_id= :vendorId and b.item_id= :itemId \n" +
        "and b.ordered_qty= :itemQty and LOWER(a.status)='active'",nativeQuery = true)
    Long checkSOExistorNot(@Param("soId") Long soId,
                           @Param("vendorId") Long vendorId,
                           @Param("itemId") Long itemId,
                           @Param("itemQty") Long itemQty);

    PurchaseOrder findByPoIdAndStatusIgnoreCase(Long poId, String active);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.purchase_order_dropship_cancel(:poNumber,:userId,:userName)", nativeQuery = true)
    void cancelDropshipPurchaseOrder(@Param("poNumber") String poNumber,
                                 @Param("userId") Long userId,
                                 @Param("userName") String userName);

}
