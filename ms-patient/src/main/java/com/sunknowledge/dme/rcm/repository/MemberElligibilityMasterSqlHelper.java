package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class MemberElligibilityMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("claim_elligibility_master_id", table, columnPrefix + "_claim_elligibility_master_id"));
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("elligibility_control_number", table, columnPrefix + "_elligibility_control_number"));
        columns.add(Column.aliased("trading_partner_service_id", table, columnPrefix + "_trading_partner_service_id"));
        columns.add(Column.aliased("trading_partner_name", table, columnPrefix + "_trading_partner_name"));
        columns.add(Column.aliased("provider_organization_name", table, columnPrefix + "_provider_organization_name"));
        columns.add(Column.aliased("provider_npi", table, columnPrefix + "_provider_npi"));
        columns.add(Column.aliased("provider_type", table, columnPrefix + "_provider_type"));
        columns.add(Column.aliased("subscriber_first_name", table, columnPrefix + "_subscriber_first_name"));
        columns.add(Column.aliased("subscriber_last_name", table, columnPrefix + "_subscriber_last_name"));
        columns.add(Column.aliased("subscriber_member_id", table, columnPrefix + "_subscriber_member_id"));
        columns.add(Column.aliased("subscriber_idcard", table, columnPrefix + "_subscriber_idcard"));
        columns.add(Column.aliased("subscriber_dob", table, columnPrefix + "_subscriber_dob"));
        columns.add(Column.aliased("subscriber_gender", table, columnPrefix + "_subscriber_gender"));
        columns.add(Column.aliased("subscriber_plan_issue_date", table, columnPrefix + "_subscriber_plan_issue_date"));
        columns.add(Column.aliased("insured_first_name", table, columnPrefix + "_insured_first_name"));
        columns.add(Column.aliased("insured_last_name", table, columnPrefix + "_insured_last_name"));
        columns.add(Column.aliased("insured_gender", table, columnPrefix + "_insured_gender"));
        columns.add(Column.aliased("insured_dob", table, columnPrefix + "_insured_dob"));
        columns.add(Column.aliased("insured_relationshipwith_subscriber", table, columnPrefix + "_insured_relationshipwith_subscriber"));
        columns.add(Column.aliased("date_of_service", table, columnPrefix + "_date_of_service"));
        columns.add(Column.aliased("service_type_codes", table, columnPrefix + "_service_type_codes"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_by_id", table, columnPrefix + "_created_by_id"));
        columns.add(Column.aliased("created_by_name", table, columnPrefix + "_created_by_name"));
        columns.add(Column.aliased("created_date", table, columnPrefix + "_created_date"));
        columns.add(Column.aliased("updated_by_id", table, columnPrefix + "_updated_by_id"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("member_elligibility_master_uuid", table, columnPrefix + "_member_elligibility_master_uuid"));

        return columns;
    }
}
