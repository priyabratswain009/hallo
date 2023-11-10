package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link WorkersCompensationAuditLog}, with proper type conversions.
 */
@Service
public class WorkersCompensationAuditLogRowMapper implements BiFunction<Row, String, WorkersCompensationAuditLog> {

    private final ColumnConverter converter;

    public WorkersCompensationAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link WorkersCompensationAuditLog} stored in the database.
     */
    @Override
    public WorkersCompensationAuditLog apply(Row row, String prefix) {
        WorkersCompensationAuditLog entity = new WorkersCompensationAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setWorersComenationId(converter.fromRow(row, prefix + "_worers_comenation_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
