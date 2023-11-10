package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class StateMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("state_id", table, columnPrefix + "_state_id"));
        columns.add(Column.aliased("state_code", table, columnPrefix + "_state_code"));
        columns.add(Column.aliased("state_name", table, columnPrefix + "_state_name"));

        return columns;
    }
}
