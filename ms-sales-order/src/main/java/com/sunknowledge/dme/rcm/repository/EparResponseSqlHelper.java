package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class EparResponseSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("epar_response_id", table, columnPrefix + "_epar_response_id"));
        columns.add(Column.aliased("epar_request_id", table, columnPrefix + "_epar_request_id"));
        columns.add(Column.aliased("par_id", table, columnPrefix + "_par_id"));
        columns.add(Column.aliased("par_no", table, columnPrefix + "_par_no"));
        columns.add(Column.aliased("payer_id_no", table, columnPrefix + "_payer_id_no"));
        columns.add(Column.aliased("payer_name", table, columnPrefix + "_payer_name"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("subscriber_relationship", table, columnPrefix + "_subscriber_relationship"));
        columns.add(Column.aliased("certification_effective_date", table, columnPrefix + "_certification_effective_date"));
        columns.add(Column.aliased("certification_expiration_date", table, columnPrefix + "_certification_expiration_date"));
        columns.add(Column.aliased("request_type", table, columnPrefix + "_request_type"));
        columns.add(Column.aliased("place_of_service", table, columnPrefix + "_place_of_service"));
        columns.add(Column.aliased("service_level", table, columnPrefix + "_service_level"));
        columns.add(Column.aliased("quantity", table, columnPrefix + "_quantity"));
        columns.add(Column.aliased("quantity_type", table, columnPrefix + "_quantity_type"));
        columns.add(Column.aliased("response_create_date", table, columnPrefix + "_response_create_date"));
        columns.add(Column.aliased("response_response_date", table, columnPrefix + "_response_response_date"));
        columns.add(Column.aliased("json_data", table, columnPrefix + "_json_data"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("e_par_response_uuid", table, columnPrefix + "_e_par_response_uuid"));

        return columns;
    }
}
