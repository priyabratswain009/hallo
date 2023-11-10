package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InvoicePostingDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("invoice_line_item_posting_id", table, columnPrefix + "_invoice_line_item_posting_id"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("posting_date", table, columnPrefix + "_posting_date"));
        columns.add(Column.aliased("posted_by_id", table, columnPrefix + "_posted_by_id"));
        columns.add(Column.aliased("posted_by_name", table, columnPrefix + "_posted_by_name"));
        columns.add(Column.aliased("posting_comment", table, columnPrefix + "_posting_comment"));
        columns.add(Column.aliased("post_type", table, columnPrefix + "_post_type"));
        columns.add(Column.aliased("deposit_id", table, columnPrefix + "_deposit_id"));
        columns.add(Column.aliased("receipt_id", table, columnPrefix + "_receipt_id"));
        columns.add(Column.aliased("post_amount", table, columnPrefix + "_post_amount"));
        columns.add(Column.aliased("post_status", table, columnPrefix + "_post_status"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("invoice_posting_details_uuid", table, columnPrefix + "_invoice_posting_details_uuid"));
        columns.add(Column.aliased("invoice_no", table, columnPrefix + "_invoice_no"));
        columns.add(Column.aliased("invoice_date", table, columnPrefix + "_invoice_date"));
        columns.add(Column.aliased("invoice_line_item_details_id", table, columnPrefix + "_invoice_line_item_details_id"));

        return columns;
    }
}
