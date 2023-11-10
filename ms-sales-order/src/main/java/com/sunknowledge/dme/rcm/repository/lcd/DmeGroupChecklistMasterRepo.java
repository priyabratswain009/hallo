package com.sunknowledge.dme.rcm.repository.lcd;

import com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster;
import com.sunknowledge.dme.rcm.dto.lcd.CoverageCriteriaChecklistDTO;
import com.sunknowledge.dme.rcm.dto.lcd.DmeGroupChecklistDocumentReferenceDTO;
import com.sunknowledge.dme.rcm.dto.lcd.DocumentChecklistDTO;
import com.sunknowledge.dme.rcm.dto.lcd.HcpcsDmeGroupItemDetailsDTO;
import com.sunknowledge.dme.rcm.repository.DmeGroupChecklistMasterRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface DmeGroupChecklistMasterRepo extends DmeGroupChecklistMasterRepository {
    @Query(value = "SELECT * FROM t_dme_group_checklist_master")
    Flux<DmeGroupChecklistMaster> getGroupCheckList();

    @Query(value = "select thdgm.hcpcs_dme_id, thdgm.hcpcs_code, thdgm.dme_group_name, thdgm.dme_group_id, tsoid.sales_order_item_details_id, tsoid.sales_order_id, \n" +
        "tsoid.patient_id, tsoid.item_location_id, tsoid.sales_order_details_item_id, tsoid.sales_order_details_item_name, tsoid.sales_order_details_proc_code,\n" +
        "tdgcm.dme_group_checklist_id, tdgcm.checklist_id, tdgcm.checklist_name \n" +
        "from t_hcpcs_dme_group_master thdgm, t_sales_order_item_details tsoid, t_dme_group_checklist_master tdgcm \n" +
        "where tsoid.sales_order_details_proc_code = thdgm.hcpcs_code and tdgcm.dme_group_id = thdgm.dme_group_id \n" +
        "and tsoid.sales_order_id = :salesOrderId and thdgm.hcpcs_code = :hcpcsCode and thdgm.status = 'Active' and tsoid.status = 'Active'")
    Flux<HcpcsDmeGroupItemDetailsDTO> getHcpcsDmeGroupItemDetailsOnSalesOrderNhcpcs(@Param("salesOrderId") Long salesOrderId, @Param("hcpcsCode") String hcpcsCode);

    @Query(value = "select tdgcm.dme_group_checklist_id, tdgcm.dme_group_id, tdgcm.dme_group_name, tdgcm.checklist_id, tdgcm.checklist_name, \n" +
        "tcdrm.checklist_document_reference_id, tcdrm.document_reference_id, tcdrm.document_reference_name, thdgm.hcpcs_code\n" +
        "from t_dme_group_checklist_master tdgcm, t_checklist_document_reference_map tcdrm, t_hcpcs_dme_group_master thdgm \n" +
        "where tdgcm.checklist_id = tcdrm.checklist_id and thdgm.dme_group_id = tdgcm.dme_group_id and tdgcm.checklist_id = :checklistId and thdgm.hcpcs_code = :hcpcsCode")
    Flux<DocumentChecklistDTO> dmeGroupChecklistDocumentReferenceListOnCheckList(@Param("checklistId") Long checklistId, @Param("hcpcsCode") String hcpcsCode);

    @Query(value = "select tdgcm.dme_group_checklist_id, tdgcm.dme_group_id, tdgcm.dme_group_name, tdgcm.checklist_id, tdgcm.checklist_name,\n" +
        "tcccm.checklist_coverage_criteria_id, tcccm.coverage_criteria_id, tcccm.coverage_criteria_details\n" +
        "from t_dme_group_checklist_master tdgcm, t_checklist_coverage_criteria_map tcccm, t_hcpcs_dme_group_master thdgm\n" +
        "where tdgcm.checklist_id = tcccm.checklist_id and thdgm.dme_group_id = tdgcm.dme_group_id \n" +
        "and tdgcm.checklist_id = :checklistId and thdgm.hcpcs_code = :hcpcsCode")
    Flux<CoverageCriteriaChecklistDTO> dmeGroupChecklistCoverageCriteriaListOnCheckList(@Param("checklistId") Long checklistId, @Param("hcpcsCode") String hcpcsCode);
}
