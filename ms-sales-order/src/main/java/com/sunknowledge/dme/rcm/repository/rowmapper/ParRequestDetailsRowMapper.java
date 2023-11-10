package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ParRequestDetails}, with proper type conversions.
 */
@Service
public class ParRequestDetailsRowMapper implements BiFunction<Row, String, ParRequestDetails> {

    private final ColumnConverter converter;

    public ParRequestDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ParRequestDetails} stored in the database.
     */
    @Override
    public ParRequestDetails apply(Row row, String prefix) {
        ParRequestDetails entity = new ParRequestDetails();
        entity.setParRequestDetailsId(converter.fromRow(row, prefix + "_par_request_details_id", Long.class));
        entity.setParRequestType(converter.fromRow(row, prefix + "_par_request_type", String.class));
        entity.setParId(converter.fromRow(row, prefix + "_par_id", Long.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setParInitiationDate(converter.fromRow(row, prefix + "_par_initiation_date", LocalDate.class));
        entity.setParNoEffetiveStartDate(converter.fromRow(row, prefix + "_par_no_effetive_start_date", LocalDate.class));
        entity.setParNoEffetiveEndDate(converter.fromRow(row, prefix + "_par_no_effetive_end_date", LocalDate.class));
        entity.setParAuthorisedBy(converter.fromRow(row, prefix + "_par_authorised_by", String.class));
        entity.setParRequestDocName(converter.fromRow(row, prefix + "_par_request_doc_name", String.class));
        entity.setParRequestDocLocation(converter.fromRow(row, prefix + "_par_request_doc_location", String.class));
        entity.setParRequestFaxNumber(converter.fromRow(row, prefix + "_par_request_fax_number", String.class));
        entity.setParRequestFaxStatus(converter.fromRow(row, prefix + "_par_request_fax_status", String.class));
        entity.setParRequestFaxDatetime(converter.fromRow(row, prefix + "_par_request_fax_datetime", ZonedDateTime.class));
        entity.setFaxResponseStatus(converter.fromRow(row, prefix + "_fax_response_status", String.class));
        entity.setParRequestResponseDocName(converter.fromRow(row, prefix + "_par_request_response_doc_name", String.class));
        entity.setDocQrCode(converter.fromRow(row, prefix + "_doc_qr_code", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setParRequestDetailsUuid(converter.fromRow(row, prefix + "_par_request_details_uuid", UUID.class));
        return entity;
    }
}
