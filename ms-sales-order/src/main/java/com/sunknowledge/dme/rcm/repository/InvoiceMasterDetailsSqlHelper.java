package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InvoiceMasterDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("invoice_id", table, columnPrefix + "_invoice_id"));
        columns.add(Column.aliased("invoice_no", table, columnPrefix + "_invoice_no"));
        columns.add(Column.aliased("invoice_date", table, columnPrefix + "_invoice_date"));
        columns.add(Column.aliased("invoice_to_entity", table, columnPrefix + "_invoice_to_entity"));
        columns.add(Column.aliased("invoice_to_payor_id", table, columnPrefix + "_invoice_to_payor_id"));
        columns.add(Column.aliased("invoice_to_payor_name", table, columnPrefix + "_invoice_to_payor_name"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("sales_order_no", table, columnPrefix + "_sales_order_no"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_middle_name", table, columnPrefix + "_patient_middle_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("item_qty_total", table, columnPrefix + "_item_qty_total"));
        columns.add(Column.aliased("charged_amount_total", table, columnPrefix + "_charged_amount_total"));
        columns.add(Column.aliased("allow_amount_total", table, columnPrefix + "_allow_amount_total"));
        columns.add(Column.aliased("payment_amount_total", table, columnPrefix + "_payment_amount_total"));
        columns.add(Column.aliased("tax_amount_total", table, columnPrefix + "_tax_amount_total"));
        columns.add(Column.aliased("adj_amount_total", table, columnPrefix + "_adj_amount_total"));
        columns.add(Column.aliased("balance_amount_total", table, columnPrefix + "_balance_amount_total"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("invoice_master_details_uuid", table, columnPrefix + "_invoice_master_details_uuid"));
        columns.add(Column.aliased("invoice_status", table, columnPrefix + "_invoice_status"));
        columns.add(Column.aliased("primary_submission_master_id", table, columnPrefix + "_primary_submission_master_id"));
        columns.add(Column.aliased("claim_control_no", table, columnPrefix + "_claim_control_no"));

        return columns;
    }
}
