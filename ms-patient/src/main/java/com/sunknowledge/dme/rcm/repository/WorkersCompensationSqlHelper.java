package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class WorkersCompensationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("workers_compensation_id", table, columnPrefix + "_workers_compensation_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("insured_employer", table, columnPrefix + "_insured_employer"));
        columns.add(Column.aliased("workers_compensation_payer_id_number", table, columnPrefix + "_workers_compensation_payer_id_number"));
        columns.add(Column.aliased("workers_compensation_plan_name", table, columnPrefix + "_workers_compensation_plan_name"));
        columns.add(Column.aliased("workers_compensation_additional_dtls", table, columnPrefix + "_workers_compensation_additional_dtls"));
        columns.add(
            Column.aliased("workers_compensation_claim_filling_code", table, columnPrefix + "_workers_compensation_claim_filling_code")
        );
        columns.add(Column.aliased("workers_compensation_tpl_code", table, columnPrefix + "_workers_compensation_tpl_code"));
        columns.add(Column.aliased("workers_compensation_tpl_name", table, columnPrefix + "_workers_compensation_tpl_name"));
        columns.add(Column.aliased("wc_property_casualty_agency_claim_no", table, columnPrefix + "_wc_property_casualty_agency_claim_no"));
        columns.add(Column.aliased("wc_carrier_id", table, columnPrefix + "_wc_carrier_id"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("workers_compensation_uuid", table, columnPrefix + "_workers_compensation_uuid"));
        columns.add(Column.aliased("employer_address_line_1", table, columnPrefix + "_employer_address_line_1"));
        columns.add(Column.aliased("employer_address_line_2", table, columnPrefix + "_employer_address_line_2"));
        columns.add(Column.aliased("employer_city", table, columnPrefix + "_employer_city"));
        columns.add(Column.aliased("employer_state", table, columnPrefix + "_employer_state"));
        columns.add(Column.aliased("employer_country", table, columnPrefix + "_employer_country"));
        columns.add(Column.aliased("employer_zip", table, columnPrefix + "_employer_zip"));
        columns.add(Column.aliased("employer_contact_no_1", table, columnPrefix + "_employer_contact_no_1"));
        columns.add(Column.aliased("employer_contact_no_2", table, columnPrefix + "_employer_contact_no_2"));
        columns.add(Column.aliased("employer_fax", table, columnPrefix + "_employer_fax"));
        columns.add(Column.aliased("employer_efax", table, columnPrefix + "_employer_efax"));
        columns.add(Column.aliased("employer_email", table, columnPrefix + "_employer_email"));
        columns.add(Column.aliased("relationship", table, columnPrefix + "_relationship"));
        columns.add(Column.aliased("mode_of_contact", table, columnPrefix + "_mode_of_contact"));

        return columns;
    }
}
