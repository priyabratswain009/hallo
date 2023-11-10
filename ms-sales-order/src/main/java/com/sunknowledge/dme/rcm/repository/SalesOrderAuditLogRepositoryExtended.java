package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.SalesOrderOverallAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@SuppressWarnings("unused")
public interface SalesOrderAuditLogRepositoryExtended extends SalesOrderMasterAuditLogRepository {

    Flux<SalesOrderMasterAuditLog> findBySalsOdrId(Long soID);

    @Query(value="SELECT * FROM\n" +
        "(\n" +
        "(SELECT sals_odr_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Sales Order' AS section FROM so.t_sales_order_master_audit_log where sals_odr_id=:salsOdrId)\n" +
        "UNION ALL\n" +
        "(SELECT sals_ordr_item_detail_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id, \n" +
        "\t'Sales Order Item' AS section FROM so.t_sales_order_item_details_audit_log \n" +
        "  where sals_ordr_item_detail_id in (SELECT sales_order_item_details_id \n" +
        "  FROM so.t_sales_order_item_details where sales_order_id=:salsOdrId))\n" +
        "UNION ALL\n" +
        "(SELECT sals_ord_insrance_details_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Sales Order Insrance' AS section FROM so.t_sales_order_insurance_details_audit_log\n" +
        "     where sals_ord_insrance_details_id in (SELECT sales_order_insurance_details_id \n" +
        "  FROM so.t_sales_order_insurance_details where sales_order_id=:salsOdrId))\n" +
        "UNION ALL\n" +
        "(SELECT sals_ordr_fincial_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Sales Order Financial' AS section FROM so.t_sales_order_financial_details_audit_log\n" +
        "    where sals_ordr_fincial_id in (SELECT sales_order_financial_id \n" +
        "  FROM so.t_sales_order_financial_details where sales_order_id=:salsOdrId))\n" +
        "UNION ALL\n" +
        "(SELECT sales_order_doc_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Sales Order Documents' AS section FROM so.t_sales_order_documents_audit_log\n" +
        "    where sales_order_doc_id in (SELECT sales_order_document_id \n" +
        "  FROM so.t_sales_order_documents where sales_order_id=:salsOdrId))\n" +
        "UNION ALL\n" +
        "(SELECT sals_odr_clincal_detils_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Sales Order Clinical' AS section FROM so.t_sales_order_clinical_details_audit_log\n" +
        "    where sals_odr_clincal_detils_id in (SELECT sales_order_clinical_details_id \n" +
        "  FROM so.t_sales_order_clinical_details where sales_order_id=:salsOdrId))\n" +
        ") AS a order by section")
    Flux<SalesOrderOverallAuditLogDTO> findAllSectionsBySalsOdrId(Long salsOdrId);

    @Query(value="SELECT (case when dml_type = 'CREATE' then\n" +
        "         new_row_data->>'created_by_id'\n" +
        "         else\n" +
        "         new_row_data->>'updated_by_id' end) as user_id,\n" +
        "         (case when dml_type = 'CREATE' then\n" +
        "         new_row_data->>'created_by_name'\n" +
        "         else\n" +
        "         new_row_data->>'updated_by_name' end) as user_name,\n" +
        "      dml_timestamp::date as date, count(*) as count_of_so_updates, \n" +
        "      ARRAY_TO_STRING(ARRAY_AGG (distinct sales_order_no), ',') as so_numbers,\n" +
        "      ARRAY_TO_STRING(ARRAY_AGG (distinct new_row_data->>'sales_order_id'||' => '\n" +
        "      ||coalesce(sales_order_no, '')), ',') as so_ids FROM\n" +
        "      (\n" +
        "      SELECT * FROM\n" +
        "      (\n" +
        "      SELECT sals_odr_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "      'Sales Order' AS section FROM so.t_sales_order_master_audit_log where dml_timestamp::date between\n" +
        "          :fromDate and :toDate \n" +
        "      UNION ALL\n" +
        "      SELECT sals_ordr_item_detail_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id, \n" +
        "      'Sales Order Item' AS section FROM so.t_sales_order_item_details_audit_log \n" +
        "        where dml_timestamp::date between :fromDate and :toDate \n" +
        "      UNION ALL\n" +
        "      SELECT sals_ord_insrance_details_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "      'Sales Order Insrance' AS section FROM so.t_sales_order_insurance_details_audit_log\n" +
        "           where dml_timestamp::date between :fromDate and :toDate \n" +
        "      UNION ALL\n" +
        "      SELECT sals_ordr_fincial_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "      'Sales Order Financial' AS section FROM so.t_sales_order_financial_details_audit_log\n" +
        "          where dml_timestamp::date between :fromDate and :toDate \n" +
        "      UNION ALL\n" +
        "      SELECT sales_order_doc_id AS ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "      'Sales Order Documents' AS section FROM so.t_sales_order_documents_audit_log\n" +
        "          where dml_timestamp::date between :fromDate and :toDate \n" +
        "      UNION ALL\n" +
        "      SELECT sals_odr_clincal_detils_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "      'Sales Order Clinical' AS section FROM so.t_sales_order_clinical_details_audit_log\n" +
        "          where dml_timestamp::date between :fromDate and :toDate \n" +
        "      ) as a left join\n" +
        "      (\n" +
        "         select sales_order_id, sales_order_no from so.t_sales_order_master  \n" +
        "      ) as b on a.new_row_data->>'sales_order_id'=b.sales_order_id::text\n" +
        "      ) AS g WHERE dml_type <> 'DELETE' \n" +
        "      group by user_id, user_name, dml_timestamp::date, dml_type")
    Flux<UserActivityReportDTO> findUserActivityDataByDmlTimestamp(LocalDate fromDate, LocalDate toDate);

}


