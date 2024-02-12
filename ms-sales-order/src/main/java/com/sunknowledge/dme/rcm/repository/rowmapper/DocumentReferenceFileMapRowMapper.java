package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link DocumentReferenceFileMap}, with proper type conversions.
 */
@Service
public class DocumentReferenceFileMapRowMapper implements BiFunction<Row, String, DocumentReferenceFileMap> {

    private final ColumnConverter converter;

    public DocumentReferenceFileMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DocumentReferenceFileMap} stored in the database.
     */
    @Override
    public DocumentReferenceFileMap apply(Row row, String prefix) {
        DocumentReferenceFileMap entity = new DocumentReferenceFileMap();
        entity.setDocumentReferenceFileMapId(converter.fromRow(row, prefix + "_document_reference_file_map_id", Long.class));
        entity.setChecklistId(converter.fromRow(row, prefix + "_checklist_id", Long.class));
        entity.setChecklistName(converter.fromRow(row, prefix + "_checklist_name", String.class));
        entity.setCoverageCriteriaId(converter.fromRow(row, prefix + "_coverage_criteria_id", Long.class));
        entity.setFileName(converter.fromRow(row, prefix + "_file_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setDocumentReferenceFileMapUuid(converter.fromRow(row, prefix + "_document_reference_file_map_uuid", UUID.class));
        entity.setFileReference(converter.fromRow(row, prefix + "_file_reference", String.class));
        entity.setDocumentReferenceNotes(converter.fromRow(row, prefix + "_document_reference_notes", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setItemGroupId(converter.fromRow(row, prefix + "_item_group_id", Long.class));
        entity.setItemGroupName(converter.fromRow(row, prefix + "_item_group_name", String.class));
        return entity;
    }
}
