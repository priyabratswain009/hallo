package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ElligibilityResponseStatusSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("elligibility_response_status_id", table, columnPrefix + "_elligibility_response_status_id"));
        columns.add(Column.aliased("elligibility_control_number", table, columnPrefix + "_elligibility_control_number"));
        columns.add(Column.aliased("traceid", table, columnPrefix + "_traceid"));
        columns.add(Column.aliased("subscriber_first_name", table, columnPrefix + "_subscriber_first_name"));
        columns.add(Column.aliased("subscriber_last_name", table, columnPrefix + "_subscriber_last_name"));
        columns.add(Column.aliased("subscriber_gender", table, columnPrefix + "_subscriber_gender"));
        columns.add(Column.aliased("subscriber_dob", table, columnPrefix + "_subscriber_dob"));
        columns.add(Column.aliased("subscriber_identifier", table, columnPrefix + "_subscriber_identifier"));
        columns.add(Column.aliased("subscriber_entitytype", table, columnPrefix + "_subscriber_entitytype"));
        columns.add(Column.aliased("subscriber_ssn", table, columnPrefix + "_subscriber_ssn"));
        columns.add(Column.aliased("payer_identifier", table, columnPrefix + "_payer_identifier"));
        columns.add(Column.aliased("payer_entitytype", table, columnPrefix + "_payer_entitytype"));
        columns.add(Column.aliased("payer_name", table, columnPrefix + "_payer_name"));
        columns.add(Column.aliased("payer_identification", table, columnPrefix + "_payer_identification"));
        columns.add(Column.aliased("plan_ssn", table, columnPrefix + "_plan_ssn"));
        columns.add(Column.aliased("plan_date", table, columnPrefix + "_plan_date"));
        columns.add(Column.aliased("plan_status_code", table, columnPrefix + "_plan_status_code"));
        columns.add(Column.aliased("plan_status", table, columnPrefix + "_plan_status"));
        columns.add(Column.aliased("plan_details", table, columnPrefix + "_plan_details"));
        columns.add(Column.aliased("service_type_codes", table, columnPrefix + "_service_type_codes"));
        columns.add(Column.aliased("elligibility_response_status_uuid", table, columnPrefix + "_elligibility_response_status_uuid"));

        return columns;
    }
}
