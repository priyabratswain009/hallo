package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PayerMaster}, with proper type conversions.
 */
@Service
public class PayerMasterRowMapper implements BiFunction<Row, String, PayerMaster> {

    private final ColumnConverter converter;

    public PayerMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PayerMaster} stored in the database.
     */
    @Override
    public PayerMaster apply(Row row, String prefix) {
        PayerMaster entity = new PayerMaster();
        entity.setPayerMasterId(converter.fromRow(row, prefix + "_payer_master_id", Long.class));
        entity.setPayerId(converter.fromRow(row, prefix + "_payer_id", String.class));
        entity.setPayerName(converter.fromRow(row, prefix + "_payer_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPayerMasterUuid(converter.fromRow(row, prefix + "_payer_master_uuid", UUID.class));
        return entity;
    }
}
