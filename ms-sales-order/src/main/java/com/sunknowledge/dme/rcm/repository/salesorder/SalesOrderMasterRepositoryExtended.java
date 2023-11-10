package com.sunknowledge.dme.rcm.repository.salesorder;

import java.util.List;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusExtendedDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
