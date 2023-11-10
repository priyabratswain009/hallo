package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PatientInsVerifStatSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("insurance_verif_id", table, columnPrefix + "_insurance_verif_id"));
        columns.add(Column.aliased("patient_insurance_id", table, columnPrefix + "_patient_insurance_id"));
        columns.add(Column.aliased("elligibility_check_date", table, columnPrefix + "_elligibility_check_date"));
        columns.add(Column.aliased("elligibility_status", table, columnPrefix + "_elligibility_status"));
        columns.add(Column.aliased("elligibility_check_type", table, columnPrefix + "_elligibility_check_type"));
        columns.add(Column.aliased("period_year", table, columnPrefix + "_period_year"));
        columns.add(Column.aliased("coverage_info", table, columnPrefix + "_coverage_info"));
        columns.add(Column.aliased("network_info", table, columnPrefix + "_network_info"));
        columns.add(Column.aliased("deductable_amt", table, columnPrefix + "_deductable_amt"));
        columns.add(Column.aliased("remaining_amt", table, columnPrefix + "_remaining_amt"));
        columns.add(Column.aliased("coinsurance_or_copay", table, columnPrefix + "_coinsurance_or_copay"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("patient_ins_verif_stat_uuid", table, columnPrefix + "_patient_ins_verif_stat_uuid"));

        return columns;
    }
}
