package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ParMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ParMaster}, with proper type conversions.
 */
@Service
public class ParMasterRowMapper implements BiFunction<Row, String, ParMaster> {

    private final ColumnConverter converter;

    public ParMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ParMaster} stored in the database.
     */
    @Override
    public ParMaster apply(Row row, String prefix) {
        ParMaster entity = new ParMaster();
        entity.setParId(converter.fromRow(row, prefix + "_par_id", Long.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientIdNumber(converter.fromRow(row, prefix + "_patient_id_number", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setPatientDob(converter.fromRow(row, prefix + "_patient_dob", LocalDate.class));
        entity.setPatientGender(converter.fromRow(row, prefix + "_patient_gender", String.class));
        entity.setInsuranceId(converter.fromRow(row, prefix + "_insurance_id", Long.class));
        entity.setInsuranceName(converter.fromRow(row, prefix + "_insurance_name", String.class));
        entity.setPayerIdNo(converter.fromRow(row, prefix + "_payer_id_no", String.class));
        entity.setPayerLevel(converter.fromRow(row, prefix + "_payer_level", String.class));
        entity.setPolicyNumber(converter.fromRow(row, prefix + "_policy_number", String.class));
        entity.setPolicyStartDate(converter.fromRow(row, prefix + "_policy_start_date", LocalDate.class));
        entity.setPolicyEndDate(converter.fromRow(row, prefix + "_policy_end_date", LocalDate.class));
        entity.setPayerContactNumber(converter.fromRow(row, prefix + "_payer_contact_number", String.class));
        entity.setPayerContactName(converter.fromRow(row, prefix + "_payer_contact_name", String.class));
        entity.setDateOfContact(converter.fromRow(row, prefix + "_date_of_contact", LocalDate.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setInitialDate(converter.fromRow(row, prefix + "_initial_date", LocalDate.class));
        entity.setEffectiveStartDate(converter.fromRow(row, prefix + "_effective_start_date", LocalDate.class));
        entity.setExpirationDate(converter.fromRow(row, prefix + "_expiration_date", LocalDate.class));
        entity.setAuthorizedBy(converter.fromRow(row, prefix + "_authorized_by", String.class));
        entity.setAddlInformation(converter.fromRow(row, prefix + "_addl_information", String.class));
        entity.setParStatus(converter.fromRow(row, prefix + "_par_status", String.class));
        entity.setComments(converter.fromRow(row, prefix + "_comments", String.class));
        entity.setLogInStatus(converter.fromRow(row, prefix + "_log_in_status", String.class));
        entity.setLogInDate(converter.fromRow(row, prefix + "_log_in_date", LocalDate.class));
        entity.setRenewalStatus(converter.fromRow(row, prefix + "_renewal_status", String.class));
        entity.setRenewalDate(converter.fromRow(row, prefix + "_renewal_date", LocalDate.class));
        entity.setRenewalAuthorizedBy(converter.fromRow(row, prefix + "_renewal_authorized_by", String.class));
        entity.setRenewalReqSentStatus(converter.fromRow(row, prefix + "_renewal_req_sent_status", String.class));
        entity.setRenewalReqReplyStatus(converter.fromRow(row, prefix + "_renewal_req_reply_status", String.class));
        entity.setOriginalParNo(converter.fromRow(row, prefix + "_original_par_no", String.class));
        entity.setExtensionStatus(converter.fromRow(row, prefix + "_extension_status", String.class));
        entity.setExtensionApprovalDate(converter.fromRow(row, prefix + "_extension_approval_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setParUuid(converter.fromRow(row, prefix + "_par_uuid", UUID.class));
        entity.setParIdNo(converter.fromRow(row, prefix + "_par_id_no", String.class));
        return entity;
    }
}
