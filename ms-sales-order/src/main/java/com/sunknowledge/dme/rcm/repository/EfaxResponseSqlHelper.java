package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class EfaxResponseSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("efax_response_id", table, columnPrefix + "_efax_response_id"));
        columns.add(Column.aliased("efax_received_date", table, columnPrefix + "_efax_received_date"));
        columns.add(Column.aliased("sender_email", table, columnPrefix + "_sender_email"));
        columns.add(Column.aliased("email_subject_line", table, columnPrefix + "_email_subject_line"));
        columns.add(Column.aliased("attachment_name", table, columnPrefix + "_attachment_name"));
        columns.add(Column.aliased("is_qr_decoded", table, columnPrefix + "_is_qr_decoded"));
        columns.add(Column.aliased("qr_value", table, columnPrefix + "_qr_value"));
        columns.add(Column.aliased("is_cmn", table, columnPrefix + "_is_cmn"));
        columns.add(Column.aliased("is_par", table, columnPrefix + "_is_par"));
        columns.add(Column.aliased("patient_id_no", table, columnPrefix + "_patient_id_no"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("cmn_id_no", table, columnPrefix + "_cmn_id_no"));
        columns.add(Column.aliased("par_id_no", table, columnPrefix + "_par_id_no"));
        columns.add(Column.aliased("so_id", table, columnPrefix + "_so_id"));
        columns.add(Column.aliased("so_no", table, columnPrefix + "_so_no"));
        columns.add(Column.aliased("is_manually_tagged", table, columnPrefix + "_is_manually_tagged"));
        columns.add(Column.aliased("is_patient_record", table, columnPrefix + "_is_patient_record"));
        columns.add(Column.aliased("efax_number", table, columnPrefix + "_efax_number"));
        columns.add(Column.aliased("is_po_ack", table, columnPrefix + "_is_po_ack"));
        columns.add(Column.aliased("document_rename_to", table, columnPrefix + "_document_rename_to"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("efax_response_uuid", table, columnPrefix + "_efax_response_uuid"));

        return columns;
    }
}
