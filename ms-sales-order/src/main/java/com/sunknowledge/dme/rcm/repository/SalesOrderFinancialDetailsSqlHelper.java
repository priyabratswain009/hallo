package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class SalesOrderFinancialDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("sales_order_financial_id", table, columnPrefix + "_sales_order_financial_id"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("item_name", table, columnPrefix + "_item_name"));
        columns.add(Column.aliased("item_proc_code", table, columnPrefix + "_item_proc_code"));
        columns.add(Column.aliased("charged_amount", table, columnPrefix + "_charged_amount"));
        columns.add(Column.aliased("allowed_amount", table, columnPrefix + "_allowed_amount"));
        columns.add(Column.aliased("primary_insurer_id", table, columnPrefix + "_primary_insurer_id"));
        columns.add(Column.aliased("primary_insurer_name", table, columnPrefix + "_primary_insurer_name"));
        columns.add(Column.aliased("primary_insurer_status", table, columnPrefix + "_primary_insurer_status"));
        columns.add(Column.aliased("primary_insurer_coverage_percentage", table, columnPrefix + "_primary_insurer_coverage_percentage"));
        columns.add(Column.aliased("primary_insurer_coverage_amount", table, columnPrefix + "_primary_insurer_coverage_amount"));
        columns.add(Column.aliased("primary_insurer_deductible_amount", table, columnPrefix + "_primary_insurer_deductible_amount"));
        columns.add(Column.aliased("primary_insurer_payment", table, columnPrefix + "_primary_insurer_payment"));
        columns.add(Column.aliased("primary_insurer_balance_amount", table, columnPrefix + "_primary_insurer_balance_amount"));
        columns.add(Column.aliased("secondary_insurer_id", table, columnPrefix + "_secondary_insurer_id"));
        columns.add(Column.aliased("secondary_insurer_name", table, columnPrefix + "_secondary_insurer_name"));
        columns.add(Column.aliased("secondary_insurer_status", table, columnPrefix + "_secondary_insurer_status"));
        columns.add(
            Column.aliased("secondary_insurer_coverager_percentage", table, columnPrefix + "_secondary_insurer_coverager_percentage")
        );
        columns.add(Column.aliased("secondary_insurer_coverage_amount", table, columnPrefix + "_secondary_insurer_coverage_amount"));
        columns.add(Column.aliased("secondary_insurer_payment", table, columnPrefix + "_secondary_insurer_payment"));
        columns.add(Column.aliased("secondary_insurer_balance_amount", table, columnPrefix + "_secondary_insurer_balance_amount"));
        columns.add(Column.aliased("tertiary_insurer_id", table, columnPrefix + "_tertiary_insurer_id"));
        columns.add(Column.aliased("tertiary_insurer_name", table, columnPrefix + "_tertiary_insurer_name"));
        columns.add(Column.aliased("tertiary_insurer_status", table, columnPrefix + "_tertiary_insurer_status"));
        columns.add(Column.aliased("tertiary_insurer_coverage_percentage", table, columnPrefix + "_tertiary_insurer_coverage_percentage"));
        columns.add(Column.aliased("tertiary_insurer_coverage_amount", table, columnPrefix + "_tertiary_insurer_coverage_amount"));
        columns.add(Column.aliased("tertiary_insurer_payment", table, columnPrefix + "_tertiary_insurer_payment"));
        columns.add(Column.aliased("tertiary_insurer_balance_amount", table, columnPrefix + "_tertiary_insurer_balance_amount"));
        columns.add(Column.aliased("patient_coinsurance_percentage", table, columnPrefix + "_patient_coinsurance_percentage"));
        columns.add(Column.aliased("patient_coinsurance_amount", table, columnPrefix + "_patient_coinsurance_amount"));
        columns.add(Column.aliased("total_patient_responsibility_amount", table, columnPrefix + "_total_patient_responsibility_amount"));
        columns.add(Column.aliased("patient_pay_amount", table, columnPrefix + "_patient_pay_amount"));
        columns.add(Column.aliased("narration", table, columnPrefix + "_narration"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("primary_invoice_no", table, columnPrefix + "_primary_invoice_no"));
        columns.add(Column.aliased("primary_invoice_date", table, columnPrefix + "_primary_invoice_date"));
        columns.add(Column.aliased("primary_invoice_status", table, columnPrefix + "_primary_invoice_status"));
        columns.add(Column.aliased("dos", table, columnPrefix + "_dos"));
        columns.add(Column.aliased("secondary_invoice_no", table, columnPrefix + "_secondary_invoice_no"));
        columns.add(Column.aliased("tertiary_invoice_no", table, columnPrefix + "_tertiary_invoice_no"));
        columns.add(Column.aliased("secondary_invoice_date", table, columnPrefix + "_secondary_invoice_date"));
        columns.add(Column.aliased("tertiary_invoice_date", table, columnPrefix + "_tertiary_invoice_date"));
        columns.add(Column.aliased("secondary_invoice_status", table, columnPrefix + "_secondary_invoice_status"));
        columns.add(Column.aliased("tertiary_invoice_status", table, columnPrefix + "_tertiary_invoice_status"));
        columns.add(Column.aliased("sales_order_financial_details_uuid", table, columnPrefix + "_sales_order_financial_details_uuid"));

        return columns;
    }
}
