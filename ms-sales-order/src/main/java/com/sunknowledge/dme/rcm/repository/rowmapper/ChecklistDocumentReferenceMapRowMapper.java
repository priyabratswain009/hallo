package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ChecklistDocumentReferenceMap}, with proper type conversions.
 */
@Service
public class ChecklistDocumentReferenceMapRowMapper implements BiFunction<Row, String, ChecklistDocumentReferenceMap> {

    private final ColumnConverter converter;

    public ChecklistDocumentReferenceMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ChecklistDocumentReferenceMap} stored in the database.
     */
    @Override
    public ChecklistDocumentReferenceMap apply(Row row, String prefix) {
        ChecklistDocumentReferenceMap entity = new ChecklistDocumentReferenceMap();
        entity.setChecklistDocumentReferenceId(converter.fromRow(row, prefix + "_checklist_document_reference_id", Long.class));
        entity.setChecklistId(converter.fromRow(row, prefix + "_checklist_id", Long.class));
        entity.setChecklistName(converter.fromRow(row, prefix + "_checklist_name", String.class));
        entity.setDocumentReferenceId(converter.fromRow(row, prefix + "_document_reference_id", Long.class));
        entity.setDocumentReferenceName(converter.fromRow(row, prefix + "_document_reference_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setChecklistDocumentReferenceMapUuid(converter.fromRow(row, prefix + "_checklist_document_reference_map_uuid", UUID.class));
        return entity;
    }
}
