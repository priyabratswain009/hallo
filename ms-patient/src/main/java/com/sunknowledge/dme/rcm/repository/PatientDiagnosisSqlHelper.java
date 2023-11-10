package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientDiagnosisSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_diagnosis_id", table, columnPrefix + "_patient_diagnosis_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("diagnosis_code_type", table, columnPrefix + "_diagnosis_code_type"));
        columns.add(Column.aliased("diagnosis_code", table, columnPrefix + "_diagnosis_code"));
        columns.add(Column.aliased("diagnosis_description", table, columnPrefix + "_diagnosis_description"));
        columns.add(Column.aliased("effective_date", table, columnPrefix + "_effective_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("patient_diagnosis_uuid", table, columnPrefix + "_patient_diagnosis_uuid"));

        return columns;
    }
}
