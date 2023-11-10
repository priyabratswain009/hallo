package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PickupExchangeItemsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("pickup_exchange_item_id", table, columnPrefix + "_pickup_exchange_item_id"));
        columns.add(Column.aliased("pickup_exchange_id", table, columnPrefix + "_pickup_exchange_id"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("item_no", table, columnPrefix + "_item_no"));
        columns.add(Column.aliased("item_name", table, columnPrefix + "_item_name"));
        columns.add(Column.aliased("whether_serialized", table, columnPrefix + "_whether_serialized"));
        columns.add(Column.aliased("pickup_item_serial_no", table, columnPrefix + "_pickup_item_serial_no"));
        columns.add(Column.aliased("pickup_item_asset_no", table, columnPrefix + "_pickup_item_asset_no"));
        columns.add(Column.aliased("replacement_item_serial_no", table, columnPrefix + "_replacement_item_serial_no"));
        columns.add(Column.aliased("replacement_item_asset_no", table, columnPrefix + "_replacement_item_asset_no"));
        columns.add(Column.aliased("quantity", table, columnPrefix + "_quantity"));
        columns.add(Column.aliased("item_pickup_exchange_type", table, columnPrefix + "_item_pickup_exchange_type"));
        columns.add(Column.aliased("item_pickup_exchange_note", table, columnPrefix + "_item_pickup_exchange_note"));
        columns.add(Column.aliased("item_pickup_exchange_comment", table, columnPrefix + "_item_pickup_exchange_comment"));
        columns.add(Column.aliased("item_pickup_exchange_status", table, columnPrefix + "_item_pickup_exchange_status"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("pickup_exchange_item_uuid", table, columnPrefix + "_pickup_exchange_item_uuid"));

        return columns;
    }
}
