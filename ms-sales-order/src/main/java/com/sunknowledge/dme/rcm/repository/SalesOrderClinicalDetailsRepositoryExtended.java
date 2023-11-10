package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderClinicalDetailsRepositoryExtended extends SalesOrderClinicalDetailsRepository{
    Flux<SalesOrderClinicalDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderClinicalDetails> getSOClinicalByUUID(UUID sOClinicalUUID);

    @Query(value="select so.get_t_sales_order_clinical_details_id_by_uuid(:sOClinicalUUID)")
    Mono<Long> getIDByUUID(@Param("sOClinicalUUID") UUID sOClinicalUUID);

    @Query(value = "select * from masterdata.mv_t_doctor_master mtdm where mtdm.npi_number = :doctorNpiNumber")
    Mono<Object> getDoctorMasterByNpi(@Param("doctorNpiNumber") String doctorNpiNumber);
}
