package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDocument}, with proper type conversions.
 */
@Service
public class PatientDocumentRowMapper implements BiFunction<Row, String, PatientDocument> {

    private final ColumnConverter converter;

    public PatientDocumentRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDocument} stored in the database.
     */
    @Override
    public PatientDocument apply(Row row, String prefix) {
        PatientDocument entity = new PatientDocument();
        entity.setPatientDocumentId(converter.fromRow(row, prefix + "_patient_document_id", Long.class));
        entity.setPatientDocumentNo(converter.fromRow(row, prefix + "_patient_document_no", String.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientUuid(converter.fromRow(row, prefix + "_patient_uuid", UUID.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setPatientDocumentType(converter.fromRow(row, prefix + "_patient_document_type", String.class));
        entity.setPatientDocumentRepositoryName(converter.fromRow(row, prefix + "_patient_document_repository_name", String.class));
        entity.setPatientDocumentName(converter.fromRow(row, prefix + "_patient_document_name", String.class));
        entity.setPatientDocumentDescription(converter.fromRow(row, prefix + "_patient_document_description", String.class));
        entity.setPatientDocumentStatus(converter.fromRow(row, prefix + "_patient_document_status", String.class));
        entity.setUploadedById(converter.fromRow(row, prefix + "_uploaded_by_id", Long.class));
        entity.setUploadedByName(converter.fromRow(row, prefix + "_uploaded_by_name", String.class));
        entity.setUploadedDate(converter.fromRow(row, prefix + "_uploaded_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setPatientDocumentUuid(converter.fromRow(row, prefix + "_patient_document_uuid", UUID.class));
        return entity;
    }
}
