package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SoLcdDocRefTransaction}, with proper type conversions.
 */
@Service
public class SoLcdDocRefTransactionRowMapper implements BiFunction<Row, String, SoLcdDocRefTransaction> {

    private final ColumnConverter converter;

    public SoLcdDocRefTransactionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SoLcdDocRefTransaction} stored in the database.
     */
    @Override
    public SoLcdDocRefTransaction apply(Row row, String prefix) {
        SoLcdDocRefTransaction entity = new SoLcdDocRefTransaction();
        entity.setSoLcdDocRefId(converter.fromRow(row, prefix + "_so_lcd_doc_ref_id", Long.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setHcpcsCode(converter.fromRow(row, prefix + "_hcpcs_code", String.class));
        entity.setChecklistId(converter.fromRow(row, prefix + "_checklist_id", Long.class));
        entity.setChecklistName(converter.fromRow(row, prefix + "_checklist_name", String.class));
        entity.setDocRefId(converter.fromRow(row, prefix + "_doc_ref_id", Long.class));
        entity.setDocRefName(converter.fromRow(row, prefix + "_doc_ref_name", String.class));
        entity.setValue(converter.fromRow(row, prefix + "_value", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setSoLcdDocRefTransactionUuid(converter.fromRow(row, prefix + "_so_lcd_doc_ref_transaction_uuid", UUID.class));
        return entity;
    }
}
