package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ParDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ParDetails}, with proper type conversions.
 */
@Service
public class ParDetailsRowMapper implements BiFunction<Row, String, ParDetails> {

    private final ColumnConverter converter;

    public ParDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ParDetails} stored in the database.
     */
    @Override
    public ParDetails apply(Row row, String prefix) {
        ParDetails entity = new ParDetails();
        entity.setParDetailsId(converter.fromRow(row, prefix + "_par_details_id", Long.class));
        entity.setParId(converter.fromRow(row, prefix + "_par_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemNo(converter.fromRow(row, prefix + "_item_no", String.class));
        entity.setItemUom(converter.fromRow(row, prefix + "_item_uom", String.class));
        entity.setItemQuantity(converter.fromRow(row, prefix + "_item_quantity", Double.class));
        entity.setHcpcsCode(converter.fromRow(row, prefix + "_hcpcs_code", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setParDetailsUuid(converter.fromRow(row, prefix + "_par_details_uuid", UUID.class));
        return entity;
    }
}
