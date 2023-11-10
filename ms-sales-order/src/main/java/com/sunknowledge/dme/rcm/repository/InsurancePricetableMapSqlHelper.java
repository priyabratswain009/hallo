package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InsurancePricetableMapSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("insurance_pricetable_map_id", table, columnPrefix + "_insurance_pricetable_map_id"));
        columns.add(Column.aliased("insurance_id_no", table, columnPrefix + "_insurance_id_no"));
        columns.add(Column.aliased("insurance_name", table, columnPrefix + "_insurance_name"));
        columns.add(Column.aliased("insurance_payer_id_no", table, columnPrefix + "_insurance_payer_id_no"));
        columns.add(Column.aliased("price_table_id", table, columnPrefix + "_price_table_id"));
        columns.add(Column.aliased("price_table_name", table, columnPrefix + "_price_table_name"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("insurance_pricetable_map_uuid", table, columnPrefix + "_insurance_pricetable_map_uuid"));
        columns.add(Column.aliased("insurance_id", table, columnPrefix + "_insurance_id"));

        return columns;
    }
}
