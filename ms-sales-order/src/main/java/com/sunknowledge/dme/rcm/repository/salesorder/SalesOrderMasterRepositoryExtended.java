package com.sunknowledge.dme.rcm.repository.salesorder;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SoClinicalInsuranceOutputDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.DoubleStream;

public interface SalesOrderMasterRepositoryExtended extends SalesOrderMasterRepository {
    Flux<SalesOrderMaster> getSOBysalesOrderMasterUuid(UUID salesOrderUUID);

    @Query(value = "select so.get_t_sales_order_master_id_by_uuid(:salesOrderUUID)")
    Mono<Long> getIDBysalesOrderMasterUuid(@Param("salesOrderUUID") UUID salesOrderUUID);

    @Query(value = "select * from t_sales_order_master tdt where tdt.sales_order_id = :salesOrderId and lower(tdt.status) = 'active'")
    Mono<SalesOrderMaster> getSOBysalesOrderId(@Param("salesOrderId") Long salesOrderId);

    @Query(value = "select so.so_id_no()")
    Mono<String> generatePatientIDNumber();

    @Query(value = "select * from t_sales_order_master tdt where tdt.patient_id = :patientId")
    Flux<SalesOrderMaster> getSOByPatientId(@Param("patientId") Long patientId);

    @Query(value = "select * from so.t_sales_order_master where sales_order_id = :soId")
    Mono<SalesOrderMaster> getSODetailsBysoId(@Param("soId") Long soId);

    @Query(value = "select so.validateclaimsubmissiondata(:soId)")
    Mono<Boolean> validateClaimSubmissionData(@Param("soId") Long soId);

    @Query(value = "call so.create_duplicate_so(:soId)")
    Mono<SalesOrderMaster> createDuplicateSalesOrder(@Param("soId") Long soId);

    @Query(value = "select * from so.checksumsecondaryforexistingprimary(:soId,:claimControlNo)\r\n"
    		+ "AS\r\n"
    		+ "(\r\n"
    		+ "	so_id bigint, \r\n"
    		+ "	claim_control_number character varying COLLATE pg_catalog.\"default\", \r\n"
    		+ "	secondary_invoice_no character varying COLLATE pg_catalog.\"default\", \r\n"
    		+ "	secondary_invoice_id bigint, \r\n"
    		+ "	reslt boolean    \r\n"
    		+ ")")
    Mono<AddSecondaryForPrimaryDTO> addSecondaryForPrimary(@Param("soId") Long soId, @Param("claimControlNo") String claimControlNo);

    @Query(value = "select so.validateclaimReSubmissiondataPeriodGreaterThanOne(:soId,:periodNo,:prevSoId)")
    Mono<Boolean> rentalHelperPeriodGreaterThanOne(@Param("soId") Long soId, @Param("periodNo") String periodNo, @Param("prevSoId") Long prevSoId);

    @Query(value = "select par_status from so.t_par_master where par_id in (:parIdList)")
    Flux<String> getParStatusByParId(@Param("parIdList") List<Long> parIdList);

    @Query(value = "select cmn_status from so.t_cmn where cmn_id in(:cmnIdList)")
    Flux<String> getCMNStatusByCMNId(@Param("cmnIdList") List<Long> cmnIdList);
    @Query(value = "select item_inventory_status_id, item_id, item_location_id, onhand_qty, onrent_qty, comitted_qty, inorder_qty from item.t_item_inventory_status where item_id= :itemId and item_location_id = :itemLocationId")
    Mono<ItemInventoryStatusExtendedDTO> getItemInventoryStatusData(@Param("itemId") Long itemId, @Param("itemLocationId") Long itemLocationId);

    @Query(value = "select mtttm.task_id from masterdata.mv_t_task_type_master mtttm " +
        "where lower(mtttm.task_name) = lower(:taskName) ")
    Mono<String> getTaskIdByTaskName(@Param("taskName") String taskName);

    @Query(value = "select mtwsm.wip_status_id from masterdata.mv_t_wip_status_master mtwsm " +
        " where lower(mtwsm.wip_status_name) = lower(:wipStatusName) ")
    Mono<String> getWipStatusIdByWipStatusName(@Param("wipStatusName") String wipStatusName);

    @Query(value = "select mtotm.object_id  from masterdata.mv_t_object_type_master mtotm where lower(mtotm.object_name) = lower(:objectName)")
    Mono<String> getObjectIdByObjectName(@Param("objectName") String objectName);

    @Query(value = "select CASE WHEN mtum.middle_name is NULL THEN CONCAT(mtum.first_name, ' ', mtum.last_name) \n" +
        "         ELSE CONCAT(mtum.first_name, ' ', mtum.middle_name, ' ', mtum.last_name) \n" +
        "         END as user_name  from masterdata.mv_t_user_master mtum where mtum.user_id = :userId")
    Mono<String> getUserNameByUserId(@Param("userId") Long userId);

    @Query(value = "select a.sales_order_id, a.sales_order_no, a.patient_id, a.patient_first_name, a.patient_middle_name, a.patient_last_name, a.patient_address_line_1 \n" +
        "as patient_address_line1, a.patient_address_line_2 as patient_address_line2, a.patient_contact_no_1 as patient_contact_no1, a.patient_contact_no_2 \n" +
        "as patient_contact_no2, a.patient_dob,  a.branch_id,  a.billing_branch_name,  a.billing_provider_npi,  a.billing_provider_organisation_name,  a.billing_provider_address_line_1\n" +
        "as billing_provider_address_line1,  a.billing_provider_address_line_2 as billing_provider_address_line2,  a.billing_provider_city,  a.billing_provider_state, \n" +
        "a.billing_provider_country,  a.billing_provider_zip_code,  a.branch_contact_person_name,  a.branch_contact_no_1 as branch_contact_no1,  b.ordering_provider_facility_name, \n" +
        "b.ordering_provider_address_line_1 as ordering_provider_address_line1,  b.ordering_provider_address_line_2 as ordering_provider_address_line2,\n" +
        "b.ordering_provider_city,  b.ordering_provider_state,  b.ordering_provider_zip,\n" +
        "CASE \n" +
        "    WHEN a.patient_middle_name <> '' \n" +
        "    THEN CONCAT_WS(' ', a.patient_first_name, a.patient_middle_name, a.patient_last_name)\n" +
        "    ELSE CONCAT_WS(' ', a.patient_first_name, a.patient_last_name)\n" +
        "END AS patient_full_name,\n" +
        "CASE \n" +
        "    WHEN b.ordering_provider_middle_name <> '' \n" +
        "    THEN CONCAT_WS(' ', b.ordering_provider_first_name, b.ordering_provider_middle_name, b.ordering_provider_last_name)\n" +
        "    ELSE CONCAT_WS(' ', b.ordering_provider_first_name, b.ordering_provider_last_name)\n" +
        "END AS ordering_provider_full_name,\n" +
        "b.ordering_provider_first_name,b.ordering_provider_middle_name, b.ordering_provider_last_name, b.ordering_provider_contact_no_1 as ordering_provider_contact_no1, \n" +
        "b.ordering_provider_fax, b.ordering_provider_npi, c.primary_insurer_policy_no, a.patient_member_id, a.patient_delivery_state, a.branch_contact_no_2 as branch_contact_no2\n" +
        ", a.branch_fax,b.icd_10_diagnosis_code_1 as icd10_diagnosis_code1, b.primary_diagnosis\n"+
        "from so.t_sales_order_master a \n" +
        "inner join so.t_sales_order_clinical_details b on a.sales_order_id = b.sales_order_id\n" +
        "inner join so.t_sales_order_insurance_details c on b.sales_order_id = c.sales_order_id \n" +
        "where a.sales_order_id = :soId and lower(a.status)='active'")
    Mono<SoClinicalInsuranceOutputDTO> getSalesOrderMasterClinicalInsuranceData(@Param("soId") Long soId);

    @Query(value = "select * from so.t_sales_order_master tsom  where tsom.sales_order_master_uuid = :salesOrderUUID")
    Mono<SalesOrderMaster> getSOBySOUuid(@Param("salesOrderUUID") UUID salesOrderUUID);
}
