package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class SoRentalHelperSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("so_rental_helper_id", table, columnPrefix + "_so_rental_helper_id"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("primary_insurer_id", table, columnPrefix + "_primary_insurer_id"));
        columns.add(Column.aliased("primary_insurer_name", table, columnPrefix + "_primary_insurer_name"));
        columns.add(Column.aliased("item_id", table, columnPrefix + "_item_id"));
        columns.add(Column.aliased("item_no", table, columnPrefix + "_item_no"));
        columns.add(Column.aliased("item_name", table, columnPrefix + "_item_name"));
        columns.add(Column.aliased("charged_amount", table, columnPrefix + "_charged_amount"));
        columns.add(Column.aliased("allowed_amount", table, columnPrefix + "_allowed_amount"));
        columns.add(Column.aliased("sou", table, columnPrefix + "_sou"));
        columns.add(Column.aliased("qty", table, columnPrefix + "_qty"));
        columns.add(Column.aliased("dos_start", table, columnPrefix + "_dos_start"));
        columns.add(Column.aliased("dos_end", table, columnPrefix + "_dos_end"));
        columns.add(Column.aliased("period_no", table, columnPrefix + "_period_no"));
        columns.add(Column.aliased("rental_period", table, columnPrefix + "_rental_period"));
        columns.add(Column.aliased("next_dos", table, columnPrefix + "_next_dos"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("so_rental_helper_uuid", table, columnPrefix + "_so_rental_helper_uuid"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("sale_type", table, columnPrefix + "_sale_type"));
        columns.add(Column.aliased("primary_insurance_price_table_id", table, columnPrefix + "_primary_insurance_price_table_id"));
        columns.add(Column.aliased("primary_insurance_price_table_name", table, columnPrefix + "_primary_insurance_price_table_name"));
        columns.add(Column.aliased("modifier_1", table, columnPrefix + "_modifier_1"));
        columns.add(Column.aliased("modifier_2", table, columnPrefix + "_modifier_2"));
        columns.add(Column.aliased("modifier_3", table, columnPrefix + "_modifier_3"));
        columns.add(Column.aliased("modifier_4", table, columnPrefix + "_modifier_4"));
        columns.add(Column.aliased("icd_pointer", table, columnPrefix + "_icd_pointer"));
        columns.add(Column.aliased("procedure_identifier", table, columnPrefix + "_procedure_identifier"));
        columns.add(Column.aliased("ordering_provider_first_name", table, columnPrefix + "_ordering_provider_first_name"));
        columns.add(Column.aliased("ordering_provider_last_name", table, columnPrefix + "_ordering_provider_last_name"));
        columns.add(Column.aliased("ordering_provider_npi", table, columnPrefix + "_ordering_provider_npi"));
        columns.add(Column.aliased("reference", table, columnPrefix + "_reference"));
        columns.add(Column.aliased("proc_code", table, columnPrefix + "_proc_code"));

        return columns;
    }
}
