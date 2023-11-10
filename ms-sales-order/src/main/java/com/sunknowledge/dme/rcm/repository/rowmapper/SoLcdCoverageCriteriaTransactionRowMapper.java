package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SoLcdCoverageCriteriaTransaction}, with proper type conversions.
 */
@Service
public class SoLcdCoverageCriteriaTransactionRowMapper implements BiFunction<Row, String, SoLcdCoverageCriteriaTransaction> {

    private final ColumnConverter converter;

    public SoLcdCoverageCriteriaTransactionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SoLcdCoverageCriteriaTransaction} stored in the database.
     */
    @Override
    public SoLcdCoverageCriteriaTransaction apply(Row row, String prefix) {
        SoLcdCoverageCriteriaTransaction entity = new SoLcdCoverageCriteriaTransaction();
        entity.setSoLcdDocRefId(converter.fromRow(row, prefix + "_so_lcd_doc_ref_id", Long.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setHcpcsCode(converter.fromRow(row, prefix + "_hcpcs_code", String.class));
        entity.setChecklistId(converter.fromRow(row, prefix + "_checklist_id", Long.class));
        entity.setChecklistName(converter.fromRow(row, prefix + "_checklist_name", String.class));
        entity.setCoverageCriteriaId(converter.fromRow(row, prefix + "_coverage_criteria_id", Long.class));
        entity.setValue(converter.fromRow(row, prefix + "_value", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setSoLcdCoverageCriteriaTransactionUuid(
            converter.fromRow(row, prefix + "_so_lcd_coverage_criteria_transaction_uuid", UUID.class)
        );
        return entity;
    }
}
