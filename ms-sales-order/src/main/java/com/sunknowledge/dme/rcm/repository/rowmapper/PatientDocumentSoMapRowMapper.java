package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientDocumentSoMap}, with proper type conversions.
 */
@Service
public class PatientDocumentSoMapRowMapper implements BiFunction<Row, String, PatientDocumentSoMap> {

    private final ColumnConverter converter;

    public PatientDocumentSoMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientDocumentSoMap} stored in the database.
     */
    @Override
    public PatientDocumentSoMap apply(Row row, String prefix) {
        PatientDocumentSoMap entity = new PatientDocumentSoMap();
        entity.setPatientDocumentSoMapId(converter.fromRow(row, prefix + "_patient_document_so_map_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setPatientDocumentId(converter.fromRow(row, prefix + "_patient_document_id", Long.class));
        entity.setPatientDocumentNo(converter.fromRow(row, prefix + "_patient_document_no", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setUploadedById(converter.fromRow(row, prefix + "_uploaded_by_id", Long.class));
        entity.setUploadedByName(converter.fromRow(row, prefix + "_uploaded_by_name", String.class));
        entity.setUploadedDate(converter.fromRow(row, prefix + "_uploaded_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setPatientDocumentSoMapUuid(converter.fromRow(row, prefix + "_patient_document_so_map_uuid", UUID.class));
        return entity;
    }
}
