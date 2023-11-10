package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ParMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("par_id", table, columnPrefix + "_par_id"));
        columns.add(Column.aliased("par_no", table, columnPrefix + "_par_no"));
        columns.add(Column.aliased("patient_id", table, columnPrefix + "_patient_id"));
        columns.add(Column.aliased("patient_id_number", table, columnPrefix + "_patient_id_number"));
        columns.add(Column.aliased("patient_first_name", table, columnPrefix + "_patient_first_name"));
        columns.add(Column.aliased("patient_last_name", table, columnPrefix + "_patient_last_name"));
        columns.add(Column.aliased("patient_dob", table, columnPrefix + "_patient_dob"));
        columns.add(Column.aliased("patient_gender", table, columnPrefix + "_patient_gender"));
        columns.add(Column.aliased("insurance_id", table, columnPrefix + "_insurance_id"));
        columns.add(Column.aliased("insurance_name", table, columnPrefix + "_insurance_name"));
        columns.add(Column.aliased("payer_id_no", table, columnPrefix + "_payer_id_no"));
        columns.add(Column.aliased("payer_level", table, columnPrefix + "_payer_level"));
        columns.add(Column.aliased("policy_number", table, columnPrefix + "_policy_number"));
        columns.add(Column.aliased("policy_start_date", table, columnPrefix + "_policy_start_date"));
        columns.add(Column.aliased("policy_end_date", table, columnPrefix + "_policy_end_date"));
        columns.add(Column.aliased("payer_contact_number", table, columnPrefix + "_payer_contact_number"));
        columns.add(Column.aliased("payer_contact_name", table, columnPrefix + "_payer_contact_name"));
        columns.add(Column.aliased("date_of_contact", table, columnPrefix + "_date_of_contact"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("initial_date", table, columnPrefix + "_initial_date"));
        columns.add(Column.aliased("effective_start_date", table, columnPrefix + "_effective_start_date"));
        columns.add(Column.aliased("expiration_date", table, columnPrefix + "_expiration_date"));
        columns.add(Column.aliased("authorized_by", table, columnPrefix + "_authorized_by"));
        columns.add(Column.aliased("addl_information", table, columnPrefix + "_addl_information"));
        columns.add(Column.aliased("par_status", table, columnPrefix + "_par_status"));
        columns.add(Column.aliased("comments", table, columnPrefix + "_comments"));
        columns.add(Column.aliased("log_in_status", table, columnPrefix + "_log_in_status"));
        columns.add(Column.aliased("log_in_date", table, columnPrefix + "_log_in_date"));
        columns.add(Column.aliased("renewal_status", table, columnPrefix + "_renewal_status"));
        columns.add(Column.aliased("renewal_date", table, columnPrefix + "_renewal_date"));
        columns.add(Column.aliased("renewal_authorized_by", table, columnPrefix + "_renewal_authorized_by"));
        columns.add(Column.aliased("renewal_req_sent_status", table, columnPrefix + "_renewal_req_sent_status"));
        columns.add(Column.aliased("renewal_req_reply_status", table, columnPrefix + "_renewal_req_reply_status"));
        columns.add(Column.aliased("original_par_no", table, columnPrefix + "_original_par_no"));
        columns.add(Column.aliased("extension_status", table, columnPrefix + "_extension_status"));
        columns.add(Column.aliased("extension_approval_date", table, columnPrefix + "_extension_approval_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("par_uuid", table, columnPrefix + "_par_uuid"));
        columns.add(Column.aliased("par_id_no", table, columnPrefix + "_par_id_no"));

        return columns;
    }
}
