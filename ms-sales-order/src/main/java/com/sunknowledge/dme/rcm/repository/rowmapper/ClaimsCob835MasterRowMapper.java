package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Master;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ClaimsCob835Master}, with proper type conversions.
 */
@Service
public class ClaimsCob835MasterRowMapper implements BiFunction<Row, String, ClaimsCob835Master> {

    private final ColumnConverter converter;

    public ClaimsCob835MasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ClaimsCob835Master} stored in the database.
     */
    @Override
    public ClaimsCob835Master apply(Row row, String prefix) {
        ClaimsCob835Master entity = new ClaimsCob835Master();
        entity.setClaimCob835MasterId(converter.fromRow(row, prefix + "_claim_cob_835_master_id", Long.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setPatientMemberId(converter.fromRow(row, prefix + "_patient_member_id", String.class));
        entity.setFileName(converter.fromRow(row, prefix + "_file_name", String.class));
        entity.setTotalClaimChargeAmount(converter.fromRow(row, prefix + "_total_claim_charge_amount", Double.class));
        entity.setTotalClaimPaymentAmount(converter.fromRow(row, prefix + "_total_claim_payment_amount", Double.class));
        entity.setTotalPatientResponsibilityAmount(converter.fromRow(row, prefix + "_total_patient_responsibility_amount", Double.class));
        entity.setClaimReceivedDate(converter.fromRow(row, prefix + "_claim_received_date", LocalDate.class));
        entity.setReceivedOn(converter.fromRow(row, prefix + "_received_on", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setPatientControlNumber(converter.fromRow(row, prefix + "_patient_control_number", String.class));
        entity.setPayerClaimControlNumber(converter.fromRow(row, prefix + "_payer_claim_control_number", String.class));
        entity.setCheckOrEftTraceNumber(converter.fromRow(row, prefix + "_check_or_eft_trace_number", String.class));
        entity.setCheckIssueOrEftEffectiveDate(converter.fromRow(row, prefix + "_check_issue_or_eft_effective_date", LocalDate.class));
        entity.setCreditOrDebitFlagCode(converter.fromRow(row, prefix + "_credit_or_debit_flag_code", String.class));
        entity.setPaymentMethodCode(converter.fromRow(row, prefix + "_payment_method_code", String.class));
        entity.setCrossoverCarrierName(converter.fromRow(row, prefix + "_crossover_carrier_name", Boolean.class));
        entity.setEntityIdentifierCode(converter.fromRow(row, prefix + "_entity_identifier_code", String.class));
        entity.setEntityTypeQualifier(converter.fromRow(row, prefix + "_entity_type_qualifier", String.class));
        entity.setPayerName(converter.fromRow(row, prefix + "_payer_name", String.class));
        entity.setPayeeName(converter.fromRow(row, prefix + "_payee_name", String.class));
        entity.setPayeeNpi(converter.fromRow(row, prefix + "_payee_npi", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setClaimsCob835MasterUuid(converter.fromRow(row, prefix + "_claims_cob_835_master_uuid", UUID.class));
        return entity;
    }
}
