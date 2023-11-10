package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SoRecurringPurchase;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SoRecurringPurchase}, with proper type conversions.
 */
@Service
public class SoRecurringPurchaseRowMapper implements BiFunction<Row, String, SoRecurringPurchase> {

    private final ColumnConverter converter;

    public SoRecurringPurchaseRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SoRecurringPurchase} stored in the database.
     */
    @Override
    public SoRecurringPurchase apply(Row row, String prefix) {
        SoRecurringPurchase entity = new SoRecurringPurchase();
        entity.setRpId(converter.fromRow(row, prefix + "_rp_id", Long.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setProcCode(converter.fromRow(row, prefix + "_proc_code", String.class));
        entity.setQty(converter.fromRow(row, prefix + "_qty", Double.class));
        entity.setBillingInterval(converter.fromRow(row, prefix + "_billing_interval", Double.class));
        entity.setInitialDos(converter.fromRow(row, prefix + "_initial_dos", LocalDate.class));
        entity.setPeriod(converter.fromRow(row, prefix + "_period", Double.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setSoRecurringPurchaseUuid(converter.fromRow(row, prefix + "_so_recurring_purchase_uuid", UUID.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        return entity;
    }
}
