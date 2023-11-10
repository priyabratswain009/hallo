package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class HcpcsDmeGroupMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("hcpcs_dme_id", table, columnPrefix + "_hcpcs_dme_id"));
        columns.add(Column.aliased("hcpcs_code", table, columnPrefix + "_hcpcs_code"));
        columns.add(Column.aliased("dme_group_name", table, columnPrefix + "_dme_group_name"));
        columns.add(Column.aliased("dme_group_id", table, columnPrefix + "_dme_group_id"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("hcpcs_dme_group_master_uuid", table, columnPrefix + "_hcpcs_dme_group_master_uuid"));

        return columns;
    }
}
