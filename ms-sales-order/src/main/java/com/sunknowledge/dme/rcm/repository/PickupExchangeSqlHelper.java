package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PickupExchangeSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("pickup_exchange_id", table, columnPrefix + "_pickup_exchange_id"));
        columns.add(Column.aliased("pickup_exchange_no", table, columnPrefix + "_pickup_exchange_no"));
        columns.add(Column.aliased("pickup_exchange_type", table, columnPrefix + "_pickup_exchange_type"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("so_no", table, columnPrefix + "_so_no"));
        columns.add(Column.aliased("branch_id", table, columnPrefix + "_branch_id"));
        columns.add(Column.aliased("branch_name", table, columnPrefix + "_branch_name"));
        columns.add(Column.aliased("inventory_location_id", table, columnPrefix + "_inventory_location_id"));
        columns.add(Column.aliased("inventory_location_name", table, columnPrefix + "_inventory_location_name"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_id_no", table, columnPrefix + "_patient_id_no"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_middle_name", table, columnPrefix + "_patient_middle_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("patient_contact_1", table, columnPrefix + "_patient_contact_1"));
        columns.add(Column.aliased("patient_contact_2", table, columnPrefix + "_patient_contact_2"));
        columns.add(Column.aliased("patient_billing_address_line_1", table, columnPrefix + "_patient_billing_address_line_1"));
        columns.add(Column.aliased("patient_billing_address_line_2", table, columnPrefix + "_patient_billing_address_line_2"));
        columns.add(Column.aliased("patient_billing_address_state", table, columnPrefix + "_patient_billing_address_state"));
        columns.add(Column.aliased("patient_billing_address_city", table, columnPrefix + "_patient_billing_address_city"));
        columns.add(Column.aliased("patient_billing_address_zip", table, columnPrefix + "_patient_billing_address_zip"));
        columns.add(Column.aliased("patient_delivey_address_line_1", table, columnPrefix + "_patient_delivey_address_line_1"));
        columns.add(Column.aliased("patient_delivey_address_line_2", table, columnPrefix + "_patient_delivey_address_line_2"));
        columns.add(Column.aliased("patient_delivey_address_state", table, columnPrefix + "_patient_delivey_address_state"));
        columns.add(Column.aliased("patient_delivey_address_city", table, columnPrefix + "_patient_delivey_address_city"));
        columns.add(Column.aliased("patient_delivey_address_zip", table, columnPrefix + "_patient_delivey_address_zip"));
        columns.add(Column.aliased("pickup_exchange_schedule_date_time", table, columnPrefix + "_pickup_exchange_schedule_date_time"));
        columns.add(Column.aliased("pickup_exchange_actual_date_time", table, columnPrefix + "_pickup_exchange_actual_date_time"));
        columns.add(Column.aliased("pickup_exchange_reason", table, columnPrefix + "_pickup_exchange_reason"));
        columns.add(Column.aliased("pickup_exchange_request", table, columnPrefix + "_pickup_exchange_request"));
        columns.add(Column.aliased("pickup_exchange_note", table, columnPrefix + "_pickup_exchange_note"));
        columns.add(Column.aliased("pickup_exchange_agent_id_no", table, columnPrefix + "_pickup_exchange_agent_id_no"));
        columns.add(Column.aliased("pickup_exchange_agent_name", table, columnPrefix + "_pickup_exchange_agent_name"));
        columns.add(Column.aliased("pickup_exchange_document_id", table, columnPrefix + "_pickup_exchange_document_id"));
        columns.add(Column.aliased("pickup_exchange_document_no", table, columnPrefix + "_pickup_exchange_document_no"));
        columns.add(Column.aliased("pickup_exchange_document_name", table, columnPrefix + "_pickup_exchange_document_name"));
        columns.add(Column.aliased("pickup_exchange_status", table, columnPrefix + "_pickup_exchange_status"));
        columns.add(Column.aliased("pickup_exchange_comments", table, columnPrefix + "_pickup_exchange_comments"));
        columns.add(Column.aliased("is_patient_signed", table, columnPrefix + "_is_patient_signed"));
        columns.add(Column.aliased("relationship_with_patient", table, columnPrefix + "_relationship_with_patient"));
        columns.add(Column.aliased("patient_signed_date_time", table, columnPrefix + "_patient_signed_date_time"));
        columns.add(Column.aliased("is_agent_signed", table, columnPrefix + "_is_agent_signed"));
        columns.add(Column.aliased("last_billing_date", table, columnPrefix + "_last_billing_date"));
        columns.add(Column.aliased("date_of_death", table, columnPrefix + "_date_of_death"));
        columns.add(
            Column.aliased("pickup_exchange_supporting_document_1", table, columnPrefix + "_pickup_exchange_supporting_document_1")
        );
        columns.add(
            Column.aliased("pickup_exchange_supporting_document_2", table, columnPrefix + "_pickup_exchange_supporting_document_2")
        );
        columns.add(Column.aliased("patient_notsigned_reason", table, columnPrefix + "_patient_notsigned_reason"));
        columns.add(Column.aliased("pickup_exchange_json_data", table, columnPrefix + "_pickup_exchange_json_data"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("pickup_exchange_uuid", table, columnPrefix + "_pickup_exchange_uuid"));

        return columns;
    }
}
