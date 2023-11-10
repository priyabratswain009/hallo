package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link BenefitCoverageResponse}, with proper type conversions.
 */
@Service
public class BenefitCoverageResponseRowMapper implements BiFunction<Row, String, BenefitCoverageResponse> {

    private final ColumnConverter converter;

    public BenefitCoverageResponseRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link BenefitCoverageResponse} stored in the database.
     */
    @Override
    public BenefitCoverageResponse apply(Row row, String prefix) {
        BenefitCoverageResponse entity = new BenefitCoverageResponse();
        entity.setBenefitCoverageResponseId(converter.fromRow(row, prefix + "_benefit_coverage_response_id", Long.class));
        entity.setBenefitCoverageRequestId(converter.fromRow(row, prefix + "_benefit_coverage_request_id", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setExpirationDate(converter.fromRow(row, prefix + "_expiration_date", LocalDate.class));
        entity.setRequestedDate(converter.fromRow(row, prefix + "_requested_date", LocalDate.class));
        entity.setResponseDate(converter.fromRow(row, prefix + "_response_date", LocalDate.class));
        entity.setServiceType(converter.fromRow(row, prefix + "_service_type", String.class));
        entity.setSubscriberMemberId(converter.fromRow(row, prefix + "_subscriber_member_id", String.class));
        entity.setPatientRelationshipCode(converter.fromRow(row, prefix + "_patient_relationship_code", String.class));
        entity.setPayerId(converter.fromRow(row, prefix + "_payer_id", String.class));
        entity.setProviderNpi(converter.fromRow(row, prefix + "_provider_npi", String.class));
        entity.setPlansStatusCode(converter.fromRow(row, prefix + "_plans_status_code", String.class));
        entity.setPlansStatus(converter.fromRow(row, prefix + "_plans_status", String.class));
        entity.setPrimaryResponse(converter.fromRow(row, prefix + "_primary_response", String.class));
        entity.setSecondaryResponse(converter.fromRow(row, prefix + "_secondary_response", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setBenefitCoverageResponseUuid(converter.fromRow(row, prefix + "_benefit_coverage_response_uuid", UUID.class));
        entity.setPatientState(converter.fromRow(row, prefix + "_patient_state", String.class));
        entity.setSubscriberRelationship(converter.fromRow(row, prefix + "_subscriber_relationship", String.class));
        entity.setMemberId(converter.fromRow(row, prefix + "_member_id", String.class));
        return entity;
    }
}
