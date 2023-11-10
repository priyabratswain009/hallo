package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class SalesOrderItemDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("sales_order_item_details_id", table, columnPrefix + "_sales_order_item_details_id"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("item_location_id", table, columnPrefix + "_item_location_id"));
        columns.add(Column.aliased("sales_order_details_item_id", table, columnPrefix + "_sales_order_details_item_id"));
        columns.add(Column.aliased("sales_order_details_item_name", table, columnPrefix + "_sales_order_details_item_name"));
        columns.add(Column.aliased("sales_order_details_stocking_uom", table, columnPrefix + "_sales_order_details_stocking_uom"));
        columns.add(Column.aliased("item_asset_no", table, columnPrefix + "_item_asset_no"));
        columns.add(Column.aliased("sales_order_details_item_description", table, columnPrefix + "_sales_order_details_item_description"));
        columns.add(Column.aliased("sales_order_details_default_vendor", table, columnPrefix + "_sales_order_details_default_vendor"));
        columns.add(Column.aliased("sales_order_details_original_dos", table, columnPrefix + "_sales_order_details_original_dos"));
        columns.add(
            Column.aliased("sales_order_details_previous_billing_date", table, columnPrefix + "_sales_order_details_previous_billing_date")
        );
        columns.add(
            Column.aliased("sales_order_details_next_billing_date", table, columnPrefix + "_sales_order_details_next_billing_date")
        );
        columns.add(Column.aliased("sales_order_details_dos_to", table, columnPrefix + "_sales_order_details_dos_to"));
        columns.add(Column.aliased("sales_order_details_next_period", table, columnPrefix + "_sales_order_details_next_period"));
        columns.add(Column.aliased("sales_order_details_special_pricing", table, columnPrefix + "_sales_order_details_special_pricing"));
        columns.add(Column.aliased("sales_order_details_price_override", table, columnPrefix + "_sales_order_details_price_override"));
        columns.add(Column.aliased("sales_order_details_special_tax_rate", table, columnPrefix + "_sales_order_details_special_tax_rate"));
        columns.add(Column.aliased("sales_order_details_qty", table, columnPrefix + "_sales_order_details_qty"));
        columns.add(Column.aliased("sales_order_details_bqty", table, columnPrefix + "_sales_order_details_bqty"));
        columns.add(Column.aliased("sales_order_details_line_qty", table, columnPrefix + "_sales_order_details_line_qty"));
        columns.add(Column.aliased("sales_order_details_proc_code", table, columnPrefix + "_sales_order_details_proc_code"));
        columns.add(Column.aliased("sales_order_details_price_option", table, columnPrefix + "_sales_order_details_price_option"));
        columns.add(Column.aliased("sales_order_details_modifier_1", table, columnPrefix + "_sales_order_details_modifier_1"));
        columns.add(Column.aliased("sales_order_details_modifier_2", table, columnPrefix + "_sales_order_details_modifier_2"));
        columns.add(Column.aliased("sales_order_details_modifier_3", table, columnPrefix + "_sales_order_details_modifier_3"));
        columns.add(Column.aliased("sales_order_details_modifier_4", table, columnPrefix + "_sales_order_details_modifier_4"));
        columns.add(Column.aliased("sales_order_details_charge_amt", table, columnPrefix + "_sales_order_details_charge_amt"));
        columns.add(Column.aliased("sales_order_details_allowed_amt", table, columnPrefix + "_sales_order_details_allowed_amt"));
        columns.add(Column.aliased("sales_order_details_taxable", table, columnPrefix + "_sales_order_details_taxable"));
        columns.add(Column.aliased("sales_order_details_abn", table, columnPrefix + "_sales_order_details_abn"));
        columns.add(Column.aliased("sales_order_details_abn_upgrade", table, columnPrefix + "_sales_order_details_abn_upgrade"));
        columns.add(Column.aliased("sales_order_details_abn_print_date", table, columnPrefix + "_sales_order_details_abn_print_date"));
        columns.add(Column.aliased("sales_order_details_abn_item", table, columnPrefix + "_sales_order_details_abn_item"));
        columns.add(Column.aliased("sales_order_details_abn_proc_code", table, columnPrefix + "_sales_order_details_abn_proc_code"));
        columns.add(Column.aliased("sales_order_details_abn_allow", table, columnPrefix + "_sales_order_details_abn_allow"));
        columns.add(Column.aliased("sales_order_details_abn_charge", table, columnPrefix + "_sales_order_details_abn_charge"));
        columns.add(Column.aliased("sales_order_details_abn_modifier_1", table, columnPrefix + "_sales_order_details_abn_modifier_1"));
        columns.add(Column.aliased("sales_order_details_abn_modifier_2", table, columnPrefix + "_sales_order_details_abn_modifier_2"));
        columns.add(Column.aliased("sales_order_details_tax_rate", table, columnPrefix + "_sales_order_details_tax_rate"));
        columns.add(Column.aliased("sales_order_details_tax_zone", table, columnPrefix + "_sales_order_details_tax_zone"));
        columns.add(Column.aliased("sales_order_details_non_tax_reason", table, columnPrefix + "_sales_order_details_non_tax_reason"));
        columns.add(Column.aliased("sales_order_details_note", table, columnPrefix + "_sales_order_details_note"));
        columns.add(Column.aliased("sales_order_details_sale_type", table, columnPrefix + "_sales_order_details_sale_type"));
        columns.add(Column.aliased("sales_order_details_item_group", table, columnPrefix + "_sales_order_details_item_group"));
        columns.add(Column.aliased("sales_order_details_item_user_1", table, columnPrefix + "_sales_order_details_item_user_1"));
        columns.add(Column.aliased("sales_order_details_item_user_2", table, columnPrefix + "_sales_order_details_item_user_2"));
        columns.add(Column.aliased("sales_order_details_item_user_3", table, columnPrefix + "_sales_order_details_item_user_3"));
        columns.add(Column.aliased("sales_order_details_item_user_4", table, columnPrefix + "_sales_order_details_item_user_4"));
        columns.add(
            Column.aliased("sales_order_details_converted_to_purchase", table, columnPrefix + "_sales_order_details_converted_to_purchase")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_manual_convert_to_purchase_mctp",
                table,
                columnPrefix + "_sales_order_details_manual_convert_to_purchase_mctp"
            )
        );
        columns.add(Column.aliased("sales_order_details_mctp_charge_amt", table, columnPrefix + "_sales_order_details_mctp_charge_amt"));
        columns.add(Column.aliased("sales_order_details_mctp_allowed_amt", table, columnPrefix + "_sales_order_details_mctp_allowed_amt"));
        columns.add(Column.aliased("sales_order_details_mctp_modifier_1", table, columnPrefix + "_sales_order_details_mctp_modifier_1"));
        columns.add(Column.aliased("sales_order_details_mctp_modifier_2", table, columnPrefix + "_sales_order_details_mctp_modifier_2"));
        columns.add(Column.aliased("sales_order_details_mctp_modifier_3", table, columnPrefix + "_sales_order_details_mctp_modifier_3"));
        columns.add(Column.aliased("sales_order_details_mctp_modifier_4", table, columnPrefix + "_sales_order_details_mctp_modifier_4"));
        columns.add(Column.aliased("sales_order_details_mctp_period", table, columnPrefix + "_sales_order_details_mctp_period"));
        columns.add(Column.aliased("sales_order_details_addtl_modifier_1", table, columnPrefix + "_sales_order_details_addtl_modifier_1"));
        columns.add(Column.aliased("sales_order_details_addtl_modifier_2", table, columnPrefix + "_sales_order_details_addtl_modifier_2"));
        columns.add(Column.aliased("sales_order_details_addtl_modifier_3", table, columnPrefix + "_sales_order_details_addtl_modifier_3"));
        columns.add(Column.aliased("sales_order_details_addtl_modifier_4", table, columnPrefix + "_sales_order_details_addtl_modifier_4"));
        columns.add(
            Column.aliased("sales_order_details_next_date_of_service", table, columnPrefix + "_sales_order_details_next_date_of_service")
        );
        columns.add(Column.aliased("sales_order_details_price_table", table, columnPrefix + "_sales_order_details_price_table"));
        columns.add(
            Column.aliased("sales_order_details_price_option_name", table, columnPrefix + "_sales_order_details_price_option_name")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_extended_charge_amount",
                table,
                columnPrefix + "_sales_order_details_extended_charge_amount"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_extended_allowance_amount",
                table,
                columnPrefix + "_sales_order_details_extended_allowance_amount"
            )
        );
        columns.add(Column.aliased("sales_order_details_item_ndc_code", table, columnPrefix + "_sales_order_details_item_ndc_code"));
        columns.add(Column.aliased("sales_order_details_manufacturer", table, columnPrefix + "_sales_order_details_manufacturer"));
        columns.add(Column.aliased("sales_order_details_cb_pricing", table, columnPrefix + "_sales_order_details_cb_pricing"));
        columns.add(
            Column.aliased(
                "sales_order_details_cb_price_table_override",
                table,
                columnPrefix + "_sales_order_details_cb_price_table_override"
            )
        );
        columns.add(Column.aliased("sales_order_details_cb_override", table, columnPrefix + "_sales_order_details_cb_override"));
        columns.add(Column.aliased("sales_order_details_messages", table, columnPrefix + "_sales_order_details_messages"));
        columns.add(Column.aliased("sales_order_details_location", table, columnPrefix + "_sales_order_details_location"));
        columns.add(Column.aliased("sales_order_details_calories_per_day", table, columnPrefix + "_sales_order_details_calories_per_day"));
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_procudure_code",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_procudure_code"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_price_option",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_price_option"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_price_option_name",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_price_option_name"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_modifier_1",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_modifier_1"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_modifier_2",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_modifier_2"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_modifier_3",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_modifier_3"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_modifier_4",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_modifier_4"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_additional_modifier_1",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_additional_modifier_1"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_additional_modifier_2",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_additional_modifier_2"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_additional_modifier_3",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_additional_modifier_3"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_additional_modifier_4",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_additional_modifier_4"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_billing_ignore",
                table,
                columnPrefix + "_sales_order_details_secondary_billing_ignore"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_secondary_special_billing",
                table,
                columnPrefix + "_sales_order_details_secondary_special_billing"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_span_date_split_billing",
                table,
                columnPrefix + "_sales_order_details_span_date_split_billing"
            )
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_cmn_form_id", table, columnPrefix + "_sales_order_details_cmnpar_cmn_form_id")
        );
        columns.add(Column.aliased("sales_order_details_cmnpar_cmn_key", table, columnPrefix + "_sales_order_details_cmnpar_cmn_key"));
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_create_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_create_date"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_expiration_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_expiration_date"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_initial_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_initial_date"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_renewal_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_renewal_date"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_recertification_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_recertification_date"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_physician_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_physician_date"
            )
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_cmn_status", table, columnPrefix + "_sales_order_details_cmnpar_cmn_status")
        );
        columns.add(Column.aliased("sales_order_details_cmnpar_par_id", table, columnPrefix + "_sales_order_details_cmnpar_par_id"));
        columns.add(Column.aliased("sales_order_details_cmnpar_par_descr", table, columnPrefix + "_sales_order_details_cmnpar_par_descr"));
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_par_initial_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_par_initial_date"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_par_expiration_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_par_expiration_date"
            )
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_cmn_log_date", table, columnPrefix + "_sales_order_details_cmnpar_cmn_log_date")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_length_of_need",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_length_of_need"
            )
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_printed_date",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_printed_date"
            )
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_cmn_printed_by", table, columnPrefix + "_sales_order_details_cmnpar_cmn_printed_by")
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_faxed_date", table, columnPrefix + "_sales_order_details_cmnpar_faxed_date")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_cmn_placeholder",
                table,
                columnPrefix + "_sales_order_details_cmnpar_cmn_placeholder"
            )
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_cmn_faxed_by", table, columnPrefix + "_sales_order_details_cmnpar_cmn_faxed_by")
        );
        columns.add(
            Column.aliased("sales_order_details_cmnpar_cmn_logged_by", table, columnPrefix + "_sales_order_details_cmnpar_cmn_logged_by")
        );
        columns.add(
            Column.aliased(
                "sales_order_details_cmnpar_number_of_refills",
                table,
                columnPrefix + "_sales_order_details_cmnpar_number_of_refills"
            )
        );
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(
            Column.aliased(
                "sales_order_details_manufacturer_item_id_number",
                table,
                columnPrefix + "_sales_order_details_manufacturer_item_id_number"
            )
        );
        columns.add(Column.aliased("cmn_id", table, columnPrefix + "_cmn_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("sales_order_item_details_uuid", table, columnPrefix + "_sales_order_item_details_uuid"));
        columns.add(Column.aliased("sales_order_item_number", table, columnPrefix + "_sales_order_item_number"));
        columns.add(Column.aliased("is_asset_tagged", table, columnPrefix + "_is_asset_tagged"));
        columns.add(Column.aliased("item_serial_no", table, columnPrefix + "_item_serial_no"));
        columns.add(Column.aliased("sales_order_details_icd_pointer", table, columnPrefix + "_sales_order_details_icd_pointer"));
        columns.add(Column.aliased("procedure_identifier", table, columnPrefix + "_procedure_identifier"));
        columns.add(Column.aliased("par_no", table, columnPrefix + "_par_no"));
        columns.add(Column.aliased("whether_serialised", table, columnPrefix + "_whether_serialised"));
        columns.add(Column.aliased("pickup_exchange_no", table, columnPrefix + "_pickup_exchange_no"));
        columns.add(Column.aliased("sales_order_abn_user_response", table, columnPrefix + "_sales_order_abn_user_response"));
        columns.add(Column.aliased("is_dropship_allowed", table, columnPrefix + "_is_dropship_allowed"));
        columns.add(Column.aliased("po_number", table, columnPrefix + "_po_number"));
        columns.add(Column.aliased("purchase_order_uuid", table, columnPrefix + "_purchase_order_uuid"));
        columns.add(Column.aliased("is_resupply_type", table, columnPrefix + "_is_resupply_type"));
        columns.add(Column.aliased("frequency_count", table, columnPrefix + "_frequency_count"));
        columns.add(Column.aliased("frequency_interval", table, columnPrefix + "_frequency_interval"));
        columns.add(Column.aliased("item_group_id", table, columnPrefix + "_item_group_id"));

        return columns;
    }
}
