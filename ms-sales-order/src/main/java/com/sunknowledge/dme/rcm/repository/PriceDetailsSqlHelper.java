package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PriceDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("price_details_id", table, columnPrefix + "_price_details_id"));
        columns.add(Column.aliased("price_table_id", table, columnPrefix + "_price_table_id"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("hcpcs", table, columnPrefix + "_hcpcs"));
        columns.add(Column.aliased("billing_code_when_secondary", table, columnPrefix + "_billing_code_when_secondary"));
        columns.add(Column.aliased("price_type", table, columnPrefix + "_price_type"));
        columns.add(Column.aliased("effective_start_date", table, columnPrefix + "_effective_start_date"));
        columns.add(Column.aliased("effective_end_date", table, columnPrefix + "_effective_end_date"));
        columns.add(Column.aliased("cmn_reqd_to_bill_status", table, columnPrefix + "_cmn_reqd_to_bill_status"));
        columns.add(Column.aliased("cmn_form_name", table, columnPrefix + "_cmn_form_name"));
        columns.add(Column.aliased("prior_auth_req_status", table, columnPrefix + "_prior_auth_req_status"));
        columns.add(Column.aliased("functional_ability_req_status", table, columnPrefix + "_functional_ability_req_status"));
        columns.add(Column.aliased("option_number", table, columnPrefix + "_option_number"));
        columns.add(Column.aliased("option_name", table, columnPrefix + "_option_name"));
        columns.add(Column.aliased("default_option_status", table, columnPrefix + "_default_option_status"));
        columns.add(Column.aliased("billing_cycle_period", table, columnPrefix + "_billing_cycle_period"));
        columns.add(Column.aliased("billing_cycle_interval", table, columnPrefix + "_billing_cycle_interval"));
        columns.add(Column.aliased("billing_in_arrears_status", table, columnPrefix + "_billing_in_arrears_status"));
        columns.add(Column.aliased("pro_rate_billing_status", table, columnPrefix + "_pro_rate_billing_status"));
        columns.add(Column.aliased("daily_billing_invoice_freq", table, columnPrefix + "_daily_billing_invoice_freq"));
        columns.add(Column.aliased("daily_billing_invoice_interval", table, columnPrefix + "_daily_billing_invoice_interval"));
        columns.add(Column.aliased("charge_amt", table, columnPrefix + "_charge_amt"));
        columns.add(Column.aliased("allowed_amt", table, columnPrefix + "_allowed_amt"));
        columns.add(Column.aliased("allowed_modifier_1", table, columnPrefix + "_allowed_modifier_1"));
        columns.add(Column.aliased("allowed_modifier_2", table, columnPrefix + "_allowed_modifier_2"));
        columns.add(Column.aliased("allowed_modifier_3", table, columnPrefix + "_allowed_modifier_3"));
        columns.add(Column.aliased("allowed_modifier_4", table, columnPrefix + "_allowed_modifier_4"));
        columns.add(Column.aliased("accept_assignment_status", table, columnPrefix + "_accept_assignment_status"));
        columns.add(Column.aliased("taxable_status", table, columnPrefix + "_taxable_status"));
        columns.add(Column.aliased("nontax_type_name", table, columnPrefix + "_nontax_type_name"));
        columns.add(Column.aliased("convert_to_purchase_last_status", table, columnPrefix + "_convert_to_purchase_last_status"));
        columns.add(Column.aliased("convert_to_purchase_charge_amt", table, columnPrefix + "_convert_to_purchase_charge_amt"));
        columns.add(Column.aliased("convert_to_purchase_allow_amt", table, columnPrefix + "_convert_to_purchase_allow_amt"));
        columns.add(Column.aliased("convert_to_purchase_modifier_1", table, columnPrefix + "_convert_to_purchase_modifier_1"));
        columns.add(Column.aliased("convert_to_purchase_modifier_2", table, columnPrefix + "_convert_to_purchase_modifier_2"));
        columns.add(Column.aliased("convert_to_purchase_modifier_3", table, columnPrefix + "_convert_to_purchase_modifier_3"));
        columns.add(Column.aliased("convert_to_purchase_modifier_4", table, columnPrefix + "_convert_to_purchase_modifier_4"));
        columns.add(Column.aliased("billing_multiplier_unit", table, columnPrefix + "_billing_multiplier_unit"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("price_details_uuid", table, columnPrefix + "_price_details_uuid"));
        columns.add(Column.aliased("price_table_name", table, columnPrefix + "_price_table_name"));
        columns.add(Column.aliased("item_no", table, columnPrefix + "_item_no"));
        columns.add(Column.aliased("item_name", table, columnPrefix + "_item_name"));
        columns.add(Column.aliased("item_uom", table, columnPrefix + "_item_uom"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("price_option_billing_period_start", table, columnPrefix + "_price_option_billing_period_start"));
        columns.add(Column.aliased("price_option_billing_period_end", table, columnPrefix + "_price_option_billing_period_end"));

        return columns;
    }
}
