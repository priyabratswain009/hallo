package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ParRequestDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("par_request_details_id", table, columnPrefix + "_par_request_details_id"));
        columns.add(Column.aliased("par_request_type", table, columnPrefix + "_par_request_type"));
        columns.add(Column.aliased("par_id", table, columnPrefix + "_par_id"));
        columns.add(Column.aliased("par_no", table, columnPrefix + "_par_no"));
        columns.add(Column.aliased("par_initiation_date", table, columnPrefix + "_par_initiation_date"));
        columns.add(Column.aliased("par_no_effetive_start_date", table, columnPrefix + "_par_no_effetive_start_date"));
        columns.add(Column.aliased("par_no_effetive_end_date", table, columnPrefix + "_par_no_effetive_end_date"));
        columns.add(Column.aliased("par_authorised_by", table, columnPrefix + "_par_authorised_by"));
        columns.add(Column.aliased("par_request_doc_name", table, columnPrefix + "_par_request_doc_name"));
        columns.add(Column.aliased("par_request_doc_location", table, columnPrefix + "_par_request_doc_location"));
        columns.add(Column.aliased("par_request_fax_number", table, columnPrefix + "_par_request_fax_number"));
        columns.add(Column.aliased("par_request_fax_status", table, columnPrefix + "_par_request_fax_status"));
        columns.add(Column.aliased("par_request_fax_datetime", table, columnPrefix + "_par_request_fax_datetime"));
        columns.add(Column.aliased("fax_response_status", table, columnPrefix + "_fax_response_status"));
        columns.add(Column.aliased("par_request_response_doc_name", table, columnPrefix + "_par_request_response_doc_name"));
        columns.add(Column.aliased("doc_qr_code", table, columnPrefix + "_doc_qr_code"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("par_request_details_uuid", table, columnPrefix + "_par_request_details_uuid"));

        return columns;
    }
}
