package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_fname", table, columnPrefix + "_patient_fname"));
        columns.add(Column.aliased("patient_lname", table, columnPrefix + "_patient_lname"));
        columns.add(Column.aliased("dob", table, columnPrefix + "_dob"));
        columns.add(Column.aliased("address", table, columnPrefix + "_address"));
        columns.add(Column.aliased("city", table, columnPrefix + "_city"));
        columns.add(Column.aliased("zip", table, columnPrefix + "_zip"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("phone_no", table, columnPrefix + "_phone_no"));
        columns.add(Column.aliased("document_name", table, columnPrefix + "_document_name"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("mrno", table, columnPrefix + "_mrno"));
        columns.add(Column.aliased("date_time", table, columnPrefix + "_date_time"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("is_tagged", table, columnPrefix + "_is_tagged"));
        columns.add(Column.aliased("document_path", table, columnPrefix + "_document_path"));
        columns.add(Column.aliased("qr_code_status", table, columnPrefix + "_qr_code_status"));

        columns.add(Column.aliased("state_master_state_id", table, columnPrefix + "_state_master_state_id"));
        columns.add(Column.aliased("document_type_document_type_id", table, columnPrefix + "_document_type_document_type_id"));
        return columns;
    }
}
