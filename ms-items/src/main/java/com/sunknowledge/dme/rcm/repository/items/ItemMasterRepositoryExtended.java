package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemMaster;
import com.sunknowledge.dme.rcm.repository.ItemMasterRepository;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ItemMasterRepositoryExtended extends ItemMasterRepository {

    ItemMaster findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    List<ItemMaster> findByItemNameAndStatusIgnoreCase(String itemName, String active);

    ItemMaster findByItemNumberAndStatusIgnoreCase(String itemNumber, String active);

    List<ItemMaster> findByItemDescriptionLikeAndStatusIgnoreCase(String itemDescription, String active);

    List<ItemMaster> findByStatusIgnoreCase(String status);

    List<ItemMaster> findByItemIdInAndStatusIgnoreCase(List<Long> itemIdList, String active);

    @Query(value = "select item.get_item_number()" ,nativeQuery = true)
    String getItemNumber();

    @Query(value = "select * from item.itemsearchforpricing(:itemNo, :itemName, :locationId, :priceTableId) as\n" +
        "(item_id bigint,item_name character varying,item_no character varying,hcpcs_code character varying,purchase_charged_amt double precision,purchase_allowed_amt double precision, "+
        "rental_charged_amt double precision,rental_allowed_amt double precision,retail_charged_amt double precision,onhand_qty bigint, "+
        "onrent_qty bigint,committed_qty bigint,inorder_qty bigint)\n" ,nativeQuery = true)
    List<Map> findItemByItemNoOrItemNameAndLocationIdAndPricetableId(@Param("itemNo") String itemNo, @Param("itemName") String itemName,
                                                                                         @Param("locationId") Long locationId, @Param("priceTableId") Long priceTableId);

    @Query(value = "select * from item.itemsearchallforpricing(:itemNo, :itemName, :priceTableId) as\n" +
        "(item_id bigint,item_name character varying,item_no character varying,hcpcs_code character varying, "+
        "itemlocationid bigint,itemlocationname character varying,purchase_charged_amt double precision, "+
        "purchase_allowed_amt double precision,rental_charged_amt double precision,rental_allowed_amt double precision, "+
        "retail_charged_amt double precision,onhand_qty bigint,onrent_qty bigint,committed_qty bigint,inorder_qty bigint) \n",nativeQuery = true )
    List<Map> findItemByItemNoOrItemNameAndPricetableId(@Param("itemNo") String itemNo, @Param("itemName") String itemName,
                                                                      @Param("priceTableId") Long priceTableId);

    ItemMaster findByItemId(Long itemId);

    List<ItemMaster> findByItemNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<ItemMaster> findByItemDescriptionLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select * from item.itemsearchforaddinso(:itemLocationId,:itemNo,:procCode,:itemName,:groupId)\n" +
        "AS\n" +
        "(\t\n" +
        "    item_group_id bigint,\n" +
        "    item_group_name character varying COLLATE pg_catalog.\"default\",\n" +
        "\titem_id bigint,\n" +
        "\titem_master_uuid character varying,\n" +
        "\titem_number character varying COLLATE pg_catalog.\"default\",\n" +
        "\titem_name character varying COLLATE pg_catalog.\"default\",\n" +
        "\titem_description character varying COLLATE pg_catalog.\"default\",\n" +
        "\titem_uom character varying COLLATE pg_catalog.\"default\",\n" +
        "\tprocedure_code character varying COLLATE pg_catalog.\"default\",\n" +
        "\tmodifier_1 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tmodifier_2 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tmodifier_3 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tmodifier_4 character varying COLLATE pg_catalog.\"default\",\n" +
        "\titem_location_id bigint,\n" +
        "\tonhand_qty bigint,\n" +
        "\tonrent_qty bigint,\n" +
        "\tcomitted_qty bigint,\n" +
        "\tinorder_qty bigint,\n" +
        "\ton_backorder integer,\n" +
        "\tassettagged character varying COLLATE pg_catalog.\"default\",\n" +
        "\tserialised character varying COLLATE pg_catalog.\"default\",\n" +
        "\tmanufacturer character varying COLLATE pg_catalog.\"default\"\n" +
        ")" ,nativeQuery = true)
    List<Map> findItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(@Param("itemLocationId") String itemLocationId,
                                                                                      @Param("itemNo") String itemNo,
                                                                                      @Param("procCode") String procCode,
                                                                                      @Param("itemName") String itemName,
                                                                                      @Param("groupId") String groupId);

    @Query(value = "select * from item.itemsearchforpricing(:itemNo, :itemName, :lid, :ptid) AS\n" +
        "(item_id bigint,item_name character varying COLLATE pg_catalog.\"default\",item_no character varying COLLATE pg_catalog.\"default\", " +
        "hcpcs_code character varying COLLATE pg_catalog.\"default\", purchase_charged_amt double precision, purchase_allowed_amt double precision, " +
        "rental_charged_amt double precision, rental_allowed_amt double precision, retail_charged_amt double precision, onhand_qty bigint, " +
        "onrent_qty bigint, committed_qty bigint, inorder_qty bigint)" ,nativeQuery = true)
    List<Map> findItemsForSoByItemNoAndItemNameAndLidAndPtid(@Param("itemNo") String itemNo,
                                                             @Param("itemName") String itemName,
                                                             @Param("lid") Long lid,
                                                             @Param("ptid") Long ptid);


    @Query(value = "select * from item.itempricesearchbypricetableid(:itemNo,:procCode,:priceTableId,:purchaseType,:dos)\n" +
        "AS\n" +
        "(\t\n" +
        "    item_no character varying COLLATE pg_catalog.\"default\",\n" +
        "\titem_name character varying COLLATE pg_catalog.\"default\",\n" +
        "\thcpcs character varying COLLATE pg_catalog.\"default\",\n" +
        "\toption_number character varying COLLATE pg_catalog.\"default\",\n" +
        "\tprice_type character varying COLLATE pg_catalog.\"default\",\n" +
        "\tprice_table_id bigint,\n" +
        " \tcmn_reqd_to_bill_status character varying COLLATE pg_catalog.\"default\",\n" +
        "\tprior_auth_req_status character varying COLLATE pg_catalog.\"default\",\n" +
        "\tcharge_amt double precision,\n" +
        "\tallowed_amt double precision,\n" +
        "\tallowed_modifier_1 character varying COLLATE pg_catalog.\"default\", \n" +
        "\tallowed_modifier_2 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tallowed_modifier_3 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tallowed_modifier_4 character varying COLLATE pg_catalog.\"default\",\n" +
        "\ttaxable_status character varying COLLATE pg_catalog.\"default\",\n" +
        "\teffective_start_date date,\n" +
        "\teffective_end_date date,\n" +
        "\tprice_value_type character varying COLLATE pg_catalog.\"default\"\n" +
        ")" ,nativeQuery = true)
    List<Map> findItemPriceByPriceTableId(@Param("itemNo") String itemNo,
                                          @Param("procCode") String procCode,
                                          @Param("priceTableId") String priceTableId,
                                          @Param("purchaseType") String purchaseType,
                                          @Param("dos") String dos);
}
