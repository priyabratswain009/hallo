package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ClaimSubmissionStatusSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("claim_status_id", table, columnPrefix + "_claim_status_id"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("sales_order_no", table, columnPrefix + "_sales_order_no"));
        columns.add(Column.aliased("invoice_id", table, columnPrefix + "_invoice_id"));
        columns.add(Column.aliased("invoice_no", table, columnPrefix + "_invoice_no"));
        columns.add(Column.aliased("payor_level", table, columnPrefix + "_payor_level"));
        columns.add(Column.aliased("payor_id_no", table, columnPrefix + "_payor_id_no"));
        columns.add(Column.aliased("claim_submission_data_id", table, columnPrefix + "_claim_submission_data_id"));
        columns.add(Column.aliased("int_claim_no", table, columnPrefix + "_int_claim_no"));
        columns.add(Column.aliased("patient_account_no", table, columnPrefix + "_patient_account_no"));
        columns.add(Column.aliased("payor_claim_control_no", table, columnPrefix + "_payor_claim_control_no"));
        columns.add(Column.aliased("original_claim_control_no", table, columnPrefix + "_original_claim_control_no"));
        columns.add(Column.aliased("patient_id_no", table, columnPrefix + "_patient_id_no"));
        columns.add(Column.aliased("payor", table, columnPrefix + "_payor"));
        columns.add(Column.aliased("claim_submission_date", table, columnPrefix + "_claim_submission_date"));
        columns.add(Column.aliased("submission_status", table, columnPrefix + "_submission_status"));
        columns.add(Column.aliased("submission_note", table, columnPrefix + "_submission_note"));
        columns.add(Column.aliased("response_status", table, columnPrefix + "_response_status"));
        columns.add(Column.aliased("response_status_notes", table, columnPrefix + "_response_status_notes"));
        columns.add(Column.aliased("response_status_date", table, columnPrefix + "_response_status_date"));
        columns.add(Column.aliased("response_277_record_id", table, columnPrefix + "_response_277_record_id"));
        columns.add(Column.aliased("era_status", table, columnPrefix + "_era_status"));
        columns.add(Column.aliased("era_status_notes", table, columnPrefix + "_era_status_notes"));
        columns.add(Column.aliased("era_date", table, columnPrefix + "_era_date"));
        columns.add(Column.aliased("era_835_record_id", table, columnPrefix + "_era_835_record_id"));
        columns.add(Column.aliased("resubmissin_status", table, columnPrefix + "_resubmissin_status"));
        columns.add(Column.aliased("resubmission_detail_id", table, columnPrefix + "_resubmission_detail_id"));
        columns.add(Column.aliased("resubmission_notes", table, columnPrefix + "_resubmission_notes"));
        columns.add(Column.aliased("void_status", table, columnPrefix + "_void_status"));
        columns.add(Column.aliased("void_note", table, columnPrefix + "_void_note"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("claim_submission_status_uuid", table, columnPrefix + "_claim_submission_status_uuid"));

        return columns;
    }
}
