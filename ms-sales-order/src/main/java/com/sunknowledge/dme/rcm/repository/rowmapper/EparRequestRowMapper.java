package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.EparRequest;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EparRequest}, with proper type conversions.
 */
@Service
public class EparRequestRowMapper implements BiFunction<Row, String, EparRequest> {

    private final ColumnConverter converter;

    public EparRequestRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EparRequest} stored in the database.
     */
    @Override
    public EparRequest apply(Row row, String prefix) {
        EparRequest entity = new EparRequest();
        entity.setEparRequestId(converter.fromRow(row, prefix + "_epar_request_id", Long.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setParId(converter.fromRow(row, prefix + "_par_id", Long.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setRequestDatetime(converter.fromRow(row, prefix + "_request_datetime", LocalDate.class));
        entity.setResponseStatus(converter.fromRow(row, prefix + "_response_status", String.class));
        entity.setResponseUrl(converter.fromRow(row, prefix + "_response_url", String.class));
        entity.setRequestJson(converter.fromRow(row, prefix + "_request_json", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setEpaRequestUuid(converter.fromRow(row, prefix + "_epa_request_uuid", UUID.class));
        return entity;
    }
}
