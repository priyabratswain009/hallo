package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class EparRequestSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("epar_request_id", table, columnPrefix + "_epar_request_id"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("so_no", table, columnPrefix + "_so_no"));
        columns.add(Column.aliased("par_id", table, columnPrefix + "_par_id"));
        columns.add(Column.aliased("par_no", table, columnPrefix + "_par_no"));
        columns.add(Column.aliased("request_datetime", table, columnPrefix + "_request_datetime"));
        columns.add(Column.aliased("response_status", table, columnPrefix + "_response_status"));
        columns.add(Column.aliased("response_url", table, columnPrefix + "_response_url"));
        columns.add(Column.aliased("request_json", table, columnPrefix + "_request_json"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("epa_request_uuid", table, columnPrefix + "_epa_request_uuid"));

        return columns;
    }
}
