package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ElligibilityResponseBenefitinformationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(
            Column.aliased(
                "elligibility_response_benefitinformation_id",
                table,
                columnPrefix + "_elligibility_response_benefitinformation_id"
            )
        );
        columns.add(Column.aliased("elligibility_response_status_id", table, columnPrefix + "_elligibility_response_status_id"));
        columns.add(Column.aliased("benefitsinformation_code", table, columnPrefix + "_benefitsinformation_code"));
        columns.add(Column.aliased("benefitsinformation_name", table, columnPrefix + "_benefitsinformation_name"));
        columns.add(Column.aliased("coverage_level_code", table, columnPrefix + "_coverage_level_code"));
        columns.add(Column.aliased("service_type_codes", table, columnPrefix + "_service_type_codes"));
        columns.add(Column.aliased("insurance_type_code", table, columnPrefix + "_insurance_type_code"));
        columns.add(Column.aliased("plan_coverage", table, columnPrefix + "_plan_coverage"));
        columns.add(Column.aliased("benefits_date_information", table, columnPrefix + "_benefits_date_information"));
        columns.add(
            Column.aliased(
                "elligibility_response_benefit_information_uuid",
                table,
                columnPrefix + "_elligibility_response_benefit_information_uuid"
            )
        );

        return columns;
    }
}
