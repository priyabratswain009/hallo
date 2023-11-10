package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ElligibilityResponseStatus}, with proper type conversions.
 */
@Service
public class ElligibilityResponseStatusRowMapper implements BiFunction<Row, String, ElligibilityResponseStatus> {

    private final ColumnConverter converter;

    public ElligibilityResponseStatusRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ElligibilityResponseStatus} stored in the database.
     */
    @Override
    public ElligibilityResponseStatus apply(Row row, String prefix) {
        ElligibilityResponseStatus entity = new ElligibilityResponseStatus();
        entity.setElligibilityResponseStatusId(converter.fromRow(row, prefix + "_elligibility_response_status_id", Long.class));
        entity.setElligibilityControlNumber(converter.fromRow(row, prefix + "_elligibility_control_number", String.class));
        entity.setTraceid(converter.fromRow(row, prefix + "_traceid", String.class));
        entity.setSubscriberFirstName(converter.fromRow(row, prefix + "_subscriber_first_name", String.class));
        entity.setSubscriberLastName(converter.fromRow(row, prefix + "_subscriber_last_name", String.class));
        entity.setSubscriberGender(converter.fromRow(row, prefix + "_subscriber_gender", String.class));
        entity.setSubscriberDob(converter.fromRow(row, prefix + "_subscriber_dob", LocalDate.class));
        entity.setSubscriberIdentifier(converter.fromRow(row, prefix + "_subscriber_identifier", String.class));
        entity.setSubscriberEntitytype(converter.fromRow(row, prefix + "_subscriber_entitytype", String.class));
        entity.setSubscriberSsn(converter.fromRow(row, prefix + "_subscriber_ssn", String.class));
        entity.setPayerIdentifier(converter.fromRow(row, prefix + "_payer_identifier", String.class));
        entity.setPayerEntitytype(converter.fromRow(row, prefix + "_payer_entitytype", String.class));
        entity.setPayerName(converter.fromRow(row, prefix + "_payer_name", String.class));
        entity.setPayerIdentification(converter.fromRow(row, prefix + "_payer_identification", String.class));
        entity.setPlanSsn(converter.fromRow(row, prefix + "_plan_ssn", String.class));
        entity.setPlanDate(converter.fromRow(row, prefix + "_plan_date", String.class));
        entity.setPlanStatusCode(converter.fromRow(row, prefix + "_plan_status_code", String.class));
        entity.setPlanStatus(converter.fromRow(row, prefix + "_plan_status", String.class));
        entity.setPlanDetails(converter.fromRow(row, prefix + "_plan_details", String.class));
        entity.setServiceTypeCodes(converter.fromRow(row, prefix + "_service_type_codes", String.class));
        entity.setElligibilityResponseStatusUuid(converter.fromRow(row, prefix + "_elligibility_response_status_uuid", UUID.class));
        return entity;
    }
}
