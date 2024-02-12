package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class BenefitCoverageResponseSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("benefit_coverage_response_id", table, columnPrefix + "_benefit_coverage_response_id"));
        columns.add(Column.aliased("benefit_coverage_request_id", table, columnPrefix + "_benefit_coverage_request_id"));
        columns.add(Column.aliased("request_control_number_ext", table, columnPrefix + "_request_control_number_ext"));
        columns.add(Column.aliased("as_on_date", table, columnPrefix + "_as_on_date"));
        columns.add(Column.aliased("service_type", table, columnPrefix + "_service_type"));
        columns.add(Column.aliased("member_first_name", table, columnPrefix + "_member_first_name"));
        columns.add(Column.aliased("member_last_name", table, columnPrefix + "_member_last_name"));
        columns.add(Column.aliased("subscriber_member_id", table, columnPrefix + "_subscriber_member_id"));
        columns.add(Column.aliased("member_gender", table, columnPrefix + "_member_gender"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("patient_gender", table, columnPrefix + "_patient_gender"));
        columns.add(Column.aliased("payer_name", table, columnPrefix + "_payer_name"));
        columns.add(Column.aliased("patient_relationship_code", table, columnPrefix + "_patient_relationship_code"));
        columns.add(Column.aliased("patient_state", table, columnPrefix + "_patient_state"));
        columns.add(Column.aliased("coverage_status", table, columnPrefix + "_coverage_status"));
        columns.add(Column.aliased("payer_group_number", table, columnPrefix + "_payer_group_number"));
        columns.add(Column.aliased("service_date", table, columnPrefix + "_service_date"));
        columns.add(Column.aliased("plan_start_date", table, columnPrefix + "_plan_start_date"));
        columns.add(Column.aliased("response_json_text", table, columnPrefix + "_response_json_text"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("benefit_coverage_response_uuid", table, columnPrefix + "_benefit_coverage_response_uuid"));

        return columns;
    }
}
