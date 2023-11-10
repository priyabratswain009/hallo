package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientClinicalInformationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_clinical_information_id", table, columnPrefix + "_patient_clinical_information_id"));
        columns.add(Column.aliased("height", table, columnPrefix + "_height"));
        columns.add(Column.aliased("weight", table, columnPrefix + "_weight"));
        columns.add(Column.aliased("functional_abilities", table, columnPrefix + "_functional_abilities"));
        columns.add(Column.aliased("capture_date", table, columnPrefix + "_capture_date"));
        columns.add(Column.aliased("infection_condition_status", table, columnPrefix + "_infection_condition_status"));
        columns.add(Column.aliased("diabetes_status", table, columnPrefix + "_diabetes_status"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("patient_clinical_information_uuid", table, columnPrefix + "_patient_clinical_information_uuid"));

        return columns;
    }
}
