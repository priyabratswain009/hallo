package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientDocumentSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_document_id", table, columnPrefix + "_patient_document_id"));
        columns.add(Column.aliased("patient_document_no", table, columnPrefix + "_patient_document_no"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_uuid", table, columnPrefix + "_patient_uuid"));
        columns.add(Column.aliased("patient_id_no", table, columnPrefix + "_patient_id_no"));
        columns.add(Column.aliased("patient_document_type", table, columnPrefix + "_patient_document_type"));
        columns.add(Column.aliased("patient_document_repository_name", table, columnPrefix + "_patient_document_repository_name"));
        columns.add(Column.aliased("patient_document_name", table, columnPrefix + "_patient_document_name"));
        columns.add(Column.aliased("patient_document_description", table, columnPrefix + "_patient_document_description"));
        columns.add(Column.aliased("patient_document_status", table, columnPrefix + "_patient_document_status"));
        columns.add(Column.aliased("uploaded_by_id", table, columnPrefix + "_uploaded_by_id"));
        columns.add(Column.aliased("uploaded_by_name", table, columnPrefix + "_uploaded_by_name"));
        columns.add(Column.aliased("uploaded_date", table, columnPrefix + "_uploaded_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("patient_document_uuid", table, columnPrefix + "_patient_document_uuid"));

        return columns;
    }
}
