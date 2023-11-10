package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientInsuranceSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("patient_insurance_id", table, columnPrefix + "_patient_insurance_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("payer_level", table, columnPrefix + "_payer_level"));
        columns.add(Column.aliased("insurance_name", table, columnPrefix + "_insurance_name"));
        columns.add(Column.aliased("insurance_id", table, columnPrefix + "_insurance_id"));
        columns.add(Column.aliased("coverage_type", table, columnPrefix + "_coverage_type"));
        columns.add(Column.aliased("payer_contact", table, columnPrefix + "_payer_contact"));
        columns.add(Column.aliased("policy_num", table, columnPrefix + "_policy_num"));
        columns.add(Column.aliased("policy_group_num", table, columnPrefix + "_policy_group_num"));
        columns.add(Column.aliased("policy_start_date", table, columnPrefix + "_policy_start_date"));
        columns.add(Column.aliased("policy_end_date", table, columnPrefix + "_policy_end_date"));
        columns.add(Column.aliased("pay_percentage", table, columnPrefix + "_pay_percentage"));
        columns.add(Column.aliased("deductable_amt", table, columnPrefix + "_deductable_amt"));
        columns.add(Column.aliased("relationship", table, columnPrefix + "_relationship"));
        columns.add(Column.aliased("insured_first_name", table, columnPrefix + "_insured_first_name"));
        columns.add(Column.aliased("insured_middle_name", table, columnPrefix + "_insured_middle_name"));
        columns.add(Column.aliased("insured_suffix", table, columnPrefix + "_insured_suffix"));
        columns.add(Column.aliased("insured_dob", table, columnPrefix + "_insured_dob"));
        columns.add(Column.aliased("insured_ssn", table, columnPrefix + "_insured_ssn"));
        columns.add(Column.aliased("insured_gender", table, columnPrefix + "_insured_gender"));
        columns.add(Column.aliased("always_crossover_status", table, columnPrefix + "_always_crossover_status"));
        columns.add(Column.aliased("claim_codes", table, columnPrefix + "_claim_codes"));
        columns.add(Column.aliased("addtnl_claim_info", table, columnPrefix + "_addtnl_claim_info"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("insured_last_name", table, columnPrefix + "_insured_last_name"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("patient_insurance_uuid", table, columnPrefix + "_patient_insurance_uuid"));
        columns.add(Column.aliased("member_id", table, columnPrefix + "_member_id"));
        columns.add(Column.aliased("patient_relationship_insured", table, columnPrefix + "_patient_relationship_insured"));
        columns.add(Column.aliased("patient_condition_employment", table, columnPrefix + "_patient_condition_employment"));
        columns.add(Column.aliased("patient_condition_auto_accident", table, columnPrefix + "_patient_condition_auto_accident"));
        columns.add(Column.aliased("patient_condition_other_accident", table, columnPrefix + "_patient_condition_other_accident"));
        columns.add(Column.aliased("insurance_payer_id_no", table, columnPrefix + "_insurance_payer_id_no"));
        columns.add(Column.aliased("expiration_status", table, columnPrefix + "_expiration_status"));
        columns.add(Column.aliased("insurer_address_line_1", table, columnPrefix + "_insurer_address_line_1"));
        columns.add(Column.aliased("insurer_address_line_2", table, columnPrefix + "_insurer_address_line_2"));
        columns.add(Column.aliased("insurer_city", table, columnPrefix + "_insurer_city"));
        columns.add(Column.aliased("insurer_state", table, columnPrefix + "_insurer_state"));
        columns.add(Column.aliased("insurer_zip", table, columnPrefix + "_insurer_zip"));
        columns.add(Column.aliased("insurer_contact_1", table, columnPrefix + "_insurer_contact_1"));
        columns.add(Column.aliased("insurer_fax", table, columnPrefix + "_insurer_fax"));

        return columns;
    }
}
