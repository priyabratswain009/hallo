package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link InsurancePricetableMap}, with proper type conversions.
 */
@Service
public class InsurancePricetableMapRowMapper implements BiFunction<Row, String, InsurancePricetableMap> {

    private final ColumnConverter converter;

    public InsurancePricetableMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link InsurancePricetableMap} stored in the database.
     */
    @Override
    public InsurancePricetableMap apply(Row row, String prefix) {
        InsurancePricetableMap entity = new InsurancePricetableMap();
        entity.setInsurancePricetableMapId(converter.fromRow(row, prefix + "_insurance_pricetable_map_id", Long.class));
        entity.setInsuranceIdNo(converter.fromRow(row, prefix + "_insurance_id_no", String.class));
        entity.setInsuranceName(converter.fromRow(row, prefix + "_insurance_name", String.class));
        entity.setInsurancePayerIdNo(converter.fromRow(row, prefix + "_insurance_payer_id_no", String.class));
        entity.setPriceTableId(converter.fromRow(row, prefix + "_price_table_id", Long.class));
        entity.setPriceTableName(converter.fromRow(row, prefix + "_price_table_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setInsurancePricetableMapUuid(converter.fromRow(row, prefix + "_insurance_pricetable_map_uuid", UUID.class));
        entity.setInsuranceId(converter.fromRow(row, prefix + "_insurance_id", Long.class));
        return entity;
    }
}
