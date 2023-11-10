package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link BenefitCoverageRequest}, with proper type conversions.
 */
@Service
public class BenefitCoverageRequestRowMapper implements BiFunction<Row, String, BenefitCoverageRequest> {

    private final ColumnConverter converter;

    public BenefitCoverageRequestRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link BenefitCoverageRequest} stored in the database.
     */
    @Override
    public BenefitCoverageRequest apply(Row row, String prefix) {
        BenefitCoverageRequest entity = new BenefitCoverageRequest();
        entity.setBenefitCoverageRequestId(converter.fromRow(row, prefix + "_benefit_coverage_request_id", Long.class));
        entity.setPayerId(converter.fromRow(row, prefix + "_payer_id", String.class));
        entity.setProviderFirstName(converter.fromRow(row, prefix + "_provider_first_name", String.class));
        entity.setProviderLastName(converter.fromRow(row, prefix + "_provider_last_name", String.class));
        entity.setProviderType(converter.fromRow(row, prefix + "_provider_type", String.class));
        entity.setProviderNpi(converter.fromRow(row, prefix + "_provider_npi", String.class));
        entity.setProviderCity(converter.fromRow(row, prefix + "_provider_city", String.class));
        entity.setProviderState(converter.fromRow(row, prefix + "_provider_state", String.class));
        entity.setProviderZipcode(converter.fromRow(row, prefix + "_provider_zipcode", String.class));
        entity.setSubmitterId(converter.fromRow(row, prefix + "_submitter_id", String.class));
        entity.setAsOfDate(converter.fromRow(row, prefix + "_as_of_date", LocalDate.class));
        entity.setServiceType(converter.fromRow(row, prefix + "_service_type", String.class));
        entity.setMemberId(converter.fromRow(row, prefix + "_member_id", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientDob(converter.fromRow(row, prefix + "_patient_dob", LocalDate.class));
        entity.setPatientGender(converter.fromRow(row, prefix + "_patient_gender", String.class));
        entity.setPatientState(converter.fromRow(row, prefix + "_patient_state", String.class));
        entity.setSubscriberRelationship(converter.fromRow(row, prefix + "_subscriber_relationship", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setBenefitCoverageRequestUuid(converter.fromRow(row, prefix + "_benefit_coverage_request_uuid", UUID.class));
        return entity;
    }
}
