package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CmnDocumentMaster}, with proper type conversions.
 */
@Service
public class CmnDocumentMasterRowMapper implements BiFunction<Row, String, CmnDocumentMaster> {

    private final ColumnConverter converter;

    public CmnDocumentMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CmnDocumentMaster} stored in the database.
     */
    @Override
    public CmnDocumentMaster apply(Row row, String prefix) {
        CmnDocumentMaster entity = new CmnDocumentMaster();
        entity.setCmnDocumentId(converter.fromRow(row, prefix + "_cmn_document_id", Long.class));
        entity.setCmnId(converter.fromRow(row, prefix + "_cmn_id", Long.class));
        entity.setCmnNo(converter.fromRow(row, prefix + "_cmn_no", String.class));
        entity.setGenerationDate(converter.fromRow(row, prefix + "_generation_date", LocalDate.class));
        entity.setInitialDocumentName(converter.fromRow(row, prefix + "_initial_document_name", String.class));
        entity.setFaxStatus(converter.fromRow(row, prefix + "_fax_status", String.class));
        entity.setOutBoundFaxStatus(converter.fromRow(row, prefix + "_out_bound_fax_status", String.class));
        entity.setOutBoundFaxNo(converter.fromRow(row, prefix + "_out_bound_fax_no", String.class));
        entity.setOutBoundFaxDateTime(converter.fromRow(row, prefix + "_out_bound_fax_date_time", LocalDate.class));
        entity.setInBoundFaxStatus(converter.fromRow(row, prefix + "_in_bound_fax_status", String.class));
        entity.setInBoundFaxNo(converter.fromRow(row, prefix + "_in_bound_fax_no", String.class));
        entity.setInBoundFaxDateTime(converter.fromRow(row, prefix + "_in_bound_fax_date_time", LocalDate.class));
        entity.setCmnDocumentMasterUuid(converter.fromRow(row, prefix + "_cmn_document_master_uuid", UUID.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setReturnDocumentName(converter.fromRow(row, prefix + "_return_document_name", String.class));
        entity.setCmnComments(converter.fromRow(row, prefix + "_cmn_comments", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        return entity;
    }
}
