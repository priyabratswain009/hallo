package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DeliveryItemsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("delivery_item_id", table, columnPrefix + "_delivery_item_id"));
        columns.add(Column.aliased("delivery_ticket_id", table, columnPrefix + "_delivery_ticket_id"));
        columns.add(Column.aliased("delivery_ticket_no", table, columnPrefix + "_delivery_ticket_no"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("so_no", table, columnPrefix + "_so_no"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("item_no", table, columnPrefix + "_item_no"));
        columns.add(Column.aliased("item_name", table, columnPrefix + "_item_name"));
        columns.add(Column.aliased("item_description", table, columnPrefix + "_item_description"));
        columns.add(Column.aliased("hcpcs_code", table, columnPrefix + "_hcpcs_code"));
        columns.add(Column.aliased("item_quantity", table, columnPrefix + "_item_quantity"));
        columns.add(Column.aliased("item_unit", table, columnPrefix + "_item_unit"));
        columns.add(Column.aliased("whether_item_serialized", table, columnPrefix + "_whether_item_serialized"));
        columns.add(Column.aliased("item_serial", table, columnPrefix + "_item_serial"));
        columns.add(Column.aliased("item_batch_lot_no", table, columnPrefix + "_item_batch_lot_no"));
        columns.add(Column.aliased("item_mfg_date", table, columnPrefix + "_item_mfg_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("delivery_items_uuid", table, columnPrefix + "_delivery_items_uuid"));
        columns.add(Column.aliased("charged_amount", table, columnPrefix + "_charged_amount"));
        columns.add(Column.aliased("allowed_amount", table, columnPrefix + "_allowed_amount"));
        columns.add(Column.aliased("item_manufacturer_name", table, columnPrefix + "_item_manufacturer_name"));
        columns.add(Column.aliased("is_dropship", table, columnPrefix + "_is_dropship"));
        columns.add(Column.aliased("po_number", table, columnPrefix + "_po_number"));
        columns.add(Column.aliased("so_sale_type", table, columnPrefix + "_so_sale_type"));

        return columns;
    }
}
