package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ClaimsCob835MasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("claim_cob_835_master_id", table, columnPrefix + "_claim_cob_835_master_id"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("patient_member_id", table, columnPrefix + "_patient_member_id"));
        columns.add(Column.aliased("file_name", table, columnPrefix + "_file_name"));
        columns.add(Column.aliased("total_claim_charge_amount", table, columnPrefix + "_total_claim_charge_amount"));
        columns.add(Column.aliased("total_claim_payment_amount", table, columnPrefix + "_total_claim_payment_amount"));
        columns.add(Column.aliased("total_patient_responsibility_amount", table, columnPrefix + "_total_patient_responsibility_amount"));
        columns.add(Column.aliased("claim_received_date", table, columnPrefix + "_claim_received_date"));
        columns.add(Column.aliased("received_on", table, columnPrefix + "_received_on"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("patient_control_number", table, columnPrefix + "_patient_control_number"));
        columns.add(Column.aliased("payer_claim_control_number", table, columnPrefix + "_payer_claim_control_number"));
        columns.add(Column.aliased("check_or_eft_trace_number", table, columnPrefix + "_check_or_eft_trace_number"));
        columns.add(Column.aliased("check_issue_or_eft_effective_date", table, columnPrefix + "_check_issue_or_eft_effective_date"));
        columns.add(Column.aliased("credit_or_debit_flag_code", table, columnPrefix + "_credit_or_debit_flag_code"));
        columns.add(Column.aliased("payment_method_code", table, columnPrefix + "_payment_method_code"));
        columns.add(Column.aliased("crossover_carrier_name", table, columnPrefix + "_crossover_carrier_name"));
        columns.add(Column.aliased("entity_identifier_code", table, columnPrefix + "_entity_identifier_code"));
        columns.add(Column.aliased("entity_type_qualifier", table, columnPrefix + "_entity_type_qualifier"));
        columns.add(Column.aliased("payer_name", table, columnPrefix + "_payer_name"));
        columns.add(Column.aliased("payee_name", table, columnPrefix + "_payee_name"));
        columns.add(Column.aliased("payee_npi", table, columnPrefix + "_payee_npi"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("claims_cob_835_master_uuid", table, columnPrefix + "_claims_cob_835_master_uuid"));

        return columns;
    }
}
