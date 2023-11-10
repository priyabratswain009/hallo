package com.sunknowledge.dme.rcm.repository.salesorder;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.dto.cmn.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.par.SalesOrderInsurancePriceDetails;

import reactor.core.publisher.Mono;

public interface SalesOrderInsuranceDetailsRepo extends SalesOrderInsuranceDetailsRepository {
    @Query("select tsoid.sales_order_insurance_details_id, tsoid.sales_order_id, tsoid.patient_id, tsoid.primary_insurer_id, tsoid.primary_insurer_name, "
        + " tsoid.primary_insurer_policy_no, tsoid.primary_insurer_patient_id_number, tsoid.primary_insurer_effective_date,"
        + " tsoid.primary_insurer_verification_date, tsoid.primary_insurer_pay_percentage, tsoid.sales_order_no,"
        + " tpd.price_details_id, tpd.price_table_id, tpd.item_id, tpd.hcpcs, tpd.price_type, tpd.effective_start_date, tpd.effective_end_date,"
        + " tpd.prior_auth_req_status, tpd.option_number, tpd.option_name, tpd.charge_amt, tpd.allowed_amt, tpd.price_table_name, tpd.item_no, tpd.item_name"
        + " from t_sales_order_insurance_details tsoid, t_price_details tpd"
        + " where tsoid.price_table_id = tpd.price_table_id and tsoid.sales_order_id = :salesOrderId and tpd.hcpcs = :hcpcsCode and tsoid.status = 'Active' and tpd.status = 'Active'")
    Mono<SalesOrderInsurancePriceDetails> getSalesOrderInsurancePriceDetailsOnSalesOrderhcpcsCode(@Param("salesOrderId") Long salesOrderId, @Param("hcpcsCode") String hcpcsCode);

    @Query("select * from t_sales_order_insurance_details tsoid where tsoid.sales_order_id = :salesOrderId")
    Mono<SalesOrderInsuranceDetailsDTO> getSalesOrderInsurancePriceDetailsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);

    @Query("select tsoid.sales_order_id,tsoid.primary_insurer_name,tsoid.primary_insurer_address_line_1,tsoid.primary_insurer_address_line_2,tsoid.primary_insurer_city,"
    		+ "tsoid.primary_insurer_state,tsoid.primary_insurer_zip,tsoid.primary_insurer_contact_1,tsoid.primary_insurer_fax from t_sales_order_insurance_details tsoid"
    		+ " where tsoid.sales_order_id = :salesOrderId")
    Mono<InsuranceMasterDTO> getsalesOrderInsuranceBySOIDForReport(@Param("salesOrderId") Long salesOrderId);
}
