package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class BenefitCoverageRequestSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("benefit_coverage_request_id", table, columnPrefix + "_benefit_coverage_request_id"));
        columns.add(Column.aliased("payer_id", table, columnPrefix + "_payer_id"));
        columns.add(Column.aliased("provider_first_name", table, columnPrefix + "_provider_first_name"));
        columns.add(Column.aliased("provider_last_name", table, columnPrefix + "_provider_last_name"));
        columns.add(Column.aliased("provider_type", table, columnPrefix + "_provider_type"));
        columns.add(Column.aliased("provider_npi", table, columnPrefix + "_provider_npi"));
        columns.add(Column.aliased("provider_city", table, columnPrefix + "_provider_city"));
        columns.add(Column.aliased("provider_state", table, columnPrefix + "_provider_state"));
        columns.add(Column.aliased("provider_zipcode", table, columnPrefix + "_provider_zipcode"));
        columns.add(Column.aliased("submitter_id", table, columnPrefix + "_submitter_id"));
        columns.add(Column.aliased("as_of_date", table, columnPrefix + "_as_of_date"));
        columns.add(Column.aliased("service_type", table, columnPrefix + "_service_type"));
        columns.add(Column.aliased("member_id", table, columnPrefix + "_member_id"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_dob", table, columnPrefix + "_patient_dob"));
        columns.add(Column.aliased("patient_gender", table, columnPrefix + "_patient_gender"));
        columns.add(Column.aliased("patient_state", table, columnPrefix + "_patient_state"));
        columns.add(Column.aliased("subscriber_relationship", table, columnPrefix + "_subscriber_relationship"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("benefit_coverage_request_uuid", table, columnPrefix + "_benefit_coverage_request_uuid"));

        return columns;
    }
}
