package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ClaimsCob835DetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("claim_cob_835_detail_id", table, columnPrefix + "_claim_cob_835_detail_id"));
        columns.add(Column.aliased("service_date", table, columnPrefix + "_service_date"));
        columns.add(Column.aliased("adjudicated_procedure_code", table, columnPrefix + "_adjudicated_procedure_code"));
        columns.add(Column.aliased("adjudicated_procedure_modifier_codes", table, columnPrefix + "_adjudicated_procedure_modifier_codes"));
        columns.add(Column.aliased("charge_amount", table, columnPrefix + "_charge_amount"));
        columns.add(Column.aliased("allowed_amount", table, columnPrefix + "_allowed_amount"));
        columns.add(Column.aliased("adjustment_pr_code_1", table, columnPrefix + "_adjustment_pr_code_1"));
        columns.add(Column.aliased("adjustment_pr_code_1_amount", table, columnPrefix + "_adjustment_pr_code_1_amount"));
        columns.add(Column.aliased("adjustment_pr_code_2", table, columnPrefix + "_adjustment_pr_code_2"));
        columns.add(Column.aliased("adjustment_pr_code_2_amount", table, columnPrefix + "_adjustment_pr_code_2_amount"));
        columns.add(Column.aliased("adjustment_pr_code_3", table, columnPrefix + "_adjustment_pr_code_3"));
        columns.add(Column.aliased("adjustment_pr_code_3_amount", table, columnPrefix + "_adjustment_pr_code_3_amount"));
        columns.add(Column.aliased("adjustment_pr_code_4", table, columnPrefix + "_adjustment_pr_code_4"));
        columns.add(Column.aliased("adjustment_pr_code_4_amount", table, columnPrefix + "_adjustment_pr_code_4_amount"));
        columns.add(Column.aliased("adjustment_co_code_1", table, columnPrefix + "_adjustment_co_code_1"));
        columns.add(Column.aliased("adjustment_co_code_1_amount", table, columnPrefix + "_adjustment_co_code_1_amount"));
        columns.add(Column.aliased("adjustment_co_code_2", table, columnPrefix + "_adjustment_co_code_2"));
        columns.add(Column.aliased("adjustment_co_code_2_amount", table, columnPrefix + "_adjustment_co_code_2_amount"));
        columns.add(Column.aliased("adjustment_co_code_3", table, columnPrefix + "_adjustment_co_code_3"));
        columns.add(Column.aliased("adjustment_co_code_3_amount", table, columnPrefix + "_adjustment_co_code_3_amount"));
        columns.add(Column.aliased("adjustment_co_code_4", table, columnPrefix + "_adjustment_co_code_4"));
        columns.add(Column.aliased("adjustment_co_code_4_amount", table, columnPrefix + "_adjustment_co_code_4_amount"));
        columns.add(Column.aliased("adjustment_cr_code_1", table, columnPrefix + "_adjustment_cr_code_1"));
        columns.add(Column.aliased("adjustment_cr_code_1_amount", table, columnPrefix + "_adjustment_cr_code_1_amount"));
        columns.add(Column.aliased("adjustment_cr_code_2", table, columnPrefix + "_adjustment_cr_code_2"));
        columns.add(Column.aliased("adjustment_cr_code_2_amount", table, columnPrefix + "_adjustment_cr_code_2_amount"));
        columns.add(Column.aliased("adjustment_cr_code_3", table, columnPrefix + "_adjustment_cr_code_3"));
        columns.add(Column.aliased("adjustment_cr_code_3_amount", table, columnPrefix + "_adjustment_cr_code_3_amount"));
        columns.add(Column.aliased("adjustment_cr_code_4", table, columnPrefix + "_adjustment_cr_code_4"));
        columns.add(Column.aliased("adjustment_cr_code_4_amount", table, columnPrefix + "_adjustment_cr_code_4_amount"));
        columns.add(Column.aliased("adjustment_oa_code_1", table, columnPrefix + "_adjustment_oa_code_1"));
        columns.add(Column.aliased("adjustment_oa_code_1_amount", table, columnPrefix + "_adjustment_oa_code_1_amount"));
        columns.add(Column.aliased("adjustment_oa_code_2", table, columnPrefix + "_adjustment_oa_code_2"));
        columns.add(Column.aliased("adjustment_oa_code_2_amount", table, columnPrefix + "_adjustment_oa_code_2_amount"));
        columns.add(Column.aliased("adjustment_oa_code_3", table, columnPrefix + "_adjustment_oa_code_3"));
        columns.add(Column.aliased("adjustment_oa_code_3_amount", table, columnPrefix + "_adjustment_oa_code_3_amount"));
        columns.add(Column.aliased("adjustment_oa_code_4", table, columnPrefix + "_adjustment_oa_code_4"));
        columns.add(Column.aliased("adjustment_oa_code_4_amount", table, columnPrefix + "_adjustment_oa_code_4_amount"));
        columns.add(Column.aliased("adjustment_pi_code_1", table, columnPrefix + "_adjustment_pi_code_1"));
        columns.add(Column.aliased("adjustment_pi_code_1_amount", table, columnPrefix + "_adjustment_pi_code_1_amount"));
        columns.add(Column.aliased("adjustment_pi_code_2", table, columnPrefix + "_adjustment_pi_code_2"));
        columns.add(Column.aliased("adjustment_pi_code_2_amount", table, columnPrefix + "_adjustment_pi_code_2_amount"));
        columns.add(Column.aliased("adjustment_pi_code_3", table, columnPrefix + "_adjustment_pi_code_3"));
        columns.add(Column.aliased("adjustment_pi_code_3_amount", table, columnPrefix + "_adjustment_pi_code_3_amount"));
        columns.add(Column.aliased("adjustment_pi_code_4", table, columnPrefix + "_adjustment_pi_code_4"));
        columns.add(Column.aliased("adjustment_pi_code_4_amount", table, columnPrefix + "_adjustment_pi_code_4_amount"));
        columns.add(Column.aliased("provider_payment_amount", table, columnPrefix + "_provider_payment_amount"));
        columns.add(Column.aliased("claim_cob_835_master_id", table, columnPrefix + "_claim_cob_835_master_id"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("service_date_to", table, columnPrefix + "_service_date_to"));
        columns.add(Column.aliased("unit_count", table, columnPrefix + "_unit_count"));
        columns.add(Column.aliased("claims_cob_835_details_uuid", table, columnPrefix + "_claims_cob_835_details_uuid"));

        return columns;
    }
}
