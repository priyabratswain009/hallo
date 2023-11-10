package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link HcpcsDmeGroupMaster}, with proper type conversions.
 */
@Service
public class HcpcsDmeGroupMasterRowMapper implements BiFunction<Row, String, HcpcsDmeGroupMaster> {

    private final ColumnConverter converter;

    public HcpcsDmeGroupMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link HcpcsDmeGroupMaster} stored in the database.
     */
    @Override
    public HcpcsDmeGroupMaster apply(Row row, String prefix) {
        HcpcsDmeGroupMaster entity = new HcpcsDmeGroupMaster();
        entity.setHcpcsDmeId(converter.fromRow(row, prefix + "_hcpcs_dme_id", Long.class));
        entity.setHcpcsCode(converter.fromRow(row, prefix + "_hcpcs_code", String.class));
        entity.setDmeGroupName(converter.fromRow(row, prefix + "_dme_group_name", String.class));
        entity.setDmeGroupId(converter.fromRow(row, prefix + "_dme_group_id", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setHcpcsDmeGroupMasterUuid(converter.fromRow(row, prefix + "_hcpcs_dme_group_master_uuid", UUID.class));
        return entity;
    }
}
