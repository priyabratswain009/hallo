package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InvoiceLineItemDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("invoice_line_item_details_id", table, columnPrefix + "_invoice_line_item_details_id"));
        columns.add(Column.aliased("primary_invoice_no", table, columnPrefix + "_primary_invoice_no"));
        columns.add(Column.aliased("invoice_date", table, columnPrefix + "_invoice_date"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("item_qty", table, columnPrefix + "_item_qty"));
        columns.add(Column.aliased("item_name", table, columnPrefix + "_item_name"));
        columns.add(Column.aliased("hcpcs_code", table, columnPrefix + "_hcpcs_code"));
        columns.add(Column.aliased("charged_amount", table, columnPrefix + "_charged_amount"));
        columns.add(Column.aliased("allow_amount", table, columnPrefix + "_allow_amount"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("invoice_line_item_details_uuid", table, columnPrefix + "_invoice_line_item_details_uuid"));
        columns.add(Column.aliased("secondary_invoice_no", table, columnPrefix + "_secondary_invoice_no"));
        columns.add(Column.aliased("tertiary_invoice_no", table, columnPrefix + "_tertiary_invoice_no"));
        columns.add(Column.aliased("patient_invoice_no", table, columnPrefix + "_patient_invoice_no"));
        columns.add(Column.aliased("primary_invoice_id", table, columnPrefix + "_primary_invoice_id"));
        columns.add(Column.aliased("secondary_invoice_id", table, columnPrefix + "_secondary_invoice_id"));
        columns.add(Column.aliased("tertiary_invoice_id", table, columnPrefix + "_tertiary_invoice_id"));
        columns.add(Column.aliased("patient_invoice_id", table, columnPrefix + "_patient_invoice_id"));

        return columns;
    }
}
