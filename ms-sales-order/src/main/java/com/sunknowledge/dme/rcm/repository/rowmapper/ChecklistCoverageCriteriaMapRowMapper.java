package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ChecklistCoverageCriteriaMap}, with proper type conversions.
 */
@Service
public class ChecklistCoverageCriteriaMapRowMapper implements BiFunction<Row, String, ChecklistCoverageCriteriaMap> {

    private final ColumnConverter converter;

    public ChecklistCoverageCriteriaMapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ChecklistCoverageCriteriaMap} stored in the database.
     */
    @Override
    public ChecklistCoverageCriteriaMap apply(Row row, String prefix) {
        ChecklistCoverageCriteriaMap entity = new ChecklistCoverageCriteriaMap();
        entity.setChecklistCoverageCriteriaId(converter.fromRow(row, prefix + "_checklist_coverage_criteria_id", Long.class));
        entity.setChecklistId(converter.fromRow(row, prefix + "_checklist_id", Long.class));
        entity.setChecklistName(converter.fromRow(row, prefix + "_checklist_name", String.class));
        entity.setCoverageCriteriaId(converter.fromRow(row, prefix + "_coverage_criteria_id", Long.class));
        entity.setCoverageCriteriaDetails(converter.fromRow(row, prefix + "_coverage_criteria_details", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setChecklistCoverageCriteriaMapUuid(converter.fromRow(row, prefix + "_checklist_coverage_criteria_map_uuid", UUID.class));
        entity.setCoverageCriteriaName(converter.fromRow(row, prefix + "_coverage_criteria_name", String.class));
        entity.setItemGroupId(converter.fromRow(row, prefix + "_item_group_id", Long.class));
        entity.setItemGroupName(converter.fromRow(row, prefix + "_item_group_name", String.class));
        return entity;
    }
}
