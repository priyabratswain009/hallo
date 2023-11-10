package com.sunknowledge.dme.rcm.dto.soItemDetailsAndClicnicalAndInsurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoItemDetailsAndClinicalAndInsuranceResponseData {
    private Long sales_order_id;
    private String sales_order_no;
    private Long patient_id;
    private String patient_id_no;
    private String patient_first_name;
    private String patient_middle_name;
    private String patient_last_name;
    private String patient_address_line_1;
    private String patient_address_line_2;
    private String patient_contact_no_1;
    private String patient_contact_no_2;
    private LocalDate patient_dob;
    private String branch_name;
    private Long branch_id;
    private String branch_address_line_1;
    private String branch_address_line_2;
    private String branch_city;
    private String branch_state;
    private String branch_zip_code;
    private String branch_fax;
    private String branch_country;
    private String branch_contact_no_1;
    private String branch_contact_person_name;
    private Long tax_zone_id;
    private Double tax_rate;
    private String city_name;
    private String state_name;
    private String zip_code;
    private Long sales_order_insurance_details_id;
    private Long primary_insurer_id;
    private String primary_insurer_name;
    private String primary_insurer_policy_no;
    private String primary_insurer_patient_id_number;
    private LocalDate primary_insurer_effective_date;
    private Long primary_insurer_pay_percentage;
    private String primary_insurer_address_line_1;
    private String primary_insurer_address_line_2;
    private String primary_insurer_city;
    private String primary_insurer_state;
    private String primary_insurer_zip;
    private String primary_insurer_contact_1;
    private String primary_insurer_fax;
    private Long price_table_id;
    private String price_table_name;
    private Long sales_order_clinical_details_id;
    private Double patient_weight_in_kg;
    private Double patient_weight_in_lbs;
    private Double height_in_inches;
    private Double height_in_cm;
    private Long ordering_provider_facility_id;
    private String ordering_provider_facility_name;
    private Long ordering_provider_id;
    private String ordering_provider_type;
    private String ordering_provider_first_name;
    private String ordering_provider_middle_name;
    private String ordering_provider_last_name;
    private String ordering_provider_npi;
    private String ordering_provider_dea;
    private String ordering_provider_address_line_1;
    private String ordering_provider_address_line_2;
    private String ordering_provider_email;
    private String ordering_provider_fax;
    private String ordering_provider_city;
    private String ordering_provider_state;
    private String ordering_provider_country;
    private String ordering_provider_contact_no_1;
    private String ordering_provider_contact_no_2;
    private String ordering_provider_efax;
    private String icd_10_diagnosis_code_1;
    private String primary_diagnosis;
    private String diagnosis_code_type;
    private String primary_insurer_contact_name;
    private String primary_insurance_payer_id;
    private String patient_gender;
    private String patient_email;
    private String contact_person_name;
}
