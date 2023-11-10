package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ClaimSubmissionStatus}, with proper type conversions.
 */
@Service
public class ClaimSubmissionStatusRowMapper implements BiFunction<Row, String, ClaimSubmissionStatus> {

    private final ColumnConverter converter;

    public ClaimSubmissionStatusRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ClaimSubmissionStatus} stored in the database.
     */
    @Override
    public ClaimSubmissionStatus apply(Row row, String prefix) {
        ClaimSubmissionStatus entity = new ClaimSubmissionStatus();
        entity.setClaimStatusId(converter.fromRow(row, prefix + "_claim_status_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setSalesOrderNo(converter.fromRow(row, prefix + "_sales_order_no", String.class));
        entity.setInvoiceId(converter.fromRow(row, prefix + "_invoice_id", Long.class));
        entity.setInvoiceNo(converter.fromRow(row, prefix + "_invoice_no", String.class));
        entity.setPayorLevel(converter.fromRow(row, prefix + "_payor_level", String.class));
        entity.setPayorIdNo(converter.fromRow(row, prefix + "_payor_id_no", String.class));
        entity.setClaimSubmissionDataId(converter.fromRow(row, prefix + "_claim_submission_data_id", Long.class));
        entity.setIntClaimNo(converter.fromRow(row, prefix + "_int_claim_no", String.class));
        entity.setPatientAccountNo(converter.fromRow(row, prefix + "_patient_account_no", String.class));
        entity.setPayorClaimControlNo(converter.fromRow(row, prefix + "_payor_claim_control_no", String.class));
        entity.setOriginalClaimControlNo(converter.fromRow(row, prefix + "_original_claim_control_no", String.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setPayor(converter.fromRow(row, prefix + "_payor", String.class));
        entity.setClaimSubmissionDate(converter.fromRow(row, prefix + "_claim_submission_date", LocalDate.class));
        entity.setSubmissionStatus(converter.fromRow(row, prefix + "_submission_status", String.class));
        entity.setSubmissionNote(converter.fromRow(row, prefix + "_submission_note", String.class));
        entity.setResponseStatus(converter.fromRow(row, prefix + "_response_status", String.class));
        entity.setResponseStatusNotes(converter.fromRow(row, prefix + "_response_status_notes", String.class));
        entity.setResponseStatusDate(converter.fromRow(row, prefix + "_response_status_date", LocalDate.class));
        entity.setResponse277RecordId(converter.fromRow(row, prefix + "_response_277_record_id", Long.class));
        entity.setEraStatus(converter.fromRow(row, prefix + "_era_status", String.class));
        entity.setEraStatusNotes(converter.fromRow(row, prefix + "_era_status_notes", String.class));
        entity.setEraDate(converter.fromRow(row, prefix + "_era_date", LocalDate.class));
        entity.setEra835RecordId(converter.fromRow(row, prefix + "_era_835_record_id", Long.class));
        entity.setResubmissinStatus(converter.fromRow(row, prefix + "_resubmissin_status", String.class));
        entity.setResubmissionDetailId(converter.fromRow(row, prefix + "_resubmission_detail_id", Long.class));
        entity.setResubmissionNotes(converter.fromRow(row, prefix + "_resubmission_notes", String.class));
        entity.setVoidStatus(converter.fromRow(row, prefix + "_void_status", String.class));
        entity.setVoidNote(converter.fromRow(row, prefix + "_void_note", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setClaimSubmissionStatusUuid(converter.fromRow(row, prefix + "_claim_submission_status_uuid", UUID.class));
        return entity;
    }
}
