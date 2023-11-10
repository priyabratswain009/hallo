package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderFinancialDetailsAuditLog}, with proper type conversions.
 */
@Service
public class SalesOrderFinancialDetailsAuditLogRowMapper implements BiFunction<Row, String, SalesOrderFinancialDetailsAuditLog> {

    private final ColumnConverter converter;

    public SalesOrderFinancialDetailsAuditLogRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderFinancialDetailsAuditLog} stored in the database.
     */
    @Override
    public SalesOrderFinancialDetailsAuditLog apply(Row row, String prefix) {
        SalesOrderFinancialDetailsAuditLog entity = new SalesOrderFinancialDetailsAuditLog();
        entity.setSalsOrdrFincialId(converter.fromRow(row, prefix + "_sals_ordr_fincial_id", Long.class));
        entity.setOldRowData(converter.fromRow(row, prefix + "_old_row_data", String.class));
        entity.setNewRowData(converter.fromRow(row, prefix + "_new_row_data", String.class));
        entity.setDmlType(converter.fromRow(row, prefix + "_dml_type", String.class));
        entity.setDmlTimestamp(converter.fromRow(row, prefix + "_dml_timestamp", LocalDate.class));
        entity.setDmlCreatedBy(converter.fromRow(row, prefix + "_dml_created_by", String.class));
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        return entity;
    }
}
