package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ChecklistDocumentReferenceMapSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("checklist_document_reference_id", table, columnPrefix + "_checklist_document_reference_id"));
        columns.add(Column.aliased("checklist_id", table, columnPrefix + "_checklist_id"));
        columns.add(Column.aliased("checklist_name", table, columnPrefix + "_checklist_name"));
        columns.add(Column.aliased("document_reference_id", table, columnPrefix + "_document_reference_id"));
        columns.add(Column.aliased("document_reference_name", table, columnPrefix + "_document_reference_name"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(
            Column.aliased("checklist_document_reference_map_uuid", table, columnPrefix + "_checklist_document_reference_map_uuid")
        );

        return columns;
    }
}