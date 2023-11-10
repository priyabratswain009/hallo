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
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("expiration_date", table, columnPrefix + "_expiration_date"));
        columns.add(Column.aliased("requested_date", table, columnPrefix + "_requested_date"));
        columns.add(Column.aliased("response_date", table, columnPrefix + "_response_date"));
        columns.add(Column.aliased("service_type", table, columnPrefix + "_service_type"));
        columns.add(Column.aliased("subscriber_member_id", table, columnPrefix + "_subscriber_member_id"));
        columns.add(Column.aliased("patient_relationship_code", table, columnPrefix + "_patient_relationship_code"));
        columns.add(Column.aliased("payer_id", table, columnPrefix + "_payer_id"));
        columns.add(Column.aliased("provider_npi", table, columnPrefix + "_provider_npi"));
        columns.add(Column.aliased("plans_status_code", table, columnPrefix + "_plans_status_code"));
        columns.add(Column.aliased("plans_status", table, columnPrefix + "_plans_status"));
        columns.add(Column.aliased("primary_response", table, columnPrefix + "_primary_response"));
        columns.add(Column.aliased("secondary_response", table, columnPrefix + "_secondary_response"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("benefit_coverage_response_uuid", table, columnPrefix + "_benefit_coverage_response_uuid"));
        columns.add(Column.aliased("patient_state", table, columnPrefix + "_patient_state"));
        columns.add(Column.aliased("subscriber_relationship", table, columnPrefix + "_subscriber_relationship"));
        columns.add(Column.aliased("member_id", table, columnPrefix + "_member_id"));

        return columns;
    }
}
