package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ParSoMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ParSoMap}, with proper type conversions.
 */
@Service
public class ParSoMapRowMapper implements BiFunction<Row, String, ParSoMap> {

    private final ColumnConverter converter;

    public ParSoMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ParSoMap} stored in the database.
     */
    @Override
    public ParSoMap apply(Row row, String prefix) {
        ParSoMap entity = new ParSoMap();
        entity.setParSoId(converter.fromRow(row, prefix + "_par_so_id", Long.class));
        entity.setParId(converter.fromRow(row, prefix + "_par_id", Long.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setParSoMapUuid(converter.fromRow(row, prefix + "_par_so_map_uuid", UUID.class));
        return entity;
    }
}
