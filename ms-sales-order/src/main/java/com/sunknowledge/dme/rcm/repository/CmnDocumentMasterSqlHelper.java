package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CmnDocumentMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("cmn_document_id", table, columnPrefix + "_cmn_document_id"));
        columns.add(Column.aliased("cmn_id", table, columnPrefix + "_cmn_id"));
        columns.add(Column.aliased("cmn_no", table, columnPrefix + "_cmn_no"));
        columns.add(Column.aliased("generation_date", table, columnPrefix + "_generation_date"));
        columns.add(Column.aliased("initial_document_name", table, columnPrefix + "_initial_document_name"));
        columns.add(Column.aliased("fax_status", table, columnPrefix + "_fax_status"));
        columns.add(Column.aliased("out_bound_fax_status", table, columnPrefix + "_out_bound_fax_status"));
        columns.add(Column.aliased("out_bound_fax_no", table, columnPrefix + "_out_bound_fax_no"));
        columns.add(Column.aliased("out_bound_fax_date_time", table, columnPrefix + "_out_bound_fax_date_time"));
        columns.add(Column.aliased("in_bound_fax_status", table, columnPrefix + "_in_bound_fax_status"));
        columns.add(Column.aliased("in_bound_fax_no", table, columnPrefix + "_in_bound_fax_no"));
        columns.add(Column.aliased("in_bound_fax_date_time", table, columnPrefix + "_in_bound_fax_date_time"));
        columns.add(Column.aliased("cmn_document_master_uuid", table, columnPrefix + "_cmn_document_master_uuid"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("return_document_name", table, columnPrefix + "_return_document_name"));
        columns.add(Column.aliased("cmn_comments", table, columnPrefix + "_cmn_comments"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));

        return columns;
    }
}
