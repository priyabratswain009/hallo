package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CmnSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("cmn_id", table, columnPrefix + "_cmn_id"));
        columns.add(Column.aliased("cmn_number", table, columnPrefix + "_cmn_number"));
        columns.add(Column.aliased("cmn_type", table, columnPrefix + "_cmn_type"));
        columns.add(Column.aliased("cmn_form_name", table, columnPrefix + "_cmn_form_name"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("sales_order_no", table, columnPrefix + "_sales_order_no"));
        columns.add(Column.aliased("cmn_create_date", table, columnPrefix + "_cmn_create_date"));
        columns.add(Column.aliased("cmn_initial_date", table, columnPrefix + "_cmn_initial_date"));
        columns.add(Column.aliased("cmn_revision_date", table, columnPrefix + "_cmn_revision_date"));
        columns.add(Column.aliased("cmn_recertification_date", table, columnPrefix + "_cmn_recertification_date"));
        columns.add(Column.aliased("cmn_expiration_date", table, columnPrefix + "_cmn_expiration_date"));
        columns.add(Column.aliased("cmn_logged_by", table, columnPrefix + "_cmn_logged_by"));
        columns.add(Column.aliased("cmn_logged_date", table, columnPrefix + "_cmn_logged_date"));
        columns.add(Column.aliased("cmn_approved_by", table, columnPrefix + "_cmn_approved_by"));
        columns.add(Column.aliased("cmn_approved_date", table, columnPrefix + "_cmn_approved_date"));
        columns.add(Column.aliased("cmn_printed_by", table, columnPrefix + "_cmn_printed_by"));
        columns.add(Column.aliased("cmn_printed_date", table, columnPrefix + "_cmn_printed_date"));
        columns.add(Column.aliased("length_of_need", table, columnPrefix + "_length_of_need"));
        columns.add(Column.aliased("fax_cmn_option", table, columnPrefix + "_fax_cmn_option"));
        columns.add(Column.aliased("cmn_cover_letter_inclusion_option", table, columnPrefix + "_cmn_cover_letter_inclusion_option"));
        columns.add(Column.aliased("cmn_faxed_by", table, columnPrefix + "_cmn_faxed_by"));
        columns.add(Column.aliased("cmn_faxed_date", table, columnPrefix + "_cmn_faxed_date"));
        columns.add(Column.aliased("fax_status", table, columnPrefix + "_fax_status"));
        columns.add(Column.aliased("fax_status_reason", table, columnPrefix + "_fax_status_reason"));
        columns.add(Column.aliased("print_cmn_option", table, columnPrefix + "_print_cmn_option"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("cmn_id_uuid", table, columnPrefix + "_cmn_id_uuid"));
        columns.add(Column.aliased("patient_prognosis", table, columnPrefix + "_patient_prognosis"));
        columns.add(Column.aliased("cmn_status", table, columnPrefix + "_cmn_status"));
        columns.add(Column.aliased("refill_authorised", table, columnPrefix + "_refill_authorised"));

        return columns;
    }
}
