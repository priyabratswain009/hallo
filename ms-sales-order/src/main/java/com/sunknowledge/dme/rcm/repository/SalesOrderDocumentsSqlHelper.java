package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class SalesOrderDocumentsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_dob", table, columnPrefix + "_patient_dob"));
        columns.add(Column.aliased("patient_dod", table, columnPrefix + "_patient_dod"));
        columns.add(Column.aliased("patient_ssn", table, columnPrefix + "_patient_ssn"));
        columns.add(Column.aliased("qmb_status", table, columnPrefix + "_qmb_status"));
        columns.add(Column.aliased("patient_gender", table, columnPrefix + "_patient_gender"));
        columns.add(Column.aliased("patient_height", table, columnPrefix + "_patient_height"));
        columns.add(Column.aliased("patient_weight", table, columnPrefix + "_patient_weight"));
        columns.add(Column.aliased("patient_contact_1", table, columnPrefix + "_patient_contact_1"));
        columns.add(Column.aliased("patient_contact_2", table, columnPrefix + "_patient_contact_2"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("hipaa_on_file_status", table, columnPrefix + "_hipaa_on_file_status"));
        columns.add(Column.aliased("branch_id", table, columnPrefix + "_branch_id"));
        columns.add(Column.aliased("branch_name", table, columnPrefix + "_branch_name"));
        columns.add(Column.aliased("document_type_id", table, columnPrefix + "_document_type_id"));
        columns.add(Column.aliased("document_type_name", table, columnPrefix + "_document_type_name"));
        columns.add(Column.aliased("document_date", table, columnPrefix + "_document_date"));
        columns.add(Column.aliased("document_note", table, columnPrefix + "_document_note"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("sales_order_document_id", table, columnPrefix + "_sales_order_document_id"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("fax", table, columnPrefix + "_fax"));
        columns.add(Column.aliased("document_title", table, columnPrefix + "_document_title"));
        columns.add(Column.aliased("document_name", table, columnPrefix + "_document_name"));
        columns.add(Column.aliased("scan_by", table, columnPrefix + "_scan_by"));
        columns.add(Column.aliased("file_upload_path", table, columnPrefix + "_file_upload_path"));
        columns.add(Column.aliased("upload_date", table, columnPrefix + "_upload_date"));
        columns.add(Column.aliased("upload_by", table, columnPrefix + "_upload_by"));
        columns.add(Column.aliased("scan_date", table, columnPrefix + "_scan_date"));
        columns.add(Column.aliased("review_status", table, columnPrefix + "_review_status"));
        columns.add(Column.aliased("review_reason", table, columnPrefix + "_review_reason"));
        columns.add(Column.aliased("review_date", table, columnPrefix + "_review_date"));
        columns.add(Column.aliased("review_by", table, columnPrefix + "_review_by"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("sales_order_no", table, columnPrefix + "_sales_order_no"));
        columns.add(Column.aliased("sales_order_creation_date", table, columnPrefix + "_sales_order_creation_date"));
        columns.add(Column.aliased("sales_order_documents_uuid", table, columnPrefix + "_sales_order_documents_uuid"));
        columns.add(Column.aliased("patient_middle_name", table, columnPrefix + "_patient_middle_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));

        return columns;
    }
}
