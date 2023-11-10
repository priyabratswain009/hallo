package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.Cmn;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Cmn}, with proper type conversions.
 */
@Service
public class CmnRowMapper implements BiFunction<Row, String, Cmn> {

    private final ColumnConverter converter;

    public CmnRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Cmn} stored in the database.
     */
    @Override
    public Cmn apply(Row row, String prefix) {
        Cmn entity = new Cmn();
        entity.setCmnId(converter.fromRow(row, prefix + "_cmn_id", Long.class));
        entity.setCmnNumber(converter.fromRow(row, prefix + "_cmn_number", String.class));
        entity.setCmnType(converter.fromRow(row, prefix + "_cmn_type", String.class));
        entity.setCmnFormName(converter.fromRow(row, prefix + "_cmn_form_name", String.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setSalesOrderNo(converter.fromRow(row, prefix + "_sales_order_no", String.class));
        entity.setCmnCreateDate(converter.fromRow(row, prefix + "_cmn_create_date", LocalDate.class));
        entity.setCmnInitialDate(converter.fromRow(row, prefix + "_cmn_initial_date", LocalDate.class));
        entity.setCmnRevisionDate(converter.fromRow(row, prefix + "_cmn_revision_date", LocalDate.class));
        entity.setCmnRecertificationDate(converter.fromRow(row, prefix + "_cmn_recertification_date", LocalDate.class));
        entity.setCmnExpirationDate(converter.fromRow(row, prefix + "_cmn_expiration_date", LocalDate.class));
        entity.setCmnLoggedBy(converter.fromRow(row, prefix + "_cmn_logged_by", Long.class));
        entity.setCmnLoggedDate(converter.fromRow(row, prefix + "_cmn_logged_date", LocalDate.class));
        entity.setCmnApprovedBy(converter.fromRow(row, prefix + "_cmn_approved_by", Long.class));
        entity.setCmnApprovedDate(converter.fromRow(row, prefix + "_cmn_approved_date", LocalDate.class));
        entity.setCmnPrintedBy(converter.fromRow(row, prefix + "_cmn_printed_by", Long.class));
        entity.setCmnPrintedDate(converter.fromRow(row, prefix + "_cmn_printed_date", LocalDate.class));
        entity.setLengthOfNeed(converter.fromRow(row, prefix + "_length_of_need", String.class));
        entity.setFaxCmnOption(converter.fromRow(row, prefix + "_fax_cmn_option", String.class));
        entity.setCmnCoverLetterInclusionOption(converter.fromRow(row, prefix + "_cmn_cover_letter_inclusion_option", String.class));
        entity.setCmnFaxedBy(converter.fromRow(row, prefix + "_cmn_faxed_by", Long.class));
        entity.setCmnFaxedDate(converter.fromRow(row, prefix + "_cmn_faxed_date", LocalDate.class));
        entity.setFaxStatus(converter.fromRow(row, prefix + "_fax_status", String.class));
        entity.setFaxStatusReason(converter.fromRow(row, prefix + "_fax_status_reason", String.class));
        entity.setPrintCmnOption(converter.fromRow(row, prefix + "_print_cmn_option", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setCmnIdUuid(converter.fromRow(row, prefix + "_cmn_id_uuid", UUID.class));
        entity.setPatientPrognosis(converter.fromRow(row, prefix + "_patient_prognosis", String.class));
        entity.setCmnStatus(converter.fromRow(row, prefix + "_cmn_status", String.class));
        entity.setRefillAuthorised(converter.fromRow(row, prefix + "_refill_authorised", String.class));
        return entity;
    }
}
