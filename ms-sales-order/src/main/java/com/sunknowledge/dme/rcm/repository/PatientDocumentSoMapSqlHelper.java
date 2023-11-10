package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientDocumentSoMapSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_document_so_map_id", table, columnPrefix + "_patient_document_so_map_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_id_no", table, columnPrefix + "_patient_id_no"));
        columns.add(Column.aliased("patient_document_id", table, columnPrefix + "_patient_document_id"));
        columns.add(Column.aliased("patient_document_no", table, columnPrefix + "_patient_document_no"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("so_no", table, columnPrefix + "_so_no"));
        columns.add(Column.aliased("uploaded_by_id", table, columnPrefix + "_uploaded_by_id"));
        columns.add(Column.aliased("uploaded_by_name", table, columnPrefix + "_uploaded_by_name"));
        columns.add(Column.aliased("uploaded_date", table, columnPrefix + "_uploaded_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("patient_document_so_map_uuid", table, columnPrefix + "_patient_document_so_map_uuid"));

        return columns;
    }
}
