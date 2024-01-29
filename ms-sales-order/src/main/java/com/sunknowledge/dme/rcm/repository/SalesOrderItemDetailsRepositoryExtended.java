package com.sunknowledge.dme.rcm.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.dto.soItemDetails.ItemDefaultVendorResponseDTO;
import com.sunknowledge.dme.rcm.dto.soItemDetailsAndClicnicalAndInsurance.SoItemDetailsAndClinicalAndInsuranceResponseData;
import com.sunknowledge.dme.rcm.service.dto.po.ResultDropshipDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.GetselectedItemsForSOID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalesOrderItemDetailsRepositoryExtended extends SalesOrderItemDetailsRepository {
	Flux<SalesOrderItemDetails> findBySalesOrderId(Long SOID);

	@Query("select * from t_sales_order_item_details where sales_order_item_details_uuid = :sOItemDetailsUUID")
	Flux<SalesOrderItemDetails> getSOItemDetailsBySOItemDetailsUUID(UUID sOItemDetailsUUID);

	@Query(value = "select so.get_t_sales_order_item_details_id_by_uuid(:sOItemDetailsUUID)")
	Mono<Long> getIDByUUID(@Param("sOItemDetailsUUID") UUID sOItemDetailsUUID);

	@Query(value = "select a.sales_order_id, a.sales_order_no, a.patient_id, a.patient_gender, a.patient_id_no, a.patient_first_name, a.patient_middle_name, a.patient_last_name, \n"
			+ "a.patient_address_line_1, a.patient_address_line_2, a.patient_contact_no_1, a.patient_contact_no_2, a.patient_dob, a.branch_name, a.tax_zone_id, \n"
			+ "a.tax_rate, a.branch_id, a.branch_address_line_1, a.branch_address_line_2, a.branch_city, a.branch_state, a.branch_zip_code, a.branch_fax, a.branch_country, a.branch_contact_no_1, a.branch_contact_person_name, "
			+ " a.city_name, a.state_name, a.zip_code, b.sales_order_insurance_details_id, b.sales_order_id, b.primary_insurer_id, a.patient_email, a.contact_person_name, \n"
			+ "b.primary_insurer_name, b.primary_insurer_policy_no,b.primary_insurer_patient_id_number, b.primary_insurer_effective_date, \n"
			+ "b.primary_insurer_pay_percentage, b.primary_insurer_address_line_1, b.primary_insurer_address_line_2, b.primary_insurer_city, b.primary_insurer_state, \n"
			+ "b.primary_insurer_zip, b.primary_insurer_contact_1, b.primary_insurer_contact_name, b.primary_insurance_payer_id, b.primary_insurer_fax, b.price_table_id, b.price_table_name, c.sales_order_clinical_details_id, \n"
			+ "c.sales_order_id, c.patient_id, c.patient_weight_in_kg, c.patient_weight_in_lbs, c.height_in_inches, c.height_in_cm, c.ordering_provider_facility_id, \n"
			+ "c.ordering_provider_facility_name, c.ordering_provider_id, c.ordering_provider_type, c.ordering_provider_first_name, c.ordering_provider_middle_name, \n"
			+ "c.ordering_provider_last_name, c.ordering_provider_npi, c.ordering_provider_dea, c.ordering_provider_address_line_1, c.ordering_provider_address_line_2,\n"
			+ "c.ordering_provider_email, c.ordering_provider_fax, c.ordering_provider_city, c.ordering_provider_state, c.ordering_provider_country, \n"
			+ "c.ordering_provider_contact_no_1, c.ordering_provider_contact_no_2, c.ordering_provider_efax, c.icd_10_diagnosis_code_1, c.primary_diagnosis, \n"
			+ "c.diagnosis_code_type from so.t_sales_order_master a, so.t_sales_order_insurance_details b, so.t_sales_order_clinical_details c where\n"
			+ "a.sales_order_id = b.sales_order_id and b.sales_order_id = c.sales_order_id and a.sales_order_id=:soId and lower(a.status)=Lower(:status)\n"
			+ "and lower(b.status)=Lower(:status) and lower(c.status)=Lower(:status)")
	Mono<SoItemDetailsAndClinicalAndInsuranceResponseData> findSoItemDetailsAndClinicalAndInsuranceDataBySoId(
			@Param("soId") Long soId, @Param("status") String status);

	@Query(value = "select default_vendor_id as id, default_vendor_name as title from masterdata.mv_t_item_master where item_id = :itemId and lower(status)  = lower('ACTIVE')")
	Mono<ItemDefaultVendorResponseDTO> findItemDefaultVendorByItemId(@Param("itemId") Long itemId);

	@Query("select distinct a.po_id,a.po_number, a.purchase_order_uuid  from item.t_purchase_order a\n"
			+ " left outer join item.t_purchase_order_items b on a.po_id=b.po_id\n"
			+ " where a.sales_order_id=:salesOrderId and a.vendor_id=:vendorId and b.item_id=:salesOrderDetailsItemId\n"
			+ " and b.ordered_qty=:salesOrderDetailsQty and LOWER(a.status)='active' and (a.po_id, a.created_date) = \n"
			+ " (select Max(a.po_id), max(a.created_date) from item.t_purchase_order a\n"
			+ " left outer join item.t_purchase_order_items b on a.po_id=b.po_id\n"
			+ " where a.sales_order_id=:salesOrderId and a.vendor_id=:vendorId and b.item_id=:salesOrderDetailsItemId\n"
			+ " and b.ordered_qty=:salesOrderDetailsQty and LOWER(a.status)='active')")
	Mono<ResultDropshipDTO> getSavedPurchaseOrder(@Param("salesOrderId") Long salesOrderId,
			@Param("vendorId") Long vendorId, @Param("salesOrderDetailsItemId") Long salesOrderDetailsItemId,
			@Param("salesOrderDetailsQty") Long salesOrderDetailsQty);

	@Query("select * from item.so_item_inventory_transaction_update(:salesOrderNo,:saleType,:itemId,:itemNo,:itemName,:itemUom,"
			+ ":itemQty,:wheatherSerialized,:serialNo,:wheatherAssetTagged,:originalDos,:branchId,:locationId,:locationName,:createdById,:createdByName,"
			+ ":createdDate,:updatedById,:updatedDate,:updatedByName) ")
	Mono<String> soItemInventoryTransactionUpdate(@Param("salesOrderNo") String salesOrderNo,
			@Param("saleType") String saleType, @Param("itemId") Long itemId, @Param("itemNo") String itemNo,
			@Param("itemName") String itemName, @Param("itemUom") String itemUom, @Param("itemQty") Long itemQty,
			@Param("wheatherSerialized") String wheatherSerialized, @Param("serialNo") String serialNo,
			@Param("wheatherAssetTagged") String wheatherAssetTagged, @Param("originalDos") LocalDate originalDos,
			@Param("branchId") Long branchId, @Param("locationId") Long locationId,
			@Param("locationName") String locationName, @Param("createdById") Long createdById,
			@Param("createdByName") String createdByName, @Param("createdDate") LocalDate createdDate,
			@Param("updatedById") Long updatedById, @Param("updatedDate") LocalDate updatedDate,
			@Param("updatedByName") String updatedByName);

	@Query("select * from so.par_link_for_so_item(:sono, :soid, :insuranceid, \n" + ":insurancename, :payeridno,\n"
			+ ":payerlevel, :policynumber, :policystartdate,\n"
			+ ":policyenddate, :payercontactnumber, :payercontactname,\n"
			+ ":effectivestartdate, :itemno, :itemid,  :itemuom, :itemquantity, \n" + ":description,\n" + ":itemname,\n"
			+ ":hcpcsno, :patientidno, :patientid, :patientfirstname,\n"
			+ ":patientlastname, :patientdob, :patientgender, :dos, :createdbyid, \n"
			+ ":createdbyname, :useexistingpar, :pricetableid \n" + ")\n" + "AS\n" + "(\n" + "    par_id bigint, \n"
			+ "    par_id_no character varying\n" + ")")
	Mono<ParMaster> linkParForSO(@Param("insuranceid") Long insuranceid, @Param("insurancename") String insurancename,
			@Param("payercontactname") String payercontactname, @Param("payercontactnumber") String payercontactnumber,
			@Param("payeridno") String payeridno, @Param("policynumber") String policynumber,
			@Param("policystartdate") LocalDate policystartdate, @Param("policyenddate") LocalDate policyenddate,
			@Param("effectivestartdate") LocalDate effectivestartdate, @Param("patientidno") String patientidno,
			@Param("patientid") Long patientid, @Param("patientdob") LocalDate patientdob,
			@Param("patientfirstname") String patientfirstname, @Param("patientlastname") String patientlastname,
			@Param("patientgender") String patientgender, @Param("itemid") Long itemid, @Param("itemno") String itemno,
			@Param("itemname") String itemname, @Param("itemquantity") Long itemquantity,
			@Param("itemuom") String itemuom, @Param("description") String description,
			@Param("createdbyid") Long createdbyid, @Param("createdbyname") String createdbyname,
			@Param("dos") LocalDate dos, @Param("hcpcsno") String hcpcsno, @Param("payerlevel") String payerlevel,
			@Param("useexistingpar") String useexistingpar, @Param("soid") Long soid, @Param("sono") String sono,
			@Param("pricetableid") Long pricetableid);

	@Query("select * from item.so_dropship_item_inventory_transaction_update(:salesOrderNo,:saleType,:itemId,:itemNo,:itemName,:itemUom,"
			+ ":itemQty,:wheatherSerialized,:serialNo,:wheatherAssetTagged,:originalDos,:branchId,:locationId,:locationName,:createdById,:createdByName,"
			+ ":createdDate,:updatedById,:updatedDate,:updatedByName, :isDropship, :poNo) ")
	Mono<String> soDropshipItemInventoryTransactionUpdate(@Param("salesOrderNo") String salesOrderNo,
			@Param("saleType") String saleType, @Param("itemId") Long itemId, @Param("itemNo") String itemNo,
			@Param("itemName") String itemName, @Param("itemUom") String itemUom, @Param("itemQty") Long itemQty,
			@Param("wheatherSerialized") String wheatherSerialized, @Param("serialNo") String serialNo,
			@Param("wheatherAssetTagged") String wheatherAssetTagged, @Param("originalDos") LocalDate originalDos,
			@Param("branchId") Long branchId, @Param("locationId") Long locationId,
			@Param("locationName") String locationName, @Param("createdById") Long createdById,
			@Param("createdByName") String createdByName, @Param("createdDate") LocalDate createdDate,
			@Param("updatedById") Long updatedById, @Param("updatedDate") LocalDate updatedDate,
			@Param("updatedByName") String updatedByName, @Param("poNo") String poNo,
			@Param("isDropship") String isDropship);

	@Query(value = "SELECT * from so.d_link_par_from_so(:parId, :salesOrderId, :salesOrderDetailsItemId, :updatedById, :updatedByName)")
	Mono<Boolean> getDLinkParForItems(@Param("parId") Long parId, @Param("salesOrderId") Long salesOrderId,
			@Param("salesOrderDetailsItemId") String salesOrderDetailsItemId, @Param("updatedById") Long updatedById,
			@Param("updatedByName") String updatedByName);

	@Query(value = "SELECT * from so.d_link_cmn_from_so(:cmnId, :salesOrderId, :salesOrderDetailsItemId, :updatedById, :updatedByName)")
	Mono<Boolean> getDLinkForCmnItems(@Param("cmnId") Long cmnId, @Param("salesOrderId") Long salesOrderId,
			@Param("salesOrderDetailsItemId") String salesOrderDetailsItemId, @Param("updatedById") Long updatedById,
			@Param("updatedByName") String updatedByName);

	@Query(value = "SELECT * from so.link_par_for_so_items(:parId, :salesOrderId, :salesOrderDetailsItemId, :createdById, :createdByName)")
	Mono<Boolean> getLinkForParItems(@Param("parId") Long parId, @Param("salesOrderId") Long salesOrderId,
			@Param("salesOrderDetailsItemId") String salesOrderDetailsItemId, @Param("updatedById") Long updatedById,
			@Param("updatedByName") String updatedByName);

	@Query(value = "SELECT * from so.link_cmn_for_so_items(:parId, :salesOrderId, :salesOrderDetailsItemId, :updatedById, :updatedByName)")
	Mono<Boolean> getLinkForCmnItems(@Param("cmnId") Long cmnId, @Param("salesOrderId") Long salesOrderId,
			@Param("salesOrderDetailsItemId") String salesOrderDetailsItemId, @Param("updatedById") Long updatedById,
			@Param("updatedByName") String updatedByName);

	@Query(value = "select * from so.t_sales_order_item_details where sales_order_id = :soId and lower(status)='active'")
	Flux<SalesOrderItemDetails> getSalesOrderItemDetailsData(@Param("soId") Long soId);

	@Query(value = "select * from so.t_sales_order_item_details where sales_order_id=:salesOrderId and lower(sales_order_details_sale_type) = 'rental' and lower(status) = 'active'")
	Flux<SalesOrderItemDetails> getSORentalItemDetailsBySOID(@Param("salesOrderId") Long salesOrderId);

	@Query(value = "select * from so.getselectedItemsForSOID(:salesOrderId,:items)")
	Flux<GetselectedItemsForSOID> getselectedItemsForSOID(@Param("salesOrderId") Long salesOrderId,
			@Param("items") String items);

}
