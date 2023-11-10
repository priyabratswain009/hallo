package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PrimaryClaimResubmisionServicelinesSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(
            Column.aliased(
                "change_health_primary_resubmision_servicelines_id",
                table,
                columnPrefix + "_change_health_primary_resubmision_servicelines_id"
            )
        );
        columns.add(
            Column.aliased(
                "change_health_primary_resubmision_master_id",
                table,
                columnPrefix + "_change_health_primary_resubmision_master_id"
            )
        );
        columns.add(Column.aliased("original_dos", table, columnPrefix + "_original_dos"));
        columns.add(Column.aliased("dos_to", table, columnPrefix + "_dos_to"));
        columns.add(Column.aliased("proc_code", table, columnPrefix + "_proc_code"));
        columns.add(Column.aliased("charge_amt", table, columnPrefix + "_charge_amt"));
        columns.add(Column.aliased("item_uom", table, columnPrefix + "_item_uom"));
        columns.add(Column.aliased("modifier_1", table, columnPrefix + "_modifier_1"));
        columns.add(Column.aliased("modifier_2", table, columnPrefix + "_modifier_2"));
        columns.add(Column.aliased("modifier_3", table, columnPrefix + "_modifier_3"));
        columns.add(Column.aliased("modifier_4", table, columnPrefix + "_modifier_4"));
        columns.add(Column.aliased("icd_pointer", table, columnPrefix + "_icd_pointer"));
        columns.add(Column.aliased("qty", table, columnPrefix + "_qty"));
        columns.add(Column.aliased("inserted_by_id", table, columnPrefix + "_inserted_by_id"));
        columns.add(Column.aliased("nserted_date", table, columnPrefix + "_nserted_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("claim_serviceline_control_no", table, columnPrefix + "_claim_serviceline_control_no"));
        columns.add(Column.aliased("procedure_identifier", table, columnPrefix + "_procedure_identifier"));
        columns.add(Column.aliased("inserted_by_name", table, columnPrefix + "_inserted_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("ordering_provider_first_name", table, columnPrefix + "_ordering_provider_first_name"));
        columns.add(Column.aliased("ordering_provider_last_name", table, columnPrefix + "_ordering_provider_last_name"));
        columns.add(Column.aliased("ordering_provider_npi", table, columnPrefix + "_ordering_provider_npi"));
        columns.add(Column.aliased("reference", table, columnPrefix + "_reference"));
        columns.add(
            Column.aliased(
                "primary_claim_resubmision_servicelines_uuid",
                table,
                columnPrefix + "_primary_claim_resubmision_servicelines_uuid"
            )
        );

        return columns;
    }
}
