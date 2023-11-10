package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DeliveryAbnDataSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("delivery_abn_data_id", table, columnPrefix + "_delivery_abn_data_id"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("primary_insurance_name", table, columnPrefix + "_primary_insurance_name"));
        columns.add(Column.aliased("primary_insurance_policy_number", table, columnPrefix + "_primary_insurance_policy_number"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_middle_name", table, columnPrefix + "_patient_middle_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("sales_order_details_abn_print_date", table, columnPrefix + "_sales_order_details_abn_print_date"));
        columns.add(Column.aliased("sales_order_details_abn_item_name", table, columnPrefix + "_sales_order_details_abn_item_name"));
        columns.add(
            Column.aliased("sales_order_details_abn_item_proc_code", table, columnPrefix + "_sales_order_details_abn_item_proc_code")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_abn_item_charge_amount",
                table,
                columnPrefix + "_sales_order_details_abn_item_charge_amount"
            )
        );
        columns.add(
            Column.aliased("sales_order_details_patient_abn_response", table, columnPrefix + "_sales_order_details_patient_abn_response")
        );
        columns.add(
            Column.aliased("sales_order_details_abn_delivery_status", table, columnPrefix + "_sales_order_details_abn_delivery_status")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_abn_patient_signature_status",
                table,
                columnPrefix + "_sales_order_details_abn_patient_signature_status"
            )
        );
        columns.add(Column.aliased("sales_order_details_abn_status", table, columnPrefix + "_sales_order_details_abn_status"));
        columns.add(Column.aliased("sales_order_details_abn_reason", table, columnPrefix + "_sales_order_details_abn_reason"));
        columns.add(Column.aliased("sales_order_details_abn_modifier_1", table, columnPrefix + "_sales_order_details_abn_modifier_1"));
        columns.add(Column.aliased("sales_order_details_abn_modifier_2", table, columnPrefix + "_sales_order_details_abn_modifier_2"));
        columns.add(Column.aliased("branch_name", table, columnPrefix + "_branch_name"));
        columns.add(Column.aliased("branch_id", table, columnPrefix + "_branch_id"));
        columns.add(Column.aliased("qr_code", table, columnPrefix + "_qr_code"));
        columns.add(Column.aliased("patient_id_no", table, columnPrefix + "_patient_id_no"));
        columns.add(Column.aliased("abn_number", table, columnPrefix + "_abn_number"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("delivery_abn_data_uuid", table, columnPrefix + "_delivery_abn_data_uuid"));

        return columns;
    }
}
