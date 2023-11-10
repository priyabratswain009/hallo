package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDetails;
import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDetails}, with proper type conversions.
 */
@Service
public class PatientDetailsRowMapper implements BiFunction<Row, String, PatientDetails> {

    private final ColumnConverter converter;

    public PatientDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDetails} stored in the database.
     */
    @Override
    public PatientDetails apply(Row row, String prefix) {
        PatientDetails entity = new PatientDetails();
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientFname(converter.fromRow(row, prefix + "_patient_fname", String.class));
        entity.setPatientLname(converter.fromRow(row, prefix + "_patient_lname", String.class));
        entity.setDob(converter.fromRow(row, prefix + "_dob", Instant.class));
        entity.setAddress(converter.fromRow(row, prefix + "_address", String.class));
        entity.setCity(converter.fromRow(row, prefix + "_city", String.class));
        entity.setZip(converter.fromRow(row, prefix + "_zip", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setPhoneNo(converter.fromRow(row, prefix + "_phone_no", String.class));
        entity.setDocumentName(converter.fromRow(row, prefix + "_document_name", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setMrno(converter.fromRow(row, prefix + "_mrno", String.class));
        entity.setDateTime(converter.fromRow(row, prefix + "_date_time", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", Integer.class));
        entity.setIsTagged(converter.fromRow(row, prefix + "_is_tagged", Boolean.class));
        entity.setDocumentPath(converter.fromRow(row, prefix + "_document_path", String.class));
        entity.setQrCodeStatus(converter.fromRow(row, prefix + "_qr_code_status", Boolean.class));
        entity.setStateMasterId(converter.fromRow(row, prefix + "_state_master_state_id", Long.class));
        entity.setDocumentTypeId(converter.fromRow(row, prefix + "_document_type_document_type_id", Long.class));
        return entity;
    }
}
