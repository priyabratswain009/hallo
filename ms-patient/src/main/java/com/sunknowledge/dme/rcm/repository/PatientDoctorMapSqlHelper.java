package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientDoctorMapSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_doctor_map_id", table, columnPrefix + "_patient_doctor_map_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("doctor_first_name", table, columnPrefix + "_doctor_first_name"));
        columns.add(Column.aliased("doctor_middle_name", table, columnPrefix + "_doctor_middle_name"));
        columns.add(Column.aliased("doctor_last_name", table, columnPrefix + "_doctor_last_name"));
        columns.add(Column.aliased("doctor_name_suffix", table, columnPrefix + "_doctor_name_suffix"));
        columns.add(Column.aliased("doctor_address_line_1", table, columnPrefix + "_doctor_address_line_1"));
        columns.add(Column.aliased("doctor_address_line_2", table, columnPrefix + "_doctor_address_line_2"));
        columns.add(Column.aliased("doctor_address_city", table, columnPrefix + "_doctor_address_city"));
        columns.add(Column.aliased("doctor_address_state", table, columnPrefix + "_doctor_address_state"));
        columns.add(Column.aliased("doctor_address_zip", table, columnPrefix + "_doctor_address_zip"));
        columns.add(Column.aliased("doctor_contact_1", table, columnPrefix + "_doctor_contact_1"));
        columns.add(Column.aliased("doctor_contact_2", table, columnPrefix + "_doctor_contact_2"));
        columns.add(Column.aliased("doctor_fax", table, columnPrefix + "_doctor_fax"));
        columns.add(Column.aliased("doctor_npi_number", table, columnPrefix + "_doctor_npi_number"));
        columns.add(Column.aliased("doctor_gender", table, columnPrefix + "_doctor_gender"));
        columns.add(Column.aliased("doctor_taxonomy_code", table, columnPrefix + "_doctor_taxonomy_code"));
        columns.add(Column.aliased("doctor_taxonomy_description", table, columnPrefix + "_doctor_taxonomy_description"));
        columns.add(Column.aliased("doctor_practice_state", table, columnPrefix + "_doctor_practice_state"));
        columns.add(Column.aliased("doctor_license_number", table, columnPrefix + "_doctor_license_number"));
        columns.add(Column.aliased("patient_doctor_map_uuid", table, columnPrefix + "_patient_doctor_map_uuid"));

        return columns;
    }
}
