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
        columns.add(Column.aliased("coveragelevelcode", table, columnPrefix + "_coveragelevelcode"));
        columns.add(Column.aliased("coveragelevel", table, columnPrefix + "_coveragelevel"));
        columns.add(Column.aliased("servicetypecodes", table, columnPrefix + "_servicetypecodes"));
        columns.add(Column.aliased("servicetypes", table, columnPrefix + "_servicetypes"));
        columns.add(Column.aliased("insurancetypecode", table, columnPrefix + "_insurancetypecode"));
        columns.add(Column.aliased("insurancetype", table, columnPrefix + "_insurancetype"));
        columns.add(Column.aliased("plancoverage", table, columnPrefix + "_plancoverage"));
        columns.add(Column.aliased("benefits_local_dateinformation", table, columnPrefix + "_benefits_local_dateinformation"));
        columns.add(
            Column.aliased(
                "elligibility_response_benefitinformation_uuid",
                table,
                columnPrefix + "_elligibility_response_benefitinformation_uuid"
            )
        );

        return columns;
    }
}
