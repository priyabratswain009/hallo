package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceSearchDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalesOrderInsuranceDetailsSearchRepositoryExtended extends SalesOrderInsuranceDetailsRepository {
    @Query(value = "select * from so.InsuranceIDLookup(:insuranceName) as\n" +
        "(primary_insurer_id bigint, primary_insurer_name character varying, primary_insurance_group_name character varying,  " +
        "primary_insurance_state_name character varying)\n")
    Flux<SalesOrderInsuranceSearchDetailsDTO> findSOInsuranceDetailsByInsuranceName(@Param("insuranceName") String insuranceName);

    @Query(value = "select * from so.so_by_insurance_id (:insuranceId) as \n" +
        "(sid bigint,sno character varying,p_first_name character varying, p_middle_name character varying," +
        "p_last_name character varying,p_dob date,p_Insurer_name character varying, p_addres_line1 character varying, " +
        "p_address_line2 character varying, Status character varying, o_status character varying," +
        "c_date timestamp without time zone,c_by_id bigint,c_by_name character varying, " +
        "primary_insurer_id bigint)\n")
    Flux<SalesOrderMasterSearchDetailsDTO> findSODetailsByInsuranceId(Long insuranceId);

    @Query(value = "select * from so.t_sales_order_insurance_details tsoid where tsoid.sales_order_id = :salesOrderID")
    Mono<SalesOrderInsuranceDetails> findBySalesOrderId(@Param("salesOrderID") Long salesOrderID);
}
