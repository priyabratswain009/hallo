package com.sunknowledge.dme.rcm.repository;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientSoIdAndSoNoOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchOutputDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalesOrderMasterSearchRepositoryExtended extends SalesOrderMasterRepository {

    @Query(value = "select * from so.so_by_created_userid (:createdById) as \n" +
        "(sid bigint,sno character varying,p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, p_address_line2 character varying, Status character varying, o_status character varying,c_date timestamp without time zone,c_by_id bigint,c_by_name character varying)\n")
    Flux<SalesOrderMasterSearchOutputDTO> findByCreatedById(@Param("createdById") Integer createdById);

    @Query(value = "select * from so.so_by_status (:status,:fromDate,:toDate) as \n" +
        "(sid bigint,sno character varying,p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, p_address_line2 character varying, Status character varying, o_status character varying,c_date timestamp without time zone,c_by_id bigint,c_by_name character varying)\n")
    Flux<SalesOrderMasterSearchDetailsDTO> findSODetailsByStatus(@Param("status") String status, @Param("fromDate") LocalDate fromDate,
                                                                 @Param("toDate") LocalDate toDate);

    @Query(value = "select * from so.so_by_branch_name (:branchName, :fromDate, :toDate) as \r\n"
        + "(sid bigint,sno character varying,p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, p_address_line2 character varying, Status character varying, o_status character varying,c_date timestamp without time zone,c_by_id bigint,c_by_name character varying, p_branch_name character varying)\r\n")
    Flux<SalesOrderMasterSearchDetailsDTO> findByBranchandCreatedDate(@Param("branchName") String branchName,
                                                                      @Param("fromDate") LocalDate createdFromDate, @Param("toDate") LocalDate createdToDate);

    @Query(value = "select * from so.so_patient_Facility_lookup(:facilityName) as "
        + "(fid bigint,fname character varying, f_npi character varying)\n")
    Flux<SalesOrderMasterSearchOutputDTO> findByFacilityName(@Param("facilityName") String facilityName);

    @Query(value = "select * from so.so_patient_SOID_lookup(:salesOrderNumber) as\r\n"
        + "(sid bigint,sno character varying, p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, p_address_line2 character varying, Status character varying, o_status character varying,c_date timestamp without time zone, c_by_name character varying\r\n"
        + ")\n")
    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySalesOrderNumber(@Param("salesOrderNumber") String salesOrderNumber);

    @Query(value = "select * from so.so_patientbranch_lookup(:branchname) as\r\n"
        + "(sid bigint,sno character varying, p_branch_name character varying)\r\n"
        + "")
    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientBranchName(@Param("branchname") String branchname);

    @Query(value = "select * from so.so_patientname_lookup(replace(:patientName, ' ', '')) as\r\n"
        + "(p_id bigint,p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_ssn character varying,  p_gender character varying, p_dob date, p_addres_line1 character varying, p_address_line2 character varying, p_city character varying, p_state character varying,p_phone1 character varying,  p_phone2 character varying)\r\n"
        + "")
    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientName(@Param("patientName") String patientName);

    Flux<SalesOrderMaster> findByPatientId(Long patientId);

    @Query(value = "select * from so.SObyUserIDapproved(:confirmationbyid) as\r\n"
        + "(sid bigint,sno character varying,p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, p_address_line2 character varying, Status character varying, o_status character varying,c_date timestamp without time zone,c_by_id bigint, c_by_name character varying, confirmation_by_id bigint,confirmation_by_name character varying)\r\n"
        + "")
    Flux<SalesOrderMasterSearchOutputDTO> findByConfirmedById(@Param("confirmationbyid") Integer confirmationbyid);

    @Query(value = "select * from so.UserIDapprovedLookup(:confirmationByName,:status,:confirmationFromDate,:confirmationToDate) as\r\n"
        + "(confirmation_by_id bigint, confirmation_by_name character varying)\r\n"
        + "")
    Flux<SalesOrderMasterSearchOutputDTO> findByByConfirmationDetails(String confirmationByName, String status, LocalDate confirmationFromDate,
                                                                      LocalDate confirmationToDate);

    @Query(value = "select * from so.UserIDcreatedLookup(:createdByName,:status,:createdFromDate,:createdToDate) as\r\n"
        + "(c_by_id bigint, c_by_name character varying)\r\n"
        + "")
    Flux<SalesOrderMasterSearchOutputDTO> findByCreationDetails(String createdByName, String status, LocalDate createdFromDate,
                                                                LocalDate createdToDate);

    @Query(value = "select * from so.SObySalesOrderDetails(:salesOderNo,:deliveryScheduleDatetime,:deliveryActualDatetime,:createdDate) as\r\n"
        + "(sid bigint,sno character varying,p_first_name character varying, p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, p_address_line2 character varying, Status character varying, o_status character varying,c_date timestamp without time zone,c_by_id bigint, c_by_name character varying, confirmation_date timestamp without time zone, delivery_schedule_datetime timestamp without time zone, delivery_actual_datetime timestamp without time zone)\r\n"
        + "")
    Flux<SalesOrderMasterSearchOutputDTO> findBySOInfo(String salesOderNo, LocalDate deliveryScheduleDatetime,
                                                       LocalDate deliveryActualDatetime, LocalDate createdDate);

    @Query(value = "select * from so.so_patient_lookup_Combined (replace(:patientName, ' ', ''), :salesOderNo, :patientDOBDt, :startDateDt, :endDateDt, :status, " +
        ":branchName, :primaryInsurerName, :patientAddress1, :facilityName) as " +
        "(sid bigint,sno character varying,b_name character varying,f_name character varying, p_first_name character varying, " +
        "p_middle_name character varying,p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, " +
        "p_address_line2 character varying, Status character varying, o_status character varying," +
        "c_date timestamp without time zone,c_by_id bigint, c_by_name character varying)")
    Flux<SalesOrderMasterSearchDetailsDTO> findSODetailsWithCombinedInformation(@Param("patientName") String patientName, @Param("salesOderNo") String salesOderNo,
                                                                                @Param("patientDOBDt") LocalDate patientDOBDt, @Param("startDateDt") LocalDate startDateDt, @Param("endDateDt") LocalDate endDateDt,
                                                                                @Param("status") String status, @Param("branchName") String branchName, @Param("primaryInsurerName") String primaryInsurerName,
                                                                                @Param("patientAddress1") String patientAddress1, @Param("facilityName") String facilityName);

    @Query(value = "select * from so.so_id_no()")
    Mono<String> getsalesOrderNo();

    //---- Method for dynamic query generator for Common/Combined Search----
    Flux<SalesOrderCommonSearchOutputDTO> getSalesOrderDetailsForSearchParametersBySalesOrderNo(Map<String, Object> conditionalKeyValue, Map<String, String> columnKeyMapper);


    @Query(value = "select * from so.sosearchallcombined(:branchName, \n" +
        ":salesOrderNo, \n" +
        ":salesOrderUUID, \n" +
        ":patientIDNo, \n" +
        ":patientFirstName, \n" +
        ":patientMiddleName, \n" +
        ":patientLastName, \n" +
        ":createdByName, \n" +
        ":createdDateFromDate, \n" +
        ":createdDateToDate, \n" +
        ":scheduleDeliveryFromDate, \n" +
        ":scheduleDeliveryToDate, \n" +
        ":deliveryActualDateStart, \n" +
        ":deliveryActualDateEnd, \n" +
        ":deliveryAddressLine1, \n" +
        ":deliveryAddressLine2, \n" +
        ":deliveryCity, \n" +
        ":deliveryState, \n" +
        ":deliveryZip, \n" +
        ":salesOrderStatus, \n" +
        ":dropshipStatus, \n" +
        ":itemNo, \n" +
        ":itemName, \n" +
        ":hcpcsCode, \n" +
        ":primaryPayerName)\n" +
        "AS\n" +
        "(\n" +
        "sales_order_no character varying COLLATE pg_catalog.\"default\",\n" +
        "sales_order_uuid uuid,\n" +
        "created_date timestamp without time zone,\n" +
        "schedule_delivery_date timestamp without time zone,\n" +
        "created_by_name character varying COLLATE pg_catalog.\"default\",\n" +
        "patient_name character varying COLLATE pg_catalog.\"default\",\n" +
        "delivery_address_line1 character varying COLLATE pg_catalog.\"default\",\n" +
        "delivery_address_line2 character varying COLLATE pg_catalog.\"default\",\n" +
        "delivery_city character varying COLLATE pg_catalog.\"default\",\n" +
        "delivery_state character varying COLLATE pg_catalog.\"default\",\n" +
        "delivery_zip character varying COLLATE pg_catalog.\"default\",\n" +
        "sales_order_status character varying COLLATE pg_catalog.\"default\",\n" +
        "patient_id_no character varying COLLATE pg_catalog.\"default\"\t\n" +
        ")")
    Flux<SalesOrderCommonSearchOutputDTO> getSalesOrderDetailsByCombinedParameters(@Param("salesOrderNo") String salesOrderNo,
                                                                                   @Param("salesOrderStatus") String salesOrderStatus,
//                                                                                   String branchID,
                                                                                   @Param("createdByName") String createdByName,
                                                                                   @Param("createdDateFromDate") String createdDateFromDate,
                                                                                   @Param("createdDateToDate") String createdDateToDate,
                                                                                   @Param("deliveryAddressLine1") String deliveryAddressLine1,
                                                                                   @Param("deliveryAddressLine2") String deliveryAddressLine2,
                                                                                   @Param("deliveryCity") String deliveryCity,
                                                                                   @Param("deliveryState") String deliveryState,
                                                                                   @Param("deliveryZip") String deliveryZip,
                                                                                   @Param("dropshipStatus") String dropshipStatus,
                                                                                   @Param("hcpcsCode") String hcpcsCode,
                                                                                   @Param("itemName") String itemName,
                                                                                   @Param("itemNo") String itemNo,
                                                                                   @Param("patientFirstName") String patientFirstName,
                                                                                   @Param("patientMiddleName") String patientMiddleName,
                                                                                   @Param("patientLastName") String patientLastName,
                                                                                   @Param("patientIDNo") String patientIDNo,
                                                                                   @Param("scheduleDeliveryFromDate") String scheduleDeliveryFromDate,
                                                                                   @Param("scheduleDeliveryToDate") String scheduleDeliveryToDate,
                                                                                   @Param("primaryPayerName") String primaryPayerName,
                                                                                   @Param("salesOrderUUID") String salesOrderUUID,
                                                                                   @Param("branchName") String branchName,
                                                                                   @Param("deliveryActualDateStart") String deliveryActualDateStart,
                                                                                   @Param("deliveryActualDateEnd") String deliveryActualDateEnd);




    @Query(value = "select sales_order_id as so_id, sales_order_no as so_no from so.t_sales_order_master where CAST(sales_order_master_uuid AS text) =:salesOrderMasterUuid")
    Mono<PatientSoIdAndSoNoOutputExtendedDTO> findSoIdAndSoNoBySoUUID(String salesOrderMasterUuid);
}
