package com.sunknowledge.dme.rcm.repository.par;

import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.dto.RenwalOrExpiringPAR.RenwalOrExpiringPARDTO;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.repository.ParMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PARCustomOutputDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ParMasterRepo extends ParMasterRepository {
    @Query("select * from t_par_master tpm where tpm.patient_id = :patientId")
    Flux<ParMaster> getParMasterOnSalesOrderPatient(@Param("patientId") Long patientId);

    @Query("select tpm.* from t_par_master tpm, t_par_details tpd, t_par_so_map tpsm where tpm.par_id = tpd.par_id and tpd.par_id = tpsm.par_id  \n" +
        "and tpsm.so_id = :salesOrderId and tpm.status = 'Active' and tpd.status = 'Active' and tpsm.status = 'Active' and tpm.par_status = 'Initiated'")
    Mono<ParMaster> getParMasterOnSalesOrderHcpcsCode(@Param("salesOrderId") Long salesOrderId);

    @Query("select * from t_par_master tpm where tpm.par_id = :parId")
    Mono<ParMaster> getParMasterOnParId(@Param("parId") Long parId);

    @Query("select tpm.par_id from t_par_master tpm where tpm.patient_id = :patientId and tpm.par_status in('Active')")
    Flux<Long> getParIdsOnPatientNparStatus(@Param("patientId") Long patientId);

//    @Query("select tpm.par_id from t_par_master tpm where tpm.patient_id = :patientId and tpm.par_status in('Active')")
//    Flux<Long> getParIdsOnPatientNparStatus(@Param("patientId") Long patientId, @Param("parStatus") String parStatus);

    @Query("select count(*) from t_par_master tpm, t_par_details tpd where tpm.par_id = tpd.par_id and tpd.hcpcs_code = :hcpcsCode and tpd.par_id in(:parIds)")
    Mono<Integer> getParCountsOnParIdsNhcpcsCode(@Param("hcpcsCode") String hcpcsCode, @Param("parIds") List<Long> parIds);

    @Query("select count(*) from t_par_master tpm, t_par_details tpd where tpm.par_id = tpd.par_id and tpm.patient_id = :patientId and tpd.hcpcs_code = :hcpcsCode and tpm.par_status = 'Active'")
    Mono<Integer> getParCountsOnPatientIdsNhcpcsCode( @Param("patientId") Long patientId, @Param("hcpcsCode") String hcpcsCode);

    @Query("select tpm.* from t_par_master tpm, t_par_so_map tpsm where tpm.par_id = tpsm.par_id and tpsm.so_id = :salesOrderId and tpm.par_status = :parStatus")
    Mono<ParMaster> getParMasterOnSalesOrderNStatus( @Param("salesOrderId") Long salesOrderId, @Param("parStatus") String parStatus);

    @Query("select tpm.* from t_par_master tpm where tpm.patient_id = :patientId and tpm.par_status = :parStatus")
    Flux<ParMaster> getParMasterOnSalesOrderPatientNstatus( @Param("patientId") Long patientId, @Param("parStatus") String parStatus);

    @Query("select * from t_par_master tpm, t_par_details tpd where tpm.par_id = tpd.par_id and tpd.hcpcs_code = :hcpcsCode and tpm.par_id in(:parIds)")
    Mono<ParMaster> getParMasterOnParIdsNhcpcsCode( @Param("hcpcsCode") String hcpcsCode, @Param("parIds") List<Long> parIds);

    @Query("select * from so.expiringpar()\r\n"
    		+ "AS\r\n"
    		+ "(\r\n"
    		+ "parid bigint,\r\n"
    		+ "	parno character varying,\r\n"
    		+ "	soid bigint,\r\n"
    		+ "	sono character varying,\r\n"
    		+ "	itemid bigint,\r\n"
    		+ "	expdate timestamp without time zone,\r\n"
    		+ "	dateofservice timestamp without time zone,\r\n"
    		+ "	patientid bigint,\r\n"
    		+ "	insuranceid bigint\r\n"
    		+ ")\r\n"
    		+ "where dateofservice is not NULL")
    Flux<RenwalOrExpiringPARDTO> getRenwalOrExpiringPARList();

    @Query("select * from so.parsearch(:patientidno,:hcpcsno,:itemno,:soId,:dos)\r\n"
    		+ "AS\r\n"
    		+ "(\r\n"
    		+ "	isparrequired character varying COLLATE pg_catalog.\"default\", 	\r\n"
    		+ "	paruuid uuid,\r\n"
    		+ "	paridno character varying COLLATE pg_catalog.\"default\", \r\n"
    		+ "    parno character varying COLLATE pg_catalog.\"default\", \r\n"
    		+ "    effectivestartdate date, \r\n"
    		+ "    expirationdate date, 	\r\n"
    		+ "	log_in_status character varying COLLATE pg_catalog.\"default\",\r\n"
    		+ "    patientfirstname  character varying COLLATE pg_catalog.\"default\",\r\n"
    		+ "    patientlastname  character varying COLLATE pg_catalog.\"default\",\r\n"
    		+ "    patientdob date,\r\n"
    		+ "	patientgender  character varying COLLATE pg_catalog.\"default\",    \r\n"
    		+ "    payeridno character varying COLLATE pg_catalog.\"default\",   \r\n"
    		+ "    policynumber character varying COLLATE pg_catalog.\"default\",     \r\n"
    		+ "	policystartdate date, \r\n"
    		+ "	policyenddate date, 	\r\n"
    		+ "	itemno character varying COLLATE pg_catalog.\"default\",   \r\n"
    		+ "	itemname character varying COLLATE pg_catalog.\"default\", 	\r\n"
    		+ "	hcpcscode character varying COLLATE pg_catalog.\"default\", 	\r\n"
    		+ "	salesOrderId character varying COLLATE pg_catalog.\"default\"	\r\n"
    		+ ")")
    Mono<ParSearchForCreate> validateParSearchProc(@Param("patientidno") String patientidno, @Param("hcpcsno") String hcpcsno,
                                                   @Param("itemno") String itemno, @Param("soId") Long soId, @Param("dos") String dos);

    @Query("select * from so.initiatedparsearchbyso(:sono)\r\n"
    		+ "AS\r\n"
    		+ "(	\r\n"
    		+ "    parnumber character varying COLLATE pg_catalog.\"default\"\r\n"
    		+ ")")
    Mono<String> initiatedparsearchbyso(@Param("sono") String sono);

    @Query("select * from so.get_paruuid_from_initiatedparsearchbyso(:sono,:insuranceid)\r\n"
    		+ "AS\r\n"
    		+ "(	\r\n"
    		+ "    paruuid UUID \n"
    		+ ")")
    Mono<String> initiatedparsearchbysoreturnparuuid(@Param("sono") String sono,@Param("insuranceid") Long insuranceid);

    //Function Calling to get PAR No
    @Query("select * from so.get_par_no()")
    Mono<String> getParNo();

    @Query("select * from t_par_master tpm where tpm.par_uuid = :parUuid")
    Mono<ParMaster> getParMasterOnParUUID(@Param("parUuid") UUID parUuid);

    @Query(value = "select tpd.prior_auth_req_status as prior_auth_req_status from so.t_price_details tpd where tpd.price_table_id=:priceTableId and tpd.item_no=:itemNo and tpd.hcpcs=:hcpcsNo " +
        "and :dos>= tpd.effective_start_date and (tpd.effective_end_date is not null or \n" +
        ":dos<=tpd.effective_end_date is not null)\n" +
        "and lower(tpd.status)='active'"
    )
    Mono<String> isPARRequired(@Param("hcpcsNo") String hcpcsNo,
                               @Param("itemNo") String itemNo, @Param("dos") LocalDate dos,@Param("priceTableId") Long priceTableId);

    @Query(value = "SELECT b.hcpcs_code, b.description, b.item_quantity, a.effective_start_date, a.expiration_date\n" +
        "FROM so.t_par_master a\n" +
        "LEFT JOIN so.t_par_details b ON a.par_id = b.par_id\n" +
        "LEFT JOIN so.t_par_so_map c ON a.par_id =c.par_id\n" +
        "where c.so_id = :soId\n" +
        "ORDER BY b.par_details_id;")
    Flux<PARCustomOutputDTO> getPARDetailsData(@Param("soId") Long soId);

    @Query(value = "select * from so.t_par_master where par_id = :parId and lower(par_status)='active' and lower(status) ='active'")
    Mono<ParMaster> getParMasterDataByParId(@Param("parId") Long parId);

    @Query(value = "SELECT a.*\n" +
        "FROM so.t_par_master a\n" +
        "inner join so.t_par_details b on a.par_id = b.par_id \n" +
        "inner join so.t_par_so_map c on a.par_id = c.par_id \n" +
        "where b.hcpcs_code = :hcpcsCode and c.so_id = :soId and lower(a.status) = 'active'")
    Mono<ParMaster> getParMasterDataByHcpcsCode(@Param("hcpcsCode") String hcpcsCode, @Param("soId") Long soId);
    
    @Query("select * from t_par_master tpm where tpm.par_no = :parNo")
    Mono<ParMaster> getParMasterOnParNo(@Param("parNo") String parNo);

    @Query("select * from t_par_master tpm where tpm.par_id_no = :parIdNno")
    Mono<ParMaster> getParMasterOnParIdNo(@Param("parIdNno") String parIdNno);

}
