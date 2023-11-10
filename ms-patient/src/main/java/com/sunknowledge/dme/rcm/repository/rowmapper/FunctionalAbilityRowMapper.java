package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.FunctionalAbility;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FunctionalAbility}, with proper type conversions.
 */
@Service
public class FunctionalAbilityRowMapper implements BiFunction<Row, String, FunctionalAbility> {

    private final ColumnConverter converter;

    public FunctionalAbilityRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FunctionalAbility} stored in the database.
     */
    @Override
    public FunctionalAbility apply(Row row, String prefix) {
        FunctionalAbility entity = new FunctionalAbility();
        entity.setFunctionalAbilityId(converter.fromRow(row, prefix + "_functional_ability_id", Long.class));
        entity.setFunctionalAbilityCode(converter.fromRow(row, prefix + "_functional_ability_code", String.class));
        entity.setFunctionalAbilityName(converter.fromRow(row, prefix + "_functional_ability_name", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setFunctionalAbilityUuid(converter.fromRow(row, prefix + "_functional_ability_uuid", UUID.class));
        return entity;
    }
}
