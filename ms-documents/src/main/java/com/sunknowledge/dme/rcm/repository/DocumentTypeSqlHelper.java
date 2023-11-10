package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DocumentTypeSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("document_type_id", table, columnPrefix + "_document_type_id"));
        columns.add(Column.aliased("document_type", table, columnPrefix + "_document_type"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));

        return columns;
    }
}
