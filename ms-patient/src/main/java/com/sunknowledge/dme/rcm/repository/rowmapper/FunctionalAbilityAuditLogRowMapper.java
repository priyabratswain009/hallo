package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FunctionalAbilityAuditLog}, with proper type conversions.
 */
@Service
public class FunctionalAbilityAuditLogRowMapper implements BiFunction<Row, String, FunctionalAbilityAuditLog> {

    private final ColumnConverter converter;

    public FunctionalAbilityAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FunctionalAbilityAuditLog} stored in the database.
     */
    @Override
    public FunctionalAbilityAuditLog apply(Row row, String prefix) {
        FunctionalAbilityAuditLog entity = new FunctionalAbilityAuditLog();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setFunalAbityId(converter.fromRow(row, prefix + "_funal_abity_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        return entity;
    }
}
