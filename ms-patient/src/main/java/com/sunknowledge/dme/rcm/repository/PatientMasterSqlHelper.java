package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_middle_name", table, columnPrefix + "_patient_middle_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("dob", table, columnPrefix + "_dob"));
        columns.add(Column.aliased("gender", table, columnPrefix + "_gender"));
        columns.add(Column.aliased("ssn", table, columnPrefix + "_ssn"));
        columns.add(Column.aliased("tax_zone_id", table, columnPrefix + "_tax_zone_id"));
        columns.add(Column.aliased("discount_percent", table, columnPrefix + "_discount_percent"));
        columns.add(Column.aliased("pos_id", table, columnPrefix + "_pos_id"));
        columns.add(Column.aliased("prior_system_key", table, columnPrefix + "_prior_system_key"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("branch_id", table, columnPrefix + "_branch_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("patient_master_uuid", table, columnPrefix + "_patient_master_uuid"));
        columns.add(Column.aliased("patient_id_number", table, columnPrefix + "_patient_id_number"));
        columns.add(Column.aliased("address_line_1", table, columnPrefix + "_address_line_1"));
        columns.add(Column.aliased("address_line_2", table, columnPrefix + "_address_line_2"));
        columns.add(Column.aliased("city", table, columnPrefix + "_city"));
        columns.add(Column.aliased("state", table, columnPrefix + "_state"));
        columns.add(Column.aliased("country", table, columnPrefix + "_country"));
        columns.add(Column.aliased("zip", table, columnPrefix + "_zip"));
        columns.add(Column.aliased("contact_no_1", table, columnPrefix + "_contact_no_1"));
        columns.add(Column.aliased("contact_no_2", table, columnPrefix + "_contact_no_2"));
        columns.add(Column.aliased("fax", table, columnPrefix + "_fax"));
        columns.add(Column.aliased("efax", table, columnPrefix + "_efax"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("mode_of_contact", table, columnPrefix + "_mode_of_contact"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("branch_name", table, columnPrefix + "_branch_name"));
        columns.add(Column.aliased("billing_address_line_1", table, columnPrefix + "_billing_address_line_1"));
        columns.add(Column.aliased("billing_address_line_2", table, columnPrefix + "_billing_address_line_2"));
        columns.add(Column.aliased("billing_address_city", table, columnPrefix + "_billing_address_city"));
        columns.add(Column.aliased("billing_address_state", table, columnPrefix + "_billing_address_state"));
        columns.add(Column.aliased("billing_address_zip", table, columnPrefix + "_billing_address_zip"));
        columns.add(Column.aliased("caregiver_name", table, columnPrefix + "_caregiver_name"));
        columns.add(Column.aliased("caregiver_contact", table, columnPrefix + "_caregiver_contact"));
        columns.add(Column.aliased("caregiver_relatinship_patient", table, columnPrefix + "_caregiver_relatinship_patient"));
        columns.add(Column.aliased("tax_zone_name", table, columnPrefix + "_tax_zone_name"));
        columns.add(Column.aliased("tax_rate", table, columnPrefix + "_tax_rate"));
        columns.add(Column.aliased("patient_dod", table, columnPrefix + "_patient_dod"));

        return columns;
    }
}
