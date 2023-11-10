package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link DmeGroupChecklistMaster}, with proper type conversions.
 */
@Service
public class DmeGroupChecklistMasterRowMapper implements BiFunction<Row, String, DmeGroupChecklistMaster> {

    private final ColumnConverter converter;

    public DmeGroupChecklistMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DmeGroupChecklistMaster} stored in the database.
     */
    @Override
    public DmeGroupChecklistMaster apply(Row row, String prefix) {
        DmeGroupChecklistMaster entity = new DmeGroupChecklistMaster();
        entity.setDmeGroupChecklistId(converter.fromRow(row, prefix + "_dme_group_checklist_id", Long.class));
        entity.setDmeGroupId(converter.fromRow(row, prefix + "_dme_group_id", Long.class));
        entity.setDmeGroupName(converter.fromRow(row, prefix + "_dme_group_name", String.class));
        entity.setChecklistId(converter.fromRow(row, prefix + "_checklist_id", Long.class));
        entity.setChecklistName(converter.fromRow(row, prefix + "_checklist_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setDmeGroupChecklistMasterUuid(converter.fromRow(row, prefix + "_dme_group_checklist_master_uuid", UUID.class));
        return entity;
    }
}
