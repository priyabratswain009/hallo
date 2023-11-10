package com.sunknowledge.dme.rcm.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PrimaryClaimSubmisionMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(
            Column.aliased("change_health_primary_submision_master_id", table, columnPrefix + "_change_health_primary_submision_master_id")
        );
        columns.add(Column.aliased("sales_order_id", table, columnPrefix + "_sales_order_id"));
        columns.add(Column.aliased("claim_control_no", table, columnPrefix + "_claim_control_no"));
        columns.add(Column.aliased("trading_partner_service_id", table, columnPrefix + "_trading_partner_service_id"));
        columns.add(Column.aliased("trading_partner_name", table, columnPrefix + "_trading_partner_name"));
        columns.add(Column.aliased("submitter_organization_name", table, columnPrefix + "_submitter_organization_name"));
        columns.add(Column.aliased("submitter_contact_person_name", table, columnPrefix + "_submitter_contact_person_name"));
        columns.add(Column.aliased("submitter_contact_no", table, columnPrefix + "_submitter_contact_no"));
        columns.add(Column.aliased("receiver_organization_name", table, columnPrefix + "_receiver_organization_name"));
        columns.add(Column.aliased("subscriber_member_id_no", table, columnPrefix + "_subscriber_member_id_no"));
        columns.add(
            Column.aliased(
                "subscriber_payment_responsibility_level_code",
                table,
                columnPrefix + "_subscriber_payment_responsibility_level_code"
            )
        );
        columns.add(Column.aliased("subscriber_first_name", table, columnPrefix + "_subscriber_first_name"));
        columns.add(Column.aliased("subscriber_last_name", table, columnPrefix + "_subscriber_last_name"));
        columns.add(Column.aliased("subscriber_gender", table, columnPrefix + "_subscriber_gender"));
        columns.add(Column.aliased("subscriber_dob", table, columnPrefix + "_subscriber_dob"));
        columns.add(Column.aliased("primary_insurer_policy_no", table, columnPrefix + "_primary_insurer_policy_no"));
        columns.add(Column.aliased("subscriber_address_line_1", table, columnPrefix + "_subscriber_address_line_1"));
        columns.add(Column.aliased("subscriber_city", table, columnPrefix + "_subscriber_city"));
        columns.add(Column.aliased("subscriber_state", table, columnPrefix + "_subscriber_state"));
        columns.add(Column.aliased("subscriber_zip_code", table, columnPrefix + "_subscriber_zip_code"));
        columns.add(Column.aliased("billing_provider_npi", table, columnPrefix + "_billing_provider_npi"));
        columns.add(Column.aliased("billing_provider_ein", table, columnPrefix + "_billing_provider_ein"));
        columns.add(Column.aliased("billing_provider_organization_name", table, columnPrefix + "_billing_provider_organization_name"));
        columns.add(Column.aliased("billing_provider_address_line_1", table, columnPrefix + "_billing_provider_address_line_1"));
        columns.add(Column.aliased("billing_provider_city", table, columnPrefix + "_billing_provider_city"));
        columns.add(Column.aliased("billing_provider_state", table, columnPrefix + "_billing_provider_state"));
        columns.add(Column.aliased("billing_provider_zip_code", table, columnPrefix + "_billing_provider_zip_code"));
        columns.add(Column.aliased("billing_provider_contact_person_name", table, columnPrefix + "_billing_provider_contact_person_name"));
        columns.add(Column.aliased("billing_provider_contact_no", table, columnPrefix + "_billing_provider_contact_no"));
        columns.add(Column.aliased("claim_filing_code", table, columnPrefix + "_claim_filing_code"));
        columns.add(Column.aliased("claim_charge_amount", table, columnPrefix + "_claim_charge_amount"));
        columns.add(Column.aliased("pos_code", table, columnPrefix + "_pos_code"));
        columns.add(Column.aliased("claim_frequency_code", table, columnPrefix + "_claim_frequency_code"));
        columns.add(Column.aliased("signature_indicator", table, columnPrefix + "_signature_indicator"));
        columns.add(Column.aliased("plan_participation_code", table, columnPrefix + "_plan_participation_code"));
        columns.add(
            Column.aliased(
                "benefits_assignment_certification_indicator",
                table,
                columnPrefix + "_benefits_assignment_certification_indicator"
            )
        );
        columns.add(Column.aliased("release_information_code", table, columnPrefix + "_release_information_code"));
        columns.add(Column.aliased("primary_diagnosis", table, columnPrefix + "_primary_diagnosis"));
        columns.add(Column.aliased("icd_10_diagnosis_code_1", table, columnPrefix + "_icd_10_diagnosis_code_1"));
        columns.add(Column.aliased("icd_10_diagnosis_code_2", table, columnPrefix + "_icd_10_diagnosis_code_2"));
        columns.add(Column.aliased("icd_10_diagnosis_code_3", table, columnPrefix + "_icd_10_diagnosis_code_3"));
        columns.add(Column.aliased("icd_10_diagnosis_code_4", table, columnPrefix + "_icd_10_diagnosis_code_4"));
        columns.add(Column.aliased("icd_10_diagnosis_code_5", table, columnPrefix + "_icd_10_diagnosis_code_5"));
        columns.add(Column.aliased("icd_10_diagnosis_code_6", table, columnPrefix + "_icd_10_diagnosis_code_6"));
        columns.add(Column.aliased("icd_10_diagnosis_code_7", table, columnPrefix + "_icd_10_diagnosis_code_7"));
        columns.add(Column.aliased("icd_10_diagnosis_code_8", table, columnPrefix + "_icd_10_diagnosis_code_8"));
        columns.add(Column.aliased("icd_10_diagnosis_code_9", table, columnPrefix + "_icd_10_diagnosis_code_9"));
        columns.add(Column.aliased("icd_10_diagnosis_code_10", table, columnPrefix + "_icd_10_diagnosis_code_10"));
        columns.add(Column.aliased("icd_10_diagnosis_code_11", table, columnPrefix + "_icd_10_diagnosis_code_11"));
        columns.add(Column.aliased("icd_10_diagnosis_code_12", table, columnPrefix + "_icd_10_diagnosis_code_12"));
        columns.add(Column.aliased("inserted_by_id", table, columnPrefix + "_inserted_by_id"));
        columns.add(Column.aliased("inserted_date", table, columnPrefix + "_inserted_date"));
        columns.add(Column.aliased("ipdated_by_id", table, columnPrefix + "_ipdated_by_id"));
        columns.add(Column.aliased("updated_date", table, columnPrefix + "_updated_date"));
        columns.add(Column.aliased("billing_provider_type", table, columnPrefix + "_billing_provider_type"));
        columns.add(Column.aliased("inserted_by_name", table, columnPrefix + "_inserted_by_name"));
        columns.add(Column.aliased("updated_by_name", table, columnPrefix + "_updated_by_name"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("billing_provider_address_line_2", table, columnPrefix + "_billing_provider_address_line_2"));
        columns.add(Column.aliased("insured_first_name", table, columnPrefix + "_insured_first_name"));
        columns.add(Column.aliased("insured_last_name", table, columnPrefix + "_insured_last_name"));
        columns.add(Column.aliased("insured_address_line_1", table, columnPrefix + "_insured_address_line_1"));
        columns.add(Column.aliased("insured_address_line_2", table, columnPrefix + "_insured_address_line_2"));
        columns.add(Column.aliased("insured_city", table, columnPrefix + "_insured_city"));
        columns.add(Column.aliased("insured_state", table, columnPrefix + "_insured_state"));
        columns.add(Column.aliased("insured_zip", table, columnPrefix + "_insured_zip"));
        columns.add(Column.aliased("insured_contact_no", table, columnPrefix + "_insured_contact_no"));
        columns.add(Column.aliased("insured_dob", table, columnPrefix + "_insured_dob"));
        columns.add(Column.aliased("insured_gender", table, columnPrefix + "_insured_gender"));
        columns.add(Column.aliased("ordering_provider_first_name", table, columnPrefix + "_ordering_provider_first_name"));
        columns.add(Column.aliased("ordering_provider_last_name", table, columnPrefix + "_ordering_provider_last_name"));
        columns.add(Column.aliased("ordering_provider_npi", table, columnPrefix + "_ordering_provider_npi"));
        columns.add(Column.aliased("patient_relationship_insured", table, columnPrefix + "_patient_relationship_insured"));
        columns.add(Column.aliased("patient_condition_employment", table, columnPrefix + "_patient_condition_employment"));
        columns.add(Column.aliased("patient_condition_auto_accident", table, columnPrefix + "_patient_condition_auto_accident"));
        columns.add(Column.aliased("patient_condition_other_accident", table, columnPrefix + "_patient_condition_other_accident"));
        columns.add(Column.aliased("is_next_level_insurer_present_status", table, columnPrefix + "_is_next_level_insurer_present_status"));
        columns.add(Column.aliased("original_dos", table, columnPrefix + "_original_dos"));
        columns.add(Column.aliased("par_no", table, columnPrefix + "_par_no"));
        columns.add(Column.aliased("billing_provider_taxonomy", table, columnPrefix + "_billing_provider_taxonomy"));
        columns.add(Column.aliased("service_provider_npi", table, columnPrefix + "_service_provider_npi"));
        columns.add(Column.aliased("service_provider_organisation_name", table, columnPrefix + "_service_provider_organisation_name"));
        columns.add(Column.aliased("service_provider_address_line_1", table, columnPrefix + "_service_provider_address_line_1"));
        columns.add(Column.aliased("service_provider_address_line_2", table, columnPrefix + "_service_provider_address_line_2"));
        columns.add(Column.aliased("service_provider_city", table, columnPrefix + "_service_provider_city"));
        columns.add(Column.aliased("service_provider_state", table, columnPrefix + "_service_provider_state"));
        columns.add(Column.aliased("service_provider_country", table, columnPrefix + "_service_provider_country"));
        columns.add(Column.aliased("service_provider_zip_code", table, columnPrefix + "_service_provider_zip_code"));
        columns.add(Column.aliased("service_provider_taxonomy", table, columnPrefix + "_service_provider_taxonomy"));
        columns.add(Column.aliased("cms_1500_form_name", table, columnPrefix + "_cms_1500_form_name"));
        columns.add(Column.aliased("trading_partner_address_line_1", table, columnPrefix + "_trading_partner_address_line_1"));
        columns.add(Column.aliased("trading_partner_address_line_2", table, columnPrefix + "_trading_partner_address_line_2"));
        columns.add(Column.aliased("trading_patner_city", table, columnPrefix + "_trading_patner_city"));
        columns.add(Column.aliased("trading_partner_state", table, columnPrefix + "_trading_partner_state"));
        columns.add(Column.aliased("trading_partner_zip", table, columnPrefix + "_trading_partner_zip"));
        columns.add(Column.aliased("diagnosis_code_type", table, columnPrefix + "_diagnosis_code_type"));
        columns.add(Column.aliased("primary_claim_submision_master_uuid", table, columnPrefix + "_primary_claim_submision_master_uuid"));

        return columns;
    }
}
