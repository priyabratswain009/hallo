package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link DeliveryItems}, with proper type conversions.
 */
@Service
public class DeliveryItemsRowMapper implements BiFunction<Row, String, DeliveryItems> {

    private final ColumnConverter converter;

    public DeliveryItemsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DeliveryItems} stored in the database.
     */
    @Override
    public DeliveryItems apply(Row row, String prefix) {
        DeliveryItems entity = new DeliveryItems();
        entity.setDeliveryItemId(converter.fromRow(row, prefix + "_delivery_item_id", Long.class));
        entity.setDeliveryTicketId(converter.fromRow(row, prefix + "_delivery_ticket_id", Long.class));
        entity.setDeliveryTicketNo(converter.fromRow(row, prefix + "_delivery_ticket_no", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemNo(converter.fromRow(row, prefix + "_item_no", String.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setItemDescription(converter.fromRow(row, prefix + "_item_description", String.class));
        entity.setHcpcsCode(converter.fromRow(row, prefix + "_hcpcs_code", String.class));
        entity.setItemQuantity(converter.fromRow(row, prefix + "_item_quantity", Integer.class));
        entity.setItemUnit(converter.fromRow(row, prefix + "_item_unit", String.class));
        entity.setWhetherItemSerialized(converter.fromRow(row, prefix + "_whether_item_serialized", String.class));
        entity.setItemSerial(converter.fromRow(row, prefix + "_item_serial", String.class));
        entity.setItemBatchLotNo(converter.fromRow(row, prefix + "_item_batch_lot_no", String.class));
        entity.setItemMfgDate(converter.fromRow(row, prefix + "_item_mfg_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setDeliveryItemsUuid(converter.fromRow(row, prefix + "_delivery_items_uuid", UUID.class));
        entity.setChargedAmount(converter.fromRow(row, prefix + "_charged_amount", Double.class));
        entity.setAllowedAmount(converter.fromRow(row, prefix + "_allowed_amount", Double.class));
        entity.setItemManufacturerName(converter.fromRow(row, prefix + "_item_manufacturer_name", String.class));
        entity.setIsDropship(converter.fromRow(row, prefix + "_is_dropship", String.class));
        entity.setPoNumber(converter.fromRow(row, prefix + "_po_number", String.class));
        entity.setSoSaleType(converter.fromRow(row, prefix + "_so_sale_type", String.class));
        return entity;
    }
}
