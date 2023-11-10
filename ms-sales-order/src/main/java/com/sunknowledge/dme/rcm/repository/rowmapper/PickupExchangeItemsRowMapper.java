package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PickupExchangeItems}, with proper type conversions.
 */
@Service
public class PickupExchangeItemsRowMapper implements BiFunction<Row, String, PickupExchangeItems> {

    private final ColumnConverter converter;

    public PickupExchangeItemsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PickupExchangeItems} stored in the database.
     */
    @Override
    public PickupExchangeItems apply(Row row, String prefix) {
        PickupExchangeItems entity = new PickupExchangeItems();
        entity.setPickupExchangeItemId(converter.fromRow(row, prefix + "_pickup_exchange_item_id", Long.class));
        entity.setPickupExchangeId(converter.fromRow(row, prefix + "_pickup_exchange_id", Long.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemNo(converter.fromRow(row, prefix + "_item_no", String.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setWhetherSerialized(converter.fromRow(row, prefix + "_whether_serialized", String.class));
        entity.setPickupItemSerialNo(converter.fromRow(row, prefix + "_pickup_item_serial_no", String.class));
        entity.setPickupItemAssetNo(converter.fromRow(row, prefix + "_pickup_item_asset_no", String.class));
        entity.setReplacementItemSerialNo(converter.fromRow(row, prefix + "_replacement_item_serial_no", String.class));
        entity.setReplacementItemAssetNo(converter.fromRow(row, prefix + "_replacement_item_asset_no", String.class));
        entity.setQuantity(converter.fromRow(row, prefix + "_quantity", Double.class));
        entity.setItemPickupExchangeType(converter.fromRow(row, prefix + "_item_pickup_exchange_type", String.class));
        entity.setItemPickupExchangeNote(converter.fromRow(row, prefix + "_item_pickup_exchange_note", String.class));
        entity.setItemPickupExchangeComment(converter.fromRow(row, prefix + "_item_pickup_exchange_comment", String.class));
        entity.setItemPickupExchangeStatus(converter.fromRow(row, prefix + "_item_pickup_exchange_status", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPickupExchangeItemUuid(converter.fromRow(row, prefix + "_pickup_exchange_item_uuid", UUID.class));
        return entity;
    }
}
