package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ChecklistCoverageCriteriaMapSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("checklist_coverage_criteria_id", table, columnPrefix + "_checklist_coverage_criteria_id"));
        columns.add(Column.aliased("checklist_id", table, columnPrefix + "_checklist_id"));
        columns.add(Column.aliased("checklist_name", table, columnPrefix + "_checklist_name"));
        columns.add(Column.aliased("coverage_criteria_id", table, columnPrefix + "_coverage_criteria_id"));
        columns.add(Column.aliased("coverage_criteria_details", table, columnPrefix + "_coverage_criteria_details"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("checklist_coverage_criteria_map_uuid", table, columnPrefix + "_checklist_coverage_criteria_map_uuid"));
        columns.add(Column.aliased("coverage_criteria_name", table, columnPrefix + "_coverage_criteria_name"));

        return columns;
    }
}
