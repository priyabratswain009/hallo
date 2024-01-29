package com.sunknowledge.dme.rcm.repository.cmn;

import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.dto.cmn.CmnSearchResponse;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.repository.CmnRepository;
import com.sunknowledge.dme.rcm.service.dto.cmn.CmnPatientData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public interface CmnRepo extends CmnRepository {
    @Query("select tsom.sales_order_id,tsom.facility_npi, tsom.patient_id, tsom.sales_order_no, tsom.billing_branch_name, tsom.branch_address_line_1, tsom.branch_address_line_2,\n" +
        "tsom.branch_city, tsom.branch_state, tsom.branch_zip_code,\n" +
        "tsom.branch_contact_no_1, tsom.branch_contact_no_2, tsom.branch_fax, tsom.patient_first_name, tsom.patient_middle_name, tsom.patient_last_name,\n" +
        "tsom.patient_address_line_1,tsom.patient_address_line_2, tsom.city_name, tsom.state_name, tsom.zip_code,\n" +
        "tsom.patient_contact_no_1, tsom.patient_contact_no_2, tsom.patient_dob,\n" +
        "\n" +
        "tsocd.ordering_provider_first_name, tsocd.ordering_provider_middle_name, tsocd.ordering_provider_last_name, \n" +
        "tsocd.ordering_provider_address_line_1, tsocd.ordering_provider_address_line_2, tsocd.ordering_provider_city, tsocd.ordering_provider_state, tsocd.ordering_provider_zip,\n" +
        "tsocd.ordering_provider_contact_no_1, tsocd.ordering_provider_contact_no_2,\n" +
        "tsocd.ordering_provider_dea, tsocd.ordering_provider_npi, tsocd.ordering_provider_fax, tsocd.primary_diagnosis,\n" +
        "tsocd.icd_10_diagnosis_code_1, tsocd.icd_10_diagnosis_code_2, tsocd.icd_10_diagnosis_code_3, tsocd.icd_10_diagnosis_code_4, tsocd.icd_10_diagnosis_code_5, tsocd.icd_10_diagnosis_code_6, \n" +
        "tsocd.icd_10_diagnosis_code_7, tsocd.icd_10_diagnosis_code_8, tsocd.icd_10_diagnosis_code_9, tsocd.icd_10_diagnosis_code_10, tsocd.icd_10_diagnosis_code_11, tsocd.icd_10_diagnosis_code_12, \n" +
        "\n" +
        "tsoid.primary_insurer_policy_no\n" +
        "\n" +
        "from t_sales_order_master tsom, t_sales_order_clinical_details tsocd, t_sales_order_insurance_details tsoid\n" +
        "where tsom.sales_order_id = tsocd.sales_order_id and tsom.sales_order_id = tsoid.sales_order_id\n" +
        "and tsom.sales_order_id = :salesOrderId and lower(tsom.status) = 'active' and lower(tsocd.status) ='active' and lower(tsoid.status) = 'active'")
    public Mono<SWODataDTO> getSWODataOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("select tsoid.sales_order_item_details_id, tsoid.sales_order_details_sale_type, tsoid.sales_order_details_modifier_1, \n" +
        "tsoid.sales_order_details_modifier_2, tsoid.sales_order_details_modifier_3, tsoid.sales_order_details_modifier_4, tsoid.sales_order_details_proc_code, tsoid.sales_order_details_item_name, tsoid.sales_order_details_charge_amt, tsoid.sales_order_details_qty,\n" +
        "tsoid.frequency_count, tsoid.frequency_interval\n" +
        "from so.t_sales_order_item_details tsoid \n" +
        "where tsoid .sales_order_id = :salesOrderId and lower(tsoid.status) = 'active'")
    public Flux<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("SELECT * FROM t_cmn WHERE sales_order_id = :salesOrderId")
    public Mono<Cmn> getCmnDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("select * from f_get_cmn_no()")
    public Mono<String> getCmnNumberSequence();

    @Query(value = "select * from so.cmnsearchso(:patientIdNo, :itemNo, :dos, :orderingProviderNpi)\n" +
        "AS\n" +
        "(\n" +
        "\tsalesordernumber character varying COLLATE pg_catalog.\"default\",\n" +
        "\tcmnid bigint,\n" +
        "    cmnnumber character varying COLLATE pg_catalog.\"default\", \n" +
        "    cmntype character varying COLLATE pg_catalog.\"default\", \n" +
        "    cmnformname character varying COLLATE pg_catalog.\"default\", \n" +
        "    patientid bigint, \n" +
        "    salesorderid bigint, \n" +
        "    salesorderno  character varying COLLATE pg_catalog.\"default\",\n" +
        "    cmncreatedate timestamp without time zone,\n" +
        "    cmninitialdate timestamp without time zone,\n" +
        "    cmnexpirationdate timestamp without time zone, \n" +
        "    cmnlogged_date timestamp without time zone, \n" +
        "    cmnapproved_by character varying COLLATE pg_catalog.\"default\",   \n" +
        "    cmnapproved_date timestamp without time zone,  \n" +
        "    lengthofneed character varying COLLATE pg_catalog.\"default\",   \n" +
        "    cmniduuid uuid,\n" +
        "    cmnstatus character varying COLLATE pg_catalog.\"default\",  \t\n" +
        "\tinitialdocumentname character varying COLLATE pg_catalog.\"default\",   \n" +
        "\treturndocumentname character varying COLLATE pg_catalog.\"default\", \t\n" +
        "\tpatientfirstname character varying COLLATE pg_catalog.\"default\",\n" +
        "\tpatientmiddlename character varying COLLATE pg_catalog.\"default\",   \n" +
        "\tpatientlastname character varying COLLATE pg_catalog.\"default\",   \n" +
        "\tdeliveryscheduledatetime timestamp without time zone,\n" +
        "\tdeliveryactualdatetime timestamp without time zone,\n" +
        "\torderstatus character varying COLLATE pg_catalog.\"default\",\n" +
        "\tsalesorderitems character varying COLLATE pg_catalog.\"default\"\n" +
        ")")
    public Mono<CmnSearchResponse> searchActiveCMNForSalesOrder(@Param("patientIdNo") String patientIdNo, @Param("itemNo") String itemNo,
                                                                @Param("dos") String dos, @Param("orderingProviderNpi") String orderingProviderNpi);

    @Query("select * from so.initiatedcmnsearchbyso(:salesOrderNo)\n" +
        "AS\n" +
        "(\t\n" +
        "    cmnnumber character varying COLLATE pg_catalog.\"default\"\n" +
        ")")
    public Mono<String> searchInitiatedCMNForSalesOrder(@Param("salesOrderNo") String salesOrderNo);

    @Query("SELECT * FROM t_cmn WHERE cmn_number = :cmnNumber")
    public Mono<Cmn> getCmnDetailsOnCmnNumber(@Param("cmnNumber") String cmnNumber);

    @Query("select * from so.active_initiated_cmn_search(:salesOrderId, :itemNo, :orderingProviderNpi, :patientId, :dos)\n" +
        "AS\n" +
        "(\n" +
        "\t  cmn_id bigint,\n" +
        "    cmn_number character varying, \n" +
        "    cmn_status character varying\n" +
        ")")
    Mono<Cmn> findCmnBySalesOrderIdAndPatientId(@Param("salesOrderId") Long salesOrderId,
                                                @Param("patientId") Long patientId,
                                                @Param("dos") LocalDate dos,
                                                @Param("itemNo") String itemNo,
                                                @Param("orderingProviderNpi") String orderingProviderNpi);

    @Query("SELECT *\n" +
        "FROM so.t_cmn\n" +
        "WHERE sales_order_id = :soId\n" +
        "AND LOWER(status) = 'active'\n" +
        "AND LOWER(cmn_status) IN ('initiated', 'logged')")
    Mono<Cmn> getCMNMasterData(@Param("soId") Long soId);

    @Query("SELECT distinct tc.sales_order_id ,tc.sales_order_no ,tsom.patient_id_no,tsom.patient_first_name ,tsom.patient_last_name  FROM so.t_cmn tc \n" +
        "       join so.t_sales_order_master tsom on tsom.patient_id =tc.patient_id \n" +
        "        WHERE cmn_number = :cmnNo and lower(tsom.status) = 'active' and lower(tc.status)='active'")
    Mono<CmnPatientData> getCMNSOIdandPatientData(@Param("cmnNo") String cmnNo);

    @Query("select * from so.t_cmn tc where tc.cmn_id_uuid = :cmnUUID and lower(tc.status) = 'active'")
    Mono<Cmn> getCMNDetailsOnUUID(@Param("cmnUUID") UUID cmnUUID);
}
