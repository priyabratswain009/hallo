package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.StateMaster;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link StateMaster}, with proper type conversions.
 */
@Service
public class StateMasterRowMapper implements BiFunction<Row, String, StateMaster> {

    private final ColumnConverter converter;

    public StateMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link StateMaster} stored in the database.
     */
    @Override
    public StateMaster apply(Row row, String prefix) {
        StateMaster entity = new StateMaster();
        entity.setStateId(converter.fromRow(row, prefix + "_state_id", Long.class));
        entity.setStateCode(converter.fromRow(row, prefix + "_state_code", String.class));
        entity.setStateName(converter.fromRow(row, prefix + "_state_name", String.class));
        return entity;
    }
}
