package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.EparResponse;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EparResponse}, with proper type conversions.
 */
@Service
public class EparResponseRowMapper implements BiFunction<Row, String, EparResponse> {

    private final ColumnConverter converter;

    public EparResponseRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EparResponse} stored in the database.
     */
    @Override
    public EparResponse apply(Row row, String prefix) {
        EparResponse entity = new EparResponse();
        entity.setEparResponseId(converter.fromRow(row, prefix + "_epar_response_id", Long.class));
        entity.setEparRequestId(converter.fromRow(row, prefix + "_epar_request_id", Long.class));
        entity.setParId(converter.fromRow(row, prefix + "_par_id", Long.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setPayerIdNo(converter.fromRow(row, prefix + "_payer_id_no", String.class));
        entity.setPayerName(converter.fromRow(row, prefix + "_payer_name", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setSubscriberRelationship(converter.fromRow(row, prefix + "_subscriber_relationship", String.class));
        entity.setCertificationEffectiveDate(converter.fromRow(row, prefix + "_certification_effective_date", LocalDate.class));
        entity.setCertificationExpirationDate(converter.fromRow(row, prefix + "_certification_expiration_date", LocalDate.class));
        entity.setRequestType(converter.fromRow(row, prefix + "_request_type", String.class));
        entity.setPlaceOfService(converter.fromRow(row, prefix + "_place_of_service", String.class));
        entity.setServiceLevel(converter.fromRow(row, prefix + "_service_level", String.class));
        entity.setQuantity(converter.fromRow(row, prefix + "_quantity", String.class));
        entity.setQuantityType(converter.fromRow(row, prefix + "_quantity_type", String.class));
        entity.setResponseCreateDate(converter.fromRow(row, prefix + "_response_create_date", LocalDate.class));
        entity.setResponseResponseDate(converter.fromRow(row, prefix + "_response_response_date", LocalDate.class));
        entity.setJsonData(converter.fromRow(row, prefix + "_json_data", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.seteParResponseUuid(converter.fromRow(row, prefix + "_e_par_response_uuid", UUID.class));
        return entity;
    }
}
